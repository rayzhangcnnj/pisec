package com.ray.pi.dao.service;

import com.alibaba.fastjson.JSONObject;
import com.ray.pi.dao.entity.TfBUser;

/**
 * Created by Ray on 2017/10/24.
 */
public interface ValidateService {

	public TfBUser queryUserInfo(Long userId);

	public JSONObject facesetAddUser();

	public JSONObject validateUser();

	public void engineStop();
}
