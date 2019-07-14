package base.pages;

import core.BaseUi;
import org.openqa.selenium.By;

public class MainPage extends BaseUi {
    private By searchField = By.name("q");
    private By submitButton = By.name("btnK");


//    public MainPage() {
////        driver = super.getDriver();
////        driver.manage().window().maximize();
//
//    }

    public MainPage load() {
        goToPage("http://google.com");
        return this;
    }

    public MainPage inputSearchText(String text) {
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchField).submit();
//        waitFor(visibilityOf(driver.findElement(By.name("btnK"))), 10);
        return this;
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
//        ExpectedConditions.invisibilityOfElementLocated(submitButton);
//        waitFor(visibilityOf(price)), 10);

    }
}
