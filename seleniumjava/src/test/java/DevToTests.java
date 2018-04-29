import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.PostPage;
import page.ProfilePage;
import page.SearchPage;
import utils.WebDriverUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class DevToTests {
    private WebDriver driver;
    private HomePage homePage;
    private SearchPage searchPage;
    private PostPage postPage;
    private ProfilePage profilePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        postPage = new PostPage(driver);
        profilePage = new ProfilePage(driver);

        homePage.open();
    }

    @Test
    public void canUserVisitHomePage() {
        assertTrue(homePage.isVisible());
    }

    @Test
    public void canUserSearchForPosts() {
        homePage.searchFor("must have extensions");
        searchPage.selectFirstResult();

        assertTrue(postPage.isReadable());
    }

    @Test
    public void canUserVisitSocialLinks() {
        final List<String> expectedLinks = new ArrayList<>(Arrays.asList(
                "https://twitter.com/thepracticaldev",
                "https://github.com/thepracticaldev",
                "https://www.instagram.com/thepracticaldev/",
                "https://www.facebook.com/thepracticaldev",
                "https://www.twitch.tv/thepracticaldev"
        )
        );

        for (final WebElement link : homePage.getKeyLinks()) {
            link.click();
            WebDriverUtils.switchToNextTab(driver);
            assertTrue(expectedLinks.contains(driver.getCurrentUrl()));
            driver.close();
            WebDriverUtils.switchToNextTab(driver);
        }
    }

    @Test
    public void canCheckAnotherUserProfile() {
        homePage.searchFor("ben halpern");
        searchPage.selectFirstResult();

        assertTrue(profilePage.isVisible());
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
