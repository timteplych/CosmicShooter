package ru.ttv.cosmicshooter.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.ttv.cosmicshooter.base.ActionListener;
import ru.ttv.cosmicshooter.base.Base2DScreen;
import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.screen.gamescreen.Bullet;
import ru.ttv.cosmicshooter.screen.gamescreen.EnemyShip;
import ru.ttv.cosmicshooter.screen.gamescreen.MessageGameOver;
import ru.ttv.cosmicshooter.screen.gamescreen.MessageNewGame;
import ru.ttv.cosmicshooter.screen.pool.BulletPool;
import ru.ttv.cosmicshooter.screen.pool.EnemyShipPool;
import ru.ttv.cosmicshooter.screen.pool.ExplosionPool;
import ru.ttv.cosmicshooter.screen.sprites.Background;
import ru.ttv.cosmicshooter.screen.gamescreen.MainShip;
import ru.ttv.cosmicshooter.screen.sprites.Star;
import ru.ttv.cosmicshooter.utils.EnemyShipEmitter;

public class GameScreen extends Base2DScreen implements ActionListener {
    private enum State {PLAYING, GAME_OVER}

    private static final int STAR_COUNT = 32;
    private static final float STEP = 0.02f;
    private Background background;
    private Texture bgTexture;
    private TextureAtlas atlas;
    private TextureAtlas mainAtlas;
    private Star star[];
    private MainShip mainShip;
    private Sound shootSound;
    private Sound explosionSound;
    private Sound laserSound;

    private BulletPool bulletPool = new BulletPool();
    private ExplosionPool explosionPool;
    private EnemyShipPool enemyShipPool;
    private EnemyShipEmitter enemyShipEmitter;

    private State state;

    private MessageGameOver messageGameOver;
    private MessageNewGame messageNewGame;

    int frags;

    public GameScreen(Game game) {
        super(game,Gdx.audio.newMusic(Gdx.files.internal("sounds/fantasy_game_background_looping.mp3")));
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("textures/2560x2560CatInSpace.jpg");
        background = new Background(new TextureRegion(bgTexture));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++){
            star[i] = new Star(atlas);
        }
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clong.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        explosionPool = new ExplosionPool(atlas,explosionSound);
        mainShip = new MainShip(atlas,bulletPool,shootSound, explosionPool);
        music.setLooping(true);
        music.setVolume(0.5f);
        enemyShipPool = new EnemyShipPool(bulletPool,explosionPool, worldBounds,mainShip, shootSound);
        enemyShipEmitter = new EnemyShipEmitter(atlas,worldBounds,enemyShipPool);
        messageGameOver = new MessageGameOver(atlas);
        messageNewGame = new MessageNewGame(atlas,this,0.9f);
        startNewGame();
    }

    @Override
    public void actionPerformed(Object src) {
        System.out.println("Start new game!!!!!!");
        startNewGame();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for(int i=0;i<star.length;i++){
            star[i].draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        explosionPool.drawActiveSprites(batch);
        enemyShipPool.drawActiveSprites(batch);
        if(state ==  State.GAME_OVER){
            messageGameOver.draw(batch);
            messageNewGame.draw(batch);
        }
        batch.end();
    }

    public void update(float delta) {
        if(mainShip.isDestroyed()){
            state = State.GAME_OVER;
        }
        for(int i=0; i<star.length; i++){
            star[i].update(delta);
        }
        explosionPool.updateActiveSprites(delta);
        switch (state){
            case PLAYING:
                mainShip.update(delta);
                enemyShipPool.updateActiveSprites(delta);
                enemyShipEmitter.generateEnemies(delta);
                bulletPool.updateActiveSprites(delta);
                break;
            case GAME_OVER:
                break;
        }
    }

    public void checkCollisions() {
        List<EnemyShip> enemyShipList = enemyShipPool.getActiveObjects();
        for(EnemyShip enemyShip : enemyShipList){
            if(enemyShip.isDestroyed()){
                continue;
            }
            float minDist = enemyShip.getHalfWidth() + mainShip.getHalfWidth();
            if(enemyShip.pos.dst2(mainShip.pos)<minDist*minDist){
                enemyShip.destroy();
                mainShip.destroy();
                state = State.GAME_OVER;
                return;
            }
        }

        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for(EnemyShip enemyShip: enemyShipList){
            if(enemyShip.isDestroyed()){
                continue;
            }
            for (Bullet bullet:bulletList){
                if(bullet.getOwner() != mainShip || bullet.isDestroyed()){
                    continue;
                }
                if(enemyShip.isBulletCollision(bullet)){
                    enemyShip.damage(bullet.getDamage());
                    bullet.destroy();
                    if(enemyShip.isDestroyed()){
                        frags++;
                        break;
                    }
                }
            }
        }

        for(Bullet bullet: bulletList){
            if(bullet.isDestroyed() || bullet.getOwner() == mainShip){
                continue;
            }
            if(mainShip.isBulletCollision(bullet)){
                mainShip.damage(bullet.getDamage());
                bullet.destroy();
                if(mainShip.isDestroyed()){
                    state = State.GAME_OVER;
                }
            }
        }
    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
        enemyShipPool.freeAllDestroyedActiveSprites();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length ; i++) {
            star[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bgTexture.dispose();
        atlas.dispose();
        bulletPool.dispose();
        explosionPool.dispose();
        enemyShipPool.dispose();
        shootSound.dispose();
        laserSound.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch,pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch,pointer);
        if(state == State.GAME_OVER){
            messageNewGame.touchUp(touch,pointer);
        }
        return super.touchUp(touch, pointer);
    }

    private void startNewGame(){
        state = State.PLAYING;
        frags = 0;
        mainShip.startNewGame();
        bulletPool.freeAllActiveObjects();
        enemyShipPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
    }
}
