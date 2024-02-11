package testrail;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRailListener implements ITestListener {

    // Implement other methods from ITestListener as needed


    @Override
    public void onTestSuccess(ITestResult result) {
        int testCaseId = getTestCaseId(result.getTestClass().getName());
        updateTestRailResult(testCaseId, TestRailResult.PASS);
    }

//    @Override
//    public void onTestSkipped(ITestResult result) {
//        String testCaseName = result.getMethod().getMethodName();
//        int testCaseId = getTestCaseId(result.getTestClass().getName());
//        updateTestRailResult(testCaseId, "SKIPPED");
//    }

    @Override
    public void onTestFailure(ITestResult result) {
        int testCaseId = getTestCaseId(result.getTestClass().getName());
        updateTestRailResult(testCaseId, TestRailResult.FAIL);
    }

    @Override
    public void onStart(ITestContext context) {
    }

    /**
     * @return The testrail run name from the testrail.run.name in your .properties file.
     */
    public static String getTestRunName() {
        String testRunName = System.getProperty("testrail.run.name");
        if (testRunName == null || testRunName.isEmpty()) {
            throw new TestRailException("!!!No testrail.run.name was set in .properties file!!!");
        }
        return testRunName;
    }

    /**
     * @return The bool value from the testrail.enabled in your .properties file.
     */
    public static boolean getTestRailEnabled() {
        String testRailEnabled = System.getProperty("testrail.enabled");
        if (testRailEnabled == null || testRailEnabled.isEmpty()) {
            throw new TestRailException("!!!No testrail.enabled was set in .properties file!!!");
        }
        return Boolean.parseBoolean(testRailEnabled);
    }

    /**
     * @return The project name from the project.name in your .properties file.
     */
    public static String getProjectName() {
        String projectName = System.getProperty("project.name");
        if (projectName == null || projectName.isEmpty()) {
            throw new TestRailException("!!!No project.name was set in .properties file!!!");
        }
        return projectName;
    }

    /**
     * Gets the Test case {@code ID} from the class of the Test.
     * ex: C1Test-> id = 1.
     * @param className of the Test we want the {@code ID} from.
     * @return returns the {@code ID} of the Test as an int.
     */
    private int getTestCaseId(String className) {
        Pattern pattern = Pattern.compile("C(\\d+)_"); //this will select the number between C and _ from the TestName!
        Matcher matcher = pattern.matcher(className);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new TestRailException("Test case ID not found in class name: " + className);
    }

    private void updateTestRailResult(int testCaseId, TestRailResult testRailResult) {
        TestRailAPI.updateTestResult(testCaseId, testRailResult);
    }
}