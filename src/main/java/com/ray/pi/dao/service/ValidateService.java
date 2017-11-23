package com.ray.pi.dao.service;

import com.alibaba.fastjson.JSONObject;
import com.ray.pi.dao.entity.TfBUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Ray on 2017/10/24.
 */
public interface ValidateService {

	TfBUser queryUserInfo(Long userId);

	JSONObject facesetAddUser(HttpSession session, HttpServletRequest request);

	JSONObject validateUser(HttpSession session);

	JSONObject editUser(HttpSession session, HttpServletRequest request);

	void drivingRemind();

	void engineStop();

	String buildMp3File(String path, String text, String userId, String type);

	JSONObject takePhoto(HttpSession session);

	void alcoholWarning();
}
