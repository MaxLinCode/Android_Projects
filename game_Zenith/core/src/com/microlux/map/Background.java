package com.microlux.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.microlux.game.Game;

/**
 * Created by Max Lin on 11/18/2015.
 */
public class Background {
    float x, y;
    int width, height;
    Texture image;


    public Background(Texture tex) {
        x = 0;
        y = 0;
        image = tex;
        width = image.getWidth();
        height = image.getHeight();
    }

    public void translate(float movex, float movey) {
        x += movex;
        y += movey;
    }

    public void update(float dt) {

    }

    public void swapBackgroundImage(Texture tex) {
        image = tex;
    }


    public void draw(SpriteBatch batch) {
        batch.draw(image, x, y, width, height);
    }
}
