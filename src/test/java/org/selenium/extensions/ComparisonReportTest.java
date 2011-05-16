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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static javax.imageio.ImageIO.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.mockito.Mockito.*;
import static org.selenium.extensions.ComparisonReport.*;
import static org.selenium.extensions.ImageComparisonAssert.*;

public class ComparisonReportTest {
    private static final String STORAGE_LOCATION = System.getProperty("java.io.tmpdir") + "/comparisonTest";

    @Mock
    ImageComparison imageComparison;
    private ComparisonReport report;
    private static final TestImage DIFF_IMAGE = new TestImage(Color.BLUE);
    private static final TestImage SOURCE_IMAGE = new TestImage(Color.RED);
    private static final TestImage TARGET_IMAGE = new TestImage(Color.GREEN);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        report = new ComparisonReport(imageComparison);
        new File(STORAGE_LOCATION).mkdir();
        when(imageComparison.getDifference()).thenReturn(DIFF_IMAGE);
        when(imageComparison.getSourceImage()).thenReturn(SOURCE_IMAGE);
        when(imageComparison.getTargetImage()).thenReturn(TARGET_IMAGE);
    }

    @After
    public void tearDown() {
        new File(STORAGE_LOCATION + "/" + DIFFERENCE_IMAGE_NAME).delete();
        new File(STORAGE_LOCATION + "/" + SOURCE_IMAGE_NAME).delete();
        new File(STORAGE_LOCATION + "/" + TARGET_IMAGE_NAME).delete();
        new File(STORAGE_LOCATION + "/" + REPORT_FILE_NAME).delete();
        assumeTrue(new File(STORAGE_LOCATION).delete());
    }

    @Test
    public void testDescribesDifferenceDelegatesToComparison() throws Exception {
        when(imageComparison.yieldsDifference()).thenReturn(false);
        assertEquals(false, report.describesDifference());
        when(imageComparison.yieldsDifference()).thenReturn(true);
        assertEquals(true, report.describesDifference());
    }

    @Test
    public void testStoreBitmaps() throws Exception {
        report.store(STORAGE_LOCATION, "");
        assertImageEquals(DIFF_IMAGE, read(new File(STORAGE_LOCATION + "/" + DIFFERENCE_IMAGE_NAME)));
        assertImageEquals(SOURCE_IMAGE, read(new File(STORAGE_LOCATION + "/" + SOURCE_IMAGE_NAME)));
        assertImageEquals(TARGET_IMAGE, read(new File(STORAGE_LOCATION + "/" + TARGET_IMAGE_NAME)));
    }

    @Test
    public void testResolveHtml() throws Exception {
        report.store(STORAGE_LOCATION, DIFFERENCE_FILENAME_PLACEHOLDER +
                "," + SOURCE_FILENAME_PLACEHOLDER +
                "," + TARGET_FILENAME_PLACEHOLDER);
        String content = readFile(STORAGE_LOCATION + "/" + REPORT_FILE_NAME);
        assertEquals(DIFFERENCE_IMAGE_NAME + "," + SOURCE_IMAGE_NAME + "," + TARGET_IMAGE_NAME, content);
    }

    @Test
    public void testHtmlLocation() throws Exception {
        String location = report.store(STORAGE_LOCATION, "");
        assertEquals(STORAGE_LOCATION +"/" + REPORT_FILE_NAME, location);
    }
    
    @Test
    public void testFolderGetsCreatedIfNotExisting() throws Exception{
        File folder = new File(STORAGE_LOCATION);
        folder.delete();
        report.store(STORAGE_LOCATION, "");
        assertTrue("folder was not created", folder.exists());
    }


    private String readFile(String fileName) throws FileNotFoundException {
        String content = "";
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext())
            content += scanner.nextLine();
        return content;
    }

    @Test
    public void testCreateHtml() throws Exception {

    }

    private static class TestImage extends BufferedImage {
        public TestImage(Color color) {
            super(1, 1, BufferedImage.TYPE_INT_ARGB);
            setRGB(0, 0, color.getRGB());
        }
    }
}


