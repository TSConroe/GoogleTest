package core;

import org.openqa.selenium.WebDriver;

public class Driver {
    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();

    public static WebDriver getThreadLocalDriver() {
        return threadLocal.get();
    }

    public static void setThreadLocalDriver(WebDriver driver) {
        threadLocal.set(driver);
    }

    public static void closeThreadLocalDriver(){
        if (threadLocal.get()!=null) {
            threadLocal.get().quit();
        }
    }


}
