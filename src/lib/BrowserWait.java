// 
// Decompiled by Procyon v0.5.36
// 

package lib;

import org.openqa.selenium.WebDriver;

public class BrowserWait
{
    public static String sleepShort(final WebDriver driver) throws InterruptedException {
        Thread.sleep(4000L);
        return "pass";
    }
    
    public static String sleepMedium(final WebDriver driver) throws InterruptedException {
        Thread.sleep(6000L);
        return "pass";
    }
}
