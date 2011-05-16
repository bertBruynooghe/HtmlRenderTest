/** Copyright 2011 Bert Bruynooghe
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
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

    public String store(String location, String htmlTemplate) throws IOException {
        File folder = new File(location);
        if (!folder.exists())
            folder.mkdirs();
        ImageIO.write(imageComparison.getDifference(), IMAGE_EXTENSION, new File(location + "/" + DIFFERENCE_IMAGE_NAME));
        ImageIO.write(imageComparison.getSourceImage(), IMAGE_EXTENSION, new File(location + "/" + SOURCE_IMAGE_NAME));
        ImageIO.write(imageComparison.getTargetImage(), IMAGE_EXTENSION, new File(location + "/" + TARGET_IMAGE_NAME));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(location + "/" + REPORT_FILE_NAME));
        htmlTemplate = htmlTemplate.replace(DIFFERENCE_FILENAME_PLACEHOLDER, DIFFERENCE_IMAGE_NAME);
        htmlTemplate = htmlTemplate.replace(SOURCE_FILENAME_PLACEHOLDER, SOURCE_IMAGE_NAME);
        htmlTemplate = htmlTemplate.replace(TARGET_FILENAME_PLACEHOLDER, TARGET_IMAGE_NAME);
        bufferedWriter.write(htmlTemplate);
        bufferedWriter.close();

        return location + "/" + REPORT_FILE_NAME;
    }
}
