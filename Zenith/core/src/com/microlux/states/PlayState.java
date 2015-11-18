package com.microlux.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.microlux.entities.PikachuAnimation;
import com.microlux.game.Game;
import com.microlux.map.Background;

/**
 * Created by MaxLin on 11/6/15.
 */
public class PlayState implements GameState {
    Background bg;

    PikachuAnimation pikachu;

    public PlayState() {
        bg = new Background(new Texture(Gdx.files.internal("background.png")));
        pikachu = new PikachuAnimation();
    }

    @Override
    public void init() {

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() < Game.SCREEN_WIDTH / 2) {
                //  move left
                //  move bg right
                bg.translate(30, 0);
            }

            if (Gdx.input.getX() >= Game.SCREEN_WIDTH / 2) {
                //  move right
                //  move bg left
                bg.translate(-30, 0);
            }
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        bg.draw(batch);
        pikachu.draw(batch);
    }

    @Override
    public void dispose() {

    }
}
