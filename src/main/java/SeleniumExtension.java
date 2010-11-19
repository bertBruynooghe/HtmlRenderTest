import com.thoughtworks.selenium.CommandProcessor;
import junit.framework.Assert;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.ParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeleniumExtension {
    private final CommandProcessor commandProcessor;

    public SeleniumExtension(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public void runCommand(String command, String... args) {
        if (commandProcessor.doCommand(command, args) != "PASSED") {
            if (command.startsWith("verify") || command.startsWith("assert"))
                Assert.fail();
        }
    }

    public void processSeleneseCommand(Node tr1, Node tr2, Node tr3) {
        this.runCommand(tr1.getFirstChild().getText(), tr2.getFirstChild().getText(), tr3.getFirstChild().getText());
    }

    public void processSeleneseTestCase(File file) throws FileNotFoundException, ParserException {
        Scanner scanner = new Scanner(file);
        StringBuffer buffer = new StringBuffer();
        try {
            while (scanner.hasNextLine())
                buffer.append(scanner.nextLine());
        }
        finally {
            scanner.close();
        }

        Parser parser = new Parser(buffer.toString());

        NodeIterator nodeIterator = parser.extractAllNodesThatMatch(new CssSelectorNodeFilter("table td")).elements();
        //TODO: seems we have problems expressing "tbody tr"; above expression is plain wrong
        List<Node> list = new ArrayList<Node>();
        while (nodeIterator.hasMoreNodes())
            list.add(nodeIterator.nextNode());


    }
}
