// 
// Decompiled by Procyon v0.5.36
// 

package lib;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScrollDown
{
    public static String scrollDown(final WebDriver driver, final String description) throws ElementNotVisibleException {
        try {
            Thread.sleep(2000L);
            final JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,7000)", new Object[0]);
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
