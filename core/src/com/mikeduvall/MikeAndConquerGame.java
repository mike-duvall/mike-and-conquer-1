package com.mikeduvall;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MikeAndConquerGame extends ApplicationAdapter {
	SpriteBatch batch;
	Pixmap redPixMap;
    Pixmap bluePixMap;
	Texture redCircleTexture;
	Texture blueCircleTexture;
	int x = 100;
	int y = 100;
	int screenHeight;
	int screenWidth;

	@Override
	public void create () {
		batch = new SpriteBatch();

        redPixMap = new Pixmap(24,24, Pixmap.Format.RGBA8888);
        redPixMap.setColor(Color.RED);
        redPixMap.fill();
		redCircleTexture = new Texture(redPixMap);

        bluePixMap = new Pixmap(24,24, Pixmap.Format.RGBA8888);
        bluePixMap.setColor(Color.BLUE);
        bluePixMap.fill();
		blueCircleTexture = new Texture(bluePixMap);
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
		boolean ifEscapeKeyIsPressed = Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
		if(ifEscapeKeyIsPressed) {
			Gdx.app.exit();
		}


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
