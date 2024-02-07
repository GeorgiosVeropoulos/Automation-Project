package com.example.testngparallel.tests;

import com.example.testngparallel.testbase.TestBase;
import managers.DataManager;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

public class C1_VerifyLinksWorkInInfoSectionTest extends TestBase {

    private Map<String, Object> data;

    @BeforeMethod()
    public void setUp() {
        data = DataManager.getTestData();
    }

    @Test(groups = {"mainPage", "all", "testRail"}, description = "")
    public void verifyLinksWorkInInfoSectionTest() {
        logMessage("Test mainPage search");
        page.loadPage.goToMainPage();
        page.mainPage
                .checkBottomTwitterBtnGoToCorrectLink((List<String>) data.get("twitter"))
                .checkBottomFacebookBtnGoToCorrectLink((List<String>) data.get("facebook"))
                .checkBottomInstagramBtnGoToCorrectLink((List<String>) data.get("instagram"))
                .checkBottomGithubBtnGoToCorrectLink((List<String>) data.get("github"))
                .checkBottomLinkedinBtnGoToCorrectLink((List<String>) data.get("linkedin"));
    }

}
