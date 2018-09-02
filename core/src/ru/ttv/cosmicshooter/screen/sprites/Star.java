package ru.ttv.cosmicshooter.screen.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.Sprite;
import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.math.Rnd;

public class Star extends Sprite {
    protected Vector2 v = new Vector2();
    private Rect worldBounds;
    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        v.set(Rnd.nextFloat(-0.005f,0.005f),Rnd.nextFloat(-0.5f,-0.1f));
        setHeightProportion(0.01f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(),worldBounds.getTop());
        pos.set(posX,posY);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v,delta);
        checkAndHandleBounds();
    }

    protected void checkAndHandleBounds() {
        if(getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if(getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if(getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
    }
}
