package ru.ttv.cosmicshooter.screen.gamescreen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.ttv.cosmicshooter.base.ActionListener;
import ru.ttv.cosmicshooter.base.ScaledTouchUpButton;

public class MessageNewGame extends ScaledTouchUpButton {
    private static final float HEIGHT = 0.07f;
    private static final float PRESS_SCALE = 0.9f;
    private static final float TOP = -0.012f;
    public MessageNewGame(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("button_new_game"), actionListener, PRESS_SCALE);
        setHeightProportion(HEIGHT);
        setTop(TOP);
    }
}
