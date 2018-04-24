import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.DevToHomePage;

import java.util.List;

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
       List<WebElement> keyLinks = driver.findElements(By.xpath("//header[contains(text(), 'KEY LINKS')]/..//a"));
        for (WebElement link: keyLinks) {
            link.click();
            String newTabUrl;

            if (driver.getWindowHandles().size() > 1) {
                switchToAnotherTab();
                newTabUrl = driver.getCurrentUrl();
                assertTrue(newTabUrl.equals(driver.getCurrentUrl()));
                driver.close();
                switchToAnotherTab();
            }
            else{
                newTabUrl = driver.getCurrentUrl();
                assertTrue(newTabUrl.equals(driver.getCurrentUrl()));
                driver.navigate().back();
            }
        }

    }
    private void switchToAnotherTab() {
        for (String handle : driver.getWindowHandles())
            driver.switchTo().window(handle);
    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
