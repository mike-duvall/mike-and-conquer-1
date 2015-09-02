package com.mikeduvall.convert;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class ShpFileToSpritePngConverter {

    public ConversionResult convertShpFileToSingleMiniGunnerPngFile(String pathAndFileName) {
        FileHandle fileHandle = Gdx.files.internal(pathAndFileName);
        InputStream is = fileHandle.read(1000);
        byte[] bytes = null;
        try {
            bytes = IOUtils.toByteArray(is);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte byte0 = bytes[0];
        byte byte1 = bytes[1];

        int numberOfImages = (byte1 * 256) + byte0;

        ConversionResult conversionResult = new ConversionResult();
        conversionResult.setNumberOfImages(numberOfImages);
        return conversionResult;
    }


}
