package com.example.testngparallel.pages.page;


import com.codeborne.selenide.*;
import com.example.testngparallel.Reporter;
import com.example.testngparallel.testbase.TestBase;
import helpers.Sleeper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static constants.TestConstants.SHORT_TIMEOUT;
import static java.time.Duration.ofSeconds;

@Log4j2
public class Page {

    // The reporter for the current executed Test.
    public Reporter reporter;

    public Page() {
        reporter = TestBase.reporters.get();
    }

    public void logFile(String filePath) {
        TestBase.logFile(filePath);
    }


    public void openURL(String url) {
        WebDriverRunner.getWebDriver().get(url);
        log.info("go to {}", url);
    }


    /**
     * <h3>Click method that waits for the element to be clickable.</h3>
     * <p>Will wait a maximum of the {@code timeout.short} set in .properties.</p>
     * @param element to be clicked!
     */
    protected void click(SelenideElement element) {
        element.click(ClickOptions.withTimeout(Duration.ofSeconds(SHORT_TIMEOUT)));
        log.info("Element: " + element + " clicked!" );
    }

    public void waitForScrollingToFinish() {
        long initialScrollPos = (long) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return window.scrollY;");
        for (int i = 0; i < 5; i++) {
            Sleeper.sleepInMillis(500L);
            long currentScrollPos = (long) ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("return window.scrollY;");
            if (currentScrollPos == initialScrollPos) {
                log.info("scrolling was done!");
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

    public WebElement getElement(By by) {
        return WebDriverRunner.getWebDriver().findElement(by);
    }
}
