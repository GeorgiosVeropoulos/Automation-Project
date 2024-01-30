package browser;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Browser {


    public enum BrowserType {
        CHROME,
        MOZILLA,
        EXPLORER,
        SAFARI;
    }


    public void setUpBrowser(BrowserType browserType) {
//        Configuration.remote = "http://localhost:4444/";
        MutableCapabilities browserOption = null;
        Configuration.browserSize = "1280x800";
        Configuration.holdBrowserOpen = false;
        Configuration.reportsFolder = "target/reportsFolder";
        Configuration.downloadsFolder = "target/downloads";

        switch (browserType) {

            case CHROME :
                browserOption = chromeOptions();
                break;
            case MOZILLA:
                break;
        }
        Configuration.browserCapabilities = browserOption;

    }

    private ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setCapability("goog:chromeOptions", Map.of("disable-popup-blocking", false));
        boolean runRemote = Boolean.parseBoolean(System.getProperty("runRemote"));
        if (runRemote) {
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--headless");
        }


        Map<String, Object> prefs = new HashMap<>();
        prefs.put("plugins.plugins_disabled", new String[]{"Chrome PDF Viewer"});
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }

    //add more browser options based on your needs.
}
