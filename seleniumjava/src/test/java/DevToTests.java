import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.DevToHomePage;

import javax.xml.ws.WebEndpoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DevToTests {
    private WebDriver driver;
    private DevToHomePage homePage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:/Users/Alex/IdeaProjects/seleniumjava/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://dev.to");
        wait = new WebDriverWait(driver, 10);
        homePage = new DevToHomePage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void goToWebsite_CheckUrl() throws InterruptedException {
        wait.until(ExpectedConditions.urlContains("https://dev.to/"));
        assertTrue(driver.getCurrentUrl().equals("https://dev.to/"));
    }

    @Test
    public void searchForVsCodeExtensionPost_TheUserCanFindItAndReadIt() throws InterruptedException {
        homePage.searchFor("must have extensions");
        wait.until(ExpectedConditions.urlToBe("https://dev.to/search?q=must%20have%20extensions"));
        WebElement post = driver.findElement(By.xpath("//h3[text() = 'Must have extensions for VS Code (according to me)']"));
        post.click();
    }

    @Test
    public void searchKeyLinks_LinksRedirectToCorrectSite() {
        final List<WebElement> keyLinks = driver.findElements(By.xpath("(//header[contains(text(), 'KEY LINKS')]/../div)[1]/a"));
        final List<String> devToLinks = new ArrayList<String>(Arrays.asList(
                "https://twitter.com/thepracticaldev",
                "https://github.com/thepracticaldev",
                "https://www.instagram.com/thepracticaldev/",
                "https://www.facebook.com/thepracticaldev",
                "https://www.twitch.tv/thepracticaldev"));

        for (final WebElement link: keyLinks) {
            link.click();
            switchToAnotherTab();
            assertTrue(devToLinks.contains(driver.getCurrentUrl()));
            driver.close();
            switchToAnotherTab();
        }

    }

    private void switchToAnotherTab() {
        for (final String handle : driver.getWindowHandles())
            driver.switchTo().window(handle);
    }

    @Test
    public void searchForProfile_NameIsEqualToSearchQuery() {
        homePage.searchFor("ben halpern");
        wait.until(ExpectedConditions.urlToBe("https://dev.to/search?q=ben%20halpern"));
        WebElement benProfile = driver.findElement(By.xpath("//*[@id=\"substories\"]/div[1]/div[1]/a"));
        benProfile.click();
        wait.until(ExpectedConditions.urlToBe("https://dev.to/ben"));
        WebElement profileName = driver.findElement(By.xpath("//*/div[1]/div/div[3]/h1/span[1]"));
        assertEquals(profileName.getText().toLowerCase(), "ben halpern");
    }




    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
