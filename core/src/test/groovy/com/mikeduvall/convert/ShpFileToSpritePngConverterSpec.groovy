package com.mikeduvall.convert

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglFiles
import spock.lang.Specification


class ShpFileToSpritePngConverterSpec extends Specification {

    def "convert shp file to png"() {

        given:
        ShpFileToSpritePngConverter converter = new ShpFileToSpritePngConverter()
        Class x = Class.forName("com.badlogic.gdx.Gdx")
        Gdx.files = new LwjglFiles();

        when:
        ConversionResult conversionResult = converter.convertShpFileToSingleMiniGunnerPngFile("e1.shp")

        then:
        conversionResult.numberOfImages == 532
        conversionResult.imageWidth == 50
        conversionResult.imageHeight == 39
    }

}