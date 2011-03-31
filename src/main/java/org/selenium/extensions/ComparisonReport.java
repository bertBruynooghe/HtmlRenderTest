package org.selenium.extensions;

import javax.imageio.ImageIO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ComparisonReport {
    private static final String IMAGE_EXTENSION = "png";
    static final String DIFFERENCE_IMAGE_NAME = "difference." + IMAGE_EXTENSION;
    static final String SOURCE_IMAGE_NAME = "source." + IMAGE_EXTENSION;
    static final String TARGET_IMAGE_NAME = "target." + IMAGE_EXTENSION;
    static final String REPORT_FILE_NAME = "report.html";
    static final String DIFFERENCE_FILENAME_PLACEHOLDER = "$DIFFERENCE";
    static final String SOURCE_FILENAME_PLACEHOLDER = "$SOURCE";
    static final String TARGET_FILENAME_PLACEHOLDER = "$TARGET";

    private final ImageComparison imageComparison;

    public ComparisonReport(ImageComparison imageComparison) {
        this.imageComparison = imageComparison;
    }

    public boolean describesDifference() {
        return imageComparison.yieldsDifference();
    }

    public String store(String storageLocation, String accessibleLocation, String htmlTemplate) throws IOException {
        ImageIO.write(imageComparison.getDifference(), IMAGE_EXTENSION, new File(storageLocation + "/" + DIFFERENCE_IMAGE_NAME));
        ImageIO.write(imageComparison.getSourceImage(), IMAGE_EXTENSION, new File(storageLocation + "/" + SOURCE_IMAGE_NAME));
        ImageIO.write(imageComparison.getTargetImage(), IMAGE_EXTENSION, new File(storageLocation + "/" + TARGET_IMAGE_NAME));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(storageLocation + "/"+ REPORT_FILE_NAME));
        htmlTemplate = htmlTemplate.replace(DIFFERENCE_FILENAME_PLACEHOLDER, DIFFERENCE_IMAGE_NAME);
        htmlTemplate = htmlTemplate.replace(SOURCE_FILENAME_PLACEHOLDER, SOURCE_IMAGE_NAME);
        htmlTemplate = htmlTemplate.replace(TARGET_FILENAME_PLACEHOLDER, TARGET_IMAGE_NAME);
        bufferedWriter.write(htmlTemplate);
        bufferedWriter.close();

        return accessibleLocation+"/"+REPORT_FILE_NAME;
    }
}
