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
        //super(new TextureRegion(atlas.findRegion("main_ship").getTexture(),190,143));
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        float posX = 0f;
        float posY = 0f;
        pos.set(posX,posY);
    }

    public void changePosition(float x, float y){
        pos.set(pos.x + x, pos.y + y);
    }
}
