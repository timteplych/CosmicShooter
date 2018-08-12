package ru.ttv.cosmicshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.ttv.cosmicshooter.Cosmic2DShooter;
import ru.ttv.cosmicshooter.CosmicShooter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Cosmic2DShooter(), config);
	}
}
