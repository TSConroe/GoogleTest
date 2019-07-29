package base.pages;


import core.driver.DriverThreadLocalProvider;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {


    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverThreadLocalProvider.getInstance().getDriver();
    }

    protected void goToPage(String url) {
        driver.get(url);
    }
}
