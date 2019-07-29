package googleTest;

import base.pages.MainPage;
import base.pages.ResultPage;
import core.driver.DriverThreadLocalProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static base.utils.Constants.DOMAIN;

public abstract class AbstractSearchTest {
    MainPage mainPage;
    ResultPage resultPage;
    WebDriver driver;

    @AfterClass()
    public void afterFirst() {
        DriverThreadLocalProvider.quitThreadLocalDriver();
    }

    @BeforeClass()
    public void beforeClass() {
        driver = DriverThreadLocalProvider.getInstance().getDriver();
        mainPage = new MainPage();
        resultPage = new ResultPage();
        mainPage.load();
    }

    @AfterMethod()
    public void afterTest() {
        if (driver.getTitle().toLowerCase().contains(DOMAIN.split("\\.")[0])) {
            mainPage.load();
        }
    }
}
