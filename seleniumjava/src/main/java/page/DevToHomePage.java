package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DevToHomePage {

    public DevToHomePage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
    }

    @FindBy(xpath = "//input[@id='nav-search']")
    private WebElement searchbar;

    public void searchFor(String query) {
        searchbar.sendKeys(query, Keys.ENTER);
    }

    public void selectPost() {

    }


}
