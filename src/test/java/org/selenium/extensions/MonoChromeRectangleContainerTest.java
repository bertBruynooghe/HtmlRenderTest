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

import java.awt.*;
import java.awt.image.BufferedImage;

import static junit.framework.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class MonoChromeRectangleContainerTest {
    public static final int RECTANGLE_COLOR = 0xFFFFF;
    public static final int OTHER_COLOR = 0;

    @Mock
    BufferedImage image;
    public MonochromeRectangleContainer container;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(image.getWidth()).thenReturn(20);
        when(image.getHeight()).thenReturn(30);
        when(image.getRGB(anyInt(), anyInt())).thenReturn(RECTANGLE_COLOR);
        container = new MonochromeRectangleContainer(image);
    }

    @Test
    public void testLeftTop() throws Exception {
        Point expected = new Point(6, 4);
        when(image.getRGB(eq(expected.x - 1), anyInt())).thenReturn(OTHER_COLOR);
        when(image.getRGB(anyInt(), eq(expected.y - 1))).thenReturn(OTHER_COLOR);
        Point corner = container.findMonochromeRectangleTopLeftCorner(new Point(10, 10));
        assertEquals(expected, corner);
    }

    @Test
    public void testRightBottom() throws Exception {
        Point expected = new Point(6, 4);
        when(image.getRGB(eq(expected.x + 1), anyInt())).thenReturn(OTHER_COLOR);
        when(image.getRGB(anyInt(), eq(expected.y + 1))).thenReturn(OTHER_COLOR);
        Point corner = container.findMonochromeRectangleBottomRightCorner(new Point(2, 2));
        assertEquals(expected, corner);
    }

}

