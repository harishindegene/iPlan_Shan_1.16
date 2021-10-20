// 
// Decompiled by Procyon v0.5.36
// 

package config;

import lib.ScrollDown;
import lib.BrowserWait;
import lib.Screenshot;
import lib.BrowserAction;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;

public class TestConfig
{
    public static WebDriver driver;
    public static ExtentReports report;
    public static ExtentTest logger;
    public static Workbook book;
    public static Sheet sheet;
    public static final String SCENARIO_SHEET_PATH = "./TestCases/InputSheet_2.xlsx";
    
    public static void startTest(final String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream("./TestCases/InputSheet_2.xlsx");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            TestConfig.book = WorkbookFactory.create((InputStream)file);
        }
        catch (InvalidFormatException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        TestConfig.sheet = TestConfig.book.getSheet(sheetName);
        System.out.println(sheetName);
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TestConfig.report = new ExtentReports("./ChromeTestReport/" + sheetName + timeStamp + ".html", Boolean.valueOf(true));
        TestConfig.logger = TestConfig.report.startTest(sheetName);
        for (int i = 0; i <= TestConfig.sheet.getLastRowNum(); ++i) {
            try {
                final String description = TestConfig.sheet.getRow(i).getCell(0).toString().trim();
                final String object_type = TestConfig.sheet.getRow(i).getCell(1).toString().trim();
                final String actions = TestConfig.sheet.getRow(i).getCell(2).toString().trim();
                final String locator_type = TestConfig.sheet.getRow(i).getCell(3).toString().trim();
                final String locator_value = TestConfig.sheet.getRow(i).getCell(4).toString().trim();
                final String testdata = TestConfig.sheet.getRow(i).getCell(5).toString().trim();
                final String expectedresult = TestConfig.sheet.getRow(i).getCell(6).toString().trim();
                final String actualresult = TestConfig.sheet.getRow(i).getCell(7).toString().trim();
                if (object_type.equalsIgnoreCase("browser")) {
                    if (testdata.equalsIgnoreCase("NA")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.chrome.driver", ".//BrowserUtilities//chromedriver.exe");
                            TestConfig.driver = (WebDriver)new ChromeDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            TestConfig.driver.quit();
                        }
                    }
                    if (testdata.equalsIgnoreCase("IE") && actions.equalsIgnoreCase("IE")) {
                        System.setProperty("webdriver.ie.driver", ".//BrowserUtilities//IEDriverServer.exe");
                        TestConfig.driver = (WebDriver)new InternetExplorerDriver();
                    }
                    if (actions.equalsIgnoreCase("closeBrowser")) {
                        TestConfig.driver.quit();
                    }
                    if (testdata.equalsIgnoreCase("Chrome")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.chrome.driver", ".//BrowserUtilities//chromedriver.exe");
                            TestConfig.driver = (WebDriver)new ChromeDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            TestConfig.driver.quit();
                        }
                    }
                    TestConfig.logger.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("OpenApp") && actions.equalsIgnoreCase("navigate")) {
                    BrowserAction.openApplication(TestConfig.driver, testdata);
                    TestConfig.logger.log(LogStatus.PASS, description);
                }
                if (actions.equalsIgnoreCase("checkpagetitle")) {
                    BrowserAction.checkPageTitle(TestConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("reloadpage")) {
                    BrowserAction.reloadPage(TestConfig.driver);
                }
                if (actions.equalsIgnoreCase("navigateback")) {
                    BrowserAction.navigateBack(TestConfig.driver);
                }
                if (object_type.equalsIgnoreCase("CaptureScreeshot") && actions.equals("screenshot")) {
                    Screenshot.getScreenshot(TestConfig.driver);
                }
                if (actions.equals("waitshort")) {
                    BrowserWait.sleepShort(TestConfig.driver);
                    TestConfig.logger.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitmedium")) {
                    BrowserWait.sleepMedium(TestConfig.driver);
                    TestConfig.logger.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitlong")) {
                    BrowserWait.sleepMedium(TestConfig.driver);
                    TestConfig.logger.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("scrolldown")) {
                    ScrollDown.scrollDown(TestConfig.driver, description);
                    TestConfig.logger.log(LogStatus.PASS, description);
                }
            }
            catch (Exception ex) {}
            TestConfig.report.endTest(TestConfig.logger);
            TestConfig.report.flush();
        }
    }
}
