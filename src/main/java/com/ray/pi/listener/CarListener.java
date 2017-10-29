package com.ray.pi.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Created by Ray on 2017/10/27.
 */
@Component
public class CarListener implements ApplicationListener<CarEvent>{

	public void onApplicationEvent(CarEvent event) {
		Scanner in = new Scanner(System.in);
		String msg = in.next();
		System.out.println("监听1："+msg);
	}
}
