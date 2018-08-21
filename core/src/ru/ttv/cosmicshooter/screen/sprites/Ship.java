package ru.ttv.cosmicshooter.screen.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ttv.cosmicshooter.base.Sprite;
import ru.ttv.cosmicshooter.math.Rect;

public class Ship extends Sprite {
    private Rect worldBounds;

    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        TextureRegion tr = ((TextureRegion) atlas.findRegion("main_ship"));
        tr.setRegion(916,95,195,287);
        regions[0] = tr;
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
        System.out.println(regions[frame].getRegionWidth());
        System.out.println(regions[frame].getRegionHeight());
        System.out.println(pos.x);
        System.out.println(pos.y);
        pos.set(pos.x + x, pos.y + y);
    }
}
