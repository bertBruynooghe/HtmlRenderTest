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
        HttpCommandProcessor processor = new HttpCommandProcessor("localhost", 4545, "*firefox", "http://localhost");
        //HttpCommandProcessor processor = new HttpCommandProcessor("localhost", 4545, "*googlechrome", "http://localhost");

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

    @Test
    public void testInjectJQuery() throws Exception {
        seleneseTestRunner.runTestCase("src/test/selenese/testcase/injectJQuery.html");
    }

}
