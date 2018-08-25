package ru.ttv.cosmicshooter.screen.gamescreen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.Sprite;
import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.screen.pool.BulletPool;

public class Ship extends Sprite {
    protected Vector2 v = new Vector2();
    protected Rect worldBounds;

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;

    protected Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int bulletDamage;

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this,bulletRegion,pos,bulletV,bulletHeight,worldBounds,bulletDamage);
    }
}
