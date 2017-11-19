package com.ar.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ar.game.AnimalRacing;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = AnimalRacing.TITLE;
		config.width = AnimalRacing.V_WIDTH * AnimalRacing.SCALE;
		config.height = AnimalRacing.V_HEIGHT * AnimalRacing.SCALE;
		config.addIcon("icon(16x16).png", Files.FileType.Local);
		config.addIcon("icon(32x32).png", Files.FileType.Local);
		config.addIcon("icon(128x128).png", Files.FileType.Local);
		new LwjglApplication(new AnimalRacing(), config);
	}
}
