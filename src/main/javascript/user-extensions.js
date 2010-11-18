Selenium.prototype.getBrowserWidth = function(locator, text) {
    //TODO: also test with chrome and iexplore
    return this.browserbot.getUserWindow().outerWidth;
};

Selenium.prototype.assertEquals = function(source, target) {
    if (source != target)
        Assert.fail("" + source + "is not equal to " + target);
};

Selenium.prototype.getNumberOfVerticalScrollSteps = function(locator, text) {
    return 0;
}