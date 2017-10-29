package com.ray.pi.listener;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Ray on 2017/10/27.
 */
public class CarEvent extends ApplicationEvent {

	private String msg;

	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the component that published the event (never {@code null})
	 */
	public CarEvent(Object source, String msg) {
		super(source);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
