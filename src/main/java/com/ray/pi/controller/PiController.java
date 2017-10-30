package com.ray.pi.controller;

import com.alibaba.fastjson.JSONObject;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.ray.pi.dao.service.ValidateService;
import com.ray.pi.gpio.CarUtil;
import com.ray.pi.gpio.GpioUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray on 2017/10/22.
 */
@Controller
public class PiController {

	//public static final GpioController gpio = GpioFactory.getInstance();

	@Autowired
	private ValidateService validateService;

	@RequestMapping("/toPiController")
	public String toPiController() {
		return "piController";
	}

	@RequestMapping(value = "/testGpio", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JSONObject startEngine() {
		JSONObject json = new JSONObject();

		GpioUnit gpio = GpioUnit.getInstance();

		try {
			GpioPinDigitalOutput myLed = gpio.getGpioPinDigitalOutput("GPIO_00");
			int i = 0;
			while (i < 3) {
				myLed.high();
				Thread.sleep(500);
				myLed.low();
				Thread.sleep(500);
				i++;
				System.out.println("No." + i);
			}
			System.out.println("End");
//			gpio.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}

		json.put("result", "success");
		return json;

	}

	@RequestMapping(value = "/carController", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public JSONObject carController(HttpServletRequest request) {
		JSONObject json = new JSONObject();

		GpioUnit gpio = GpioUnit.getInstance();

		String distance = request.getParameter("distance");
		try {
			GpioPinDigitalOutput[] pin = gpio.getCAR();

			CarUtil.car(distance, pin);


		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("distance", distance);

		json.put("resultCode", "success");
		json.put("resultMap", resultMap);
		return json;
	}


}
