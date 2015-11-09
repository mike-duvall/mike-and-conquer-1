package com.mikeduvall;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import redhorizon.filetypes.shp.ShpFileCNC;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GraphicsDemo implements ApplicationListener {
    private SpriteBatch batch;
    private Pixmap pixmap;
    private Texture texture;
    private Sprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // A Pixmap is basically a raw image in memory as repesented by pixels
        // We create one 256 wide, 128 height using 8 bytes for Red, Green, Blue and Alpha channels
        pixmap = new Pixmap(256,128, Pixmap.Format.RGBA8888);

        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("core/assets/e1.shp", "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FileChannel inChannel = aFile.getChannel();
        ShpFileCNC shpFileCNC = new ShpFileCNC("core/assets/test.file", inChannel);
        ByteBuffer[] byteBuffers = shpFileCNC.getRawImagesData();
        ByteBuffer byteBuffer0 = byteBuffers[0];


        //Fill it red
        pixmap.setColor(Color.GREEN);
//        for(int x = 5; x < 10; x++) {
//            for(int y = 0; y < 20; y++) {
//                pixmap.drawPixel(x,y);
//            }
//        }

//
//        //Draw two lines forming an X
//        pixmap.setColor(Color.BLACK);
//        pixmap.drawLine(0, 0, pixmap.getWidth()-1, pixmap.getHeight()-1);
//        pixmap.drawLine(0, pixmap.getHeight()-1, pixmap.getWidth()-1, 0);
//
//        //Draw a circle about the middle
//        pixmap.setColor(Color.RED);
//        pixmap.drawCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, pixmap.getHeight()/2 - 1);
        int currentIndex = 0;

        for(int y = 0; y < 39; y++) {
            for(int x = 0; x < 50; x++) {
                if(!byteBuffer0.hasRemaining()) {
                    continue;
                }

                System.out.println(currentIndex);
                byte nextByte = byteBuffer0.get(currentIndex);
                if(nextByte != 0) {
//					minigunnerPixMap.drawPixel(x, y, Color.rgb888(0, 66, 0));
                    int r = 255;
                    int g = 255;
                    int b = 255;
                    int alpha = 255;

//                    public static final Color WHITE = new Color(1, 1, 1, 1);
//                    public static final Color BLACK = new Color(0, 0, 0, 1);
//                    public static final Color RED = new Color(1, 0, 0, 1);
//                    public static final Color GREEN = new Color(0, 1, 0, 1);
                    Color MIKEGREEN = new Color(0, 0.9f, 0, 1);

//                    int color = Color.argb8888(alpha, r, g, b);
                    int color1 = Color.GREEN.toIntBits();
                    pixmap.setColor(Color.GREEN);
//                    pixmap.drawPixel(x, y, color1);
                    pixmap.drawPixel(x, y );

//                    int color2 = Color.GREEN.toIntBits();
//                    pixmap.drawPixel(x + 10, y, color2);

                }
                currentIndex++;
            }
        }



        texture = new Texture(pixmap);

        //It's the textures responsibility now... get rid of the pixmap
        pixmap.dispose();

        sprite = new Sprite(texture);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.setPosition(0, 0);
        sprite.draw(batch);
        sprite.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}