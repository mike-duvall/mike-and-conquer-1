package com.mikeduvall.convert;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class ShpFileToSpritePngConverter {

    public ConversionResult convertShpFileToSingleMiniGunnerPngFile(String pathAndFileName) {
        byte[] bytes = readBytesFromFile(pathAndFileName);
        ConversionResult conversionResult = new ConversionResult();
        conversionResult.setNumberOfImages(getNumberOfImages(bytes));
        conversionResult.setImageWidth(readImageWidth(bytes));
        conversionResult.setImageHeight(readImageHeight(bytes));
        return conversionResult;
    }


    private int readTwoBytesAsInt(int byteOffset1, int byteOffset2, byte[] bytes) {
        byte byte0 = bytes[byteOffset1];
        byte byte1 = bytes[byteOffset2];
        return (byte1 * 256) + byte0;
    }

    private int readImageWidth(byte[] bytes) {
        return readTwoBytesAsInt(6,7,bytes);
    }

    private int readImageHeight(byte[] bytes) {
        return readTwoBytesAsInt(8,9,bytes);
    }

    private int getNumberOfImages(byte[] bytes) {
        return readTwoBytesAsInt(0,1,bytes);
    }

    private byte[] readBytesFromFile(String fileName) {
        FileHandle fileHandle = Gdx.files.internal(fileName);
        InputStream is = fileHandle.read(1000);
        return readBytesFromInputStream(is);
    }

    private byte[] readBytesFromInputStream(InputStream is) {
        try {
            return IOUtils.toByteArray(is);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
