package com.ray.pi.dao.service;

import com.alibaba.fastjson.JSONObject;
import com.ray.pi.common.aip.AipFaceClient;
import com.ray.pi.common.aip.AipSpeechClient;
import com.ray.pi.common.utils.DateDistance;
import com.ray.pi.common.utils.MP3Player;
import com.ray.pi.dao.entity.TfBHeadImage;
import com.ray.pi.dao.entity.TfBUser;
import com.ray.pi.dao.persistence.TfBHeadImageMapper;
import com.ray.pi.dao.persistence.TfBUserMapper;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray on 2017/10/24.
 */
@Service
public class ValidateServiceImpl implements ValidateService {

	@Autowired
	private TfBUserMapper userMapper;

	@Autowired
	private TfBHeadImageMapper imageMapper;

	public TfBUser queryUserInfo(Long userId) {
		TfBUser user = userMapper.selectByPrimaryKey(userId);
		return user;
	}

	/**
	 * 用户注册
	 * @return
	 */
	public JSONObject facesetAddUser() {
		JSONObject json = new JSONObject();
//		String image = "/Users/zhangrui/Documents/pi/headImage/userHeadImages/IMG20170304134603.jpg";
		String image = "/home/pi/headImage/userHeadImages/IMG20170304134603.jpg";

		TfBUser user = new TfBUser();
		user.setUserName("新用户");
		user.setCreateId("pi");
		user.setCreateTime(new Date());
		user.setLastDriveStartTime(new Date());
		user.setLastDriveEndTime(new Date());
		userMapper.insertSelective(user);
		TfBHeadImage headImage = new TfBHeadImage();
		headImage.setPath(image);
		headImage.setCreateId("pi");
		headImage.setMd5("md5");
		headImage.setCreateTime(new Date());
		headImage.setUserId(user.getUserId());
		imageMapper.insertSelective(headImage);

		AipFaceClient aip = new AipFaceClient();
		org.json.JSONObject res = aip.facesetAddUser(image,user.getUserId().toString(),user.getUserName(),"pi");

		boolean flag = !res.has("error_code");
		if (flag) {
			json.put("resultCode", "success");
			return json;
		}
		json.put("resultCode", "error");
		return json;
	}

	/**
	 * 用户校验
	 * @return
	 */
	public JSONObject validateUser(){
		JSONObject json = new JSONObject();

//		String cameraIamge = "/Users/zhangrui/Documents/pi/headImage/cameraImage/IMG_20170511_184307.jpg";
		String cameraIamge = "/home/pi/headImage/cameraImage/IMG_20170511_184307.jpg";

		AipFaceClient aip = new AipFaceClient();
		org.json.JSONObject res = aip.identifyUser(cameraIamge, "pi");
		Integer resultNum = res.getInt("result_num");
		JSONArray result = new JSONArray();
		Map<String, Object> resultMap = new HashMap<>();
		String userId = "";
		if (resultNum > 0) {
			result = res.getJSONArray("result");
			String uid = result.getJSONObject(0).getString("uid");
			userId = uid.substring(uid.indexOf("_")+1,uid.length());
			resultMap.put("userId", userId);
			resultMap.put("userName", result.getJSONObject(0).getString("user_info"));

			TfBUser currentUser = userMapper.selectByPrimaryKey(Long.parseLong(userId));
			if (currentUser!=null) {
				long[] _times = DateDistance.getDistanceTimesByDate(currentUser.getLastDriveStartTime(), currentUser.getLastDriveEndTime());
				long[] times = DateDistance.getDistanceTimesByDate(currentUser.getLastDriveEndTime(), new Date());
				long day = times[0];
				long hour = times[1];
				long min = times[2];
				long _day = _times[0];
				long _hour = _times[1];
				if (_hour<2&&_day==0) {
					TfBUser userInfo = new TfBUser();
					userInfo.setUserId(Long.parseLong(userId));
					userInfo.setLastDriveStartTime(new Date());
					userInfo.setDriveState("1");
					userMapper.updateByPrimaryKeySelective(userInfo);

					json.put("resultCode", "success");
					json.put("resultMap", resultMap);
					return json;
				}else{
					if (day >= 0 && hour >= 0 && min >= 20) {
						TfBUser userInfo = new TfBUser();
						userInfo.setUserId(Long.parseLong(userId));
						userInfo.setDriveState("1");
						userInfo.setLastDriveStartTime(new Date());
						userMapper.updateByPrimaryKeySelective(userInfo);

						json.put("resultCode", "success");
						json.put("resultMap", resultMap);

						String filePath = buildMp3File(currentUser.getUserName()+"你好！欢迎使用智能驾驶系统！",userId, "1");
						MP3Player mp3 = new MP3Player(filePath);
						mp3.play();

						return json;
					}else {
						json.put("resultCode", "error");
						json.put("resultMsg", "您已连续驾驶超过2小时，请休息半小时");
						String filePath = buildMp3File("您已连续驾驶超过2小时，请休息半小时",userId, "2");
						MP3Player mp3 = new MP3Player(filePath);
						mp3.play();
						return json;
					}

				}
			}

		}
		json.put("resultCode", "error");
		json.put("resultMsg", "用户验证失败,请重新尝试");
		String filePath = buildMp3File("用户验证失败,请重新尝试",userId, "3");
		MP3Player mp3 = new MP3Player(filePath);
		mp3.play();
		return json;
	}

	public void engineStop() {
		TfBUser userInfo = new TfBUser();
		userInfo.setLastDriveEndTime(new Date());
		userInfo.setDriveState("0");
		userMapper.updateDrivingUser(userInfo);
	}

	public String buildMp3File(String text, String userId, String type){
		AipSpeechClient aip = new AipSpeechClient();
		byte[] speech = aip.synthesis(text, "zh", 1);
		String prefix = "";
		if ("1".equals(type)) {
			prefix = "welcome";
		} else if ("2".equals(type)) {
			prefix = "warning";
		} else {
			prefix = "fail";
		}

//		String pathName = "/Users/zhangrui/Documents/pi/"+ prefix + userId +".mp3";
		String pathName = "/home/pi/speech/"+ prefix + userId +".mp3";
		File file = new File(pathName);
		try {
			OutputStream os = new FileOutputStream(file);
			os.write(speech);
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathName;
	}

}
