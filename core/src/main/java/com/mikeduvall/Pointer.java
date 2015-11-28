package com.mikeduvall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mikeduvall.convert.PaletteEntry;
import com.mikeduvall.convert.PaletteFile;
import redhorizon.filetypes.shp.ShpFileDune2;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Pointer extends GameObject {

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
        ShpFileDune2 shpFileDune2 = new ShpFileDune2("dummy", inChannel);

        ByteBuffer[] byteBuffers = shpFileDune2.getRawImagesData();
        ByteBuffer byteBuffer12 = byteBuffers[12];
        ByteBuffer byteBuffer0 = byteBuffers[0];

        int width = shpFileDune2.width();
        int height = shpFileDune2.height();

        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        drawByteBufferOnPixMap(pixmap, paletteFile, byteBuffer0, width, height);
        basicPointerPixmap = createScaledPixMap(pixmap, 4);
        pixmap.dispose();

        pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        drawByteBufferOnPixMap(pixmap, paletteFile, byteBuffer12, width, height);
        selectionPointerPixmap = createScaledPixMap(pixmap, 4);
        pixmap.dispose();

//        Gdx.input.setCu
//        activateBasePointer();
        Gdx.input.setCursorPosition(200, 200);
    }

    public void activateBasePointer() {
        Gdx.input.setCursorImage(basicPointerPixmap,0,0);
    }

    public void activateSelectionPointer() {
//        Gdx.input.setCursorImage(selectionPointerPixmap,16 * 4, 16 * 4);
//        Gdx.input.setCursorImage(selectionPointerPixmap,0,0);
        Gdx.input.setCursorImage(selectionPointerPixmap,15 * 4, 12 * 4);
    }


    private Pixmap createScaledPixMap(Pixmap basePixmap, int scale) {
        int newWidth = basePixmap.getWidth() * scale;
        int newHeight = basePixmap.getHeight() * scale;
        Pixmap scaledPixmap = new Pixmap(newWidth, newHeight, basePixmap.getFormat());
        scaledPixmap.drawPixmap(basePixmap,0,0,basePixmap.getWidth(),basePixmap.getHeight(),0,0,scaledPixmap.getWidth(), scaledPixmap.getHeight());
        return scaledPixmap;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 1, 1, 1);
//        Rectangle boundingRectangle = this.
//        shapeRenderer.rect(10,10, 50, 50);
        int screenHeight = Gdx.graphics.getHeight();
//        int screenWidth = Gdx.graphics.getWidth();
        float collisionBoxMultiplier = 16;
        shapeRenderer.rect(
                Gdx.input.getX() - (collisionBoxMultiplier *2),
                screenHeight - Gdx.input.getY() - (collisionBoxMultiplier*2),
                collisionBoxMultiplier * 4,
                collisionBoxMultiplier * 4 );
    }


}
