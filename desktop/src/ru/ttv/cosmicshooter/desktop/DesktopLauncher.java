package ru.ttv.cosmicshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.ttv.cosmicshooter.Cosmic2DShooter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float aspect = 3f/4f;
		config.width = 350;
		config.height = (int)(config.width/aspect);
		config.resizable = false;
		new LwjglApplication(new Cosmic2DShooter(), config);
	}
}
