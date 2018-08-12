package ru.ttv.cosmicshooter;

import com.badlogic.gdx.Game;

import ru.ttv.cosmicshooter.Screen.MenuScreen;

public class Cosmic2DShooter extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
