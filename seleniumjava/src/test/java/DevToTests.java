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

import static org.testng.Assert.assertTrue;

public class DevToTests {
    private WebDriver driver;
    private DevToHomePage homePage;
    private WebDriverWait wait;
    private ChromeOptions options;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:/Users/Alex/IdeaProjects/seleniumjava/driver/chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
        driver.get("https://dev.to");
        wait = new WebDriverWait(driver, 10);
        homePage = new DevToHomePage(driver);

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
    public void searchForFacebookIcon_TheUserCanFindItAndClickIt() throws InterruptedException {
        WebElement facebookIcon = driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper-left\"]/div[2]/div[3]/div[1]/a[4]"));
        facebookIcon.click();
        String parentHandle = driver.getWindowHandle(); // get the current window handle
        System.out.println(parentHandle);
        Thread.sleep(5);


    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
