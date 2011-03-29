package org.selenium.extensions;

import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

public class ImageComparisonAssertTest {
    @Mock
    private ComparisonReportFactory reportFactory;

    @Mock
    private BufferedImage dummyImg;

    @Mock
    private ComparisonReport comparisonReport;

    private ImageComparisonAssert comparisonAssert;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(reportFactory.createComparisonReport(Matchers.<BufferedImage>any(), Matchers.<BufferedImage>any())).
                thenReturn(comparisonReport);
        comparisonAssert = new ImageComparisonAssert(reportFactory);
    }

    @Test(expected = AssertionFailedError.class)
    public void testAssertImageEquals() throws Exception {
        when(comparisonReport.describesDifference()).thenReturn(true);
        try {
            comparisonAssert.assertImageEquals(dummyImg, dummyImg);
        } catch (AssertionFailedError error) {
            assertEquals("", error.getMessage());
            throw error;
        }
    }
}
