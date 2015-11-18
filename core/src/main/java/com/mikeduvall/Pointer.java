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
        ShpFileDune2 shpFileCNC = new ShpFileDune2("dummy", inChannel);

        ByteBuffer[] byteBuffers = shpFileCNC.getRawImagesData();
        ByteBuffer byteBuffer12 = byteBuffers[12];
        ByteBuffer byteBuffer0 = byteBuffers[0];

        int width = shpFileCNC.width();
        int height = shpFileCNC.height();

        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        drawByteBufferOnPixMap(pixmap, paletteFile, byteBuffer0, width, height);
        basicPointerPixmap = createScaledPixMap(pixmap, 4);
        pixmap.dispose();

        pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        drawByteBufferOnPixMap(pixmap, paletteFile, byteBuffer12, width, height);
        selectionPointerPixmap = createScaledPixMap(pixmap, 4);
        pixmap.dispose();

        activateBasePointer();
        Gdx.input.setCursorPosition(200, 200);
    }

    public void activateBasePointer() {
        Gdx.input.setCursorImage(basicPointerPixmap,0,0);
    }

    public void activateSelectionPointer() {
        Gdx.input.setCursorImage(selectionPointerPixmap,0,0);
    }


    private Pixmap createScaledPixMap(Pixmap basePixmap, int scale) {
        int newWidth = basePixmap.getWidth() * scale;
        int newHeight = basePixmap.getHeight() * scale;
        Pixmap scaledPixmap = new Pixmap(newWidth, newHeight, basePixmap.getFormat());
        scaledPixmap.drawPixmap(basePixmap,0,0,basePixmap.getWidth(),basePixmap.getHeight(),0,0,scaledPixmap.getWidth(), scaledPixmap.getHeight());
        return scaledPixmap;
    }

}
