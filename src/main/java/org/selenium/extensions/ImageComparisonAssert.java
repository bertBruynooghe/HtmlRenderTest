package org.selenium.extensions;

import junit.framework.AssertionFailedError;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageComparisonAssert {
    private final ComparisonReportFactory reportFactory;

    public ImageComparisonAssert(ComparisonReportFactory reportFactory) {
        this.reportFactory = reportFactory;
    }

    public void assertEqual(BufferedImage img1, BufferedImage img2) throws IOException {
        ComparisonReport report = reportFactory.createComparisonReport(img1, img2);
        if (report.describesDifference()) {
            String location = report.store("", "", "");
            throw new AssertionFailedError("sdflkjs");
        }
    }

    public static void assertImageEquals(BufferedImage img1, BufferedImage img2) throws IOException {
        ComparisonReportFactory comparisonReportFactory =
                new ComparisonReportFactory(new ImageComparisonFactory(0xFF00FF00));
        new ImageComparisonAssert(comparisonReportFactory).assertEqual(img1, img2);
    }
}
