package com.example.testngparallel.testbase;

import browser.Browser;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.testngparallel.assertionutils.AssertionListener;
import com.example.testngparallel.pages.pagefactory.PageFactory;
import helpers.ScreenshotReport;
import lombok.extern.log4j.Log4j2;
import managers.ConfigManager;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import testrail.TestRailListener;

import java.io.File;
import java.lang.reflect.Method;

import static helpers.CustomColorOnConsole.*;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
@Listeners({TestRailListener.class, AssertionListener.class})
public class TestBase {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<String> customAssertionMessage = new ThreadLocal<>();
    public PageFactory page = new PageFactory();
    private SoftAssert softAssert = new SoftAssert();



    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        //-DreportName=YOUR_REPORT_NAME
        ConfigManager.loadConfigFile();
        String reportName = System.getProperty("reportName") != null ? System.getProperty("reportName") : "extent-report";
        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/report/".concat(reportName).concat("/report.html"));
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext context, Method method) {
        //using reflection we can fetch the name of the parent class
        String className = method.getDeclaringClass().getSimpleName();
        ExtentTest extentTest = extent.createTest(className);
        test.set(extentTest);

        //handle browser
        Browser browser = new Browser();
        browser.setUpBrowser(Browser.BrowserType.CHROME);
        open("about:blank");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        softAssert = new SoftAssert();
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result, Method method) {
        String resultString = "Test finished with result: ";

        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            String softAssertFailureMessage = "Soft assert failure(s) in the test.";
            log.info(setColor(softAssertFailureMessage, Color.RED));
            result.setStatus(2);
            getExtentTest().fail(softAssertFailureMessage);
            logScreenShot();
            extent.flush();
            throw e; // This line ensures that the test fails in TestNG if soft asserts fail
        }

        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.CREATED) {
            test.get().fail(result.getThrowable());
            resultString = resultString + getExtentTest().getStatus().toString().toUpperCase();
            log.info(setColor(resultString, Color.RED));
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.get().skip(result.getThrowable());
            log.info(setColor(resultString, Color.WHITE));
        } else {
            test.get().pass("Test passed");
            log.info(setColor(resultString, Color.GREEN));
        }
        logScreenShot();
        extent.flush();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        test.remove();
        log.info("Closing Browser!");
        Selenide.closeWebDriver();

    }

    public static void logMessage(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2]; // Index 2 is the caller's element
        String className = caller.getClassName();
        int lineNumber = caller.getLineNumber();
        getExtentTest().info(message);
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
        getExtentTest().log(Status.INFO, MarkupHelper.createLabel(fileLink, ExtentColor.BLUE));
    }

    public void assertTrue(boolean b) {
        try {
            Assert.assertTrue(b);
            getExtentTest().pass("assertion passed!");
        } catch (AssertionError e) {
            getExtentTest().fail("assertion failed!");
            throw new AssertionError(e);
        }

    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }

    public void assertTrue(boolean b, String message) {
        try {
            Assert.assertTrue(b, message);
            getExtentTest().pass(message);
        } catch (AssertionError e) {
            getExtentTest().fail(message);
            throw new AssertionError(e);
        }
    }

    public void logStep(String stepName, String stepContext) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2]; // Index 2 is the caller's element
        String className = caller.getClassName();
        int lineNumber = caller.getLineNumber();
        getExtentTest().info("<b><font size='5'>" + stepName + "</font></b><br>" + stepContext);
        log.info(String.format("[%s:%d] %s",
                className.substring(className.lastIndexOf('.') + 1), lineNumber,
                stepName + " : " + stepContext));
    }

    public void logScreenShot() {
        ScreenshotReport.logScreenShot(getExtentTest());
    }

    public static ExtentTest getExtentTest() {
        return test.get();
    }

}
