package com.ray.pi.controller;

import com.pi4j.io.gpio.*;
import com.ray.pi.dao.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Ray on 2017/10/22.
 */
@Controller
public class PiController {

	public static final GpioController gpio = GpioFactory.getInstance();

	@Autowired
	private ValidateService validateService;

	public void startEngine() {
		try {
			GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "", PinState.LOW);

			while (true) {

				myLed.high();
				Thread.sleep(500);
				myLed.low();
				Thread.sleep(500);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
