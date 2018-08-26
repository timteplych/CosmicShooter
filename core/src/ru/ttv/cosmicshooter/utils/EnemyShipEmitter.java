package ru.ttv.cosmicshooter.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.math.Rnd;
import ru.ttv.cosmicshooter.screen.gamescreen.EnemyShip;
import ru.ttv.cosmicshooter.screen.pool.EnemyShipPool;

public class EnemyShipEmitter {
    private static final float SMALL_ENEMYSHIP_HEIGHT = 0.1f;
    private static final float SMALL_ENEMYSHIP_BULLET_HEIGHT = 0.01f;
    private static final float SMALL_ENEMYSHIP_BULLET_VY = -0.3f;
    private static final int   SMALL_ENEMYSHIP_BULLET_DAMAGE = 1;
    private static final float SMALL_ENEMYSHIP_RELOAD_INTERVAL = 3f;
    private static final int   SMALL_ENEMYSHIP_HP = 1;

    private Rect worldBounds;

    private float generateInterval = 4f;
    private float generateTimer;

    private TextureRegion[] enemySmallRegion;
    private Vector2 enemySmallV = new Vector2(0,-0.2f);

    private TextureRegion bulletRegion;

    private EnemyShipPool enemyShipPool;

    public EnemyShipEmitter(TextureAtlas atlas, Rect worldBounds, EnemyShipPool enemyShipPool) {
        this.worldBounds = worldBounds;
        this.enemyShipPool = enemyShipPool;

        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        this.enemySmallRegion = Regions.split(textureRegion0,1,2,2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
    }

    public void generateEnemies(float delta){
        generateTimer += delta;
        if(generateTimer >= generateInterval){
            generateTimer = 0f;
            EnemyShip enemyShip = enemyShipPool.obtain();
            enemyShip.set(
                    enemySmallRegion,
                    enemySmallV,
                    bulletRegion,
                    SMALL_ENEMYSHIP_BULLET_HEIGHT,
                    SMALL_ENEMYSHIP_BULLET_VY,
                    SMALL_ENEMYSHIP_BULLET_DAMAGE,
                    SMALL_ENEMYSHIP_RELOAD_INTERVAL,
                    SMALL_ENEMYSHIP_HEIGHT,
                    SMALL_ENEMYSHIP_HP
            );
            enemyShip.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfHeight());
            enemyShip.setBottom(worldBounds.getTop());

        }
    }
}
