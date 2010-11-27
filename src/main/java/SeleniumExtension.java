import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;

import java.lang.reflect.Field;

public class SeleniumExtension {
    private final DefaultSelenium selenium;

    public SeleniumExtension(DefaultSelenium selenium) {
        this.selenium = selenium;
    }

    public CommandProcessor getProcessor() throws NoSuchFieldException, IllegalAccessException {
        Field field = DefaultSelenium.class.getDeclaredField("commandProcessor");
        field.setAccessible(true);
        return (CommandProcessor) field.get(selenium);
    }
}
