Selenium.prototype.getBrowserWidth = function(width) {
    Assert.fail();
};

Selenium.prototype.assertEquals = function(source, target) {
    if (source != target)
        Assert.fail("" + source + "is not equal to " + target);
};


