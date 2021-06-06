package pom.common;

import static pom.common.EnvContext.BROWSER_TYPE.CHROME;

public class EnvContext {
    //TODO: Externalize settings to properties and read from it
    public static final BROWSER_TYPE browser_type = CHROME;
    public static final int wait_timeout = 10;
    public static final String baseUrl = "https://www.w3schools.com";

    public enum BROWSER_TYPE {
        CHROME
    }
}
