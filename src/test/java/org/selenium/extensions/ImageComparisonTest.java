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
    private BufferedImage sourceImage;

    @Mock
    private BufferedImage targetImage;

    @Before
    public void setUp() throws Exception {
        when(sourceImage.getHeight()).thenReturn(1);
        when(sourceImage.getWidth()).thenReturn(1);
        when(sourceImage.getType()).thenReturn(BufferedImage.TYPE_INT_ARGB);
        when(targetImage.getHeight()).thenReturn(1);
        when(targetImage.getWidth()).thenReturn(1);
    }

    @Test
    public void testDifference() throws Exception {
        when(sourceImage.getRGB(0, 0)).thenReturn(0xffffff);
        when(targetImage.getRGB(0, 0)).thenReturn(0x000000);
        ImageComparison comparison = new ImageComparison(sourceImage, targetImage, DIFF_COLOR);
        assertEquals(DIFF_COLOR, comparison.getDifference().getRGB(0, 0));
        assertTrue(comparison.yieldsDifference());
    }

    @Test
    public void testGetters() throws Exception{
        ImageComparison comparison = new ImageComparison(sourceImage, targetImage, DIFF_COLOR);
        assertEquals(sourceImage, comparison.getSourceImage());
        assertEquals(targetImage, comparison.getTargetImage());
    }

    @Test
    public void testPixelEquality() throws Exception {
        when(sourceImage.getRGB(0, 0)).thenReturn(0xffffff);
        when(targetImage.getRGB(0, 0)).thenReturn(0xffffff);
        ImageComparison comparison = new ImageComparison(sourceImage, targetImage, DIFF_COLOR);
        assertEquals(0, comparison.getDifference().getRGB(0, 0));
        assertFalse(comparison.yieldsDifference());
    }

}
