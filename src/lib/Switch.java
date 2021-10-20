// 
// Decompiled by Procyon v0.5.36
// 

package lib;

import org.openqa.selenium.WebDriver;

public class Switch
{
    public static String switchToDefault(final WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String swtichToActive(final WebDriver driver) {
        try {
            driver.switchTo().activeElement();
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
