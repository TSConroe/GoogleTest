package base.pages;

import org.openqa.selenium.By;

import static base.utils.Constants.DOMAIN;

public class MainPage extends BasePage {
    private By searchField = By.name("q");

    public void load() {
        goToPage("http://" + DOMAIN);
    }

    public MainPage inputSearchText(String text) {
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(text);
        return this;
    }

    public ResultPage submitSearch() {
        driver.findElement(searchField).submit();
        return new ResultPage();
    }


}
