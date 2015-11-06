package com.mikeduvall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
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

public class Minigunner {

    int x;
    int y;

    Pixmap minigunnerPixMap;
    Texture minigunnerTexture;


    public Minigunner() {

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

        minigunnerPixMap = new Pixmap(50, 39, Pixmap.Format.RGBA8888);

        int currentIndex = 0;

        for(int y = 0; y < 39; y++) {
            for(int x = 0; x < 50; x++) {
                if(!byteBuffer0.hasRemaining()) {
                    continue;
                }

                byte nextByte = byteBuffer0.get(currentIndex);
                if(nextByte != 0) {
                    int index = Byte.toUnsignedInt(nextByte);

                    index = mapColorIndex(index);
//                    System.out.println("index(decimal) = " + index);
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(minigunnerTexture, this.getX(), this.getY());
    }


    protected int mapColorIndex(int index) {
        return index;
    }
}
