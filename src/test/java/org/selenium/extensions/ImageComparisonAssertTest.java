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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;
import java.util.Formatter;
import java.util.UUID;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;
import static org.selenium.extensions.ComparisonReportFactory.DEFAULT_REPORT_TEMPLATE;
import static org.selenium.extensions.ImageComparisonAssert.*;

public class ImageComparisonAssertTest {
    public static final String TMP_LOCATION = System.getProperty("java.io.tmpdir");
    public static final UUID DUMMY_UUID = new UUID(98742L, 987428L);

    static private ComparisonReportFactory reportFactory;

    @Mock
    private BufferedImage dummyImg;

    @Mock
    private ComparisonReport comparisonReport;


    @BeforeClass
    public static void setUpClass(){
        ImageComparisonAssert.uuidProvider = new ImageComparisonAssert.UUIDProvider(){
            @Override
            UUID getRandomUUID() {
                return DUMMY_UUID;
            }
        };
        reportFactory = mock(ComparisonReportFactory.class);
        ImageComparisonAssert.reportFactory = reportFactory;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(reportFactory.createComparisonReport(Matchers.<BufferedImage>any(), Matchers.<BufferedImage>any())).
                thenReturn(comparisonReport);
    }

    @Test(expected = AssertionFailedError.class)
    public void testAssertImageEqualsFailsWhenNotEquals() throws Exception {
        when(comparisonReport.describesDifference()).thenReturn(true);
            assertImageEquals(dummyImg, dummyImg);
    }

    @Test
    public void testMessageWhenAssertImageEqualsWhenNotEquals() throws Exception {
        when(comparisonReport.describesDifference()).thenReturn(true);
        String htmlLocation = "htmlLocation";
        when(comparisonReport.store(anyString(), anyString())).thenReturn(htmlLocation);
        try {
            assertImageEquals(dummyImg, dummyImg);
        } catch (AssertionFailedError error) {
            assertEquals(new Formatter().format(FAILURE_MESSAGE_PATTERN, htmlLocation).toString(), error.getMessage());
        }
    }

    @Test
    public void testStorageLocationWhenAssertImageEqualsWhenNotEquals() throws Exception {
        when(comparisonReport.describesDifference()).thenReturn(true);
        try {
            assertImageEquals(dummyImg, dummyImg);
        } catch (AssertionFailedError error) {
            String tmpLocation = TMP_LOCATION + ImageComparisonAssert.COMPARISON_FAILURE_FOLDER_PREFIX + DUMMY_UUID.toString();
            verify(comparisonReport).store(eq(tmpLocation), anyString());
        }
    }

    @Test
    public void testHtmlReportWhenAssertImageEqualsWhenNotEquals() throws Exception{
        when(comparisonReport.describesDifference()).thenReturn(true);
        try {
            assertImageEquals(dummyImg, dummyImg);
        } catch (AssertionFailedError error) {
            verify(comparisonReport).store(anyString(), eq(DEFAULT_REPORT_TEMPLATE));
        }
    }

    @Test
    public void testAssertImageEqualsWhenEquals() throws Exception {
        when(comparisonReport.describesDifference()).thenReturn(false);
        assertImageEquals(dummyImg, dummyImg);
    }

}
