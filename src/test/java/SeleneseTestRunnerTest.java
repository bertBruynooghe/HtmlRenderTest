import com.thoughtworks.selenium.CommandProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileNotFoundException;

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
    public void process_selenese_testcase_calls_command_processor() throws FileNotFoundException {
        seleneseTestRunner.runTestCase("/src/test/java/dummySeleneseTestCase.html");
        verify(processor).doCommand("command", new String[]{"arg1", "arg2"});
    }
}
