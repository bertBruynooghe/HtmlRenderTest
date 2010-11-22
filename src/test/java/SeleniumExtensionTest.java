import com.thoughtworks.selenium.CommandProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SeleniumExtensionTest {
    @Mock
    private CommandProcessor processor;

    private SeleniumExtension seleniumExtension;

    @Before
    public void setUp() {
        seleniumExtension = new SeleniumExtension(processor);
    }

    @Test
    public void process_selenese_testcase_calls_command_processor() throws FileNotFoundException {
        seleniumExtension.processSeleneseTestCase("/src/test/java/seleneseTestCase.html");
        verify(processor).doCommand("command", new String[]{"arg1", "arg2"});
    }
}
