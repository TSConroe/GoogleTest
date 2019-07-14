package base.pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MainPage extends BasePage {
    private By searchField = By.name("q");

    @Step()
    public MainPage load() {
        goToPage("http://google.com");
        return this;
    }

    @Step()
    public MainPage inputSearchText(String text) {
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchField).submit();
        return this;
    }
}
