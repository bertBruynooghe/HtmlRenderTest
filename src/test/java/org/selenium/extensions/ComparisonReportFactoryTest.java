package org.selenium.extensions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;

import static junit.framework.Assert.assertEquals;

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
