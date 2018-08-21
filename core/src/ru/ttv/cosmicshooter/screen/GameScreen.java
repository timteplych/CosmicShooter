package ru.ttv.cosmicshooter.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.ttv.cosmicshooter.base.Base2DScreen;
import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.screen.sprites.Background;
import ru.ttv.cosmicshooter.screen.sprites.Ship;
import ru.ttv.cosmicshooter.screen.sprites.Star;

public class GameScreen extends Base2DScreen {
    private static final int STAR_COUNT = 64;
    private Background background;
    private Texture bgTexture;
    private TextureAtlas atlas;
    private TextureAtlas mainAtlas;
    private Star star[];
    private Ship ship;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("textures/2560x2560CatInSpace.jpg");
        background = new Background(new TextureRegion(bgTexture));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++){
            star[i] = new Star(atlas);
        }

        mainAtlas = new TextureAtlas("textures/mainAtlas.tpack");


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
        batch.end();
    }

    public void update(float delta) {
        for(int i=0; i<star.length; i++){
            star[i].update(delta);
        }
    }

    public void checkCollisions() {

    }

    public void deleteAllDestroyed() {

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length ; i++) {
            star[i].resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        bgTexture.dispose();
        atlas.dispose();
    }
}
