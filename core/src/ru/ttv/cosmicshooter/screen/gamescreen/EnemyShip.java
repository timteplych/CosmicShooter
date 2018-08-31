package ru.ttv.cosmicshooter.screen.gamescreen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.screen.pool.BulletPool;
import ru.ttv.cosmicshooter.screen.pool.ExplosionPool;

public class EnemyShip extends Ship {
    private enum State{DESCENT,FIGHT}
    private State state;
    private Vector2 descentV = new Vector2(0, -0.15f);
    private Vector2 v0 = new Vector2();
    private MainShip mainShip;

    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Sound sound, MainShip mainShip, Rect worldBounds) {
        super(bulletPool, explosionPool, sound, worldBounds);
        this.mainShip = mainShip;
        this.v.set(v0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v,delta);


        switch (state){
            case DESCENT:
                if(getTop() <= worldBounds.getTop()){
                    v.set(v0);
                    state = state.FIGHT;
                }
                break;
            case FIGHT:
                reloadTimer += delta;
                if (reloadTimer >= reloadInterval) {
                    reloadTimer = 0f;
                    shoot();
                }
                if (getBottom() < worldBounds.getBottom()) {
                    boom();
                    destroy();
                }
                break;
        }
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
        v.set(descentV);
        reloadTimer = reloadInterval;
        state = State.DESCENT;

    }
}
