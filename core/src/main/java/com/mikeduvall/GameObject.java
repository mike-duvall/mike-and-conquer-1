/*
 * Copyright 2015, Michael A. DuVall
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mikeduvall;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.mikeduvall.convert.PaletteEntry;
import com.mikeduvall.convert.PaletteFile;

import java.nio.ByteBuffer;

public class GameObject {

    void drawByteBufferOnPixMap(Pixmap pixmap, PaletteFile paletteFile, ByteBuffer byteBuffer0, int width, int height) {

        int currentIndex = 0;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(!byteBuffer0.hasRemaining()) {
                    continue;
                }

                byte nextByte = byteBuffer0.get(currentIndex);
                if(nextByte != 0) {
                    int index = Byte.toUnsignedInt(nextByte);

                    index = mapColorIndex(index);
                    PaletteEntry paletteEntry = paletteFile.getPaletteEntries().get(index);

                    float red = paletteEntry.getRed() / 63.0f;
                    float green = paletteEntry.getGreen() / 63.0f;
                    float blue = paletteEntry.getBlue() / 63.0f;

                    Color color = new Color(red, green, blue, 1);

                    pixmap.setColor(color);

                    pixmap.drawPixel(x, y );

                }
                currentIndex++;
            }
        }
    }



    protected int mapColorIndex(int index) {
        return index;
    }


}
