package com.microlux.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	public static int SCREEN_HEIGHT;
	public static int SCREEN_WIDTH;
	SpriteBatch batch;

	GameStateManager gsm;
	GameInputProcessor inputProcessor;

	@Override
	public void create () {
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		inputProcessor = new GameInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render () {
		//  clear screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//  update
		gsm.update(Gdx.graphics.getDeltaTime());

		//  draw
		batch.begin();
		gsm.draw(batch);
		batch.end();
	}

	public void dispose() {
		batch.dispose();
	}
}
