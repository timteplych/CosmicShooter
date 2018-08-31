package ru.ttv.cosmicshooter.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.ttv.cosmicshooter.math.Rect;
import ru.ttv.cosmicshooter.utils.Regions;

public class Sprite extends Rect {
    protected float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;
    protected int frame;
    protected boolean isDestroyed;

    public Sprite() {
    }

    public Sprite(TextureRegion region) {
        if (region == null) {
            throw new RuntimeException("region == null");
        }
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public Sprite(TextureRegion region, int rows, int cols, int frames){
        this.regions = Regions.split(region,rows,cols,frames);
        /*if(region == null){
            throw new RuntimeException("region == null");
        }
        regions = new TextureRegion[1];
        regions[0] = region;*/
    }

    public void draw(SpriteBatch batch){
        batch.draw(
                regions[frame],//current region
                getLeft(),getBottom(),//drawing point
                halfWidth,halfHeight,//rotation point
                getWidth(),getHeight(),//width and height
                scale,scale,//scale x and scale y
                angle //rotation angle
        );
    }

    public void setHeightProportion(float height){
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height*aspect);
    }

    public void resize(Rect worldBounds) {

    }

    public void update(float delta) {

    }

    public boolean touchDown(Vector2 touch, int pointer) {

        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void destroy(){
        this.isDestroyed = true;
    }

    public void flushDestroy(){
        this.isDestroyed = false;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}
