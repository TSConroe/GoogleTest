package googleTest;

import io.qameta.allure.Description;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class Title extends AbstractSearchTest {

    @Test()
    @Parameters({"keyWord", "domainToVerify"})
    @Description("Search for keyword 'automation' on google, verify that there is expected domain on search results  pages")
    public void searchByTextAndVerifyDomainNameTest(@Optional("testautomationday") String keyWord, @Optional("testautomationday.com") String domainToVerify) {
        List<String> domains = new ArrayList();
        mainPage.inputSearchText(keyWord)
                .submitSearch();

        List<String> urls = resultPage.getSearchingResults(5);
        resultPage.getDomainNameFromUrl(urls);
        assertThat("Title open page by key word " + keyWord + " not contain it in title", domains, hasItems(domainToVerify));
    }


}
