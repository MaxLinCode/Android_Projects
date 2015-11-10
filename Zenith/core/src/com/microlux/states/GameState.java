package com.microlux.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by MaxLin on 11/6/15.
 */
public interface GameState {

    abstract void init();
    abstract void handleInput();
    abstract void update(float dt);
    abstract void draw(SpriteBatch batch);
    abstract void dispose();
}
