package com.mikeduvall;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import redhorizon.filetypes.shp.ShpFileCNC;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFromFileAndDrawToScreen extends ApplicationAdapter {
	SpriteBatch batch;
	Pixmap redPixMap;
	Texture redCircleTexture;
	int x = 100;
	int y = 100;
	int screenHeight;
	int screenWidth;

	@Override
	public void create () {
		batch = new SpriteBatch();


		RandomAccessFile aFile = null;
		try {
			aFile = new RandomAccessFile("/home/mduvall/workspace/mike-and-conquer/core/assets/e1.shp", "rw");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		FileChannel inChannel = aFile.getChannel();
		ShpFileCNC shpFileCNC = new ShpFileCNC("/home/mduvall/workspace/mike-and-conquer/core/assets/test.file", inChannel);
		ByteBuffer[] byteBuffers = shpFileCNC.getRawImagesData();
		ByteBuffer byteBuffer0 = byteBuffers[0];


        redPixMap = new Pixmap(50, 39, Pixmap.Format.RGBA8888);
		int currentIndex = 0;

		for(int y = 0; y < 39; y++) {
			for(int x = 0; x < 50; x++) {
//                if(currentIndex > 1948) {
//                    continue;
//                }
                if(!byteBuffer0.hasRemaining()) {
                    continue;
                }

                System.out.println(currentIndex);
//				char nextChar = byteBuffer0.getChar(currentIndex);
                byte nextByte = byteBuffer0.get(currentIndex);
				if(nextByte != 0) {
					redPixMap.drawPixel(x, y, Color.rgb888(100, 100, 100));
				}

				currentIndex++;
			}
		}

		redCircleTexture = new Texture(redPixMap);

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
		batch.draw(redCircleTexture, 600, 440);
		batch.end();
	}

	private void handleInput() {
		boolean leftMouseButtonPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		boolean ifEscapeKeyIsPressed = Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
		if(ifEscapeKeyIsPressed) {
			Gdx.app.exit();
		}


		if( leftMouseButtonPressed) {
			x = Gdx.input.getX() - (redCircleTexture.getWidth() / 2);
			y = screenHeight - Gdx.input.getY() - (redCircleTexture.getHeight() / 2);
		}
	}
}
