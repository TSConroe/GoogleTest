package base.pages;

import core.BaseUi;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ResultPage extends BaseUi {
    private By searchResults = By.cssSelector(".srg .g h3");
    private By nexButton = By.cssSelector("#pnnext");
    private By searchResultsDomain = By.cssSelector(".r > a:first-child");

//    public ResultPage() {
////        driver = super.getDriver();
//
//    }

    public List<String> getSearchingResults() {
        //only from the first page
        return getSearchingResults(0);
    }

    public List<String> getSearchingResults(int pageCount) {
        List<String> result = new ArrayList<String>();
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
