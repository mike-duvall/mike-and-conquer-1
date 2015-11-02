package com.mikeduvall.convert

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import spock.lang.Specification

import java.nio.ByteBuffer


class PaletteFileSpec extends MikeSpecBase {


    def "should read palette entries"() {
        given:
        String filename = "temperat.pal"
        FileHandle fileHandle = Gdx.files.internal(filename)
        InputStream is = fileHandle.read(1000)

        PaletteFile paletteFile = new PaletteFile(is)

        when:
        List<PaletteEntry> paletteEntryList = paletteFile.getPaletteEntries()


        then:
        paletteEntryList.size() == 256

        paletteEntryList.get(0).red == 0
        paletteEntryList.get(0).green == 0
        paletteEntryList.get(0).blue  == 0


        paletteEntryList.get(1).red == 0x2a
        paletteEntryList.get(0).green == 0
        paletteEntryList.get(1).blue  == 0x2a


        paletteEntryList.get(254).red == 0x21
        paletteEntryList.get(254).green == 0x22
        paletteEntryList.get(254).blue  == 0x1d

        paletteEntryList.get(255).red == 0x3f
        paletteEntryList.get(255).green == 0x3f
        paletteEntryList.get(255).blue  == 0x3f

    }


}