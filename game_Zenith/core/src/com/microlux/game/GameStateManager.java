package com.microlux.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.microlux.states.GameOverState;
import com.microlux.states.GameState;
import com.microlux.states.PlayState;

/**
 * Created by MaxLin on 11/6/15.
 */
public class GameStateManager {
    public static final int TYPE_MENUSTATE = 0;
    public static final int TYPE_PLAYSTATE = 1;
    public static final int TYPE_GAMEOVERSTATE = 2;

    GameState currentState;

    public GameStateManager() {
        currentState = new PlayState();
    }

    public void update(float dt) {
        currentState.handleInput();
        currentState.update(dt);
    }

    public void draw(SpriteBatch batch) {
        currentState.draw(batch);
    }

    public void selectState(int stateType) {
        currentState.dispose();
        switch (stateType) {
            case TYPE_MENUSTATE:
                //currentState = new MenuState();
                break;
            case TYPE_PLAYSTATE:
                currentState = new PlayState();
                break;
            case TYPE_GAMEOVERSTATE:
                currentState = new GameOverState();
                break;
        }

    }
}
