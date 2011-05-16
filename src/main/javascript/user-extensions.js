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
Selenium.prototype.getBrowserWidth = function(locator, text) {
    //TODO: also test with chrome and iexplore
    return this.browserbot.getUserWindow().outerWidth;
};

Selenium.prototype.assertEquals = function(source, target) {
    if (source != target)
        Assert.fail("" + source + "is not equal to " + target);
};

Selenium.prototype.getNumberOfVerticalScrollSteps = function(locator) {
    var element = this.page().findElement(locator);
    return Math.ceil(element.scrollHeight / element.offsetHeight);
}

Selenium.prototype.doScrollDown = function(locator) {
    var element = this.page().findElement(locator);
    element.scrollTop += element.offsetHeight;
}


