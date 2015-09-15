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

}