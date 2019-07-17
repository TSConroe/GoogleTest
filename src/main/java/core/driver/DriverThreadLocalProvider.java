package core.driver;

import base.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.jsoup.helper.Validate.fail;

@Slf4j
public class DriverThreadLocalProvider {
    private static final Map<String, Supplier<WebDriver>> webDriverMap = new HashMap<String, Supplier<WebDriver>>() {{
        put("chrome", () -> {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        });
        put("firefox", () -> {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        });
    }};


    public DriverThreadLocalProvider() {
        if (ThreadLocalDriver.getDriver() == null) {
            if (!webDriverMap.containsKey(Constants.BROWSER)) {
                fail(String.format("Incorrect browser '%s' . Please check browser name", Constants.BROWSER));
            }
            log.info("Starting browser");

            WebDriver local = webDriverMap.get(Constants.BROWSER).get();
            local.manage().window().setSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
            local.manage().deleteAllCookies();
            local.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            local.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            local.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

            ThreadLocalDriver.setDriver(local);

        }
    }

    public static DriverThreadLocalProvider getInstance() {
        return new DriverThreadLocalProvider();
    }

    public WebDriver getDriver() {
        return ThreadLocalDriver.getDriver();
    }

    public static void quitThreadLocalDriver() {
        ThreadLocalDriver.quitThreadLocalDriver();
    }

    private static class ThreadLocalDriver {
        private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

        private static WebDriver getDriver() {
            return threadLocal.get();
        }

        private static void setDriver(WebDriver driver) {
            threadLocal.set(driver);
        }

        private static void quitThreadLocalDriver() {
            if (threadLocal.get() != null) {
                threadLocal.get().quit();
                threadLocal.remove();
                log.info("Close browser");
            }
        }
    }
}
