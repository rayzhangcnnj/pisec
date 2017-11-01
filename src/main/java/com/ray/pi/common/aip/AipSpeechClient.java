package com.ray.pi.common.aip;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;

import java.util.HashMap;

/**
 * Created by Ray on 2017/11/1.
 */
public class AipSpeechClient {

	private static final String APP_ID = "10313230";
	private static final String API_KEY = "3T6rW9fT3jIl12rmnBXAO7rO";
	private static final String SECRET_KEY = "NhYCPh6d8F4s30QBYkXxHVkRsmtWlWei";

	private AipSpeech client;

	public AipSpeechClient() {
		// 初始化一个AipFace
		client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
	}

	public byte[] synthesis(String text, String lang, int ctp)
	{
//		TtsResponse res = this.client.synthesis("你好百度", "zh", 1, null);
//		System.out.println(res.getErrorCode());

		// 设置可选参数
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("spd", "5");
		options.put("pit", "5");
		options.put("per", "4");
		TtsResponse res = this.client.synthesis(text, lang, ctp, options);
		//System.out.println(res.getErrorCode());
		byte[] data = res.getData();
		return data;
	}
}
