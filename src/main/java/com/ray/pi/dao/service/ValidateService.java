package com.ray.pi.dao.service;

import com.ray.pi.dao.entity.TfBUser;

/**
 * Created by Ray on 2017/10/24.
 */
public interface ValidateService {

	public void userLogin(Integer userId);

	public TfBUser queryUserInfo(Integer userId);
}
