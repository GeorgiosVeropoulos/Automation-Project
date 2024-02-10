package com.example.testngparallel.tests;

import com.example.testngparallel.testbase.TestBase;
import managers.DataManager;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

public class C1_VerifyLinksWorkInInfoSectionTest extends TestBase {

    private  List<String> twitterValues;
    private  List<String> facebookValues;
    private  List<String> instagramValues;
    private  List<String> githubValues;
    private  List<String> linkedInValues;

    @BeforeMethod()
    public void setUp() {
        Map<String, Object> data = DataManager.getTestData();
        twitterValues = DataManager.getListOfStrings(data, "twitter");
        facebookValues = DataManager.getListOfStrings(data, "facebook");
        instagramValues = DataManager.getListOfStrings(data, "instagram");
        githubValues = DataManager.getListOfStrings(data, "github");
        linkedInValues = DataManager.getListOfStrings(data, "linkedin");

    }

    @Test(groups = {"mainPage", "all", "testRail"}, description = "")
    public void verifyLinksWorkInInfoSectionTest() {
        logMessage("Test mainPage search");
        page.loadPage.goToMainPage();
        page.mainPage
                .checkBottomTwitterBtnGoToCorrectLink(twitterValues)
                .checkBottomFacebookBtnGoToCorrectLink(facebookValues)
                .checkBottomInstagramBtnGoToCorrectLink(instagramValues)
                .checkBottomGithubBtnGoToCorrectLink(githubValues)
                .checkBottomLinkedinBtnGoToCorrectLink(linkedInValues);
    }

}
