package com.example.testngparallel.assertionutils;

import com.example.testngparallel.testbase.TestBase;
import org.testng.Assert;

public class AssertionUtils {

    public void assertTrue(boolean b, String message) {
        try {
            Assert.assertTrue(b, message);
            TestBase.getExtentTest().pass(message);
        } catch (AssertionError e) {
            TestBase.getExtentTest().fail(message);
            throw new AssertionError(e);
        }
    }
}
