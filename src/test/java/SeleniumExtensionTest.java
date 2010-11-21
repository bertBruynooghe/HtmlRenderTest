import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeleniumExtensionTest {
    @Mock
    private CommandProcessor processor;

    private Selenium selenium;
    private SeleniumExtension seleniumExtension;

    @Before
    public void setUp() {
        selenium = new DefaultSelenium(processor);
        seleniumExtension = new SeleniumExtension(processor);
    }

    @Test
    public void fail_when_assert_command_does_not_return_PASSED() {
        setProcessorCommandResult(processor, "NOT PASSED");
        Assert.assertTrue("expected failure did not occur", assertionFailedOnRunCommand("assertTest"));
    }

    @Test
    public void do_not_fail_when_assert_command_does_return_PASSED() {
        setProcessorCommandResult(processor, "PASSED");
        Assert.assertFalse("unexpected failure did not occur", assertionFailedOnRunCommand("assertTest"));
    }

    @Test
    public void fail_when_verify_command_does_not_return_PASSED() {
        setProcessorCommandResult(processor, "NOT PASSED");
        Assert.assertTrue("expected failure did not occur", assertionFailedOnRunCommand("verifyTest"));
    }

    @Test
    public void do_not_fail_when_assert_command_is_no_assertion() {
        setProcessorCommandResult(processor, "NOT PASSED");
        Assert.assertFalse("unexpected failure", assertionFailedOnRunCommand("not_assert_or_verify"));
    }

    @Test
    public void process_selenese_command_calls_command_processor() {
//        String command = "command";
//        String arg1 = "arg1";
//        String arg2 = "arg2";
//        DOMParser parser = new DOMParser();
//        try {
//            parser.parse("<?xml version=\"1.0\" encoding=\"UTF-8\"?><html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\"><td>" + command + "</td>" +
//                "<td>" + arg1 + "</td>" +
//                "<td>" + arg2 + "</td></html>");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        Document document = parser.getDocument();
//            NodeList childNodes = document.getElementsByTagName("td");
//            seleniumExtension.processSeleneseCommand(childNodes.item(0), childNodes.item(1), childNodes.item(2));
//        Mockito.verify(processor).doCommand(command, new String[]{arg1, arg2});
    }

    @Test
    public void process_selenese_testcase_calls_command_processor() throws FileNotFoundException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource(this.getClass().getName().replace('.', '/') + ".class");
        String path = url.getPath();
        int i = path.indexOf("target");
        path = path.substring(0, i);

        seleniumExtension.processSeleneseTestCase(new File(path + "/src/test/java/seleneseTestCase.html"));
        //TODO: in fact, this is not correct: we should check that Selenium.runCommand is called
        Mockito.verify(processor).doCommand("command", new String[]{"arg1", "arg2"});
    }

    private static void setProcessorCommandResult(CommandProcessor processor, String result) {
        when(processor.doCommand(Mockito.anyString(), Mockito.<String[]>any()))
                .thenReturn(result);
    }

    private boolean assertionFailedOnRunCommand(String command) {
        boolean testFailed = false;
        try {
            seleniumExtension.runCommand(command);
        }
        catch (AssertionFailedError e) {
            testFailed = true;
        }
        return testFailed;
    }
}
