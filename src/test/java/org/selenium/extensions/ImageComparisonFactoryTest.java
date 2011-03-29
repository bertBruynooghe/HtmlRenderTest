package org.selenium.extensions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.image.BufferedImage;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ImageComparisonFactoryTest {
    public static final int DUMMY_IMAGE_TYPE = BufferedImage.TYPE_INT_ARGB;
    @Mock
    BufferedImage dummyImage;

    public static final int DUMMY_DIFF_COLOR = 0xFF00FF00;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(dummyImage.getType()).thenReturn(DUMMY_IMAGE_TYPE);
        when(dummyImage.getHeight()).thenReturn(1);
        when(dummyImage.getWidth()).thenReturn(1);
    }

    @Test
    public void testFactory() throws Exception {
        ImageComparison comparison = new ImageComparisonFactory(DUMMY_DIFF_COLOR).newComparison(dummyImage, dummyImage);
        assertEquals(ImageComparison.class, comparison.getClass());
    }
}
