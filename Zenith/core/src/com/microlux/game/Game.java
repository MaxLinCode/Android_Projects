package com.microlux.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {
	public static final int WORLD_WIDTH = 1920;
	public static final int WORLD_HEIGHT = 1080;
	public static float ASPECT_RATIO;
	SpriteBatch batch;

	GameStateManager gsm;
	GameInputProcessor inputProcessor;

	private Viewport viewport;
	private OrthographicCamera camera;

	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		ASPECT_RATIO = h/w;
		camera = new OrthographicCamera();
		camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);
		camera.update();

		viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

		batch = new SpriteBatch();
		gsm = new GameStateManager();
		inputProcessor = new GameInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render () {
		//  set camera
		camera.update();
		batch.setProjectionMatrix(camera.combined);

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

	public void resize(int width, int height) {
		viewport.update(width,height);
	}

	public void dispose() {
		batch.dispose();
	}
}
