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

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;

import static junit.framework.Assert.*;

public class ComparisonReportFactoryTest {
    @Mock
    private ImageComparisonFactory comparisonFactory;

    @Mock
    private BufferedImage dummyImage;

    public final static int dummyColor = 0xFF00FF00;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateComparisonReport() throws Exception {
        ComparisonReportFactory factory = new ComparisonReportFactory(comparisonFactory);
        ComparisonReport comparisonReport = factory.createComparisonReport(dummyImage, dummyImage);
        assertEquals(ComparisonReport.class, comparisonReport.getClass());
    }
}
