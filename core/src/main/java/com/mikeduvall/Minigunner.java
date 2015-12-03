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
//        minigunnerPixMap = reducify5(minigunnerPixMap);
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


//        minigunnerSprite.setOrigin(minigunnerSprite.getOriginX(), minigunnerSprite.getOriginY() + 2);

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

    private Pixmap reducify1(Pixmap minigunnerPixMap) {
        Pixmap newPixmap = new Pixmap(30, minigunnerPixMap.getHeight(), minigunnerPixMap.getFormat());
        int newX = 0;
        int newY = 0;
        int srcX = 10;
        int srcY = 0;
        int srcWidth = 30;
        int srcHeigth = minigunnerPixMap.getHeight();
        newPixmap.drawPixmap(minigunnerPixMap, 0,0, srcX, srcY, srcWidth, srcHeigth );
        return newPixmap;
    }

    private Pixmap reducify2(Pixmap minigunnerPixMap) {
        int newX = 0;
        int newY = 0;
        int srcX = 15;
        int srcY = 0;
        int srcWidth = minigunnerPixMap.getWidth() - (2 * srcX);
        int srcHeigth = minigunnerPixMap.getHeight();
        Pixmap newPixmap = new Pixmap(srcWidth, minigunnerPixMap.getHeight(), minigunnerPixMap.getFormat());
        newPixmap.drawPixmap(minigunnerPixMap, 0,0, srcX, srcY, srcWidth, srcHeigth );
        return newPixmap;
    }

    private Pixmap reducify2a(Pixmap minigunnerPixMap) {
        int newX = 0;
        int newY = 0;
        int srcX = 17;
        int srcY = 0;
        int srcWidth = minigunnerPixMap.getWidth() - (2 * srcX);
        int srcHeight = minigunnerPixMap.getHeight() - (2 * srcY);
        Pixmap newPixmap = new Pixmap(srcWidth, srcHeight, minigunnerPixMap.getFormat());
        newPixmap.drawPixmap(minigunnerPixMap, 0,0, srcX, srcY, srcWidth, srcHeight );
        return newPixmap;
    }

    private Pixmap reducify5(Pixmap minigunnerPixMap) {
        int newX = 0;
        int newY = 0;
        int srcX = 17;
        int srcY = 9;
        int srcWidth = minigunnerPixMap.getWidth() - (2 * srcX);
        int srcHeight = minigunnerPixMap.getHeight() - (2 * srcY);
        Pixmap newPixmap = new Pixmap(srcWidth, srcHeight, minigunnerPixMap.getFormat());
        newPixmap.drawPixmap(minigunnerPixMap, newX, newY, srcX, srcY, srcWidth, srcHeight );
        return newPixmap;
    }



    private Pixmap reducify3(Pixmap minigunnerPixMap) {
        int newX = 0;
        int newY = 0;
        int srcX = 20;
        int srcY = 0;
        int srcWidth = 10;
        int srcHeight = minigunnerPixMap.getHeight();
        Pixmap newPixmap = new Pixmap(srcWidth, srcHeight, minigunnerPixMap.getFormat());
        newPixmap.drawPixmap(minigunnerPixMap, 0,0, srcX, srcY, srcWidth, srcHeight );
        return newPixmap;
    }


    private Pixmap reducify4(Pixmap minigunnerPixMap) {
        int newX = 0;
        int newY = 0;
        int srcX = 20;
        int srcY = 10;
        int srcWidth = 10;
        int srcHeight = 39 - 10 - 10;
        Pixmap newPixmap = new Pixmap(srcWidth, srcHeight, minigunnerPixMap.getFormat());
        newPixmap.drawPixmap(minigunnerPixMap, 0,0, srcX, srcY, srcWidth, srcHeight );
        return newPixmap;
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

//        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.setColor(1,0,0,1);

        float originalScaleX = minigunnerSprite.getScaleX();
        float originalScaleY = minigunnerSprite.getScaleY();
        minigunnerSprite.setScale(4.0f, 4.0f);
        Rectangle boundingRectangle = this.minigunnerSprite.getBoundingRectangle();
        float bx = boundingRectangle.getX();
        float by = boundingRectangle.getY();
        float bw = boundingRectangle.getWidth();
        float bh = boundingRectangle.getHeight();
        shapeRenderer.rect(boundingRectangle.getX(), boundingRectangle.getY(), boundingRectangle.getWidth(), boundingRectangle.getHeight());
//        minigunnerSprite.setScale(originalScaleX,originalScaleY);


        float originX = minigunnerSprite.getOriginX();
        float originY = minigunnerSprite.getOriginY();
        float absoluteOriginX = minigunnerSprite.getX() + originX;
        float absoluteOriginY = minigunnerSprite.getY() + originY;
        float x = minigunnerSprite.getX();
        float y = minigunnerSprite.getY();
//        shapeRenderer.line(originX - 10, originY, originX + 10, originY);
        shapeRenderer.point(minigunnerSprite.getX() + originX, minigunnerSprite.getY() + originY, 0.0f);
//        shapeRenderer.circle(originX + minigunnerSprite.getX(), originY + minigunnerSprite.getY(), 1);
//            shapeRenderer.line(bx, by, bx + bw, by + bh);
//            shapeRenderer.line(bx, by + bh, bx + bw, by);

    }

    public void drawOrigin(ShapeRenderer shapeRenderer) {

        shapeRenderer.setColor(1,0,0,1);
        float originX = minigunnerSprite.getOriginX();
        float originY = minigunnerSprite.getOriginY();

        shapeRenderer.point(originX,originY, 1);

    }


}
