package com.ray.pi.listener;


import com.ray.pi.dao.service.ValidateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ray on 2017/11/1.
 */
public class EngineStopListener {
	private static final Logger logger = Logger.getLogger(EngineStopListener.class);

	@Autowired
	private ValidateService validateService;

	/**
	 * 在jvm关闭时关闭线程池
	 */
	static{
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				logger.info("--------系统关闭--------");
			}
		}));
	}

	public void destory() {
		validateService.engineStop();
		logger.info("--------结束驾驶--------");
	}
}
