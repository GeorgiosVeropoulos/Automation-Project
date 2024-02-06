package com.example.testngparallel.assertionutils;

import com.aventstack.extentreports.Status;
import com.example.testngparallel.testbase.TestBase;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class AssertionListener extends TestListenerAdapter implements IInvokedMethodListener {

    @Override
    public void onTestFailure(ITestResult tr) {
        logAssertions(tr);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Do nothing before method invocation
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            String text = method.getTestMethod().getDescription();
            logAssertions(testResult);
        }
    }

    private void logAssertions(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            logFailureAssertion(testResult);
        } else if (testResult.getStatus() == ITestResult.SUCCESS) {
            logPassAssertion(testResult);
        }
    }

    private void logFailureAssertion(ITestResult testResult) {
        String failureMessage = String.format("Assertion FAILED: %s",
                getCustomMessageFromLogs(testResult));
        TestBase.getExtentTest().log(Status.FAIL, failureMessage);
    }

    private void logPassAssertion(ITestResult testResult) {
        String passMessage = String.format("Assertion PASSED: %s",
                getCustomMessageFromLogs(testResult));
        TestBase.getExtentTest().log(Status.PASS, passMessage);
    }

    private String getCustomMessageFromLogs(ITestResult testResult) {
        String outputDirectory = testResult.getTestContext().getOutputDirectory();
        Object logsAttribute = testResult.getTestContext().getAttribute("org.testng.reporter.TestReporter@Logs");
        if (logsAttribute instanceof String) {
            String logs = (String) logsAttribute;

            // Find the index of the custom message prefix
            int customMessageStartIndex = logs.indexOf("Custom Message:");
            if (customMessageStartIndex != -1) {
                // Find the end index of the custom message
                int customMessageEndIndex = logs.indexOf("\n", customMessageStartIndex);

                // Check if the end index is found
                if (customMessageEndIndex != -1) {
                    // Extract and return the custom message
                    return logs.substring(customMessageStartIndex + "Custom Message:".length(), customMessageEndIndex).trim();
                }
            }
        }
        return null;
    }
}
