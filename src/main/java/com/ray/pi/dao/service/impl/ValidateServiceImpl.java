package com.ray.pi.dao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ray.pi.common.aip.AipFaceClient;
import com.ray.pi.common.aip.AipSpeechClient;
import com.ray.pi.common.utils.DateDistance;
import com.ray.pi.common.utils.MP3Player;
import com.ray.pi.common.utils.ShellKit;
import com.ray.pi.common.utils.StringUtil;
import com.ray.pi.dao.entity.TfBHeadImage;
import com.ray.pi.dao.entity.TfBUser;
import com.ray.pi.dao.persistence.TfBHeadImageMapper;
import com.ray.pi.dao.persistence.TfBUserMapper;
import com.ray.pi.dao.service.ValidateService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
	public JSONObject facesetAddUser(HttpSession session, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		String path = session.getServletContext().getRealPath("/");
		String _path = path.substring(0,path.indexOf("pisec/"));
		String pic = StringUtil.generatePhotoName(_path+"headImage/userHeadImages/", new Date());
		StringBuffer shell = new StringBuffer("sh ");
		shell.append(_path).append("takePhoto.sh ").append(pic);

		try {
			ShellKit.execShell(shell.toString());


			File file = new File(pic);
			int times = 1;
			while (!file.exists()) {
				Thread.sleep(2000);
				System.out.println("等待生成照片，第"+ times++ +"次");
				if (times == 5) {
					break;
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
			String filePath = buildMp3File(_path,"用户注册失败，请重试","sys", "3");
			MP3Player mp3 = new MP3Player(filePath);
			mp3.play();
			json.put("resultCode", "error");
			return json;
		}

		String userName = request.getParameter("userName");
		if ("".equals(userName) || null == userName) {
			userName = "新用户";
		}

//		String image = "/Users/zhangrui/Documents/pi/headImage/userHeadImages/IMG20170304134603.jpg";
//		String image = "/home/pi/headImage/userHeadImages/IMG20170304134603.jpg";

		TfBUser user = new TfBUser();
		user.setUserName(userName);
		user.setCreateId("pi");
		user.setCreateTime(new Date());
		user.setLastDriveStartTime(new Date());
		user.setLastDriveEndTime(new Date());
		userMapper.insertSelective(user);
		TfBHeadImage headImage = new TfBHeadImage();
		headImage.setPath(pic);
		headImage.setCreateId("pi");
		headImage.setMd5("md5");
		headImage.setCreateTime(new Date());
		headImage.setUserId(user.getUserId());
		imageMapper.insertSelective(headImage);

		AipFaceClient aip = new AipFaceClient();
		org.json.JSONObject res = aip.facesetAddUser(pic,user.getUserId().toString(),user.getUserName(),"pi");

		boolean flag = !res.has("error_code");
		if (flag) {
			json.put("resultCode", "success");
			return json;
		}
		json.put("resultCode", "error");
		return json;
	}

	public JSONObject editUser(HttpSession session, HttpServletRequest request){
		JSONObject json = new JSONObject();

		String path = session.getServletContext().getRealPath("/");
		String userName = request.getParameter("userName");

		json.put("path", path);
		json.put("userName", userName);

		return json;
	}

	/**
	 * 用户校验
	 * @return
	 */
	public JSONObject validateUser(HttpSession session){
		JSONObject json = new JSONObject();

//		String cameraIamge = "/Users/zhangrui/Documents/pi/headImage/cameraImage/IMG_20170511_184307.jpg";
//		String cameraIamge = "/home/pi/headImage/cameraImage/IMG_20170511_184307.jpg";
		String path = session.getServletContext().getRealPath("/");
		String _path = path.substring(0,path.indexOf("pisec/"));
		String pic = StringUtil.generatePhotoName(_path+"headImage/cameraImage/", new Date());
		StringBuffer shell = new StringBuffer("sh ");
		shell.append(_path).append("takePhoto.sh ").append(pic);

		try {
			ShellKit.execShell(shell.toString());


			File file = new File(pic);
			int times = 1;
			while (!file.exists()) {
				Thread.sleep(2000);
				System.out.println("等待生成照片，第"+ times++ +"次");
				if (times == 5) {
					break;
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
			String filePath = buildMp3File(_path,"用户验证失败,请重新尝试","sys", "3");
			MP3Player mp3 = new MP3Player(filePath);
			mp3.play();
		}

		try {

			AipFaceClient aip = new AipFaceClient();
			org.json.JSONObject res = aip.identifyUser(pic, "pi");
			System.out.println(res.toString(2));
			Integer resultNum = res.getInt("result_num");
			JSONArray result = new JSONArray();
			Map<String, Object> resultMap = new HashMap<>();
			String userId = "";
			if (resultNum > 0) {
				result = res.getJSONArray("result");
				String uid = result.getJSONObject(0).getString("uid");
				userId = uid.substring(uid.indexOf("_") + 1, uid.length());
				resultMap.put("userId", userId);
				resultMap.put("userName", result.getJSONObject(0).getString("user_info"));

				TfBUser currentUser = userMapper.selectByPrimaryKey(Long.parseLong(userId));
				if (currentUser != null) {
					long[] _times = DateDistance.getDistanceTimesByDate(currentUser.getLastDriveStartTime(), currentUser.getLastDriveEndTime());
					long[] times = DateDistance.getDistanceTimesByDate(currentUser.getLastDriveEndTime(), new Date());
					long day = times[0];
					long hour = times[1];
					long min = times[2];
					long _day = _times[0];
					long _hour = _times[1];
					if (_hour < 2 && _day == 0) {
						TfBUser userInfo = new TfBUser();
						userInfo.setUserId(Long.parseLong(userId));
						userInfo.setLastDriveStartTime(new Date());
						userInfo.setDriveState("1");
						userMapper.updateByPrimaryKeySelective(userInfo);

						json.put("resultCode", "success");
						json.put("resultMap", resultMap);

						String filePath = buildMp3File(_path, currentUser.getUserName() + "你好！欢迎使用智能驾驶系统！", userId, "1");
						MP3Player mp3 = new MP3Player(filePath);
						mp3.play();

						return json;
					} else {
						if (day >= 0 && hour >= 0 && min >= 20) {
							TfBUser userInfo = new TfBUser();
							userInfo.setUserId(Long.parseLong(userId));
							userInfo.setDriveState("1");
							userInfo.setLastDriveStartTime(new Date());
							userMapper.updateByPrimaryKeySelective(userInfo);

							json.put("resultCode", "success");
							json.put("resultMap", resultMap);

							String filePath = buildMp3File(_path, currentUser.getUserName() + "你好！欢迎使用智能驾驶系统！", userId, "1");
							MP3Player mp3 = new MP3Player(filePath);
							mp3.play();

							return json;
						} else {
							json.put("resultCode", "error");
							json.put("resultMsg", "您已连续驾驶超过2小时，请休息半小时");
							String filePath = buildMp3File(_path, "您已连续驾驶超过2小时，请休息半小时", userId, "2");
							MP3Player mp3 = new MP3Player(filePath);
							mp3.play();
							return json;
						}

					}
				}

			}
			json.put("resultCode", "error");
			json.put("resultMsg", "用户验证失败,请重新尝试");
			String filePath = buildMp3File(_path, "用户验证失败,请重新尝试", userId, "3");
			MP3Player mp3 = new MP3Player(filePath);
			mp3.play();

		} catch (Exception e) {
			e.printStackTrace();
			json.put("resultCode", "error");
			json.put("resultMsg", "用户验证失败,请重新尝试");
			String filePath = buildMp3File(_path, "用户验证失败,请重新尝试", "sys", "3");
			MP3Player mp3 = new MP3Player(filePath);
			mp3.play();
		}
		return json;
	}

	public void drivingRemind() {
//		String path = .getServletContext().getRealPath("/");
//		String _path = path.substring(0,path.indexOf("pisec/"));
		String _path = "/home/pi/apache-tomcat-7.0.82/webapps/";
		TfBUser userInfo = userMapper.selectDrivingOvertime();
		if (null != userInfo) {
			String filePath = buildMp3File(_path,"您已连续驾驶超过2小时，请休息半小时",userInfo.getUserId().toString(), "2");
			MP3Player mp3 = new MP3Player(filePath);
			mp3.play();
		}
	}

	public void engineStop() {
		TfBUser userInfo = new TfBUser();
		userInfo.setLastDriveEndTime(new Date());
		userInfo.setDriveState("0");
		userMapper.updateDrivingUser(userInfo);
	}

	public String buildMp3File(String path, String text, String userId, String type){
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
//		String pathName = "/home/pi/speech/"+ prefix + userId +".mp3";
		String pathName = path + "speech/" + prefix + userId +".mp3";
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

	public JSONObject takePhoto(HttpSession session) {
		JSONObject json = new JSONObject();

		String path = session.getServletContext().getRealPath("/");
		String _path = path.substring(0,path.indexOf("pisec/"));
		String pic = StringUtil.generatePhotoName(_path+"headImage/cameraImage/", new Date());
		StringBuffer shell = new StringBuffer("sh ");
		shell.append(_path).append("takePhoto.sh ").append(pic);
		ShellKit.execShell(shell.toString());


		json.put("resultCode", "success");
		json.put("resultMsg", "拍照成功");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("path", shell);
		json.put("resultMap", resultMap);

		return json;
	}

	public void alcoholWarning() {
		String alcoholWarningPath = "/home/pi/apache-tomcat-7.0.82/webapps/";
		String allPath = "/home/pi/apache-tomcat-7.0.82/webapps/speech/warningalcohol.mp3";
		File file = new File(allPath);
		if (!file.exists()){
			alcoholWarningPath = buildMp3File(alcoholWarningPath, "系统检测到车内酒精浓度超标，请勿酒后驾驶", "alcohol" , "2");
		}
		MP3Player mp3 = new MP3Player(allPath);
		mp3.play();
	}

	public JSONObject playQuestion(){
		JSONObject json = new JSONObject();
		String questionPath = "/home/pi/apache-tomcat-7.0.82/webapps/";
		String allPath = "/home/pi/apache-tomcat-7.0.82/webapps/speech/warningquestion.mp3";
		File file = new File(allPath);
		if (!file.exists()){
			buildMp3File(questionPath, "您的驾驶状态怎么样", "question" , "2");
		}
		MP3Player mp3 = new MP3Player(allPath);
		mp3.play();
		return json;
	}

	public JSONObject playAnswer(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String _flag = request.getParameter("flag");
		String answerPath = "/home/pi/apache-tomcat-7.0.82/webapps/";
		String allPathTrue = "/home/pi/apache-tomcat-7.0.82/webapps/speech/warningtrueanswer.mp3";
		String allPathFalse = "/home/pi/apache-tomcat-7.0.82/webapps/speech/warningfalseanswer.mp3";
		if (_flag!=null && !"".equals(_flag) && "1".equals(_flag)) {

			File file = new File(allPathTrue);
			if (!file.exists()) {
				buildMp3File(answerPath, "好的，请继续保持专注", "trueanswer", "2");
			}
			MP3Player mp3 = new MP3Player(allPathTrue);
			mp3.play();
		} else if (_flag!=null && !"".equals(_flag) && "0".equals(_flag)) {
			File file = new File(allPathFalse);
			if (!file.exists()) {
				buildMp3File(answerPath, "系统检测到当前驾驶状态不佳，请注意不要连续驾驶", "falseanswer", "2");
			}
			MP3Player mp3 = new MP3Player(allPathFalse);
			mp3.play();
		}

		return json;
	}

//	public JSONObject getPath(){
//		JSONObject json = new JSONObject();
//
//		json.put("path", templatesPath);
//		return json;
//	}

//	public static void main(String[] args) {
//		String path = "/Users/zhangrui/Documents/maven/maven-repository/com/ray/pisec/";
//		path.substring(0,path.indexOf("pisec/"));
//		System.out.println(path);
//	}

}
