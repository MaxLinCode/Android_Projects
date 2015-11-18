package com.microlux.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by MaxLin on 11/12/15.
 */
public class PikachuAnimation {
    private TextureAtlas atlas;
    private Array<TextureAtlas.AtlasRegion> pikachuRun;
    private Animation pikachuRunAnim;
    private TextureRegion currentFrame;
    private int width,height;
    private float stateTime = 0;

    private float x,y;

    public PikachuAnimation()
    {
        x = 0;
        y = 0;
        atlas = new TextureAtlas(Gdx.files.internal("pikachuRun.atlas"));
        pikachuRun = atlas.getRegions();
        pikachuRunAnim = new Animation(0.15f, pikachuRun);
        pikachuRunAnim.setPlayMode(Animation.PlayMode.LOOP);
        currentFrame = pikachuRunAnim.getKeyFrame(stateTime);
        width = currentFrame.getRegionWidth();
        height = currentFrame.getRegionHeight();
    }

    public void update(float dt) {
        stateTime += dt;
        currentFrame = pikachuRunAnim.getKeyFrame(stateTime);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, x, y, currentFrame.getRegionWidth() * 4, currentFrame.getRegionHeight() * 4);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
