package com.mikeduvall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
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
        float originX = minigunnerSprite.getOriginX();
        float originY = minigunnerSprite.getOriginY();
        Rectangle boundingBox = minigunnerSprite.getBoundingRectangle();

        minigunnerSprite.setScale(4.0f,4.0f);

        float scaledOriginX = minigunnerSprite.getOriginX();
        float scaledOriginY = minigunnerSprite.getOriginY();
        Rectangle scaledBoundingBox = minigunnerSprite.getBoundingRectangle();
        int x = 3;


        minigunnerSprite.setOrigin(minigunnerSprite.getOriginX(), minigunnerSprite.getOriginY() + 2);
//        Rectangle boundingBox = minigunnerSprite.getBoundingRectangle();
//        boundingBox.setX(boundingBox.getX() - 5);
//        int boxSize = 20;
//        float boundsLeft = minigunnerSprite.getOriginX() - boxSize;
//        float boundsRight = minigunnerSprite.getOriginX() + boxSize;
//        float boundsTop = minigunnerSprite.getOriginY() + boxSize;
//        float boundsBottom = minigunnerSprite.getOriginY() - boxSize;
//
//
//        minigunnerSprite.setBounds(boundsLeft, boundsRight, boundsTop, boundsBottom);

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
//        minigunnerSprite.setScale(4.0f,4.0f);
        minigunnerSprite.draw(batch);
    }


    public Sprite getMinigunnerSprite() {
        return minigunnerSprite;
    }

    public void draw(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(1, 1, 1, 1);

        float originalScaleX = minigunnerSprite.getScaleX();
        float originalScaleY = minigunnerSprite.getScaleY();
        minigunnerSprite.setScale(2.0f, 2.0f);
        Rectangle boundingRectangle = this.minigunnerSprite.getBoundingRectangle();
        float bx = boundingRectangle.getX();
        float by = boundingRectangle.getY();
        float bw = boundingRectangle.getWidth();
        float bh = boundingRectangle.getHeight();
        shapeRenderer.rect(boundingRectangle.getX(), boundingRectangle.getY(), boundingRectangle.getWidth(), boundingRectangle.getHeight());
        minigunnerSprite.setScale(originalScaleX,originalScaleY);


        float originX = minigunnerSprite.getOriginX();
        float originY = minigunnerSprite.getOriginY();
        shapeRenderer.circle(originX + minigunnerSprite.getX(), originY + minigunnerSprite.getY(), 5);
    //        shapeRenderer.line(bx, by, bx + bw, by + bh);
    //        shapeRenderer.line(bx, by + bh, bx + bw, by);

    }

}
