/** Copyright 2011 Bert Bruynooghe
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.selenium.extensions;

import com.thoughtworks.selenium.CommandProcessor;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SeleneseTestRunner {
    public SeleneseTestRunner(CommandProcessor processor) {
        this.processor = processor;
    }

    private CommandProcessor processor;

    public void runTestCase(String path) throws IOException, SAXException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource(this.getClass().getName().replace('.', '/') + ".class");
        String executedFrom = url.getPath();
        int i = executedFrom.indexOf("target");
        executedFrom = executedFrom.substring(0, i);
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

                processor.doCommand(getArgument(tdList, 0), new String[]{getArgument(tdList, 1), getArgument(tdList, 2)});
            }
        }
    }

    private String getArgument(List<Node> tdList, int index) {
        String secondArgument = "";
        if (null != tdList.get(index) && null != tdList.get(index).getFirstChild())
            secondArgument = tdList.get(index).getFirstChild().getNodeValue();
        return secondArgument;
    }
}
