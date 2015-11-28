package com.mikeduvall;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MikeAndConquerGame extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	GDIMinigunner gdiMinigunner;
	NodMinigunner nodMinigunner;
	Pointer pointer;
	int x = 100;
	int y = 100;
	int screenHeight;
	int screenWidth;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();

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

		spriteBatch.enableBlending();
		spriteBatch.begin();
		gdiMinigunner.draw(spriteBatch);
		nodMinigunner.draw(spriteBatch);
		spriteBatch.end();

//		Gdx.gl.glEnable(GL20.GL_BLEND);
//		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);


		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		gdiMinigunner.draw(shapeRenderer);
//        nodMinigunner.draw(shapeRenderer);
        pointer.draw(shapeRenderer);
		shapeRenderer.end();

//		Gdx.gl.glDisable(GL20.GL_BLEND);



	}

	private void handleInput() {
		if(pointerIsNearGDIMinigunner() ) {
			pointer.activateSelectionPointer();
		}
		else {
			pointer.activateBasePointer();
//			Gdx.input.setCursorImage(null,0,0);
		}

		boolean leftMouseButtonPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		boolean ifEscapeKeyIsPressed = Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
		if(ifEscapeKeyIsPressed) {
			Gdx.app.exit();
		}
	}

	private boolean pointerIsNearGDIMinigunner() {
		return gdiMinigunner.getMinigunnerSprite().getBoundingRectangle().contains(Gdx.input.getX(), Gdx.input.getY() - (32 *2 ));
	}


}
