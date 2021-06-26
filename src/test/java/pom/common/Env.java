package pom.common;

import utils.CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Env {

    private static Properties runProperties = null;
    static {
        runProperties = new Properties();
        try {
            runProperties.load(new FileInputStream(CommonUtils.getFileFromClasspath("suites/run.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getProperty(String key){
        String prop = runProperties.getProperty(key);
        return (prop != null) ? prop : "";
    }

    public static class Run {
        public static Browser.Type browser;
        public static class Wait {
            public static int timeout;
        }
        public static class Options {
            public static boolean headless;
            public static boolean inspect;
        }
    }

    public static class App {
        public static String url;
    }

    public static void load(){
        String browser = getProperty("run.browser");
        String runWaitTimeout = getProperty("run.wait.timeout");
        String runOptionsHeadless = getProperty("run.options.headless");
        String runOptionsInspect = getProperty("run.options.inspect");
        String appUrl = getProperty("app.url");

        Run.browser = (!browser.isBlank()) ? Browser.Type.valueOf(browser.toUpperCase()) : Browser.Type.SAFARI;
        Run.Wait.timeout = (runWaitTimeout.isBlank()) ? 10 : Integer.valueOf(runWaitTimeout);
        Run.Options.headless = (runOptionsHeadless.isBlank()) ? false : Boolean.valueOf(runOptionsHeadless);
        Run.Options.inspect = (runOptionsInspect.isBlank()) ? false : Boolean.valueOf(runOptionsInspect);
        App.url = appUrl;
    }
}
