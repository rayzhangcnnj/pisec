package com.ray;

import com.baidu.aip.face.AipFace;
import com.ray.pi.common.aip.AipFaceClient;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ray on 2017/10/24.
 */
public class AipTest {

	public static final String APP_ID = "10277354";
    public static final String API_KEY = "aqRG4suKYdaVh0SKxcHyBqEn";
    public static final String SECRET_KEY = "eVtk0scPzDlHAjRMQlFUUZEII7Pq1rmE";

    public static void main(String[] args) {
        // 初始化一个AipFace
//        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
//
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", 8080);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
//        String path = "test.jpg";
//        JSONObject res = client.detect(path, new HashMap<String, String>());
//        System.out.println(res.toString(2));
        AipFaceClient client = new AipFaceClient();
        String imagePath1 = "/Users/zhangrui/Downloads/IMG20170304134603.jpg";
        String imagePath2 = "/Users/zhangrui/Downloads/IMG_20170511_184307.jpg";
        JSONObject json = client.faceRecognize(imagePath1, imagePath2);
        System.out.println(json.toString());
    }

    public static void faceRecognize(AipFace client) {
        // 参数为本地图片路径
        String imagePath1 = "/Users/zhangrui/Downloads/IMG20170304134603.jpg";
        String imagePath2 = "/Users/zhangrui/Downloads/IMG_20170511_184307.jpg";
        ArrayList<String> pathArray = new ArrayList<String>();
        pathArray.add(imagePath1);
        pathArray.add(imagePath2);
        JSONObject response = client.match(pathArray, new HashMap<String, String>());
        System.out.println(response.toString());
    }
}
