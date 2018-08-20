package ru.ttv.cosmicshooter.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.Base2DScreen;
import ru.ttv.cosmicshooter.base.Sprite;
import ru.ttv.cosmicshooter.math.Rect;

public class MenuScreen extends Base2DScreen {
    private static final int STAR_COUNT = 128;
    private static final float BUTTON_PRESS_SCALE = 0.9f;
    private static final float BUTTON_HEIGHT = 0.15f;

    private Texture img;
    private Texture imgBackGround;
    private Sprite imgSprite;
    private Sprite imgBackGroundSprite;

    private Vector2 position;
    private Vector2 velocity;

    private Vector2 touchPosition;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        imgBackGround = new Texture("2560x2560CatInSpace.jpg");
        img = new Texture("destroyer348x478.png");
        imgBackGroundSprite = new Sprite(new TextureRegion(imgBackGround));

        imgSprite = new Sprite(new TextureRegion(img));
        imgBackGroundSprite.setSize(23f,23f);
        imgSprite.setSize(4f,4f);

        position = new Vector2(0,0);
        velocity = new Vector2(0,0);
        touchPosition = null;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        imgBackGroundSprite.draw(batch);
        imgSprite.draw(batch);
        batch.end();

        if(touchPosition != null){
            if(Math.round(position.x) != touchPosition.x && Math.round(position.y) != touchPosition.y){
                position.add(velocity);
            }
        }else{
            position.add(velocity);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        imgBackGround.dispose();
        img.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        if(touchPosition == null){
            touchPosition = new Vector2(screenX,Gdx.graphics.getHeight() - screenY);
        }else{
            touchPosition.set(screenX,Gdx.graphics.getHeight() - screenY);
        }
        velocity.set(touchPosition.x,touchPosition.y);
        velocity.sub(position);
        velocity.nor();
        System.out.println("Touch position x: "+touchPosition.x+" Touch position y: "+touchPosition.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
