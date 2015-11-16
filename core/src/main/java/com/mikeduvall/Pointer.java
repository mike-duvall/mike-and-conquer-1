package com.mikeduvall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mikeduvall.convert.PaletteEntry;
import com.mikeduvall.convert.PaletteFile;
import redhorizon.filetypes.shp.ShpFileCNC;
import redhorizon.filetypes.shp.ShpFileDune2;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Pointer {

    Pixmap pixmap;
//    Texture texture;
//    Sprite sprite;


    public Pointer() {

        String filename = "temperat.pal";
        FileHandle fileHandle = Gdx.files.internal(filename);
        InputStream is = fileHandle.read(1000);
        PaletteFile paletteFile = new PaletteFile(is);


        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("mouse.shp", "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FileChannel inChannel = aFile.getChannel();
//        ShpFileCNC shpFileCNC = new ShpFileCNC("dummy", inChannel);
        ShpFileDune2 shpFileCNC = new ShpFileDune2("dummy", inChannel);

        ByteBuffer[] byteBuffers = shpFileCNC.getRawImagesData();
//        ByteBuffer byteBuffer0 = byteBuffers[12];
        ByteBuffer byteBuffer0 = byteBuffers[0];

        int width = shpFileCNC.width();
        int height = shpFileCNC.height();

        pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);

        int currentIndex = 0;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(!byteBuffer0.hasRemaining()) {
                    continue;
                }

                byte nextByte = byteBuffer0.get(currentIndex);
                if(nextByte != 0) {
                    int index = Byte.toUnsignedInt(nextByte);

                    index = mapColorIndex(index);
                    PaletteEntry paletteEntry = paletteFile.getPaletteEntries().get(index);

                    float red = paletteEntry.getRed() / 63.0f;
                    float green = paletteEntry.getGreen() / 63.0f;
                    float blue = paletteEntry.getBlue() / 63.0f;

                    Color color = new Color(red, green, blue, 1);

                    pixmap.setColor(color);

                    pixmap.drawPixel(x, y );

                }
                currentIndex++;
            }
        }
//        texture = new Texture(pixmap);
//        sprite = new Sprite(texture);

        Pixmap scaledPixmap = new Pixmap(128, 128, Pixmap.Format.RGBA8888);
        scaledPixmap.drawPixmap(pixmap,0,0,32,32,0,0,128,128);
        Gdx.input.setCursorImage(scaledPixmap,0,0);
        Gdx.input.setCursorPosition(200,200);
        pixmap.dispose();
        scaledPixmap.dispose();


    }

//    public float getX() {
//        return sprite.getX();
//    }
//
//    public void setX(float x) {
//        sprite.setX(x);
//    }
//
//    public float getY() {
//        return sprite.getY();
//    }
//
//    public void setY(float y) {
//        sprite.setY(y);
//    }
//
//    public void draw(SpriteBatch batch) {
//        sprite.setScale(4.0f,4.0f);
//        sprite.draw(batch);
//    }


    protected int mapColorIndex(int index) {
        return index;
    }
}
