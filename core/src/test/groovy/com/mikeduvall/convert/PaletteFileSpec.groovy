package com.mikeduvall.convert

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import spock.lang.Specification

import java.nio.ByteBuffer


class PaletteFileSpec extends MikeSpecBase {

    def "should read palette file"() {
        given:
        String filename = "temperat.pal"
        FileHandle fileHandle = Gdx.files.internal(filename)
        InputStream is = fileHandle.read(1000)

        PaletteFile paletteFile = new PaletteFile(is)

        when:
        ByteBuffer byteBuffer = paletteFile.readBytes()

        then:
        byteBuffer
        byteBuffer.get(0) == 0
        byteBuffer.get(1) == 0
        byteBuffer.get(2) == 0
        byteBuffer.get(3) == 0x2a
        byteBuffer.get(4) == 0
        byteBuffer.get(5) == 0x2a

        byteBuffer.get(762) == 0x21
        byteBuffer.get(763) == 0x22
        byteBuffer.get(764) == 0x1d

        byteBuffer.get(765) == 0x3f
        byteBuffer.get(766) == 0x3f
        byteBuffer.get(767) == 0x3f
        byteBuffer.array().length == 768
    }

}