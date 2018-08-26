package ru.ttv.cosmicshooter.screen.gamescreen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.screen.pool.BulletPool;
import ru.ttv.cosmicshooter.screen.pool.EnemyShipPool;
import ru.ttv.cosmicshooter.screen.pool.ExplosionPool;

public class EnemyShip extends Ship {
    private Vector2 v0 = new Vector2();
    private MainShip mainShip;

    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Sound sound, MainShip mainShip) {
        super(bulletPool, explosionPool, sound);
        this.mainShip = mainShip;
        this.v.set(v0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp
    ){
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0f,bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        setHeightProportion(height);

    }
}
