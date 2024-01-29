package com.example.testngparallel.pages.page;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.example.testngparallel.testbase.TestBase;
import helpers.Sleeper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

@Log4j2
public class Page {

    public void log(String message) {
        TestBase.logMessage(message);
    }

    public void logFile(String filePath) {
        TestBase.logFile(filePath);
    }

    public void assertTrue(boolean b, String message) {
        TestBase.assertTrue(b, message);
    }


    public void openURL(String url) {
        WebDriverRunner.getWebDriver().get(url);
        log("go to " + url);
    }




    protected void click(SelenideElement element) {
        element.shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        log("Button " + element + " clicked!");
    }

    public void waitForScrollingToFinish() {
        long initialScrollPos = (long) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return window.scrollY;");
        for (int i = 0; i < 5; i++) {
            Sleeper.sleepInMillis(500L);
            long currentScrollPos = (long) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return window.scrollY;");
            if (currentScrollPos == initialScrollPos) {
                log("scrolling was done!");
                break;
            }
            initialScrollPos = currentScrollPos;
        }
    }

    public void switchToChildWindow() {
        Selenide.switchTo().window(1);
    }

    public void switchToMainWindow() {
        Selenide.closeWindow();
        Selenide.switchTo().window(0);
    }
}
