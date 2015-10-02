package com.mikeduvall.convert

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglFiles
import spock.lang.Specification


class ShpTDLoaderSpec extends Specification {


    def setup() {
        Class.forName("com.badlogic.gdx.Gdx")
        Gdx.files = new LwjglFiles();

    }

    def "isShpTd() returns false for invalid shp file"() {
        given:
        ShpTdLoader shpTdLoader = new ShpTdLoader()

        when:
        boolean isValidFile = shpTdLoader.isShpTd("non-existent-file.shp")

        then:
        assert isValidFile == false
    }


    def "isShpTd() returns true for valid shp file"() {
        given:
        ShpTdLoader shpTdLoader = new ShpTdLoader()

        when:
        boolean isValidFile = shpTdLoader.isShpTd("e1.shp")

        then:
        assert isValidFile == true
    }


    def "tryParseSprite() returns list of SpriteFrames"() {
        given:
        ShpTdLoader shpTdLoader = new ShpTdLoader()

        when:
        boolean result = shpTdLoader.tryParseSprite("e1.shp")

        then:
        assert result
        assert shpTdLoader.imageCount == 532
        assert shpTdLoader.size.width == 50
        assert shpTdLoader.size.height == 39


//        List<ISpriteFrame> iSpriteFrameList =  shpTdLoader.getiSpriteFrameList()
//        assert iSpriteFrameList.size() > 0
//        ISpriteFrame firstFrame = iSpriteFrameList.get(0)
//        assert firstFrame.getSize().getWidth() == 50

//        assert firstFrame.getFileOffset() ==  343
//        assert firstFrame.getFormat() == 0x40 // or what?

//        FileOffset = data & 0xffffff;
//        Format = (Format)(data >> 24);
//
//        RefOffset = stream.ReadUInt16();
//        RefFormat = (Format)stream.ReadUInt16();

    }


}