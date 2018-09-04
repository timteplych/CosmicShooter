package ru.ttv.cosmicshooter.screen.gamescreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HealthPointsBar {
    private HealthPoint[] healthPoints;
    private Texture hpTexture;
    private int startHpLevel;

    public HealthPointsBar(int startHpLevel) {
        this.startHpLevel = startHpLevel;
        hpTexture = new Texture("textures/heart.png");
        healthPoints = new HealthPoint[5];
        float leftMargin = -0.18f;
        for (int i = 0; i < 5 ; i++) {
            leftMargin += 0.05;
            healthPoints[i] = new HealthPoint(new TextureRegion(hpTexture),0.4f,leftMargin);
        }
    }

    public void draw(SpriteBatch batch, int hp){
        int oneHeartSize = startHpLevel/healthPoints.length;

        for (int i = 0; i < healthPoints.length; i++) {
            if(hp<=0) break;
            if((hp+oneHeartSize)>=oneHeartSize){
                healthPoints[i].draw(batch);
                hp -= oneHeartSize;
            }

        }
    }
}
