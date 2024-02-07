package com.example.testngparallel.pages;

import browser.Element;
import com.codeborne.selenide.*;
import com.example.testngparallel.pages.page.Page;
import constants.TestConstants;
import helpers.PdfUtilities;
import helpers.Sleeper;
import org.openqa.selenium.By;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage extends Page {

    private SelenideElement pTitle = $(By.id("title"));
    private SelenideElement aboutMeNavBtn = $(By.id("about-me"));
    private SelenideElement myWorkNavBtn = $(By.id("my-work"));

    private Element myWorkElement = new Element(By.id("my-work"));
    private SelenideElement workedAtNavBtn = $(By.id("worked-at"));
    private SelenideElement infoNavBtn = $(By.id("info"));

    private SelenideElement moreInforWorkingOnBtn = $(By.id("more-info-working-on"));

    private SelenideElement bottomIcons = $(By.xpath("//section//ul[@class = 'icons']"));

    private SelenideElement bottomTwitterBtn = $(By.id("bottom_twitter"));
    private SelenideElement bottomFacebookBtn = $(By.id("bottom_facebook"));
    private SelenideElement bottomInstagramBtn = $(By.id("bottom_instagram"));
    private SelenideElement bottomGithubBtn = $(By.id("bottom_github"));
    private SelenideElement bottomLinkedinBtn = $(By.id("bottom_linkedin"));


    private SelenideElement cvBtn = $(By.xpath("(//ul[@class = 'actions']//li)[1]//a"));


    public MainPage waitForPageLoaded() {
        pTitle.shouldBe(Condition.visible);
        return this;
    }

    public MainPage clickAboutMeNavBtn() {
        click(aboutMeNavBtn);
        waitForScrollingToFinish();
        return this;
    }

    public MainPage clickMyWorkNavBtn() {
//        click(myWorkNavBtn);
        myWorkElement.shouldBe(Condition.visible).click();
        waitForScrollingToFinish();
        return this;
    }

    public MainPage clickWorkedAtNavBtn() {
        click(workedAtNavBtn);
        waitForScrollingToFinish();
        return this;
    }

    public MainPage clickInfoNavBtn() {
        click(infoNavBtn);
        waitForScrollingToFinish();
        return this;
    }

    public MainPage clickCVBtn() {
        click(cvBtn);
        return this;
    }

    /**
     * @return The filePath of the pdf.
     */
    public String downloadCV() {
        clickCVBtn();
        switchToChildWindow();
        String filePath = PdfUtilities.download();
        logFile(filePath);
        switchToMainWindow();
        return filePath;
    }

    public String downloadCVwithHTTP() {
        return PdfUtilities.downloadFile(cvBtn);
    }



    public MainPage clickMoreInforWorkingOnBtn() {
        click(moreInforWorkingOnBtn);
        return this;
    }

    public MainPage checkBottomIconsGoToCorrectLinks(List<String> urls) {
        List<String> visitedUrl = new ArrayList<>();
        ElementsCollection icons = bottomIcons.findAll(By.xpath(".//a"));
        for (SelenideElement icon : icons) {
            click(icon);
            switchToChildWindow();
            visitedUrl.add(WebDriverRunner.getWebDriver().getCurrentUrl());
            Sleeper.sleepInSeconds(2); // to lazy to wait for proper elements in each social just sleep it out bro!
            switchToMainWindow();
        }
        for (String url : urls) {
            assertTrue(visitedUrl.contains(url), "verify " + url + " is contained in visited urls: " + visitedUrl);
        }
        return this;
    }

    private void verifyURLContainsValues(SelenideElement elementToBeClicked, List<String> values) {
        click(elementToBeClicked);
        switchToChildWindow();
        String visitedUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Sleeper.sleepInSeconds(2);
        switchToMainWindow();
        for (String value : values) {
            assertTrue(visitedUrl.contains(value), visitedUrl + " should contain " + value + " but DIDN'T");
        }
    }

    public MainPage checkBottomTwitterBtnGoToCorrectLink(List<String> values) {
        verifyURLContainsValues(bottomTwitterBtn, values);
        return this;
    }

    public MainPage checkBottomFacebookBtnGoToCorrectLink(List<String> values) {
        verifyURLContainsValues(bottomFacebookBtn, values);
        return this;
    }

    public MainPage checkBottomInstagramBtnGoToCorrectLink(List<String> values) {
        verifyURLContainsValues(bottomInstagramBtn, values);
        return this;
    }

    public MainPage checkBottomGithubBtnGoToCorrectLink(List<String> values) {
        verifyURLContainsValues(bottomGithubBtn, values);
        return this;
    }

    public MainPage checkBottomLinkedinBtnGoToCorrectLink(List<String> values) {
        verifyURLContainsValues(bottomLinkedinBtn, values);
        return this;
    }

}
