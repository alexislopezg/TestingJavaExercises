package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public final class HomePage extends Page {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='nav-search']")
    private WebElement searchbar;

    @FindBy(xpath = "(//header[contains(text(), 'KEY LINKS')]/../div)[1]/a")
    private List<WebElement> keyLinks;

    public HomePage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        final int TIME_OUT_IN_SECONDS = 10;
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }

    public List<WebElement> getKeyLinks() {
        return keyLinks;
    }

    public boolean isVisible() {
        return driver.getCurrentUrl().equals("https://dev.to/");
    }

    public void open() {
        driver.get("https://dev.to");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.urlContains("https://dev.to/"));
    }

    public void searchFor(String query) {
        searchbar.sendKeys(query, Keys.ENTER);
        wait.until(ExpectedConditions.urlContains("https://dev.to/search?q="));
    }

}
