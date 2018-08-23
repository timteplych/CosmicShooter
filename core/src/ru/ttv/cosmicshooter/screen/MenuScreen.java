package ru.ttv.cosmicshooter.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.ActionListener;
import ru.ttv.cosmicshooter.base.Base2DScreen;
import ru.ttv.cosmicshooter.base.Sprite;
import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.screen.menu.ButtonExit;
import ru.ttv.cosmicshooter.screen.menu.ButtonNewGame;
import ru.ttv.cosmicshooter.screen.sprites.Background;
import ru.ttv.cosmicshooter.screen.sprites.Star;

public class MenuScreen extends Base2DScreen implements ActionListener {
    private static final int STAR_COUNT = 256;
    private static final float BUTTON_PRESS_SCALE = 0.9f;
    private static final float BUTTON_HEIGHT = 0.15f;

    private Texture imgBackGround;
    private TextureAtlas atlas;
    private Star star[];
    private Background background;
    private ButtonExit buttonExit;
    private ButtonNewGame buttonNewGame;

    private Vector2 position;
    private Vector2 velocity;

    private Vector2 touchPosition;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        imgBackGround = new Texture("textures/2560x2560CatInSpace.jpg");
        background = new Background(new TextureRegion(imgBackGround));

        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++){
            star[i] = new Star(atlas);
        }

        buttonExit = new ButtonExit(atlas,this,BUTTON_PRESS_SCALE);
        buttonExit.setHeightProportion(BUTTON_HEIGHT);

        buttonNewGame = new ButtonNewGame(atlas,this,BUTTON_PRESS_SCALE);
        buttonNewGame.setHeightProportion(BUTTON_HEIGHT);

        position = new Vector2(0,0);
        velocity = new Vector2(0,0);
        touchPosition = null;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void draw(){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        for(int i=0;i<star.length;i++){
            star[i].draw(batch);
        }
        buttonExit.draw(batch);
        buttonNewGame.draw(batch);
        batch.end();

        if(touchPosition != null){
            if(Math.round(position.x) != touchPosition.x && Math.round(position.y) != touchPosition.y){
                position.add(velocity);
            }
        }else{
            position.add(velocity);
        }
    }

    public void update(float delta){
        for(int i=0; i<star.length; i++){
            star[i].update(delta);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        imgBackGround.dispose();
        atlas.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length ; i++) {
            star[i].resize(worldBounds);
        }
        buttonExit.resize(worldBounds);
        buttonNewGame.resize(worldBounds);
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonExit.touchDown(touch,pointer);
        buttonNewGame.touchDown(touch,pointer);
        return super.touchDown(touch,pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer);
        buttonNewGame.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }



    @Override
    public void actionPerformed(Object src) {
        if(src == buttonExit){
            Gdx.app.exit();
        }else if (src == buttonNewGame){
            game.setScreen(new GameScreen(game));
        }
    }
}
