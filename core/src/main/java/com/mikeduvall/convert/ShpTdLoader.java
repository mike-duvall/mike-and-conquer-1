package com.mikeduvall.convert;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ShpTdLoader {

    public ShpTdLoader() {

    }


    public boolean isShpTd(String shpFileName) {
        try {
            DataInputStream s = getDataInputStreamdFromFile(shpFileName);

            try {
                int imageCount = s.readInt();
                if (imageCount == 0) {
//            s.Position = start;
                    return false;
                }
            } catch (IOException e) {
//            throw new RuntimeException("Error parsing .shp file", e);
                return false;
            }
        }
        catch(GdxRuntimeException ex) {
            return false;
        }


        return true;

    }

    private DataInputStream getDataInputStreamdFromFile(String shpFileName) {
        FileHandle fileHandle = Gdx.files.internal(shpFileName);
        InputStream is = fileHandle.read(1000);
        DataInputStream dataInputStream = new DataInputStream(is);
        return dataInputStream;
    }

    private byte[] readBytesFromFile(String fileName) {
        FileHandle fileHandle = Gdx.files.internal(fileName);
        InputStream is = fileHandle.read(1000);
        return readBytesFromInputStream(is);
    }


    private byte[] readBytesFromInputStream(InputStream is) {
        try {
            return IOUtils.toByteArray(is);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



//    static bool IsShpTD(Stream s)
//    {
//        var start = s.Position;
//
//        // First word is the image count
//        var imageCount = s.ReadUInt16();
//        if (imageCount == 0)
//        {
//            s.Position = start;
//            return false;
//        }
//
//        // Last offset should point to the end of file
//        var finalOffset = start + 14 + 8 * imageCount;
//        if (finalOffset > s.Length)
//        {
//            s.Position = start;
//            return false;
//        }
//
//        s.Position = finalOffset;
//        var eof = s.ReadUInt32();
//        if (eof != s.Length)
//        {
//            s.Position = start;
//            return false;
//        }
//
//        // Check the format flag on the first frame
//        s.Position = start + 17;
//        var b = s.ReadUInt8();
//
//        s.Position = start;
//        return b == 0x20 || b == 0x40 || b == 0x80;
//    }


}
