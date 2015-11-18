package com.mikeduvall;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MikeAndConquerGame extends ApplicationAdapter {
	SpriteBatch batch;
	GDIMinigunner gdiMinigunner;
	NodMinigunner nodMinigunner;
	Pointer pointer;
	int x = 100;
	int y = 100;
	int screenHeight;
	int screenWidth;

	@Override
	public void create () {
		batch = new SpriteBatch();

		gdiMinigunner = new GDIMinigunner();
		gdiMinigunner.setX(600);
		gdiMinigunner.setY(440);

		nodMinigunner = new NodMinigunner();
		nodMinigunner.setX(800);
		nodMinigunner.setY(440);

		pointer = new Pointer();
//		pointer.setX(600);
//		pointer.setY(440);


	}


	public static String hex(int n) {
		// call toUpperCase() if that's required
		return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		screenHeight = height;
		screenWidth = width;
	}

	@Override
	public void render () {
		handleInput();
		handleDrawing();
	}

	private void handleDrawing() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.enableBlending();
		batch.begin();
		gdiMinigunner.draw(batch);
		nodMinigunner.draw(batch);
		batch.end();


	}

	private void handleInput() {
		if(Gdx.input.getX() > 200 ) {
			pointer.activateBasePointer();
		}
		else {
			pointer.activateSelectionPointer();
		}

		boolean leftMouseButtonPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		boolean ifEscapeKeyIsPressed = Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
		if(ifEscapeKeyIsPressed) {
			Gdx.app.exit();
		}
	}
}
