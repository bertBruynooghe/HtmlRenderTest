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

public class ImageComparisonFactory {
    private final int diffColor;

    public ImageComparisonFactory(int diffColor) {
        this.diffColor = diffColor;
    }

    ImageComparison newComparison(BufferedImage img1, BufferedImage img2) {
        return new ImageComparison(img1, img2, diffColor);
    }
}
