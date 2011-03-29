package org.selenium.extensions;

import java.awt.image.BufferedImage;

public class ComparisonReportFactory {
    private final ImageComparisonFactory comparisonFactory;
    private final int diffColor;

    public ComparisonReportFactory(ImageComparisonFactory comparisonFactory, int diffColor) {
        this.comparisonFactory = comparisonFactory;
        this.diffColor = diffColor;
    }

    public ComparisonReport createComparisonReport(BufferedImage img1, BufferedImage img2) {
        ImageComparison comparison = comparisonFactory.newComparison(img1, img2, diffColor);
        return new ComparisonReport(comparison);
    }
}
