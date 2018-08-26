package ru.ttv.cosmicshooter.screen.pool;

import com.badlogic.gdx.audio.Sound;

import ru.ttv.cosmicshooter.base.SpritesPool;
import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.screen.gamescreen.EnemyShip;
import ru.ttv.cosmicshooter.screen.gamescreen.MainShip;

public class EnemyShipPool extends SpritesPool<EnemyShip> {
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Rect worldBounds;
    private MainShip mainShip;
    private Sound sound;

    public EnemyShipPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, MainShip mainShip, Sound sound) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.mainShip = mainShip;
        this.sound = sound;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool,explosionPool,sound, mainShip);
    }
}
