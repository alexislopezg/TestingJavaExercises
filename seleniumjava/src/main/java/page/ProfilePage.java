package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends Page {

    @FindBy(xpath = "//span[@itemprop='name']")
    private WebElement name;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isVisible() {
        return name.isDisplayed();
    }

}
