package com.ray.pi.common.aip;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.ArrayList;
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
}
