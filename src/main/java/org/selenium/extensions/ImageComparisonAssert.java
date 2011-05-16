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

import junit.framework.AssertionFailedError;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Formatter;
import java.util.UUID;

public class ImageComparisonAssert {
    static final String COMPARISON_FAILURE_FOLDER_PREFIX = "/comparisonFailure";
    static final String FAILURE_MESSAGE_PATTERN = "Images don't match, see <%s>";
    static ComparisonReportFactory reportFactory =  new ComparisonReportFactory(new ImageComparisonFactory(0xFF00FF00));
    static UUIDProvider uuidProvider = new UUIDProvider();

    public static void assertImageEquals(BufferedImage img1, BufferedImage img2) throws IOException {
        ComparisonReport report = reportFactory.createComparisonReport(img1, img2);
        if (report.describesDifference()) {
            String location = System.getProperty("java.io.tmpdir") + COMPARISON_FAILURE_FOLDER_PREFIX + uuidProvider.getRandomUUID();
            String htmlPath = report.store(location, ComparisonReportFactory.DEFAULT_REPORT_TEMPLATE);
            throw new AssertionFailedError(new Formatter().format(FAILURE_MESSAGE_PATTERN, htmlPath).toString());
        }
    }

    static class UUIDProvider {
        UUID getRandomUUID(){
            return UUID.randomUUID();
        }
    }
}
