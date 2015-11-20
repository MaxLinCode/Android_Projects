package com.microlux.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.microlux.game.Game;

/**
 * Created by MaxLin on 11/18/15.
 */
public class Player {
    Sprite zenith;
    float x,y;
    int width,height;
    Texture sun = new Texture(Gdx.files.internal("sunSprite.png"));
    Texture moon = new Texture(Gdx.files.internal("moonSprite.png"));
    boolean isSun = true;

    public Player() {
        zenith = new Sprite(sun, 0, 0, 32, 32);
    }

    public void update(float dt) {
        x = zenith.getX();
        y = zenith.getY();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(sun, 250, 250);
    }




}
