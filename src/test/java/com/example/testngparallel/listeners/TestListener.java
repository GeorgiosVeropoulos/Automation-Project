package com.example.testngparallel.listeners;

import helpers.CustomColorOnConsole;
import lombok.extern.log4j.Log4j2;
import managers.ConfigManager;
import managers.SuiteManager;
import org.testng.*;
import org.testng.asserts.SoftAssert;

import static helpers.CustomColorOnConsole.setColor;
import static managers.SuiteManager.flush;
import static managers.SuiteManager.getCurrentTest;

@Log4j2
public class TestListener implements ITestListener, ISuiteListener, IExecutionListener, IHookable {

    public static final ThreadLocal<SoftAssert> softasserts = new ThreadLocal<>();

    private static String resultString = "Test finished with result: ";

    public static SoftAssert getSoftAssert() {
        return softasserts.get();
    }

    @Override
    public void onExecutionStart() {
        ConfigManager.loadConfigFile();
    }

    @Override
    public void onStart(ISuite suite) {
       new SuiteManager();
    }

    @Override
    public void onFinish(ISuite suite) {
        flush();
    }

    // we steal the test in order to apply soft asserts and other voodoo shit.
    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        // we set a new soft asserts for each Test
//        softasserts.set(new SoftAssert());
        callBack.runTestMethod(testResult);

        if (testResult.getThrowable() != null) {

        } else {
            // we evaluate soft asserts when test finishes
            softasserts.get().assertAll();
        }

        switch (getCurrentTest().getStatus()) {
            case FAIL -> testResult.setStatus(2);
            case PASS -> testResult.setStatus(1);
            case SKIP -> testResult.setStatus(3);
        }

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getCurrentTest().pass("Test " + result.getMethod().getMethodName() + " PASSED!");
        log.info(setColor(resultString + getCurrentTest().getStatus().toString().toUpperCase(), CustomColorOnConsole.Color.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            getCurrentTest().fail(result.getThrowable());
        }
        resultString = resultString + getCurrentTest().getStatus().toString().toUpperCase();
        log.info(setColor(resultString, CustomColorOnConsole.Color.RED));
        getCurrentTest().fail("Test " + result.getMethod().getMethodName() + " FAILED!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getCurrentTest().skip(result.getThrowable());
        log.info(setColor(resultString, CustomColorOnConsole.Color.WHITE));
        getCurrentTest().skip("Test " + result.getMethod().getMethodName() + " SKIPPED!");
    }
}
