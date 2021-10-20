// 
// Decompiled by Procyon v0.5.36
// 

package lib;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import java.io.File;
import org.openqa.selenium.TakesScreenshot;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.openqa.selenium.WebDriver;
import config.TestConfig;

public class Screenshot extends TestConfig
{
    public static String getScreenshot(final WebDriver driver) throws Exception {
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        final TakesScreenshot ts = (TakesScreenshot)driver;
        final File source = (File)ts.getScreenshotAs(OutputType.FILE);
        final String destination = String.valueOf(System.getProperty("user.dir")) + "/Screenshots/" + timeStamp + ".png";
        final File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
}
