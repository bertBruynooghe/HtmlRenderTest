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

import java.awt.*;
import java.awt.image.BufferedImage;

public class MonochromeRectangleContainer {

    private final BufferedImage image;

    public MonochromeRectangleContainer(BufferedImage image) {
        this.image = image;
    }

    Point findMonochromeRectangleTopLeftCorner(Point pointInRectangle) {
        int rectangleColor = getColor(pointInRectangle);
        Point result = new Point(pointInRectangle);

        while (getColor(result) == rectangleColor && result.x >= 0)
            result.x--;
        result.x++;

        while (getColor(result) == rectangleColor && result.y >= 0)
            result.y--;
        result.y++;

        return result;
    }

    Point findMonochromeRectangleBottomRightCorner(Point pointInRectangle) {
        int rectangleColor = getColor(pointInRectangle);
        Point result = new Point(pointInRectangle);

        //TODO: test extra condition
        while (result.x <= image.getWidth() - 1 && getColor(result) == rectangleColor)
            result.x++;
        result.x--;

        while (result.y < image.getHeight() - 1 && getColor(result) == rectangleColor)
            result.y++;
        result.y--;

        return result;
    }

    private int getColor(Point p) {
        return image.getRGB(p.x, p.y);
    }

}
