package ru.ttv.cosmicshooter.screen.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.Sprite;
import ru.ttv.cosmicshooter.math.Rect;

public class Star extends Sprite {
    private Vector2 v = new Vector2();
    private Rect worldBounds;
    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        v.set(Rnd.ne)
    }
}
