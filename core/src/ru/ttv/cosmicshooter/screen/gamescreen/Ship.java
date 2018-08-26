package ru.ttv.cosmicshooter.screen.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.Sprite;
import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.screen.pool.BulletPool;
import ru.ttv.cosmicshooter.screen.pool.ExplosionPool;

public class Ship extends Sprite {
    protected Vector2 v = new Vector2();
    protected Rect worldBounds;

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected ExplosionPool explosionPool;

    protected Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int bulletDamage;

    protected Sound shootSound;

    protected int hp = 100;

    protected float reloadInterval;
    protected float reloadTimer;

    public Ship(BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound, Rect worldBounds) {
        this.shootSound = shootSound;
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;

    }

    public Ship(TextureRegion region, int rows, int cols, int frames,Sound shootSound) {
        super(region, rows, cols, frames);
        this.shootSound = shootSound;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    protected void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this,bulletRegion,pos,bulletV,bulletHeight,worldBounds,bulletDamage);
        shootSound.play();
    }

    public void boom(){
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(),pos);
    }


}
