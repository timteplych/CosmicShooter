package ru.ttv.cosmicshooter.utils;

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

    private static final float LARGE_ENEMYSHIP_HEIGHT = 0.15f;
    private static final float LARGE_ENEMYSHIP_BULLET_HEIGHT = 0.02f;
    private static final float LARGE_ENEMYSHIP_BULLET_VY = -0.3f;
    private static final int   LARGE_ENEMYSHIP_BULLET_DAMAGE = 2;
    private static final float LARGE_ENEMYSHIP_RELOAD_INTERVAL = 3f;
    private static final int   LARGE_ENEMYSHIP_HP = 10;


    private Rect worldBounds;

    private float generateInterval = 4f;
    private float generateTimer;

    private TextureRegion[] enemySmallRegion;
    private TextureRegion[] enemyLargeRegion;
    private TextureRegion[] enemySmallRegion1;
    private Vector2 enemySmallV = new Vector2(0,-0.2f);
    private Vector2 enemyLargeV = new Vector2(0, -0.1f);

    private TextureRegion bulletRegion;

    private EnemyShipPool enemyShipPool;

    public EnemyShipEmitter(TextureAtlas atlas, Rect worldBounds, EnemyShipPool enemyShipPool) {
        this.worldBounds = worldBounds;
        this.enemyShipPool = enemyShipPool;

        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemySmallRegion = Regions.split(textureRegion0,1,2,2);
        this.enemySmallRegion1 = Regions.split(textureRegion1,1,2,2);
        this.enemyLargeRegion = Regions.split(textureRegion2,1,2,2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
    }

    public void generateEnemies(float delta){
        generateTimer += delta;
        if(generateTimer >= generateInterval){
            generateTimer = 0f;
            EnemyShip enemyShip = enemyShipPool.obtain();
            float rndEnemy = Rnd.nextFloat(0f,30f);
            if(rndEnemy >=0 && rndEnemy < 10){
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
            }else if(rndEnemy >= 10 && rndEnemy < 20){
                enemyShip.set(
                        enemyLargeRegion,
                        enemyLargeV,
                        bulletRegion,
                        LARGE_ENEMYSHIP_BULLET_HEIGHT,
                        LARGE_ENEMYSHIP_BULLET_VY,
                        LARGE_ENEMYSHIP_BULLET_DAMAGE,
                        LARGE_ENEMYSHIP_RELOAD_INTERVAL,
                        LARGE_ENEMYSHIP_HEIGHT,
                        LARGE_ENEMYSHIP_HP
                );
            }else if(rndEnemy >= 20){
                enemyShip.set(
                        enemySmallRegion1,
                        enemySmallV,
                        bulletRegion,
                        SMALL_ENEMYSHIP_BULLET_HEIGHT,
                        SMALL_ENEMYSHIP_BULLET_VY,
                        SMALL_ENEMYSHIP_BULLET_DAMAGE,
                        SMALL_ENEMYSHIP_RELOAD_INTERVAL,
                        SMALL_ENEMYSHIP_HEIGHT,
                        SMALL_ENEMYSHIP_HP
                );
            }

            enemyShip.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfHeight());
            enemyShip.setBottom(worldBounds.getTop());

        }
    }
}
