import com.thoughtworks.selenium.CommandProcessor;
import junit.framework.Assert;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
        this.runCommand(tr1.getFirstChild().getNodeValue(), tr2.getFirstChild().getNodeValue(), tr3.getFirstChild().getNodeValue());
    }

    public void processSeleneseTestCase(File file) throws FileNotFoundException {
        try {
            DOMParser parser = new DOMParser();
            parser.parse(new InputSource(file.getPath()));
            Document document = parser.getDocument();
            //TODO: do this using XPath instead...
            Node tBody = document.getElementsByTagName("tbody").item(0);
            NodeList trCandidateList = tBody.getChildNodes();
            for (int i = 0; i < trCandidateList.getLength(); i++) {
                Node trCandidate = trCandidateList.item(i);
                if (trCandidate.getNodeName().equals("TR")) {
                    NodeList tdCandidateList = trCandidate.getChildNodes();
                    List<Node> tdList = new ArrayList<Node>();
                    for (int j = 0; j < tdCandidateList.getLength(); j++)
                        if (tdCandidateList.item(j).getNodeName().equals("TD"))
                            tdList.add(tdCandidateList.item(j));
                    processSeleneseCommand(tdList.get(0), tdList.get(1), tdList.get(2));
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
