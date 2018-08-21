package ru.ttv.cosmicshooter.screen.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.Sprite;
import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.math.Rnd;

public class Ship extends Sprite {
    private Rect worldBounds;

    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        setHeightProportion(0.25f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        float posX = 0.5f;
        float posY = 0.5f;
        pos.set(posX,posY);
    }
}
