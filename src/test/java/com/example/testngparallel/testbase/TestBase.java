package com.example.testngparallel.testbase;

import browser.Browser;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.testngparallel.Reporter;
import com.example.testngparallel.listeners.TestListener;
import helpers.ScreenshotReport;
import lombok.extern.log4j.Log4j2;
import managers.SuiteManager;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import testrail.TestRailListener;

import java.io.File;
import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.open;
import static managers.SuiteManager.*;

@Log4j2
@Listeners({TestRailListener.class, TestListener.class})
public class TestBase {

//    public PageFactory page = new PageFactory();

    // the current reporter containing the extentTest and softAsserts

    public static final ThreadLocal<Reporter> reporters = new ThreadLocal<>();
    public Reporter reporter;


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext context, Method method) {
        //using reflection we can fetch the name of the parent class
        String className = method.getName();
        SuiteManager.createTest(className);
        TestListener.softasserts.set(new SoftAssert());
        reporter = new Reporter(SuiteManager.getCurrentTest(), TestListener.getSoftAssert());
        reporters.set(reporter);

        //handle browser
        Browser browser = new Browser();
        browser.setUpBrowser(Browser.BrowserType.CHROME);
        open("about:blank");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result, Method method) {
        logScreenShot();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        removeCurrentTest();
        log.info("Closing Browser!");
        Selenide.closeWebDriver();

    }

    public static void logInfo(String message) {
        logMessage(Status.INFO, message);
    }

    public static void logError(String message) {
        logMessage(Status.FAIL, message);
    }

    public static void logMessage(Status status, String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2]; // Index 2 is the caller's element
        String className = caller.getClassName();
        int lineNumber = caller.getLineNumber();
        getCurrentTest().log(status, message);
        log.info(String.format("[%s:%d] %s",
                className.substring(className.lastIndexOf('.') + 1), lineNumber,
                message));
    }

    //this is too much voodoo, but it works for what it needs to do.
    public static void logFile(String filePath) {
        File file = new File(filePath);
        String targetDirectory = "target\\";
        int targetIndex = filePath.indexOf(targetDirectory);
        String remainingPath = "";
        if (targetIndex != -1) {
            remainingPath = filePath.substring(targetIndex + targetDirectory.length());
        } else {
            log.info("Target directory not found in the file path." + filePath);
        }
        String fileLink = "<a href='../../" + remainingPath + "' target='_blank' style='color: blue;'>" +
                "Click to open downloaded file: " + file.getName() + "</a>";
        getCurrentTest().log(Status.INFO, MarkupHelper.createLabel(fileLink, ExtentColor.BLUE));
    }

    public void logStep(String stepName, String stepContext) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2]; // Index 2 is the caller's element
        String className = caller.getClassName();
        int lineNumber = caller.getLineNumber();
        getCurrentTest().info("<b><font size='5'>" + stepName + "</font></b><br>" + stepContext);
        log.info(String.format("[%s:%d] %s",
                className.substring(className.lastIndexOf('.') + 1), lineNumber,
                stepName + " : " + stepContext));
    }

    public void logScreenShot() {
        ScreenshotReport.logScreenShot(getCurrentTest());
    }

}
