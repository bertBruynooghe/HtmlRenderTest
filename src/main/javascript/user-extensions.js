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


