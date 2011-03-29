package org.selenium.extensions;

import java.awt.image.BufferedImage;

public class ImageComparisonFactory {
    private final int diffColor;

    public ImageComparisonFactory(int diffColor) {
        this.diffColor = diffColor;
    }

    ImageComparison newComparison(BufferedImage img1, BufferedImage img2) {
        return new ImageComparison(img1, img2, diffColor);
    }
}
