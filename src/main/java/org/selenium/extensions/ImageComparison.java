package org.selenium.extensions;

import java.awt.image.BufferedImage;

public class ImageComparison {
    final private BufferedImage difference;
    final private boolean different;

    public ImageComparison(BufferedImage image1, BufferedImage image2, int diffColor) {
        boolean tmpDifferent = false;
        difference = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());
        for (int x = 0; x < image2.getWidth(); x++)
            for (int y = 0; y < image2.getHeight(); y++) {
                if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
                    difference.setRGB(x, y, diffColor);
                    tmpDifferent = true;
                } else {
                    difference.setRGB(x, y, 0x00000000);
                }
            }
        different = tmpDifferent;
    }

    public BufferedImage getDifference() {
        return difference;
    }

    public boolean yieldsDifference() {
        return different;
    }
}
