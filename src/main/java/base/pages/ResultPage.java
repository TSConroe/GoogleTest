package base.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static base.utils.Utils.getDomainName;

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

    public ResultPage openFirstResultLink() {
        openResultLink(0);
        return this;
    }

    private ResultPage openResultLink(int index) {
        driver.findElements(searchResults).get(index).click();
        return this;
    }

    private ResultPage clickNextPage() {
        driver.findElement(nexButton).click();
        return this;
    }

    public List<String> getDomainNameFromUrl(List<String> urls) {
        List<String> domains = new ArrayList<>();
        for (String url : urls) {
            domains.add(getDomainName(url));
        }
        return domains;
    }
}
