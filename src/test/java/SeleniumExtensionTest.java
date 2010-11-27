import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;
import junit.framework.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SeleniumExtensionTest {
    @Test
    public void testGetCommandProcessor() throws NoSuchFieldException, IllegalAccessException {
        CommandProcessor processor = mock(CommandProcessor.class);
        DefaultSelenium selenium = new DefaultSelenium(processor);
        Assert.assertSame(processor, new SeleniumExtension(selenium).getProcessor());
    }

    //next things to do:
    //screencomparison
}

