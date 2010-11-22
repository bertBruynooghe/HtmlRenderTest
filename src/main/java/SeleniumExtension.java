import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SeleniumExtension extends DefaultSelenium {
    public SeleniumExtension(String serverHost, int serverPort, String browserStartCommand, String browserURL) {
        super(serverHost, serverPort, browserStartCommand, browserURL);
    }

    public SeleniumExtension(CommandProcessor processor) {
        super(processor);
    }

    public void processSeleneseTestCase(String path) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource(this.getClass().getName().replace('.', '/') + ".class");
        String executedFrom = url.getPath();
        int i = executedFrom.indexOf("target");
        executedFrom = executedFrom.substring(0, i);
        try {
            DOMParser parser = new DOMParser();
            parser.parse(new InputSource(new File(executedFrom + "/" + path).getPath()));
            Document document = parser.getDocument();
            //TODO: do this using XPath instead...
            Node tBody = document.getElementsByTagName("tbody").item(0);
            NodeList trCandidateList = tBody.getChildNodes();
            for (int i1 = 0; i1 < trCandidateList.getLength(); i1++) {
                Node trCandidate = trCandidateList.item(i1);
                if (trCandidate.getNodeName().equals("TR")) {
                    NodeList tdCandidateList = trCandidate.getChildNodes();
                    List<Node> tdList = new ArrayList<Node>();
                    for (int j = 0; j < tdCandidateList.getLength(); j++)
                        if (tdCandidateList.item(j).getNodeName().equals("TD"))
                            tdList.add(tdCandidateList.item(j));

                    String secondArgument = "";
                    if (null != tdList.get(2) && null != tdList.get(2).getFirstChild())
                        secondArgument = tdList.get(2).getFirstChild().getNodeValue();
                    commandProcessor.doCommand(tdList.get(0).getFirstChild().getNodeValue(), new String[]{tdList.get(1).getFirstChild().getNodeValue(), secondArgument});
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
