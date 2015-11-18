package com.microlux.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by MaxLin on 11/12/15.
 */
public class GameInputProcessor extends InputAdapter {
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
}
