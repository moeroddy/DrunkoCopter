package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.HeliDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = HeliDemo.WIDTH;
		config.height = HeliDemo.HEIGHT;
		config.title = HeliDemo.TITLE;
		new LwjglApplication(new HeliDemo(), config);
	}
}
