package ru.ttv.cosmicshooter.screen.gamescreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.screen.pool.BulletPool;

public class MainShip extends Ship {
    private static final float SHIP_HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;

    private Vector2 v0 = new Vector2(0.5f,0.0f);


    private boolean pressedLeft;
    private boolean pressedRight;

    


    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"),1,2,2);
        setHeightProportion(SHIP_HEIGHT);
//        TextureRegion tr = ((TextureRegion) atlas.findRegion("main_ship"));
//        tr.setRegion(916,95,195,287);
//        regions[0] = tr;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletHeight = 0.01f;
        this.bulletV.set(0,0.5f);
        this.bulletDamage = 1;
        this.bulletPool = bulletPool;

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v,delta);
        if(getRight() > worldBounds.getRight()){
            setRight(worldBounds.getRight());
            stop();
        }
        if(getLeft() < worldBounds.getLeft()){
            setLeft(worldBounds.getLeft());
            stop();
        }
    }

    public void keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if(pressedRight){
                    moveRight();
                }else stop();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if(pressedLeft){
                    moveLeft();
                }else stop();
                break;
        }
    }

    private void moveRight(){
        v.set(v0);
    }

    private void moveLeft(){
        v.set(v0).rotate(180);
    }

    private void stop(){
        v.setZero();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if(touch.x < worldBounds.pos.x){
            moveLeft();
        }else{
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        stop();
        return false;
    }
}
