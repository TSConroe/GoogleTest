package googleTest;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class DomainName extends AbstractSearchTest {

    @Parameters({"keyWord"})
    @Test()
    @Description("Search for keyword on google, open the first link on search results page and verify that title contains searched word")
    public void searchByTextAndVerifyTitleTest(@Optional("automation") String keyWord) {
        mainPage.inputSearchText(keyWord)
                .submitSearch();
        resultPage.openFirstResultLink();
        assertThat("Title open page by key word" + keyWord + "not contain it in title", driver.getTitle().toLowerCase(), containsString(keyWord.toLowerCase()));
    }
}
