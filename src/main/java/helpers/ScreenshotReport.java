package helpers;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotReport {

    /**
     * Adds the screenshot to the specified {@code test}.
     * @param test to be set.
     */
    public static void logScreenShot(ExtentTest test) {
        String base64Image = captureScreen();
        String smallImageTag = "<a href='data:image/png;base64," + base64Image
                + "' data-featherlight='image'><img src='data:image/png;base64," + base64Image
                + "' style='max-height: 450px; max-width: 450px; cursor: pointer;'/></a>";
        test.log(Status.INFO, smallImageTag);
    }

    /**
     * Takes and image as {@code Base64}, so we don't need to save image files like a bunch of animals.
     * @return the {@code Base64} String.
     */
    private static String captureScreen() {
        return ((TakesScreenshot) Selenide.webdriver().driver().getWebDriver())
                .getScreenshotAs(OutputType.BASE64);
    }

}
