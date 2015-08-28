package com.mikeduvall;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MikeAndConquerGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture redCircleTexture;
	Texture blueCircleTexture;
	int x = 100;
	int y = 100;
	int screenHeight;
	int screenWidth;

	@Override
	public void create () {
		batch = new SpriteBatch();
		redCircleTexture = new Texture("red-circle.png");
		blueCircleTexture = new Texture("blue-circle.png");
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		screenHeight = height;
		screenWidth = width;
	}

	@Override
	public void render () {
		boolean leftMouseButtonPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);

		if( leftMouseButtonPressed) {
			x = Gdx.input.getX() - (redCircleTexture.getWidth() / 2);
			y = screenHeight - Gdx.input.getY() - (redCircleTexture.getHeight() / 2);
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.enableBlending();
		batch.begin();
		batch.draw(redCircleTexture, 0, 0);
		batch.draw(redCircleTexture, x, y);
		batch.draw(blueCircleTexture, 600, 440);
		batch.end();
	}
}
