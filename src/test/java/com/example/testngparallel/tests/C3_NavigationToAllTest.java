package com.example.testngparallel.tests;

import com.example.testngparallel.pages.base.WebTestBase;
import com.example.testngparallel.testbase.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class C3_NavigationToAllTest extends WebTestBase {

    @BeforeMethod
    public void setUp() {
        logInfo("this was before the Test Started!");

    }


    @Test(groups = {"navigation","all"})
    public void navigationToAllTools() {
        logInfo("Test mainPage navigationToAllTools");
        page.loadPage.goToMainPage();

        logStep("Step 1", "Navigate to My Work");
        page.mainPage.clickMyWorkNavBtn();
        logScreenShot();

        logStep("Step 2", "Navigate to Info");
        page.mainPage.clickInfoNavBtn();
        logScreenShot();

        logStep("Step 3", "Navigate to About Me");
        page.mainPage.clickAboutMeNavBtn();
        logScreenShot();

        logStep("Step 4", "Navigate to Worked At");
        page.mainPage.clickWorkedAtNavBtn();
        logScreenShot();

    }
}
