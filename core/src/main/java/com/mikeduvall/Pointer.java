package com.mikeduvall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.mikeduvall.convert.PaletteEntry;
import com.mikeduvall.convert.PaletteFile;
import redhorizon.filetypes.shp.ShpFileDune2;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Pointer {

    Pixmap basicPointerPixmap;
    Pixmap selectionPointerPixmap;


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
        ShpFileDune2 shpFileCNC = new ShpFileDune2("dummy", inChannel);

        ByteBuffer[] byteBuffers = shpFileCNC.getRawImagesData();
        ByteBuffer byteBuffer12 = byteBuffers[12];
        ByteBuffer byteBuffer0 = byteBuffers[0];

        int width = shpFileCNC.width();
        int height = shpFileCNC.height();

        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        drawByteBufferOnPixMap(pixmap, paletteFile, byteBuffer0, width, height);
        basicPointerPixmap = createScaledPixMap(pixmap, 4);

        pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        drawByteBufferOnPixMap(pixmap, paletteFile, byteBuffer12, width, height);
        selectionPointerPixmap = createScaledPixMap(pixmap, 4);


        Gdx.input.setCursorImage(basicPointerPixmap,0,0);
//        Gdx.input.setCursorImage(selectionPointerPixmap,0,0);
        Gdx.input.setCursorPosition(200,200);
        pixmap.dispose();
    }

    private Pixmap createScaledPixMap(Pixmap basePixmap, int scale) {
        int newWidth = basePixmap.getWidth() * scale;
        int newHeight = basePixmap.getHeight() * scale;
        Pixmap scaledPixmap = new Pixmap(newWidth, newHeight, basePixmap.getFormat());
        scaledPixmap.drawPixmap(basePixmap,0,0,basePixmap.getWidth(),basePixmap.getHeight(),0,0,scaledPixmap.getWidth(), scaledPixmap.getHeight());
        return scaledPixmap;
    }

    private void drawByteBufferOnPixMap(Pixmap pixmap, PaletteFile paletteFile, ByteBuffer byteBuffer0, int width, int height) {

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
