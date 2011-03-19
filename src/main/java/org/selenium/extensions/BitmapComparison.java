package org.selenium.extensions;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.*;

public class BitmapComparison {
    final private BufferedImage difference;
    final private boolean different;

    public BitmapComparison(BufferedImage image1, BufferedImage image2, int diffColor) {
        boolean equal = true;
        difference = new BufferedImage(image1.getWidth(), image1.getHeight(), TYPE_INT_ARGB);
        for (int x = 0; x < image2.getWidth(); x++)
            for (int y = 0; y < image2.getHeight(); y++) {
                if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
                    difference.setRGB(x, y, diffColor);
                    equal = false;
                } else {
                    difference.setRGB(x, y, 0x00000000);
                }
            }
        different = !equal;
//        difference = new BufferedImage(image1.getWidth(), image2.getHeight(), BufferedImage.TYPE_INT_ARGB);
//        for (int x = 0; x < image1.getWidth(); x++)
//            for (int y = 0; y < image1.getHeight(); y++)
//                if (image1.getRGB(x, y) == image2.getRGB(x, y))
//                    difference.setRGB(x, y, 0);
//                else {
//                    different = true;
//                    difference.setRGB(x, y, diffColor | 0xff000000);
//                }
    }

    public BufferedImage getDifference() {
        return difference;
    }

    public boolean isDifferent() {
        return different;
    }
}
