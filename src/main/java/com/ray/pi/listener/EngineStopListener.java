package com.ray.pi.listener;


import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.util.Console;
import com.ray.pi.dao.service.ValidateService;
import com.ray.pi.gpio.GpioUnit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

/**
 * Created by Ray on 2017/11/1.
 */
public class EngineStopListener {
	private static final Logger logger = Logger.getLogger(EngineStopListener.class);

	@Autowired
	private ValidateService validateService;

	private int count = 0;

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

	public void initListener() {
		GpioUnit gpio = GpioUnit.getInstance();
		try {
			GpioPinDigitalMultipurpose mq = gpio.getMq();
			final Console console = new Console();

			mq.addListener(new GpioPinListenerDigital() {
				public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

					boolean isHigh = event.getState().isHigh();
					int stateValue = event.getState().getValue();
					System.out.println(isHigh+"====="+stateValue);

					if (!isHigh && stateValue == 0) {
						count++;
					}
//					if (!isHigh && stateValue == 0) {
//						validateService.alcoholWarning(stateValue);
//					}
					while (count > 2) {
						doAction();
					}
				}
			});

			mq.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
				public Void call() throws Exception {
					System.out.println(" --> GPIO TRIGGER CALLBACK RECEIVED ");
					return null;
				}
			}));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doAction() {
		System.out.println("发现酒精");
		validateService.alcoholWarning();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count = 0;

	}

	public void destory() {
		validateService.engineStop();
		logger.info("--------结束驾驶--------");
	}
}
