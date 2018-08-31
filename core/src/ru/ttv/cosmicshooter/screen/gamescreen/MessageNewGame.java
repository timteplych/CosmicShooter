package ru.ttv.cosmicshooter.screen.gamescreen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.ActionListener;
import ru.ttv.cosmicshooter.base.ScaledTouchUpButton;

public class MessageNewGame extends ScaledTouchUpButton {
    private static final float HEIGHT = 0.07f;
    private static final float BOTTOM_MARGIN = 0.1f;//0.009f;
    public MessageNewGame(TextureAtlas atlas, ActionListener actionListener, float pressScale) {
        super(atlas.findRegion("button_new_game"), actionListener, pressScale);
        setHeightProportion(HEIGHT);
        setBottom(BOTTOM_MARGIN);
    }


    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        actionListener.actionPerformed(this);
        return super.touchUp(touch, pointer);
    }
}
