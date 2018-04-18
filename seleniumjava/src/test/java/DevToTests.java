import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.DevToHomePage;

import static org.testng.Assert.assertTrue;

public class DevToTests {
    private WebDriver driver;
    private DevToHomePage homePage;
    final WebDriverWait wait = new WebDriverWait(driver, 5);

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:/Users/Alex/IdeaProjects/seleniumjava/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://dev.to");

        homePage = new DevToHomePage(driver);
    }

    @Test
    public void goToWebsite_CheckUrl() throws InterruptedException {
        wait();
        assertTrue(driver.getCurrentUrl().equals("https://dev.to/"));
    }

    @Test
    public void searchForVsCodeExtensionPost_TheUserCanFindItAndReadIt() throws InterruptedException {
        homePage.searchFor("must have extensions");

        wait(10);
        WebElement post = driver.findElement(By.xpath("//h3[text() = 'Must have extensions for VS Code (according to me)']"));
        post.click();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
