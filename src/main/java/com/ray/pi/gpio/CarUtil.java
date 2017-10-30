package com.ray.pi.gpio;

import com.pi4j.io.gpio.GpioPinDigitalOutput;

/**
 * Created by Ray on 2017/10/30.
 */
public class CarUtil {

	static int run = 0;

	public static void car(String distance, GpioPinDigitalOutput[] pin){
		if("F".equals(distance)){
			forward(pin);
		}
		else if("B".equals(distance)){
			backward(pin);
		}
		else if("L".equals(distance)){
			turn_left(pin);
		}
		else if("R".equals(distance)){
			turn_right(pin);
		}
		else if("S".equals(distance)){
			stop(pin);
		}else{
		}
	}

	public static void forward(GpioPinDigitalOutput[] pin){
		if(run==0){
			pin[1].high();
			pin[2].high();
			run=1;
		}
	}
	public static void backward(GpioPinDigitalOutput[] pin){
		if(run==0){
			pin[0].high();
			pin[3].high();
			run=1;
		}
	}
	public static void turn_left(GpioPinDigitalOutput[] pin){
		if(run==0){
			pin[0].high();
			pin[2].high();
			run=1;
		}
	}
	public static void turn_right(GpioPinDigitalOutput[] pin){
		if(run==0){
			pin[1].high();
			pin[3].high();
			run=1;
		}
	}
	public static void stop(GpioPinDigitalOutput[] pin){
		if(run==1){
			pin[0].low();
			pin[1].low();
			pin[2].low();
			pin[3].low();
			run=0;
		}
	}
}
