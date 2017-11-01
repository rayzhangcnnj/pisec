package com.ray.pi.common.utils;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * Created by Ray on 2017/11/1.
 */
public class MP3Player {
	private String filename;
	private Player player;

	public MP3Player(String filename) {
		this.filename = filename;
	}

	public void play() {
		try {
			BufferedInputStream buffer = new BufferedInputStream(
			new FileInputStream(filename));
			player = new Player(buffer);
			player.play();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	public static void main(String[] args) {
//		MP3Player mp3 = new MP3Player("/Users/zhangrui/Documents/pi/src.mp3");
//		mp3.play();
//	}

}
