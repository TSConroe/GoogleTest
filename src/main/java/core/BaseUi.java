package core;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static core.Driver.closeThreadLocalDriver;
import static org.jsoup.helper.Validate.fail;

public abstract class BaseUi {
    private static final Logger logger = LoggerFactory.getLogger(BaseUi.class);
    public static final String BROWSER = "chrome";
    public static final String BROWSER_VERSION = System.getenv("browserVersion");
    public static WebDriver driver;

    //    public WebDriver driver = DriverConstructor.getInstance().getDriver();
    static {
        if (BROWSER.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            Driver.setThreadLocalDriver(driver);

        } else if (BROWSER.equals("firefox")) {
            WebDriverManager.firefoxdriver().version(BROWSER_VERSION).setup();
            driver = new FirefoxDriver();
        } else if (BROWSER.equals("ie")) {
            WebDriverManager.iedriver().version(BROWSER_VERSION).setup();
            driver = new InternetExplorerDriver();
        } else if (BROWSER.equals("edge")) {
            WebDriverManager.iedriver().version(BROWSER_VERSION).setup();
            driver = new EdgeDriver();
        } else {
            fail("Incorrect browser. Please check browser name");
        }
        logger.info("Starting browser");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

    }

    public void goToPage(String url) {
        driver.get(url);
//        getDriver().get(url);
    }

    public WebDriver getDriver() {
//        return this.driver;
        Driver.setThreadLocalDriver(driver);
        return Driver.getThreadLocalDriver();
    }

    public void waitFor(ExpectedCondition<WebElement> condition) {
        waitFor(condition, null);
    }

    public void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        int temp = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait webDriverWait = new WebDriverWait(driver, temp);
        webDriverWait.until(condition);
    }

    public void closeBrowser() {
        closeThreadLocalDriver();
    }

}
