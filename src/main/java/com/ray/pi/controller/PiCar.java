package com.ray.pi.controller;

import com.pi4j.io.gpio.*;
import com.ray.pi.listener.CarEventPublisher;

import java.util.Scanner;

/**
 * 驱动控制
 * Created by Ray on 2017/10/26.
 */
public class PiCar {

	static final GpioController gpio = GpioFactory.getInstance();
	static int run=0;


	static final GpioPinDigitalOutput[] pin ={gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW),gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, PinState.LOW),gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW),gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, PinState.LOW)};
	public static void main(String[] args) throws InterruptedException {
		//listen();
		car();
	}

	public static void listen() {
		Scanner in = new Scanner(System.in);
		CarEventPublisher carPublish = new CarEventPublisher();
	}

	public static void car(){
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.print("请输入指令(w s a d t):");
			String input = in.next();
			if("w".equals(input)){
				forward();
				System.out.println("");
			}
			else if("s".equals(input)){
				backward();
				System.out.println("");
			}
			else if("a".equals(input)){
				turn_left();
				System.out.println("");
			}
			else if("d".equals(input)){
				turn_right();
				System.out.println("");
			}
			else if("t".equals(input)){
				stop();
				System.out.println("");
			}else{
				System.out.println("输入错误，请重新输入");
			}
		}
	}

	public static void forward(){
		if(run==0){
			pin[1].high();
			pin[2].high();
			run=1;
		}
	}
	public static void backward(){
		if(run==0){
			pin[0].high();
			pin[3].high();
			run=1;
		}
	}
	public static void turn_left(){
		if(run==0){
			pin[0].high();
			pin[2].high();
			run=1;
		}
	}
	public static void turn_right(){
		if(run==0){
			pin[1].high();
			pin[3].high();
			run=1;
		}
	}
	public static void stop(){
		if(run==1){
			pin[0].low();
			pin[1].low();
			pin[2].low();
			pin[3].low();
			run=0;
			gpio.shutdown();
		}
	}
}
