package helper;

import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class UITestRegistry {
    private static final UITestRegistry instance = new UITestRegistry();
    private final Map<Thread, UITest> registry = new ConcurrentHashMap<>();

    private UITestRegistry(){ }

    public static UITestRegistry getInstance(){ return instance;}

    public void removeCurrentRunningTest() { registry.remove(Thread.currentThread()); }

    public UITest getCurrentRunningUITest(){ return registry.get(Thread.currentThread());}

    public void registerUITest(UITest uiTest){
        registry.put(Thread.currentThread(), uiTest);
    }
}
