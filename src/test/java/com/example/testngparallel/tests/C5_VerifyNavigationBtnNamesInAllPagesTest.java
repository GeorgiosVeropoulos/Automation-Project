package com.example.testngparallel.tests;

import com.example.testngparallel.pages.base.WebTestBase;
import com.example.testngparallel.testbase.TestBase;
import managers.DataManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class C5_VerifyNavigationBtnNamesInAllPagesTest extends WebTestBase {

    private List<String> main;

    private List<String> generic;

    @BeforeMethod
    public void setUp() {
        Map<String, Object> data = DataManager.getTestData();
        main = DataManager.getListOfStrings(data, "main");
        generic = DataManager.getListOfStrings(data, "generic");
    }

    @Test(groups = "all")
    public void c5_VerifyNavigationBtnNamesInAllPagesTest() {

        logStep("Precondition", "Open webPage");
        page.loadPage.goToMainPage();

        logStep("Step 1", "Check navigation btn names are correct in MainPage");
        page.mainPage.checkNavigationBtnsNames(main);

        logStep("Step 2", "Navigate to Generic Page by clicking the btn," +
                "More Info/Working on");
        page.mainPage.navigateToGenericPage();

        logStep("Step 3", "Check navigation btn names are correct in GenericPage");
        page.genericPage.checkNavigationBtnsNames(generic);


    }
}
