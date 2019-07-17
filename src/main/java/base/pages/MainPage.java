package base.pages;

import core.BasePage;
import org.openqa.selenium.By;

import static base.utils.Constants.DOMAIN;

public class MainPage extends BasePage {
    private By searchField = By.name("q");

    public MainPage load() {
        goToPage("http://" + DOMAIN);
        return this;
    }

    public MainPage inputSearchText(String text) {
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(text);
        return this;
    }

    public MainPage submitSearch() {
        driver.findElement(searchField).submit();
        return this;
    }
}
