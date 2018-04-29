package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class PostPage extends Page {

    private WebDriverWait wait;

    @FindBy(xpath = "//h1")
    private WebElement title;

    public PostPage(WebDriver driver) {
        super(driver);
        final int TIME_OUT_IN_SECONDS = 10;
        wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }

    public boolean isReadable() {
        wait.until(ExpectedConditions.elementToBeClickable(title));

        return title.isDisplayed();
    }

}
