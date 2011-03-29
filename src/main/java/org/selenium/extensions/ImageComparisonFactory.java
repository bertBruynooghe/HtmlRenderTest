package org.selenium.extensions;

import java.awt.image.BufferedImage;

public class ImageComparisonFactory {
    ImageComparison newComparison(BufferedImage img1, BufferedImage img2, int diffColor) {
        return new ImageComparison(img1, img2, diffColor);
    }
}
