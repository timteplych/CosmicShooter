package ru.ttv.cosmicshooter.screen.pool;

import ru.ttv.cosmicshooter.base.SpritesPool;
import ru.ttv.cosmicshooter.screen.gamescreen.Bullet;

/**
 * Bullet pool
 */
public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void debugLog() {
        System.out.println("active/free:"+activeObjects.size()+"/"+freeObjects.size());
    }
}
