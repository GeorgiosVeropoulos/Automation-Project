package com.example.testngparallel;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * This class will be used to wrap the extent test and provide info to all Tests inside the ExtentReport.
 */
public class Reporter {

    private final ExtentTest extentTest;
    private final SoftAssert softAssert;


    public Reporter(ExtentTest test, SoftAssert softAssert) {
        this.extentTest = test;
        this.softAssert = softAssert;
    }



    //<editor-fold desc="LOG MESSAGES">
    public void info(String message) {
        extentTest.log(Status.INFO, message);
    }

    public void fail(String message) {
        extentTest.log(Status.FAIL, message);
    }

    public void pass(String message) {
        extentTest.log(Status.PASS, message);
    }
    //</editor-fold>

    // <editor-fold desc="HARD ASSERTS">

    public Reporter assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            pass(message);
        } catch (AssertionError error) {
            fail("Tried to " + message + " but found result " + condition);
            throw new AssertionError(error);
        }
        return this;
    }

    public Reporter assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
            pass(message);
        } catch (AssertionError error) {
            fail("Tried to " + message + " but found result " + condition);
            throw new AssertionError(error);
        }
        return this;
    }

    public Reporter assertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            pass(message);
        } catch (AssertionError error) {
            fail("ERROR for " + actual + " == " + expected + " message was: " + message);
            throw new AssertionError(error);
        }
        return this;
    }

    //</editor-fold>

    //<editor-fold desc="SOFT ASSERTS">
    public Soft soft() {
        return new Soft();
    }

    public class Soft {

        public Soft assertTrue(boolean condition, String message) {
            if (condition) {
                pass(message + condition);
            } else  {
                fail(message + condition);
            }
            softAssert.assertTrue(condition, message);
            return this;
        }

        public Soft assertFalse(boolean condition, String message) {
            if (!condition) {
                pass(message + condition);
            } else  {
                fail(message + condition);
            }
            softAssert.assertFalse(condition, message);
            return this;
        }
        // rework this
        public Soft assertEquals(Object actual, Object expected, String message) {
            try {
                Assert.assertEquals(actual, expected, message);
                pass(message);
            } catch (AssertionError error) {
                softAssert.assertEquals(actual, expected, message);
                fail("ERROR for " + actual + " == " + expected + " message was: " + message);
            }
            return this;
        }
    }
    //</editor-fold>

}
