package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class SearchPage extends Page {

    @FindBy(xpath = "//div[contains(@class, 'single-article')][1]//h3")
    private WebElement post;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void selectFirstResult() {
        post.click();
    }

}
