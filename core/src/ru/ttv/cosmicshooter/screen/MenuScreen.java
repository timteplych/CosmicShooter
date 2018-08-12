package ru.ttv.cosmicshooter.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {
    private SpriteBatch batch;
    private Texture img;
    private Texture imgBackGround;

    private Vector2 position;
    private Vector2 velocity;

    private Vector2 touchPosition;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        imgBackGround = new Texture("2560x2560CatInSpace.jpg");
        img = new Texture("destroyer348x478.png");
        position = new Vector2(0,0);
        velocity = new Vector2(0,0);
        touchPosition = null;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(imgBackGround,0,0,500,500);
        batch.draw(img, position.x, position.y,100,140);
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
        batch.dispose();
        imgBackGround.dispose();
        img.dispose();
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
