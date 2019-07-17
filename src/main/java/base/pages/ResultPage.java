package base.pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ResultPage extends BasePage {
    private By searchResults = By.cssSelector(".srg .g h3");
    private By nexButton = By.cssSelector("#pnnext");
    private By searchResultsDomain = By.cssSelector(".r > a:first-child");

    public List<String> getSearchingResults(int pageCount) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            List<WebElement> domains = driver.findElements(searchResultsDomain);
            for (WebElement domain : domains) {
                result.add(domain.getAttribute("href"));
            }
            //no reason to click last time
            if (i != pageCount - 1) {
                clickNextPage();
            }
        }
        return result;
    }

    public void openFirstResultLink() {
        openResultLink(0);
    }

    private void openResultLink(int index) {
        driver.findElements(searchResults).get(index).click();
    }

    private void clickNextPage() {
        driver.findElement(nexButton).click();
    }
}
