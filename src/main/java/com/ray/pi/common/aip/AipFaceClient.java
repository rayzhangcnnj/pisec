package com.ray.pi.common.aip;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Ray on 2017/10/24.
 */
public class AipFaceClient {

	private static final String APP_ID = "10277354";
	private static final String API_KEY = "aqRG4suKYdaVh0SKxcHyBqEn";
	private static final String SECRET_KEY = "eVtk0scPzDlHAjRMQlFUUZEII7Pq1rmE";

	private AipFace client;

	public AipFaceClient() {
		// 初始化一个AipFace
		client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
	}

	/**
	 * @param proxyType 0:http  1:socket
	 * @param proxyHost
	 * @param proxyPort
	 */
	public AipFaceClient(int proxyType, String proxyHost, int proxyPort) {
		// 初始化一个AipFace
		client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
		if (proxyType == 0) {
			client.setHttpProxy(proxyHost, proxyPort);  // 设置http代理
		} else if (proxyType == 1) {
			client.setSocketProxy(proxyHost, proxyPort);  // 设置socket代理
		}
	}

	/**
	 * 人脸比对
	 * @param imagePath1
	 * @param imagePath2
	 * @return {"result":[{"index_j":"1","index_i":"0","score":96.428985595703}],"result_num":1,"log_id":2853252054102415}
	 */
	public JSONObject faceRecognize(String imagePath1, String imagePath2) {
		// 参数为本地图片路径
		ArrayList<String> pathArray = new ArrayList<String>();
		pathArray.add(imagePath1);
		pathArray.add(imagePath2);
		JSONObject response = this.client.match(pathArray, new HashMap<String, String>());
		return response;
	}

	/**
	 * 人脸比对(分组比对)
	 * @param pathArray
	 * @return {"result":[{"index_j":"1","index_i":"0","score":96.428985595703}],"result_num":1,"log_id":2853252054102415}
	 */
	public JSONObject faceRecognize(ArrayList<String> pathArray) {
		JSONObject response = this.client.match(pathArray, new HashMap<String, String>());
		return response;
	}

	/**
	 * 人脸注册
	 * @param path
	 * @param userId
	 * @param userName
	 * @param group
	 * @return // 注册成功 {"log_id": 73473737,}
	 *         // 注册发生错误 {"error_code": 216616,	"log_id": 674786177,"error_msg": "image exist"}
	 */
	public JSONObject facesetAddUser(String path, String userId, String userName, String group) {
		// 参数为本地图片路径
		HashMap<String, String> options = new HashMap<String, String>();
		JSONObject res = this.client.addUser("uid_"+userId, userName, Arrays.asList(group), path, options);
		//System.out.println(res.toString(2));
		return res;
	}

	/**
	 * 用户信息修改
	 * @param path
	 * @param userId
	 * @param userName
	 * @param group
	 * @return
	 */
	public JSONObject editUser(String path, String userId, String userName, String group) {
		HashMap<String, String> options = new HashMap<String, String>(1);
		JSONObject res = this.client.updateUser("uid" + userId, userName, group, path, options);
		return res;
	}

	/**
	 * 人脸识别
	 * @param path
	 * @param group
	 * @return {"log_id": 73473737,"result_num":1,"result": [{"group_id" : "test1","uid": "u333333","user_info": "Test User","scores": [99.3,83.4]}]}
	 */
	public JSONObject identifyUser(String path, String group) {
		HashMap<String, Object> options = new HashMap<String, Object>(1);
		options.put("user_top_num", 1);
		JSONObject res = this.client.identifyUser(Arrays.asList(group), path, options);
		//System.out.println(res.toString(2));
		return res;
	}

	/**
	 * 人脸删除
	 * @param uid
	 * @param group
	 * @return
	 */
	public JSONObject deleteUser(String uid, String group) {
		JSONObject res = client.deleteUser(uid, Arrays.asList(group));
//		System.out.println(res.toString(2));
		return res;
	}

	public JSONObject getGroupUsers(String group) {
		HashMap<String, Object> options = new HashMap<String, Object>(2);
		options.put("start", 0);
		options.put("num", 100);
		JSONObject res = client.getGroupUsers(group, options);
		return res;
	}

//	public static void main(String[] args) {
//		AipFaceClient client = new AipFaceClient();
//		JSONObject res = client.getGroupUsers("pi");
////		JSONObject res = client.deleteUser("uid_13","pi");
//		System.out.println(res.toString(2));
//	}
}
