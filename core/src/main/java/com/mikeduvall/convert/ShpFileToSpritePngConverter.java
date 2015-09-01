package com.mikeduvall.convert;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class ShpFileToSpritePngConverter {

    public ConversionResult convertShpFileToSingleMiniGunnerPngFile(String pathAndFileName) {
        FileHandle fileHandle = Gdx.files.internal(pathAndFileName);
        ConversionResult conversionResult = new ConversionResult();
        conversionResult.setNumberOfImages(532);
        return conversionResult;
    }


}
