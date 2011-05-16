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

public class BrowserSpecifications {
    String platform;
    String userAgent;
    String version;
    String minorVersion;
    private final Selenium selenium;

    public BrowserSpecifications(Selenium selenium) {
        this.selenium = selenium;
    }

    public String getBrowserSpecificPath() {
        fetchNavigatorProperties();

        StringBuffer browserSpec = new StringBuffer(platform);
        browserSpec.append("/");
        String browserName = createBrowserName(userAgent, formatVersion(version));
        browserSpec.append(browserName);

        String minorVersionStr = formatVersion(minorVersion);
        browserSpec.append(minorVersionStr);
        return browserSpec.toString();
    }

    void fetchNavigatorProperties() {
        if (null == platform) {
            platform = selenium.getEval("navigator.platform");
            userAgent = selenium.getEval("navigator.userAgent");
            version = selenium.getEval("navigator.appVersion");
            minorVersion = selenium.getEval("navigator.appMinorVersion");
        }
    }

    public static String createBrowserName(String browserName, String version) {
        if (browserName.contains("MSIE")) {
            browserName = browserName.replaceAll("(.*)(MSIE [^;]*)(.*)", "$2");
        } else if (browserName.contains("Firefox")) {
            browserName = browserName.replaceAll("(.*)(Firefox/[^ ]*)(.*)", "$2");
        } else if (browserName.contains("Chrome")) {
            browserName = browserName.replaceAll("(.*)(Chrome/[^ ]*)(.*)", "$2");
        } else
            browserName = "unknownBrowser" + version;
        browserName = browserName.replaceAll("/", " ");
        return browserName;
    }

    static String formatVersion(String value) {
        String minorVersionStr = "";
        if (null != value && !value.equals("undefined") && !value.equals("null")) {
            minorVersionStr = value.replaceAll("(;)([^;]*)(;)", "$2");
        }
        return minorVersionStr;
    }
}