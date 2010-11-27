package org.selenium.extensions;

import com.thoughtworks.selenium.CommandProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SeleneseTestRunnerTest {
    @Mock
    private CommandProcessor processor;

    private SeleneseTestRunner seleneseTestRunner;

    @Before
    public void setUp() {
        seleneseTestRunner = new SeleneseTestRunner(processor);
    }

    @Test
    public void run_testcase_calls_command_processor() throws IOException, SAXException {
        seleneseTestRunner.runTestCase("/src/test/java/org/selenium/extensions/dummySeleneseTestCase.html");
        verify(processor).doCommand("command", new String[]{"arg1", "arg2"});
    }
}
