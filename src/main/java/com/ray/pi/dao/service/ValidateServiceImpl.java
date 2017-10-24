package com.ray.pi.dao.service;

import com.ray.pi.dao.entity.TfBUser;
import com.ray.pi.dao.persistence.TfBUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ray on 2017/10/24.
 */
@Service
public class ValidateServiceImpl implements ValidateService {

	@Autowired
	private TfBUserMapper userMapper;

	public TfBUser queryUserInfo(Long userId) {
		TfBUser user = userMapper.selectByPrimaryKey(userId);
		return user;
	}
}
