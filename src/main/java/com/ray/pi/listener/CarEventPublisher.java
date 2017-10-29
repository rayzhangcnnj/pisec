package com.ray.pi.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Ray on 2017/10/27.
 */
@Component
public class CarEventPublisher {

	@Autowired
	private ApplicationContext applicationContext;

	public void pushListener(String msg) {
		applicationContext.publishEvent(new CarEvent(this, msg));
	}
}
