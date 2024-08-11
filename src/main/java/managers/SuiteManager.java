package managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SuiteManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public SuiteManager() {
        String reportName = System.getProperty("reportName") != null ? System.getProperty("reportName") : "extent-report";
        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/report/".concat(reportName).concat("/report.html"));
        //temporary solution to enable scrolling on the detail with the usage of middlemouseclick and drag.
        htmlReporter.config().setCss("div.test-content-detail {overflow: auto;}");
        extent.attachReporter(htmlReporter);
    }


    public static void createTest(String testName) {
        test.set(extent.createTest(testName));
    }

    public static ExtentTest getCurrentTest() {
        return test.get();
    }

    public static void removeCurrentTest() {
        test.remove();
    }

    public static void flush() {
        extent.flush();
    }

}
