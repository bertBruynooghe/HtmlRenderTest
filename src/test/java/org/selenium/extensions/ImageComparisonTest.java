package org.selenium.extensions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.image.BufferedImage;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ImageComparisonTest {
    public static final int DIFF_COLOR = 0xFF00FF00;

    @Mock
    private BufferedImage image1;

    @Mock
    private BufferedImage image2;

    @Before
    public void setUp() throws Exception {
        when(image1.getHeight()).thenReturn(1);
        when(image1.getWidth()).thenReturn(1);
        when(image1.getType()).thenReturn(BufferedImage.TYPE_INT_ARGB);
        when(image2.getHeight()).thenReturn(1);
        when(image2.getWidth()).thenReturn(1);
    }

    @Test
    public void testDifference() throws Exception {
        when(image1.getRGB(0, 0)).thenReturn(0xffffff);
        when(image2.getRGB(0, 0)).thenReturn(0x000000);
        ImageComparison comparison = new ImageComparison(image1, image2, DIFF_COLOR);
        assertEquals(DIFF_COLOR, comparison.getDifference().getRGB(0, 0));
        assertTrue(comparison.yieldsDifference());
    }

    @Test
    public void testPixelEquality() throws Exception {
        when(image1.getRGB(0, 0)).thenReturn(0xffffff);
        when(image2.getRGB(0, 0)).thenReturn(0xffffff);
        ImageComparison comparison = new ImageComparison(image1, image2, DIFF_COLOR);
        assertEquals(0, comparison.getDifference().getRGB(0, 0));
        assertFalse(comparison.yieldsDifference());
    }

}
