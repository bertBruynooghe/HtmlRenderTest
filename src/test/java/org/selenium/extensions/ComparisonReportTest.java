package org.selenium.extensions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ComparisonReportTest {
    @Mock
    ImageComparison imageComparison;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDescribesDifferenceDelegatesToComparison() throws Exception {
        ComparisonReport report = new ComparisonReport(imageComparison);
        report.describesDifference();

        Mockito.verify(imageComparison).yieldsDifference();
    }
}
