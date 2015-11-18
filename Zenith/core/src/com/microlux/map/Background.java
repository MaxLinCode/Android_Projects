package com.microlux.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public void draw(SpriteBatch batch) {
        batch.draw(image, x, y, width * 2, height * 2);
    }
}
