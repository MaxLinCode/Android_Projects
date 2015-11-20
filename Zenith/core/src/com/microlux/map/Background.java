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
    Sprite mapSprite;


    public Background(Texture tex) {
        image = tex;
        width = image.getWidth();
        height = image.getHeight();
        mapSprite = new Sprite(image, 0, 0, width, height);
    }

    public void translate(float movex, float movey) {
        mapSprite.setX(mapSprite.getX() + movex);
        mapSprite.setY(mapSprite.getY() + movey);
    }

    public void update(float dt) {

    }

    public void swapBackgroundImage(Texture tex) {
        image = tex;
    }


    public void draw(SpriteBatch batch) {
        mapSprite.draw(batch);
    }
}
