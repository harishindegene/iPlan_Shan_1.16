// 
// Decompiled by Procyon v0.5.36
// 

package lib;

import org.openqa.selenium.NoSuchElementException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.Color;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.net.URL;
import java.net.HttpURLConnection;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.openqa.selenium.JavascriptExecutor;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.File;
import ru.yandex.qatools.ashot.AShot;
import java.util.Collection;
import java.util.ArrayList;
import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import io.percy.selenium.Percy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import config.ChromeConfig;
import java.awt.datatransfer.Clipboard;
import java.awt.AWTException;
import com.relevantcodes.extentreports.LogStatus;
import java.awt.Robot;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import org.openqa.selenium.WebDriver;
import config.EdgeConfig;

public class EdgeWebActions extends EdgeConfig
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
    
    public static String downloadfile(final WebDriver driver, final String testdata, final String description, final String expectedresult) throws AWTException {
        final StringSelection ss = new StringSelection(testdata);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        final Robot robot = new Robot();
        robot.keyPress(17);
        robot.keyPress(83);
        robot.keyRelease(83);
        robot.keyRelease(17);
        robot.keyPress(10);
        robot.keyRelease(10);
        robot.delay(2000);
        robot.keyPress(17);
        robot.keyPress(86);
        robot.keyRelease(86);
        robot.keyRelease(17);
        robot.keyPress(10);
        robot.keyRelease(10);
        robot.keyPress(10);
        robot.keyRelease(10);
        EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
        return expectedresult;
    }
    
    public static String uploadfile(final WebDriver driver, final String testdata, final String description, final String expectedresult) throws AWTException {
        final StringSelection stringSelection = new StringSelection(testdata);
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        Robot robot = null;
        try {
            robot = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(250);
        robot.keyPress(10);
        robot.keyRelease(10);
        robot.keyPress(17);
        robot.keyPress(86);
        robot.keyRelease(86);
        robot.keyRelease(17);
        robot.keyPress(10);
        robot.delay(150);
        robot.keyRelease(10);
        robot.delay(150);
        robot.keyRelease(10);
        robot.keyPress(10);
        robot.delay(150);
        robot.keyRelease(10);
        EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
        return expectedresult;
    }
    
    public static String checkPageTitle(final WebDriver driver, final String testdata, final String description, final String expectedresult) throws Exception {
        final String title = driver.getTitle();
        if (testdata.equalsIgnoreCase(title)) {
            EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Browser Title Is Displayed As Expected");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + title + "</span>");
        }
        else {
            ChromeConfig.loggerEC.log(LogStatus.FAIL, "Page Title incorrect");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]" + title + "</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
        }
        return "pass";
    }
    
    public static String MouseDrag(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult, final String actualresult) throws Exception {
        try {
            final WebElement dragfrom = driver.findElement(By.xpath(locatorValue));
            final WebElement dropto = driver.findElement(By.xpath(actualresult));
            final Actions a1 = new Actions(driver);
            Thread.sleep(2000L);
            a1.dragAndDrop(dragfrom, dropto).build().perform();
            EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Drag and Drop Success</span>");
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return actualresult;
    }
    
    public static String typeAction(final WebDriver driver, final String locatorType, final String locatorValue, final String testdata, final String description) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { testdata });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { testdata });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String select(final WebDriver driver, final String locatorType, final String locatorValue, final String testdata, final String description) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final Select comboBox = new Select(driver.findElement(By.id(locatorValue)));
                comboBox.selectByValue(testdata);
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final Select comboBox = new Select(driver.findElement(By.xpath(locatorValue)));
                System.out.println(testdata);
                comboBox.selectByVisibleText(testdata);
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "pass";
    }
    
    public static String reloadPage(final WebDriver driver) {
        try {
            driver.navigate().refresh();
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Page Reloaded Successfully");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String percyload(final WebDriver driver, final String testdata) {
        try {
            final Percy percy = new Percy(driver);
            percy.snapshot(testdata);
            EdgeWebActions.loggerEC.log(LogStatus.PASS, String.valueOf(testdata) + "Percy Page Capture sucess");
            return "pass";
        }
        catch (Exception e) {
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Unable to capture Page screenshot");
            return e.getMessage();
        }
    }
    
    public static String getpageloadtime(final WebDriver driver) {
        try {
            final long start = System.currentTimeMillis();
            driver.navigate().refresh();
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Page Reloaded Successfully");
            final long finish = System.currentTimeMillis();
            final long totalTime = finish - start;
            final long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);
            System.out.println("Total Time for page load - " + totalTime);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "Total Time for page load -" + seconds + "Seconds");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String navigateBack(final WebDriver driver) {
        try {
            driver.navigate().back();
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Back Page navigation success");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String navigateForward(final WebDriver driver) {
        try {
            driver.navigate().forward();
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Forward Page navigation success");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static void explicitwait(final WebDriver driver, final String locatorType, final String locatorValue) {
        final WebDriverWait wait = new WebDriverWait(driver, 20L);
        final WebElement element = (WebElement)wait.until((Function)ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
    }
    
    public static void switchnewtab(final WebDriver driver) {
        final ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window((String)tabs.get(1));
    }
    
    public static String captureImage(final WebDriver driver, final String locator, final String locatorValue, final String description, final String actualresult, final String testdata) throws IOException {
        EdgeWebActions.loggerEC.log(LogStatus.INFO, "Image Capture Method");
        try {
            final WebElement logoImageElement = driver.findElement(By.xpath(locatorValue));
            final ru.yandex.qatools.ashot.Screenshot s1 = new AShot().takeScreenshot(driver, logoImageElement);
            final BufferedImage actualImage = s1.getImage();
            final File dir = new File("./ChromeTestImages");
            final File[] dir_contents = dir.listFiles();
            if (dir.listFiles().length != 0) {
                for (int i = 0; i < dir_contents.length; ++i) {
                    if (dir_contents[i].getName().equalsIgnoreCase(String.valueOf(testdata) + ".png")) {
                        EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Problem with the file name given. Duplicates Found");
                    }
                }
            }
            ImageIO.write(s1.getImage(), "png", new File("./ChromeTestImages/" + testdata + ".png"));
            final File f = new File("./ChromeTestImages/" + testdata + ".png");
            if (f.exists()) {
                System.out.println("Image File Captured");
                EdgeWebActions.loggerEC.log(LogStatus.PASS, "Image Capture Successfully");
            }
            else {
                System.out.println("Image File NOT exist");
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Image Capture Failed");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return testdata;
    }
    
    public static String compareImage(final WebDriver driver, final String locator, final String locatorValue, final String description, final String actualresult, final String testdata) throws IOException {
        try {
            final BufferedImage expectedImage = ImageIO.read(new File("./ChromeTestImages/" + testdata + ".png"));
            final WebElement logoImageElement = driver.findElement(By.xpath(locatorValue));
            final ru.yandex.qatools.ashot.Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImageElement);
            final BufferedImage actualImage = logoImageScreenshot.getImage();
            final ImageDiffer imgDiff = new ImageDiffer();
            final ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
            if (diff.hasDiff()) {
                System.out.println("Images are Not Same");
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Images are not same");
            }
            else {
                System.out.println("Images are Same");
                EdgeWebActions.loggerEC.log(LogStatus.PASS, "Images are same");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return testdata;
    }
    
    public static void keyboardTab(final WebDriver driver) throws Exception {
        try {
            final Robot r1 = new Robot();
            r1.keyPress(9);
            r1.keyRelease(9);
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Tab Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(getScreenshot(driver)));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void SelectAll(final WebDriver driver) throws Exception {
        try {
            final Robot r1 = new Robot();
            r1.keyPress(65);
            r1.keyRelease(65);
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Select Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(getScreenshot(driver)));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void keyboardDownArrow(final WebDriver driver) throws Exception {
        try {
            final Robot r1 = new Robot();
            r1.keyPress(40);
            r1.keyRelease(40);
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Down Arrow Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(getScreenshot(driver)));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void keyboardUpArrow(final WebDriver driver) throws Exception {
        try {
            final Robot r1 = new Robot();
            r1.keyPress(38);
            r1.keyRelease(38);
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Up Arrow Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(getScreenshot(driver)));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void keyboardEnter(final WebDriver driver) throws Exception {
        try {
            final Robot r1 = new Robot();
            r1.keyPress(10);
            r1.keyRelease(10);
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "Enter Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(getScreenshot(driver)));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void switchbacktab(final WebDriver driver) throws InterruptedException, AWTException {
        final ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window((String)tabs.get(0));
    }
    
    public static void closeactivetab(final WebDriver driver) throws InterruptedException, AWTException {
        final ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window((String)tabs.get(1));
        driver.close();
    }
    
    public static String sleepShort(final WebDriver driver) throws InterruptedException {
        Thread.sleep(2000L);
        return "pass";
    }
    
    public static String sleepMedium(final WebDriver driver) throws InterruptedException {
        Thread.sleep(4000L);
        return "pass";
    }
    
    public static String sleepLong(final WebDriver driver) throws InterruptedException {
        Thread.sleep(7000L);
        return "pass";
    }
    
    public static String customWait(final WebDriver driver, final String testdata) throws InterruptedException {
        final int waitvalue = Integer.parseInt(testdata);
        driver.manage().timeouts().implicitlyWait((long)waitvalue, TimeUnit.SECONDS);
        return "pass";
    }
    
    public static String verifyTooltip(final WebDriver driver, final String locator, final String locatorValue, final String expectedresult, final String description, String actualresult, final String testdata) throws Exception {
        final Actions a1 = new Actions(driver);
        final WebElement tooltiparea = driver.findElement(By.xpath(locatorValue));
        Thread.sleep(2000L);
        a1.clickAndHold(tooltiparea).perform();
        actualresult = tooltiparea.getAttribute("title");
        System.out.println("@#@#@#@#@#@# Actual Result" + actualresult);
        Thread.sleep(2000L);
        if (actualresult.equalsIgnoreCase(testdata)) {
            EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + actualresult + "</span>");
        }
        else {
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            actualresult = tooltiparea.getAttribute("title");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult + "</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
        }
        return testdata;
    }
    
    public static String clickAction(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult, final String actualresult) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).click();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.findElement(By.xpath(locatorValue)).click();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Click Action Failure </span>");
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String gettooltiptext(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", new Object[] { locatorValue });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", new Object[] { locatorValue });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Click Action Failure </span>");
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String javascriptClick(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", new Object[] { locatorValue });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", new Object[] { locatorValue });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Click Action Failure </span>");
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String closeBrowserTab(final WebDriver driver, final String description) throws Exception {
        final ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window((String)tabs.get(1));
        driver.close();
        driver.switchTo().window((String)tabs.get(0));
        return "pass";
    }
    
    public static String InputDynamicData(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String testdata) throws Exception {
        try {
            final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { String.valueOf(timeStamp) + testdata });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { String.valueOf(timeStamp) + testdata });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String slideraction(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String testdata) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final WebElement slider = driver.findElement(By.id(locatorValue));
                final Actions move = new Actions(driver);
                move.click(slider).sendKeys(new CharSequence[] { (CharSequence)Keys.ARROW_RIGHT }).perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final WebElement slider = driver.findElement(By.id(locatorValue));
                final Actions move = new Actions(driver);
                move.click(slider).sendKeys(new CharSequence[] { (CharSequence)Keys.ARROW_RIGHT }).perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String entervalue(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String numerictestdata) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { numerictestdata });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { numerictestdata });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String GetCssColor(final WebDriver driver, final String locatorType, final String locatorValue, final String expectedresult, final String description) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).getCssValue("border-top");
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final String css = driver.findElement(By.id(locatorValue)).getCssValue("border-top");
                System.out.println(css);
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.getMessage();
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String MouseHover(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.id(locatorValue));
                a1.moveToElement(element).build().perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).build().perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Hover Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).build().perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Hover Action Successful</span>");
            }
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String mouseclick(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.id(locatorValue));
                a1.moveToElement(element).click().build().perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).click().build().perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).click().build().perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String rightclick(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.id(locatorValue));
                a1.moveToElement(element).contextClick().build().perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).contextClick().build().perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).contextClick().build().perform();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String ischeckBoxEnabled(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final Boolean status = driver.findElement(By.id(locatorValue)).isSelected();
                if (Boolean.TRUE) {
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Check box is enabled</span>");
                }
                else {
                    EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Check box is disabled</span>");
                }
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final Boolean status = driver.findElement(By.xpath(locatorValue)).isSelected();
                System.out.println(status);
                if (status.equals("True")) {
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Check box is enabled</span>");
                }
                else {
                    EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Check box is disabled</span>");
                }
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Boolean status = driver.findElement(By.name(locatorValue)).isSelected();
                if (Boolean.TRUE) {
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
                }
                else {
                    EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
                }
            }
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String getScreenshot(final WebDriver driver) throws Exception {
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        final TakesScreenshot ts = (TakesScreenshot)driver;
        final File source = (File)ts.getScreenshotAs(OutputType.FILE);
        final String destination = String.valueOf(System.getProperty("user.dir")) + "/Screenshots/" + timeStamp + ".png";
        final File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
    
    public static String scrollDown(final WebDriver driver, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            Thread.sleep(2000L);
            final JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,7000)", new Object[0]);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Scroll Down Success</span>");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String scrollUp(final WebDriver driver, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            Thread.sleep(2000L);
            final JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,-7000)", new Object[0]);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Scroll Up Success</span>");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String verifyURL(final WebDriver driver, final String testdata, final String description, final String expectedresult) throws Exception {
        final String url = driver.getCurrentUrl();
        if (testdata.equalsIgnoreCase(url)) {
            EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            EdgeWebActions.loggerEC.log(LogStatus.PASS, "URL Is Correct");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + url + "</span>");
        }
        else {
            ChromeConfig.loggerEC.log(LogStatus.FAIL, "Page Title incorrect");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + url + "</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
        }
        return "pass";
    }
    
    public static String ClickAllLinks(final WebDriver driver, final String description, final String testdata) throws MalformedURLException, IOException {
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        final List<WebElement> link = (List<WebElement>)driver.findElements(By.tagName("a"));
        EdgeWebActions.loggerEC.log(LogStatus.INFO, "Total no. of links are " + link.size());
        final Iterator<WebElement> it = link.iterator();
        while (it.hasNext()) {
            url = it.next().getAttribute("href");
            System.out.println(url);
            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured for anchor tag or it is empty");
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, String.valueOf(url) + "- URL is either not configured for anchor tag or it is empty");
            }
            else if (url == "javascript:void(0)" || url.startsWith("javascript")) {
                System.out.println("Javascript URL" + url);
                int k = 1;
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, String.valueOf(url) + "- Not a URL");
                ++k;
            }
            else if (url.startsWith(testdata)) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, String.valueOf(url) + "- URL with same domain");
                huc = (HttpURLConnection)new URL(url).openConnection();
                System.out.println("Normal link - " + url);
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                System.out.println(respCode);
                if (respCode != 200) {
                    EdgeWebActions.loggerEC.log(LogStatus.FAIL, String.valueOf(url) + "<b>- Broken Link or Warning<b>");
                    EdgeWebActions.loggerEC.log(LogStatus.FAIL, "<b>Response Code:</b>" + respCode);
                }
                else {
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, String.valueOf(url) + "- Valid Link");
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, "<b>Response Code:</b>" + respCode);
                }
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, String.valueOf(url) + "- URL with different domain");
                huc = (HttpURLConnection)new URL(url).openConnection();
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                System.out.println(respCode);
                if (respCode != 200) {
                    EdgeWebActions.loggerEC.log(LogStatus.FAIL, String.valueOf(url) + "<b>- Broken Link or Warning<b>");
                    EdgeWebActions.loggerEC.log(LogStatus.FAIL, "<b>Response Code:</b>" + respCode);
                }
                else {
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, String.valueOf(url) + "- Valid Link");
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, "<b>Response Code:</b>" + respCode);
                }
            }
        }
        return url;
    }
    
    public static String enterText(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String testdata) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { testdata });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { testdata });
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String verifybgcolor(final WebDriver driver, final String locator, final String expectedresult, final String description, final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locator));
            final String color = element.getCssValue("background-color");
            System.out.println(color);
            final String hex = Color.fromString(color).asHex();
            System.out.println(hex);
            if (testdata.equalsIgnoreCase(hex)) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + hex + "</span>");
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + hex + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot2));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String verifyYCordinate(final WebDriver driver, final String locator, final String expectedresult, final String description, final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locator));
            final Point point = element.getLocation();
            final int yvalue = point.getY();
            final int ytestdata = Integer.parseInt(testdata);
            if (ytestdata == yvalue) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + yvalue + "</span>");
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + yvalue + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot2));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String verifyXCordinate(final WebDriver driver, final String locator, final String expectedresult, final String description, final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locator));
            final Point point = element.getLocation();
            final int xvalue = point.getX();
            final int xtestdata = Integer.parseInt(testdata);
            if (xtestdata == xvalue) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + xvalue + "</span>");
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + xvalue + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot2));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String verifyText(final WebDriver driver, final String locator, final String expectedresult, final String description, String actualresult, final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locator));
            actualresult = element.getText();
            final String actualresult2 = actualresult.replaceAll("\\s+", "");
            System.out.println(actualresult2);
            final String testdata2 = testdata.replaceAll("\\s+", "");
            System.out.println(testdata2);
            if (testdata2.equalsIgnoreCase(actualresult2)) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                System.out.println(actualresult);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + actualresult + "</span>");
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                System.out.println(actualresult);
                final String actualresult3 = element.getText();
                System.out.println(actualresult3);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot2));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String comparetext(final WebDriver driver, final String locatorvalue, final String expectedresult, final String description, final String testdata) throws Exception {
        try {
            Throwable t = null;
            try {
                final InputStream input = new FileInputStream("./config/config.properties");
                try {
                    final WebElement element = driver.findElement(By.xpath(locatorvalue));
                    final String data = element.getText();
                    System.out.println("%%#$#$#$##$#$#" + data);
                    final Properties prop = new Properties();
                    prop.load(input);
                    final String comparetext = prop.getProperty(testdata);
                    System.out.println("^^^^^^^^^^^^" + comparetext);
                    if (comparetext.equalsIgnoreCase(data)) {
                        EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                        EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                        EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + data + "</span>");
                    }
                }
                finally {
                    if (input != null) {
                        input.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    final Throwable exception = null;
                    t = exception;
                }
                else {
                    final Throwable exception = null;
                    if (t != exception) {
                        t.addSuppressed(exception);
                    }
                }
            }
        }
        catch (Exception io) {
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]: Unable to Save</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            io.printStackTrace();
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)io);
        }
        return "fail";
    }
    
    public static String savetext(final WebDriver driver, final String locatorvalue, final String expectedresult, final String description, final String testdata) throws Exception {
        try {
            Throwable t = null;
            try {
                final OutputStream output = new FileOutputStream("./config/config.properties");
                try {
                    final WebElement element = driver.findElement(By.xpath(locatorvalue));
                    final String data = element.getText();
                    System.out.println("%%#$#$#$##$#$#" + data);
                    final Properties prop = new Properties();
                    prop.setProperty(testdata, data);
                    prop.store(output, null);
                    System.out.println("$%$%$%$%$%$%$%$%$%$$%$%$%$%" + prop);
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + data + "</span>");
                }
                finally {
                    if (output != null) {
                        output.close();
                    }
                }
            }
            finally {
                if (t == null) {
                    final Throwable exception = null;
                    t = exception;
                }
                else {
                    final Throwable exception = null;
                    if (t != exception) {
                        t.addSuppressed(exception);
                    }
                }
            }
        }
        catch (Exception io) {
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]: Unable to Save</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            io.printStackTrace();
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)io);
        }
        return "fail";
    }
    
    public static String verifyimagesrc(final WebDriver driver, final String locatorvalue, final String expectedresult, final String description, final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locatorvalue));
            final String src = ((JavascriptExecutor)driver).executeScript("return arguments[0].attributes['src'].value;", new Object[] { element }).toString();
            System.out.println(src);
            System.out.println("******************@#@#@#@#@" + testdata);
            final String actualresult1 = src.replaceAll("\\s+", "");
            System.out.println(actualresult1);
            final String testdata2 = testdata.replaceAll("\\s+", "");
            System.out.println(testdata2);
            if (testdata2.equalsIgnoreCase(actualresult1)) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + src + "</span>");
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + src + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot2));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String verifyvalue(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String testdata, final String expectedresult) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locatorValue));
            final String result = element.getText();
            final String convertdata = testdata.replaceAll("\\.0*$", "");
            if (convertdata.equalsIgnoreCase(element.getText())) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                System.out.println("++Actualllll Resullttt" + result);
                System.out.println("++Tessstttt Data Resullttt" + convertdata);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + result + "</span>");
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                System.out.println("++Actualllll Resullttt" + result);
                System.out.println("++Tessstttt Data Resullttt" + testdata);
                final String actualresult2 = element.getText();
                System.out.println("++++harish+++" + actualresult2);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + result + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot2));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String verifyhiddenvalue(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String testdata, final String expectedresult) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locatorValue));
            final String elementval = element.getAttribute("value");
            System.out.println("*************************************************************" + elementval);
            final String convertdata = testdata.replaceAll("\\.0*$", "");
            System.out.println("*************************************************************()" + convertdata);
            if (elementval.equalsIgnoreCase(convertdata)) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + elementval + "</span>");
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + elementval + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot2));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String verifySelectText(final WebDriver driver, final String locator, final String expectedresult, final String description, String actualresult, final String testdata) throws Exception {
        final WebElement element = driver.findElement(By.xpath(locator));
        if (element != null) {
            final Select comboBox = new Select(element);
            final String selectedComboValue = comboBox.getFirstSelectedOption().getText();
            System.out.println(selectedComboValue);
            if (testdata.equalsIgnoreCase(selectedComboValue)) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + actualresult + "</span>");
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                final Select comboBox2 = new Select(element);
                actualresult = comboBox.getFirstSelectedOption().getText();
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
            return "pass";
        }
        System.out.println("Element not available");
        return "Failed :: Element not available";
    }
    
    public static String cleartext(final WebDriver driver, final String locator, final String expectedresult, final String description, final String actualresult, final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locator));
            element.clear();
            EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]: Text Clear successfully</span>");
        }
        catch (Exception e) {
            System.out.println("Element not available");
        }
        return "fail";
    }
    
    public static String clearvalue(final WebDriver driver, final String locator, final String expectedresult, final String description, final String actualresult, final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locator));
            Thread.sleep(2000L);
            element.sendKeys(new CharSequence[] { (CharSequence)Keys.BACK_SPACE });
            EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]: Text Clear successfully</span>");
        }
        catch (Exception e) {
            System.out.println("Element not available");
        }
        return "fail";
    }
    
    public static String verifyInputText(final WebDriver driver, final String locator, final String expectedresult, final String description, final String actualresult, final String testdata) throws Exception {
        final WebElement element = driver.findElement(By.xpath(locator));
        if (element != null) {
            final String text = element.getAttribute("value");
            System.out.println(text);
            if (testdata.equalsIgnoreCase(text)) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + text + "</span>");
            }
            else {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                final String actualresult2 = element.getAttribute("value");
                System.out.println(actualresult2);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult2 + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
            return "pass";
        }
        System.out.println("Element not available");
        return "Failed :: Element not available";
    }
    
    public static String verifyTextNotPresent(final WebDriver driver, final String locator, final String expectedresult, final String description, String actualresult, final String testdata) throws Exception {
        final WebElement element = driver.findElement(By.xpath(locator));
        if (element != null) {
            if (!testdata.equalsIgnoreCase(element.getText())) {
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                actualresult = element.getText();
                System.out.println(actualresult);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]: Text is not getting displayed</span>");
            }
            else if (testdata.equalsIgnoreCase(element.getText())) {
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                System.out.println(actualresult);
                final String actualresult2 = element.getText();
                System.out.println(actualresult2);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult2 + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            }
        }
        return "pass";
    }
    
    public static String verifyElement(final WebDriver driver, final String locatortype, final String locatorvalue, final String description) throws Exception {
        try {
            if (locatortype.equalsIgnoreCase("id")) {
                final List<WebElement> dynamicElement = (List<WebElement>)driver.findElements(By.id(locatorvalue));
                if (dynamicElement.size() != 0) {
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, "Element Present In The Page");
                }
                else {
                    driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
                }
            }
            else if (locatortype.equalsIgnoreCase("xpath")) {
                driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
                final List<WebElement> dynamicElement = (List<WebElement>)driver.findElements(By.xpath(locatorvalue));
                System.out.println("***************" + dynamicElement);
                if (dynamicElement.size() != 0) {
                    EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                }
                else {
                    EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Elment Not Present In The Page");
                }
                System.out.println("***************" + dynamicElement);
                final String screenshot = Screenshot.getScreenshot(driver);
                EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
                driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.loggerEC.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static boolean isElementPresent(final WebDriver driver, final String locatorvalue, final String locatortype) {
        try {
            if (locatortype.equalsIgnoreCase("xpath")) {
                driver.findElement(By.xpath(locatorvalue));
                EdgeWebActions.loggerEC.log(LogStatus.PASS, "WebElement present in the page");
                return true;
            }
            if (locatortype.equalsIgnoreCase("id")) {
                driver.findElement(By.xpath(locatorvalue));
                EdgeWebActions.loggerEC.log(LogStatus.PASS, "WebElement present in the page");
                return true;
            }
        }
        catch (NoSuchElementException e) {
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "WebElement not present in the page");
            return false;
        }
        return false;
    }
    
    public static String switchtoactive(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final WebElement frame = driver.findElement(By.id(locatorValue));
                driver.switchTo().frame(frame);
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Switch To Video Frame Successfull</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final WebElement frame = driver.findElement(By.xpath(locatorValue));
                driver.switchTo().frame(frame);
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Switch To Video Frame Successfull</span>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Switch To Video Frame Failure </span>");
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String switchtodefault(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.switchTo().defaultContent();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Switch To Video Frame Successfull</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.switchTo().defaultContent();
                EdgeWebActions.loggerEC.log(LogStatus.PASS, description);
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Switch To Video Frame Successfull</span>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, description);
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            EdgeWebActions.loggerEC.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Switch To Video Frame Failure </span>");
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, "Snapshot below: " + EdgeWebActions.loggerEC.addScreenCapture(screenshot));
            EdgeWebActions.loggerEC.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
}
