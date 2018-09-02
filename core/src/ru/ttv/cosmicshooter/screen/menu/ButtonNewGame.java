package ru.ttv.cosmicshooter.screen.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.ttv.cosmicshooter.base.ActionListener;
import ru.ttv.cosmicshooter.base.ScaledTouchUpButton;
import ru.ttv.cosmicshooter.math.Rect;

public class ButtonNewGame extends ScaledTouchUpButton {
    public ButtonNewGame(TextureAtlas atlas, ActionListener actionListener, float pressScale) {
        super(atlas.findRegion("btPlay"), actionListener, pressScale);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
