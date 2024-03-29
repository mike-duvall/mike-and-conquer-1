package com.mikeduvall.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mikeduvall.MikeAndConquerGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = 320; // Original C&C Dos resolution
//		config.height = 200;

		config.width = 1600; // Original C&C resolution * 5
		config.height = 1000;

		new LwjglApplication(new MikeAndConquerGame(), config);
	}
}
