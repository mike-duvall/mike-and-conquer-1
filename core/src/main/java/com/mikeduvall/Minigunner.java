package com.mikeduvall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mikeduvall.convert.PaletteFile;
import redhorizon.filetypes.shp.ShpFileCNC;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Minigunner extends GameObject {

    Pixmap minigunnerPixMap;
    Texture minigunnerTexture;
    Sprite minigunnerSprite;


    public Minigunner() {

        String filename = "temperat.pal";
        FileHandle fileHandle = Gdx.files.internal(filename);
        InputStream is = fileHandle.read(1000);
        PaletteFile paletteFile = new PaletteFile(is);


        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("e1.shp", "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FileChannel inChannel = aFile.getChannel();
        ShpFileCNC shpFileCNC = new ShpFileCNC("dummyfilename", inChannel);
        ByteBuffer[] byteBuffers = shpFileCNC.getRawImagesData();
        ByteBuffer byteBuffer0 = byteBuffers[0];

        minigunnerPixMap = new Pixmap(50, 39, Pixmap.Format.RGBA8888);

        drawByteBufferOnPixMap(minigunnerPixMap, paletteFile, byteBuffer0, 50, 39 );
        minigunnerTexture = new Texture(minigunnerPixMap);
        minigunnerSprite = new Sprite(minigunnerTexture);

    }

    public float getX() {
        return minigunnerSprite.getX();
    }

    public void setX(float x) {
        minigunnerSprite.setX(x);
    }

    public float getY() {
        return minigunnerSprite.getY();
    }

    public void setY(float y) {
        minigunnerSprite.setY(y);
    }

    public void draw(SpriteBatch batch) {
        minigunnerSprite.setScale(4.0f,4.0f);
        minigunnerSprite.draw(batch);
    }


    public Sprite getMinigunnerSprite() {
        return minigunnerSprite;
    }
}
