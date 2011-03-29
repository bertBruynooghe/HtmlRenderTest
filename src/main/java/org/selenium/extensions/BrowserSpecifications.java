package org.selenium.extensions;

import com.thoughtworks.selenium.Selenium;

public class BrowserSpecifications {
    //TODO: move this code to userExtensions.js

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