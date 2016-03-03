package com.microlux.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.microlux.game.Game;

/**
 * Created by maxlin on 12/10/15.
 */
public class GameOverState implements GameState {
    Texture bg = new Texture(Gdx.files.internal("gameOverBackground.png"));
    @Override
    public void init() {

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(bg,0 ,0 , Game.WORLD_WIDTH, Game.WORLD_HEIGHT);
    }

    @Override
    public void dispose() {

    }
}
