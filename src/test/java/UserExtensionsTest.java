import com.thoughtworks.selenium.HttpCommandProcessor;
import org.junit.Test;

public class UserExtensionsTest {
    @Test
    public void runSeleneseTestCases() {
        HttpCommandProcessor processor = new HttpCommandProcessor("beantn0a000189", 4545, "*firefox", "http://localhost");
        SeleniumExtension selenium = new SeleniumExtension(processor);
        selenium.start();
        selenium.processSeleneseTestCase("src/test/selenese/testcase/assertEqualsTest.html");
        selenium.processSeleneseTestCase("src/test/selenese/testcase/browserWidthTest.html");
        selenium.processSeleneseTestCase("src/test/selenese/testcase/getNumberOfVerticalScrollStepsTest.html");
        selenium.stop();
    }
}
