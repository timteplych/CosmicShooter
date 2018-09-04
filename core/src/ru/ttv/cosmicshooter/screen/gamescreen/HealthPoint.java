package ru.ttv.cosmicshooter.screen.gamescreen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ttv.cosmicshooter.base.Sprite;

public class HealthPoint extends Sprite {
    private static final float HEIGHT = 0.05f;
    private float bottomMargin;

    public HealthPoint(TextureRegion region, float bottomMargin, float leftMargin) {
        super(region);
        setHeightProportion(HEIGHT);
        setLeft(leftMargin);
        setBottom(bottomMargin);
    }
}
