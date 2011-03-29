package org.selenium.extensions;

import org.springframework.beans.factory.annotation.Autowired;

public class ComparisonReport {

    private final ImageComparison imageComparison;

    public ComparisonReport(ImageComparison imageComparison) {
        this.imageComparison = imageComparison;
    }

    public boolean describesDifference() {
        return imageComparison.yieldsDifference();
    }

    public String store(String s) {
        //TODO
        return "/tmp";
    }
}
