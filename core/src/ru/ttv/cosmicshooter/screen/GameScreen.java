package ru.ttv.cosmicshooter.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.Base2DScreen;
import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.screen.pool.BulletPool;
import ru.ttv.cosmicshooter.screen.sprites.Background;
import ru.ttv.cosmicshooter.screen.gamescreen.MainShip;
import ru.ttv.cosmicshooter.screen.sprites.Star;

public class GameScreen extends Base2DScreen {
    private static final int STAR_COUNT = 64;
    private static final float STEP = 0.02f;
    private Background background;
    private Texture bgTexture;
    private TextureAtlas atlas;
    private TextureAtlas mainAtlas;
    private Star star[];
    private MainShip mainShip;
    private Sound shootSound;

    private BulletPool bulletPool = new BulletPool();



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
        mainShip = new MainShip(atlas,bulletPool,shootSound);
        music.setLooping(true);
        music.setVolume(0.5f);
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
        batch.end();
    }

    public void update(float delta) {
        for(int i=0; i<star.length; i++){
            star[i].update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveSprites(delta);
    }

    public void checkCollisions() {

    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
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
        shootSound.dispose();

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
        return super.touchUp(touch, pointer);
    }
}
