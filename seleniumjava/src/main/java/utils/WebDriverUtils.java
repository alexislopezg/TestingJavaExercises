package utils;

import org.openqa.selenium.WebDriver;

public class WebDriverUtils {

    public static void switchToNextTab(final WebDriver driver) {
        for (final String handle : driver.getWindowHandles())
            driver.switchTo().window(handle);
    }

}
