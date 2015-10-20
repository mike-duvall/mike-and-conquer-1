package com.mikeduvall.convert

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglFiles
import spock.lang.Specification


class MikeSpecBase extends Specification {

    def setup() {
        Class.forName("com.badlogic.gdx.Gdx")
        Gdx.files = new LwjglFiles();

    }


}