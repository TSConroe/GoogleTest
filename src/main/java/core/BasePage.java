package core;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static core.Driver.closeThreadLocalDriver;
import static org.jsoup.helper.Validate.fail;

public abstract class BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    private static final String BROWSER = System.getenv("BROWSER");
    private static final int height = Integer.parseInt(System.getenv("height"));
    private static final int width = Integer.parseInt(System.getenv("width"));
    protected static WebDriver driver;
    private static Dimension resolution = new Dimension(width, height);

    static {
        if (BROWSER.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (BROWSER.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (BROWSER.equals("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else {
            fail("Incorrect browser. Please check browser name");
        }
        logger.info("Starting browser");
        Driver.setThreadLocalDriver(driver);
        driver.manage().window().setSize(resolution);
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

//    public void waitFor(ExpectedCondition<WebElement> condition) {
//        waitFor(condition, null);
//    }

    public void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        int temp = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait webDriverWait = new WebDriverWait(driver, temp);
        webDriverWait.until(condition);
    }

    public void closeBrowser() {
        closeThreadLocalDriver();
    }

}
