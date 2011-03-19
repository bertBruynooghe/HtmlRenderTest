package org.selenium.extensions;

import com.thoughtworks.selenium.Selenium;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BrowserSpecificationsTest {
    private static final String FIREFOX_USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-GB; rv:1.9.0.13) Gecko/2009073022 Firefox/3.0.13 (.NET CLR 3.5.30729)";
    private static final String MSIE_USER_AGENT = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; MS-RTC LM 8; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)";
    private static final String CHROME_USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.195.27 Safari/532.0";
    private static final String FIREFOX_1_5_USER_AGENT = "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.8.0.10) Gecko/20070223 CentOS/1.5.0.10-0.1.el4.centos Firefox/1.5.0.10 ";

    @Test
    public void testGetBrowserNameForFirefox3_0_13() {
        assertEquals("Firefox 3.0.13", BrowserSpecifications.createBrowserName(FIREFOX_USER_AGENT, ""));
    }

    @Test
    public void testGetBrowserNameForFirefox1_5() {
        assertEquals("Firefox 1.5.0.10", BrowserSpecifications.createBrowserName(FIREFOX_1_5_USER_AGENT, "null"));
    }

    @Test
    public void testGetBrowserNameForIE6_0() {
        assertEquals("MSIE 6.0", BrowserSpecifications.createBrowserName(MSIE_USER_AGENT, ""));
    }

    @Test
    public void testGetBrowserNameForChrome3_0_195_27() {
        assertEquals("Chrome 3.0.195.27", BrowserSpecifications.createBrowserName(CHROME_USER_AGENT, ""));
    }

    @Test
    public void testGetMinorVersionForNullString() {
        assertEquals("", BrowserSpecifications.formatVersion("null"));
    }

    @Test
    public void testGetMinorVersionForUndefined() {
        assertEquals("", BrowserSpecifications.formatVersion("undefined"));
    }

    @Test
    public void testGetMinorVersionForEmptyString() {
        assertEquals("", BrowserSpecifications.formatVersion(""));
    }

    @Test
    public void testUnknownBrowserName() {
        assertEquals("unknownBrowserXXX", BrowserSpecifications.createBrowserName("XXX", "XXX"));
    }

    @Test
    public void testFetchNavigatorProperties() {
        Selenium selenium = mock(Selenium.class);
        BrowserSpecifications browserSpecifications = new BrowserSpecifications(selenium);
        browserSpecifications.fetchNavigatorProperties();
        verify(selenium).getEval("navigator.platform");
        verify(selenium).getEval("navigator.userAgent");
        verify(selenium).getEval("navigator.appVersion");
        verify(selenium).getEval("navigator.appMinorVersion");
    }

    @Test
    public void testBrowserSpecificPath() {
        Selenium selenium = mock(Selenium.class);
        when(selenium.getEval("navigator.platform")).thenReturn("platform");
        when(selenium.getEval("navigator.userAgent")).thenReturn("userAgent");
        when(selenium.getEval("navigator.appVersion")).thenReturn("Version");
        when(selenium.getEval("navigator.appMinorVersion")).thenReturn("MinorVersion");
        BrowserSpecifications browserSpecifications = new BrowserSpecifications(selenium);
        assertEquals("platform/unknownBrowserVersionMinorVersion", browserSpecifications.getBrowserSpecificPath());
    }

}
