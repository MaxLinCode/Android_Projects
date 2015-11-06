package com.microlux.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	
	Animation runAnim;
	TextureRegion[] runFrames;
	TextureRegion currentFrame;
	float stateTime = 0;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
	
		Texture runSheet = new Texture(Gdx.files.internal("runningSheet.png"));
		TextureRegion[][] tempFrames = TextureRegion.split(runSheet, runSheet.getWidth() / 6, runSheet.getHeight() / 5);
		runFrames = new TextureRegion[5 * 6];
		
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				runFrames[index++] = tempFrames[i][j];
			}
		}
		tempFrames = null;
		
		runAnim = new Animation(1f/30f, runFrames);
		runAnim.setPlayMode(PlayMode.LOOP);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		batch.begin();
		currentFrame = runAnim.getKeyFrame(stateTime);
		batch.draw(currentFrame, 400, 400, currentFrame.getRegionWidth() * 4, currentFrame.getRegionHeight() * 4);
		batch.end();
	}
}
