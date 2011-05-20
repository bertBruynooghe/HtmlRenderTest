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

import java.awt.image.BufferedImage;

public class ComparisonReportFactory {
    public static final String DEFAULT_REPORT_TEMPLATE = "<html>" +
            "<body>" +
            "<h3>Stored screenshot</h3>" +
            "<img src='$SOURCE'/>" +
            "<h3>Current screenshot</h3>" +
            "<input id='diffCheck' type='checkbox' checked='true' onchange='diff.style.display=(diffCheck.checked)?\"block\":\"none\"'>" +
            "difference<br>" +
            "<input id='currCheck' type='checkbox' checked='true' onchange='curr.style.display=(currCheck.checked)?\"block\":\"none\"'>" +
            "current" +
            "<div>" +
            "<img src='$TARGET' style='position:absolute;' id='curr'/>" +
            "<img src='$DIFFERENCE' style='position:absolute;' id='diff'/>" +
            "</div>" +
            "</body>" +
            "</html>";

    private final ImageComparisonFactory comparisonFactory;

    public ComparisonReportFactory(ImageComparisonFactory comparisonFactory) {
        this.comparisonFactory = comparisonFactory;
    }

    public ComparisonReport createComparisonReport(BufferedImage img1, BufferedImage img2) {
        ImageComparison comparison = comparisonFactory.newComparison(img1, img2);
        return new ComparisonReport(comparison);
    }
}
