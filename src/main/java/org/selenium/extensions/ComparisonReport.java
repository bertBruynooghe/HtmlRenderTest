package org.selenium.extensions;

import org.springframework.beans.factory.annotation.Autowired;

public class ComparisonReport {
    @Autowired
    Class<? extends ImageComparison> imageComparisonClass;

    public ComparisonReport(ImageComparison imageComparison) {
    }

    public boolean describesDifference() {
        return false;
    }

    public String store(String s) {
        return "/tmp";
    }
}
