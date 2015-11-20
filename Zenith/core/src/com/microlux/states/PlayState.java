package com.microlux.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.microlux.entities.PikachuAnimation;
import com.microlux.entities.Player;
import com.microlux.game.Game;
import com.microlux.map.Background;

import java.io.File;

/**
 * Created by MaxLin on 11/6/15.
 */
public class PlayState implements GameState {
    Background bg;

    PikachuAnimation pikachu;
    Player player;

    public PlayState() {
        bg = new Background(new Texture(Gdx.files.internal("bgSunAndMoon.png")));
        player = new Player();
    }

    @Override
    public void init() {

    }

    @Override
    public void handleInput() {
        // remember that input is top corner (0,0)
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getY() > Gdx.graphics.getHeight() / 2) {
                bg.translate(0 , 10);
            }

            if (Gdx.input.getY() <= Gdx.graphics.getHeight() / 2) {
                bg.translate(0 , -10);
            }
        }

    }

    @Override
    public void update(float dt)
    {
        player.update(dt);
    }

    @Override
    public void draw(SpriteBatch batch) {
        bg.draw(batch);
        player.draw(batch);
    }

    @Override
    public void dispose() {

    }
}
