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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.mockito.Mockito.*;

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
