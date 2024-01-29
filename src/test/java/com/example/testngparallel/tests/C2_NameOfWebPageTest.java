package com.example.testngparallel.tests;

import com.codeborne.selenide.Selenide;
import com.example.testngparallel.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;


public class C2_NameOfWebPageTest extends TestBase {

    @Test(groups = {"tools", "all", "testRail"})
    public void toolsMenu() {
        page.loadPage.goToMainPage();
        logStep("Test name of web page", "context message right below?");
        page.mainPage.waitForPageLoaded();
        String title = Selenide.title();

        Assert.assertTrue(title.contains("Georgios Veropoulos"));
    }
}