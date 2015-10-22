package com.mikeduvall;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mikeduvall.convert.PaletteEntry;
import com.mikeduvall.convert.PaletteFile;
import redhorizon.filetypes.shp.ShpFileCNC;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFromFileAndDrawToScreen extends ApplicationAdapter {
	SpriteBatch batch;
	Pixmap minigunnerPixMap;
	Texture minigunnerTexture;
	int x = 100;
	int y = 100;
	int screenHeight;
	int screenWidth;

	@Override
	public void create () {
		batch = new SpriteBatch();


		String filename = "/home/mduvall/workspace/mike-and-conquer/core/assets/temperat.pal";
		FileHandle fileHandle = Gdx.files.internal(filename);
		InputStream is = fileHandle.read(1000);
		PaletteFile paletteFile = new PaletteFile(is);


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

//        minigunnerPixMap = new Pixmap(50, 39, Pixmap.Format.RGBA8888);
        minigunnerPixMap = new Pixmap(50, 39, Pixmap.Format.RGBA4444);
//        minigunnerPixMap = new Pixmap(50, 39, Pixmap.Format.RGB888);

		int currentIndex = 0;

		for(int y = 0; y < 39; y++) {
			for(int x = 0; x < 50; x++) {
                if(!byteBuffer0.hasRemaining()) {
                    continue;
                }

                System.out.println(currentIndex);
                byte nextByte = byteBuffer0.get(currentIndex);
				if(nextByte != 0) {
//                    public static final Color WHITE = new Color(1, 1, 1, 1);
//                    public static final Color BLACK = new Color(0, 0, 0, 1);
//                    public static final Color RED = new Color(1, 0, 0, 1);
//                    public static final Color GREEN = new Color(0, 1, 0, 1);

//                    Color color = new Color(0.33333f, 1, 1, 1);
//					Color color = new Color(1,1, 0.33333f,  1);
					int index = Byte.toUnsignedInt(nextByte);

					PaletteEntry paletteEntry = paletteFile.getPaletteEntries().get(index);

					float red = paletteEntry.getRed() / 63.0f;
					float green = paletteEntry.getGreen() / 63.0f;
					float blue = paletteEntry.getBlue() / 63.0f;

					Color color = new Color(red, green, blue, 1);

                    minigunnerPixMap.setColor(color);

                    minigunnerPixMap.drawPixel(x, y );

				}
				currentIndex++;
			}
		}
		minigunnerTexture = new Texture(minigunnerPixMap);
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
		batch.draw(minigunnerTexture, 600, 440);
		batch.end();


	}

	private void handleInput() {
		boolean leftMouseButtonPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		boolean ifEscapeKeyIsPressed = Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
		if(ifEscapeKeyIsPressed) {
			Gdx.app.exit();
		}


		if( leftMouseButtonPressed) {
			x = Gdx.input.getX() - (minigunnerTexture.getWidth() / 2);
			y = screenHeight - Gdx.input.getY() - (minigunnerTexture.getHeight() / 2);
		}
	}
}
