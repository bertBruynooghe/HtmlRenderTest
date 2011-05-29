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

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;

public class ViewPort {
    //next value is heuristically determined
    public static final Dimension WINDOWS_DIM_BIG_ENOUGH_TO_HAVE_CENTER_IN_CANVAS = new Dimension(400, 400);

    public static final String EMPTY_PAGE_URL_PATTERN = "http://%s/selenium-server/core/Blank.html";
    private static final String windowOpenScriptPattern = "var %s = window.open('%s','%s'," +
            "'width=%d,height=%d," +
            "menubar=no,scrollbars=yes,toolbar=no,location=yes,directories=no,resizable=yes,top=0,left=0');";
    private static final String closeWindowPattern = "%s.close();";
    private static final String POPUP_WINDOW = "popup_window";
    public static final String REMOTE_SCREENSHOT_NAME = "reference.png";
    public static final Point SCREEN_ORIGIN = new Point(0, 0);
    public static final String REMOTE_STORAGE_LOCATION = "/home/bert/dev/selenium-remote-control-1.0.3/selenium-server-1.0.3/";

    private final Rectangle canvas;
    private final Selenium selenium;

    public ViewPort(Selenium selenium, Dimension viewPortDim) throws AWTException, IOException {
        this.selenium = selenium;

        String openScript = openWindowScript(WINDOWS_DIM_BIG_ENOUGH_TO_HAVE_CENTER_IN_CANVAS);
        selenium.runScript(openScript);
        waitForWindowOpened();

        selenium.captureScreenshot(REMOTE_SCREENSHOT_NAME);

        //TODO: construct the path based on properties?
        BufferedImage capture = ImageIO.read(new File(REMOTE_STORAGE_LOCATION + REMOTE_SCREENSHOT_NAME));
        //TODO: make first quadrant really first quadrant
        Rectangle scanRect = new Rectangle(SCREEN_ORIGIN, WINDOWS_DIM_BIG_ENOUGH_TO_HAVE_CENTER_IN_CANVAS);
        Point canvasOrigin = calculateViewPortOrigin(getSubImage(capture, scanRect));
        scanRect = new Rectangle(canvasOrigin, WINDOWS_DIM_BIG_ENOUGH_TO_HAVE_CENTER_IN_CANVAS);
        //TODO: creating a screenshot of the 4th quadrant is enough
        Point canvasOvershoot = calculateCanvasOvershoot(getSubImage(capture, scanRect));
        Dimension windowDimension = new Dimension(viewPortDim.width + canvasOvershoot.x,
                viewPortDim.height + canvasOvershoot.y);
        selenium.runScript(resizeWindowScript(windowDimension));
        waitForWindowOpened();
        canvas = new Rectangle(canvasOrigin, viewPortDim);
        selenium.selectWindow(POPUP_WINDOW);
    }

    private BufferedImage getSubImage(BufferedImage capture, Rectangle rectangle) {
        return capture.getSubimage(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    private String openWindowScript(Dimension dimension) {
        return format(windowOpenScriptPattern, POPUP_WINDOW, "localhost:4545",
                POPUP_WINDOW, dimension.width, dimension.height);
    }

    private void waitForWindowOpened() {
        try {
            //TODO: find a better way to determine if the window is already open
            selenium.waitForCondition("false", "1000");
        } catch (SeleniumException e) {
        }
    }

    private String resizeWindowScript(Dimension browserDim) {
        String closeWindowScript = format(closeWindowPattern, POPUP_WINDOW);
        return closeWindowScript + openWindowScript(browserDim);
    }

    private Point calculateCanvasOvershoot(BufferedImage image) {
        MonochromeRectangleContainer container = new MonochromeRectangleContainer(image);

        Point corner = container.findMonochromeRectangleBottomRightCorner(new Point(image.getWidth() / 2, image.getHeight() / 2));
        return new Point(WINDOWS_DIM_BIG_ENOUGH_TO_HAVE_CENTER_IN_CANVAS.width - corner.x,
                WINDOWS_DIM_BIG_ENOUGH_TO_HAVE_CENTER_IN_CANVAS.height - corner.y);
    }

    private Point calculateViewPortOrigin(BufferedImage image) {
        MonochromeRectangleContainer container = new MonochromeRectangleContainer(image);
        return container.findMonochromeRectangleTopLeftCorner(new Point(image.getWidth() / 2, image.getHeight() / 2));
    }

    private String format(String pattern, Object... objects) {
        return new Formatter(Locale.US).format(pattern, objects).toString();
    }

    public BufferedImage createSnapshot() throws AWTException {
        //System.out.println(canvas.x + "," + canvas.y + "," +canvas.width + "," + canvas.height);
        return new Robot().createScreenCapture(canvas);
    }

}
