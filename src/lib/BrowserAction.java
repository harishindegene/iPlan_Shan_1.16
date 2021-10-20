// 
// Decompiled by Procyon v0.5.36
// 

package lib;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import config.TestConfig;

public class BrowserAction extends TestConfig
{
    public static String openApplication(final WebDriver driver, final String localurl) {
        try {
            driver.manage().window().maximize();
            driver.get(localurl);
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String closeApplication(final WebDriver driver) {
        try {
            driver.close();
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String checkPageTitle(final WebDriver driver, final String testdata, final String description, final String expectedresult) throws Exception {
        final String title = driver.getTitle();
        if (testdata.equalsIgnoreCase(title)) {
            BrowserAction.logger.log(LogStatus.PASS, description);
            BrowserAction.logger.log(LogStatus.PASS, "Browser Title Is Displayed As Expected");
            BrowserAction.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            BrowserAction.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + title + "</span>");
        }
        else {
            TestConfig.logger.log(LogStatus.FAIL, "Page Title incorrect");
            BrowserAction.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            BrowserAction.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]" + title + "</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            BrowserAction.logger.log(LogStatus.FAIL, "Snapshot below: " + BrowserAction.logger.addScreenCapture(screenshot));
        }
        return "pass";
    }
    
    public static String reloadPage(final WebDriver driver) {
        try {
            driver.navigate().refresh();
            BrowserAction.logger.log(LogStatus.PASS, "Page Reloaded Successfully");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String navigateBack(final WebDriver driver) {
        try {
            driver.navigate().back();
            BrowserAction.logger.log(LogStatus.PASS, "Page navigation success");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
