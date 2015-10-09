package com.mikeduvall.convert

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglFiles
import redhorizon.filetypes.shp.ShpFileCNC
import spock.lang.Specification

import java.nio.channels.FileChannel


class ShpFileCNCSpec extends Specification {

    def setup() {
        Class.forName("com.badlogic.gdx.Gdx")
        Gdx.files = new LwjglFiles();

    }



    def "x"() {
        given:
        RandomAccessFile aFile     = new RandomAccessFile("/home/mduvall/workspace/mike-and-conquer/core/assets/e1.shp", "rw");
        FileChannel      inChannel = aFile.getChannel();
        inChannel


        when:
        ShpFileCNC shpFileCNC = new ShpFileCNC("/home/mduvall/workspace/mike-and-conquer/core/assets/test.file", inChannel)

        then:
        shpFileCNC.width() == 50
        shpFileCNC.height()  == 39

    }


}