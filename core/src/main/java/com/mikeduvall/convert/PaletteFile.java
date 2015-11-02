package com.mikeduvall.convert;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class PaletteFile {

    InputStream inputStream;
    List<PaletteEntry> paletteEntryList;

    public PaletteFile(InputStream inputStream) {
        this.inputStream = inputStream;
        paletteEntryList = new ArrayList<>();

        ByteBuffer bytes = readBytes();


        while(bytes.hasRemaining()) {
            PaletteEntry newPaletteEntry = new PaletteEntry();
            newPaletteEntry.setRed(bytes.get());
            newPaletteEntry.setGreen(bytes.get());
            newPaletteEntry.setBlue(bytes.get());
            paletteEntryList.add(newPaletteEntry);
        }

    }

    private  ByteBuffer readBytes() {
        try {

            byte[] byteArray = IOUtils.toByteArray(inputStream);
            ByteBuffer buf = ByteBuffer.wrap(byteArray);
            return buf;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<PaletteEntry> getPaletteEntries() {
        return paletteEntryList;
    }
}
