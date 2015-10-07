package com.mikeduvall.convert;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.LittleEndianInputStream;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ShpTdLoader {

    public ShpTdLoader() {

    }


    public boolean isShpTd(String shpFileName) {
        try {
            LittleEndianInputStream s = getDataInputStreamFromFile(shpFileName);

            int start = 0;
            s.mark(600000);

            try {
                int imageCount = s.readUnsignedShort();
                if (imageCount == 0) {
                    s.reset();
                    return false;
                }

                // Last offset should point to the end of file
                int finalOffset = start + 14 + 8 * imageCount;
                int sizeOfStream = getSizeOfFile(shpFileName);
                if (finalOffset > sizeOfStream) {
                    s.reset();
                    return false;
                }


                s.reset();
                s.mark(600000);

                s.skipBytes(finalOffset);

                int eof = s.readInt();

                if(eof != sizeOfStream) {
                    return false;
                }

                // Check the format flag on the first frame
                s.reset();
                s.mark(600000);

                s.skipBytes(17);

                int b = s.readUnsignedByte();

                s.reset();
                return b == 0x20 || b == 0x40 || b == 0x80;
            } catch (IOException e) {
                return false;
            }
        }
        catch(GdxRuntimeException ex) {
            return false;
        }


    }

    private int getSizeOfFile(String fileName) {
        return readBytesFromFile(fileName).length;
    }


    private LittleEndianInputStream getDataInputStreamFromFile(String shpFileName) {
        FileHandle fileHandle = Gdx.files.internal(shpFileName);
        InputStream is = fileHandle.read(1000);
        LittleEndianInputStream dataInputStream = new LittleEndianInputStream(is);
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

    List<ISpriteFrame> iSpriteFrameList;
    int imageCount;
    Size size;

    public boolean tryParseSprite(String shpFileName) {
//        iSpriteFrameList = new ArrayList<ISpriteFrame>();
//        ISpriteFrame dummyFrame = new ISpriteFrame();
//        Size size = new Size();
//        size.setWidth(5);
//        dummyFrame.setSize(size);
//        iSpriteFrameList.add(dummyFrame);
//        return true;
        try {
            LittleEndianInputStream s = getDataInputStreamFromFile(shpFileName);

            int start = 0;
            s.mark(600000);

            try {
                imageCount = s.readUnsignedShort();
                if (imageCount == 0) {
                    s.reset();
                    return false;
                }

                s.skipBytes(4);
                size = new Size();
                size.setWidth(s.readUnsignedShort());
                size.setHeight(s.readUnsignedShort());

                s.skipBytes(4);
                iSpriteFrameList = new ArrayList<ISpriteFrame>();
                ISpriteFrame dummyFrame = new ISpriteFrame();

                int byte1 = s.readUnsignedByte();
                int byte2 = s.readUnsignedByte();
                int byte3 = s.readUnsignedByte();
                int byte4 = s.readUnsignedByte();


                long data = s.readLong();
//                long fileOffset = data & 0xffffff;
                long fileOffset = data & 0xffffff00;
                fileOffset = fileOffset - 0x80000000;
//                Format = (Format)(data >> 24);
                dummyFrame.setFileOffset(fileOffset);

                iSpriteFrameList.add(dummyFrame);


//                // Last offset should point to the end of file
//                int finalOffset = start + 14 + 8 * imageCount;
//                int sizeOfStream = getSizeOfFile(shpFileName);
//                if (finalOffset > sizeOfStream) {
//                    s.reset();
//                    return false;
//                }
//
//
//                s.reset();
//                s.mark(600000);
//
//                s.skipBytes(finalOffset);
//
//                int eof = s.readInt();
//
//                if(eof != sizeOfStream) {
//                    return false;
//                }
//
//                // Check the format flag on the first frame
//                s.reset();
//                s.mark(600000);
//
//                s.skipBytes(17);
//
//                int b = s.readUnsignedByte();
//
//                s.reset();
//                return b == 0x20 || b == 0x40 || b == 0x80;
            } catch (IOException e) {
                return false;
            }
            return true;
        }
        catch(GdxRuntimeException ex) {
            return false;
        }

    }



    public List<ISpriteFrame> getiSpriteFrameList() {
        return iSpriteFrameList;
    }

    public void setiSpriteFrameList(List<ISpriteFrame> iSpriteFrameList) {
        this.iSpriteFrameList = iSpriteFrameList;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }
}
