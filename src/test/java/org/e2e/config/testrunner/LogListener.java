package org.e2e.config.testrunner;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class LogListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        for(String att: result.getTestContext().getAttributeNames()) {
            Reporter.log((String) result.getTestContext().getAttribute(att), true);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        for(String att: result.getTestContext().getAttributeNames()) {
            Reporter.log((String) result.getTestContext().getAttribute(att), true);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        for(String att: result.getTestContext().getAttributeNames()) {
            Reporter.log((String) result.getTestContext().getAttribute(att), true);
        }
    }
}
