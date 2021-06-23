package pom.common;

import static pom.common.EnvContext.BROWSER_TYPE.*;

public class EnvContext {
    //TODO: Externalize settings to properties and read from it
    public static final BROWSER_TYPE defaultBrowser = SAFARI;
    public static final int waitTimeout = 10;
    public static final String baseUrl = "https://www.w3schools.com";
    public static final boolean isHeadless = false;

    public enum BROWSER_TYPE {
        CHROME,
        SAFARI
    }
}
