package com.ray.pi.task;

import com.ray.pi.dao.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Ray on 2017/11/5.
 */
@Component
public class TaskServiceImpl {

	@Autowired
	private ValidateService validateService;

	@Scheduled(cron = "0 0/10 * * * ?")
	public void remindTask() {
		System.out.println("========定时提醒检查开始========");
		validateService.drivingRemind();
		System.out.println("========定时提醒检查结束========");
	}

}
