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
import static org.mockito.Mockito.*;

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
