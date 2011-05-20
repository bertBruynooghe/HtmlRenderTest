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

import java.awt.image.BufferedImage;

public class ImageComparison {
    private final BufferedImage difference;
    private final boolean different;
    private final BufferedImage sourceImage;
    private final BufferedImage targetImage;

    public ImageComparison(BufferedImage sourceImage, BufferedImage targetImage, int diffColor) {
        this.sourceImage = sourceImage;
        this.targetImage = targetImage;
        boolean tmpDifferent = false;
        difference = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < targetImage.getWidth(); x++)
            for (int y = 0; y < targetImage.getHeight(); y++) {
                if (sourceImage.getRGB(x, y) != targetImage.getRGB(x, y)) {
                    difference.setRGB(x, y, diffColor);
                    tmpDifferent = true;
                } else {
                    difference.setRGB(x, y, 0x00000000);
                }
            }
        different = tmpDifferent;
    }

    public BufferedImage getDifference() {
        return difference;
    }

    public boolean yieldsDifference() {
        return different;
    }

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public BufferedImage getTargetImage() {
        return targetImage;
    }
}
