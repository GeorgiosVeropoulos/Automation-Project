package constants;

import java.nio.file.Paths;

public class TestConstants {

    public static final String WEB_PAGE_URL = "https://www.georgeveropoulos.com/";
    public static final String DOWNLOADS_FOLDER = Paths.get(System.getProperty("user.dir"), "target", "downloads").toString();

    public static final int SHORT_TIMEOUT = Integer.parseInt(System.getProperty("timeout.short"));
    public static final int MEDIUM_TIMEOUT = Integer.parseInt(System.getProperty("timeout.medium"));
    public static final int LONG_TIMEOUT = Integer.parseInt(System.getProperty("timeout.long"));
}
