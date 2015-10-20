package com.mikeduvall.convert;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class PaletteFile {

    InputStream inputStream;

    public PaletteFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ByteBuffer readBytes() {
        try {

            byte[] byteArray = IOUtils.toByteArray(inputStream);
            ByteBuffer buf = ByteBuffer.wrap(byteArray);
            return buf;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
