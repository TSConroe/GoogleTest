package googleTest;

import base.pages.MainPage;
import base.pages.ResultPage;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static base.utils.Utils.getDomainName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.testng.Assert.fail;

public class GoogleSearch extends MainPage {
    private MainPage mainPage;
    private ResultPage resultPage;

    @BeforeClass()
    public void beforeFirst() {
        mainPage = new MainPage();
        resultPage = new ResultPage();
        mainPage.load();
    }

    @AfterMethod()
    public void afterTest() {
        if (driver.getTitle().equals("Google")) {
            mainPage.load();
        }
    }

    @AfterClass()
    public void afterFirst() {
        mainPage.closeBrowser();
    }

    @Parameters({"keyWord"})
    @Test()
    @Description("Search for keyword on google, open the first link on search results page and verify that title contains searched word")
    public void searchByTextAndVerifyTitle(@Optional("123") String keyWord) {
        if (keyWord.isEmpty()) {
            fail("Key word is empty, noting to check!");
        }

        mainPage.inputSearchText(keyWord);
        resultPage.openFirstResultLink();
        assertThat("GoogleSearch open page by key word" + keyWord + "not contain it in title", driver.getTitle().toLowerCase(), containsString(keyWord.toLowerCase()));
    }

    @Parameters({"keyWord", "domainToVerify"})
    @Test()
    @Description("Search for keyword 'automation' on google, verify that there is expected domain on search results  pages")
    public void searchByTextAndVerifyDomainName(@Optional("testautomationday") String keyWord, @Optional("testautomationday.com") String domainToVerify) {
        List<String> domains = new ArrayList();
        mainPage.inputSearchText(keyWord);

        List<String> urls = resultPage.getSearchingResults(5);
        for (String url : urls) {
            domains.add(getDomainName(url));
        }
        assertThat("GoogleSearch open page by key word " + keyWord + " not contain it in title", domains, hasItems(domainToVerify));
    }

}
