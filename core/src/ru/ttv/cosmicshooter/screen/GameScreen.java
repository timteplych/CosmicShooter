package ru.ttv.cosmicshooter.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private static final float STEP = 0.02f;
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
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++){
            star[i] = new Star(atlas);
        }

        ship = new Ship(atlas);

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
        ship.draw(batch);
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
        ship.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bgTexture.dispose();
        atlas.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(Input.Keys.LEFT == keycode){
            System.out.println("left arrow key pressed");
            ship.changePosition(-1*STEP,0);
        }
        if(Input.Keys.RIGHT == keycode){
            System.out.println("right arrow key pressed");
            ship.changePosition(STEP,0);
        }
        if(Input.Keys.UP == keycode){
            System.out.println("up arrow key pressed");
            ship.changePosition(0,STEP);
        }
        if(Input.Keys.DOWN == keycode){
            System.out.println("down arrow key pressed");
            ship.changePosition(0,-1*STEP);
        }
        return super.keyDown(keycode);
    }
}
