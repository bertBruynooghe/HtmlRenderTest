package org.selenium.extensions;

import java.awt.image.BufferedImage;

public class BitmapComparison {

    private final BufferedImage difference;
    private boolean different = false;

    public BitmapComparison(BufferedImage image1, BufferedImage image2, int diffColor) {
        difference = new BufferedImage(image1.getWidth(), image2.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < image1.getWidth(); x++)
            for (int y = 0; y < image1.getHeight(); y++)
                if (image1.getRGB(x, y) == image2.getRGB(x, y))
                    difference.setRGB(x, y, 0);
                else {
                    different = true;
                    difference.setRGB(x, y, diffColor | 0xff000000);
                }
    }

    public BufferedImage getDifference() {
        return difference;
    }

    public boolean isDifferent() {
        return different;
    }
}
