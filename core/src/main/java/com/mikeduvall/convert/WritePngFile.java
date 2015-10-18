package com.mikeduvall.convert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WritePngFile {


    public void write() {
        BufferedImage off_Image =
                new BufferedImage(100, 50,
                        BufferedImage.TYPE_INT_ARGB);


        off_Image.setRGB(20,20,100);
        off_Image.setRGB(21,20,100);
        off_Image.setRGB(22,20,100);
        off_Image.setRGB(23,20,100);
        off_Image.setRGB(24,20,100);
        off_Image.setRGB(25,20,100);


//        Graphics2D g2d = off_Image.createGraphics();
//        g2d.setColor(Color.blue);
//        g2d.drawRect(0,0,50,25);


        File outputfile = new File("saved.png");
        try {
            ImageIO.write(off_Image, "png", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
