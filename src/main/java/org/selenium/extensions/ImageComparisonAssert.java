package org.selenium.extensions;

import junit.framework.AssertionFailedError;

import java.awt.image.BufferedImage;

public class ImageComparisonAssert {
    private final ComparisonReportFactory reportFactory;

    public ImageComparisonAssert(ComparisonReportFactory reportFactory) {
        this.reportFactory = reportFactory;
    }

    public void assertImageEquals(BufferedImage img1, BufferedImage img2) {
        ComparisonReport report = reportFactory.createComparisonReport(img1, img2);
        if (report.describesDifference()) {
            String location = report.store("");
            throw new AssertionFailedError("sdflkjs");
        }
    }

    public static void assertEquals(BufferedImage img1, BufferedImage img2) {
        ComparisonReportFactory comparisonReportFactory =
                new ComparisonReportFactory(new ImageComparisonFactory(0xFF00FF00));
        new ImageComparisonAssert(comparisonReportFactory).assertImageEquals(img1, img2);
    }
}
