package com.example.testngparallel.tests;

import com.codeborne.selenide.Selenide;
import com.example.testngparallel.pages.base.WebTestBase;
import com.example.testngparallel.testbase.TestBase;
import org.testng.annotations.Test;


public class C2_NameOfWebPageTest extends WebTestBase {

    @Test(groups = {"tools", "all", "testRail", "par"})
    public void toolsMenu() {
        page.loadPage.goToMainPage();

        logStep("Test name of web page", "context message right below?");
        page.mainPage.waitForPageLoaded();
        String title = Selenide.title();
        reporter.assertTrue(title.contains("Georgios Veropoulos"), "Title contains the Georgios Veropoulos");
        logStep("Finish", "");
    }
}
