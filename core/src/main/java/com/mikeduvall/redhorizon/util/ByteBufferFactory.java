package com.mikeduvall.redhorizon.util;



import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferFactory {

    public static ByteBuffer createLittleEndianByteBuffer(int capacity) {
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer;
    }
}
