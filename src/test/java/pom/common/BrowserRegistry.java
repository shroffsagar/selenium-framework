package pom.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BrowserRegistry {
    private static final Map<Thread, Browser> registry = new ConcurrentHashMap<>();

    private BrowserRegistry(){ }

    static void removeBrowserFromRegistry() { registry.remove(Thread.currentThread()); }

    static Browser getCurrentRunningBrowser(){ return registry.get(Thread.currentThread());}

    static void registerCurrentRunningBrowser(Browser browser) {
        registry.put(Thread.currentThread(), browser);
    }
}
