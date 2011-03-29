package org.selenium.extensions;

import java.awt.image.BufferedImage;

public class ComparisonReportFactory {
    private final ImageComparisonFactory comparisonFactory;

    public ComparisonReportFactory(ImageComparisonFactory comparisonFactory) {
        this.comparisonFactory = comparisonFactory;
    }

    public ComparisonReport createComparisonReport(BufferedImage img1, BufferedImage img2) {
        ImageComparison comparison = comparisonFactory.newComparison(img1, img2);
        return new ComparisonReport(comparison);
    }
}
