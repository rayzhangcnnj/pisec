package com.ray.pi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ray on 2017/11/5.
 */
public class StringUtil {

	/**
	 * 生成图片全路径
	 * @param path 前缀路径
	 * @param date 时间
	 * @return
	 */
	public static String generatePhotoName(String path, Date date) {
		StringBuffer img = new StringBuffer("");
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		img.append(path).append("IMG_").append(format.format(date)).append(".jpg");
		return img.toString();
	}
}
