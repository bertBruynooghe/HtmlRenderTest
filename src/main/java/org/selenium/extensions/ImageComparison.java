package org.selenium.extensions;

import java.awt.image.BufferedImage;

public class ImageComparison {
    private final BufferedImage difference;
    private final boolean different;
    private final BufferedImage sourceImage;
    private final BufferedImage targetImage;

    public ImageComparison(BufferedImage sourceImage, BufferedImage targetImage, int diffColor) {
        this.sourceImage = sourceImage;
        this.targetImage = targetImage;
        boolean tmpDifferent = false;
        difference = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), sourceImage.getType());
        for (int x = 0; x < targetImage.getWidth(); x++)
            for (int y = 0; y < targetImage.getHeight(); y++) {
                if (sourceImage.getRGB(x, y) != targetImage.getRGB(x, y)) {
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

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public BufferedImage getTargetImage() {
        return targetImage;
    }
}
