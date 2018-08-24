package ru.ttv.cosmicshooter.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Pool of sprites
 *
 */

public abstract class SpritesPool<T extends Sprite> {
    //list of active objects
    protected List<T> activeObjects = new ArrayList<T>();
    //list of free objects
    protected List<T> freeObjects = new ArrayList<T>();

    protected abstract T newObject();

    public T obtain(){
        T object;
        if(freeObjects.isEmpty()){
            object = newObject();
        }else{
            object = freeObjects.remove(freeObjects.size()-1);
        }
        activeObjects.add(object);
        return object;
    }

    public void updateActiveSprites(float delta){
        for (int i = 0; i < activeObjects.size(); i++) {
            Sprite sprite = activeObjects.get(i);
            if(!sprite.isDestroyed){
                sprite.update(delta);
            }
        }
    }

    public void drawActiveSprites(SpriteBatch batch){
        for (int i = 0; i < activeObjects.size(); i++) {
            Sprite sprite = activeObjects.get(i);
            if(!sprite.isDestroyed){
                sprite.draw(batch);
            }
        }
    }

    public void freeAllDestroyedActiveSprites(float delta){
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if(!sprite.isDestroyed){
                free(sprite);
                i--;
                sprite.flushDestroy();
            }
        }
    }

    private void free(T object){
        if(activeObjects.remove(object)){
            freeObjects.add(object);
        }
    }

    public void dispose(){
        activeObjects.clear();
        freeObjects.clear();
    }

}
