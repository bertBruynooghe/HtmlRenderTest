package userextensions;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.Selenium;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.selenium.extensions.SeleneseTestRunner;
import org.xml.sax.SAXException;

import java.io.IOException;

public class UserExtensionsTest {
    private static Selenium selenium;
    private static SeleneseTestRunner seleneseTestRunner;

    @BeforeClass
    static public void beforeClass() {
        HttpCommandProcessor processor = new HttpCommandProcessor("beantn0a000189", 4545, "*firefox", "http://localhost");
        seleneseTestRunner = new SeleneseTestRunner(processor);
        selenium = new DefaultSelenium(processor);
        selenium.start();
    }


    @AfterClass
    static public void tearDown() {
        selenium.stop();
    }

    @Test
    public void assertEqualsTest() throws IOException, SAXException {
        seleneseTestRunner.runTestCase("src/test/selenese/testcase/assertEqualsTest.html");
    }

    @Test
    public void runBrowserWidthTest() throws IOException, SAXException {
        seleneseTestRunner.runTestCase("src/test/selenese/testcase/browserWidthTest.html");
    }

    @Test
    public void testVerticalScrollFunctionality() throws IOException, SAXException {
        seleneseTestRunner.runTestCase("src/test/selenese/testcase/getNumberOfVerticalScrollStepsTest.html");
    }

}
