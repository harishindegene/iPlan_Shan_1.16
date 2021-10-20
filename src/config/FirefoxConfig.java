// 
// Decompiled by Procyon v0.5.36
// 

package config;

import lib.FireFoxWebActions;
import lib.BrowserAction;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import dataProvider.ExcelDataProvider;

public class FirefoxConfig extends ExcelDataProvider
{
    public static WebDriver driver;
    public static Workbook book;
    public static Sheet sheet;
    public static final String SCENARIO_SHEET_PATH = "./TestCases/InputSheet.xlsx";
    
    public static void startTest(final String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream("./TestCases/InputSheet.xlsx");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FirefoxConfig.book = WorkbookFactory.create((InputStream)file);
        }
        catch (InvalidFormatException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        FirefoxConfig.sheet = FirefoxConfig.book.getSheet(sheetName);
        System.out.println(sheetName);
        for (int i = 0; i <= FirefoxConfig.sheet.getLastRowNum(); ++i) {
            try {
                final String description = FirefoxConfig.sheet.getRow(i).getCell(0).toString().trim();
                final String object_type = FirefoxConfig.sheet.getRow(i).getCell(1).toString().trim();
                final String actions = FirefoxConfig.sheet.getRow(i).getCell(2).toString().trim();
                final String locator_type = FirefoxConfig.sheet.getRow(i).getCell(3).toString().trim();
                final String locator_value = FirefoxConfig.sheet.getRow(i).getCell(4).toString().trim();
                final String testdata = FirefoxConfig.sheet.getRow(i).getCell(5).toString().trim();
                final String numerictestdata = new DataFormatter().formatCellValue(FirefoxConfig.sheet.getRow(i).getCell(5));
                final String expectedresult = FirefoxConfig.sheet.getRow(i).getCell(6).toString().trim();
                final String actualresult = FirefoxConfig.sheet.getRow(i).getCell(7).toString().trim();
                if (object_type.equalsIgnoreCase("browser")) {
                    if (testdata.equalsIgnoreCase("NA")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.gecko.driver", ".//BrowserUtilities//geckodriver.exe");
                            FirefoxConfig.driver = (WebDriver)new FirefoxDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            FirefoxConfig.driver.quit();
                        }
                    }
                    if (testdata.equalsIgnoreCase("IE") && actions.equalsIgnoreCase("IE")) {
                        System.setProperty("webdriver.ie.driver", ".//BrowserUtilities//IEDriverServer.exe");
                        FirefoxConfig.driver = (WebDriver)new InternetExplorerDriver();
                    }
                    if (actions.equalsIgnoreCase("closeBrowser")) {
                        FirefoxConfig.driver.quit();
                    }
                    if (testdata.equalsIgnoreCase("Chrome")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.chrome.driver", ".//BrowserUtilities//chromedriver.exe");
                            FirefoxConfig.driver = (WebDriver)new ChromeDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            FirefoxConfig.driver.quit();
                        }
                    }
                    FirefoxConfig.loggerf.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("OpenApp") && actions.equalsIgnoreCase("navigate")) {
                    BrowserAction.openApplication(FirefoxConfig.driver, testdata);
                    FirefoxConfig.loggerf.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("type")) {
                    FireFoxWebActions.typeAction(FirefoxConfig.driver, locator_type, locator_value, testdata, description);
                }
                if (actions.equalsIgnoreCase("keyboardTab")) {
                    FireFoxWebActions.keyboardTab(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardDownArrow")) {
                    FireFoxWebActions.keyboardDownArrow(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("selectall")) {
                    FireFoxWebActions.SelectAll(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardUpArrow")) {
                    FireFoxWebActions.keyboardUpArrow(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardEnter")) {
                    FireFoxWebActions.keyboardEnter(FirefoxConfig.driver);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("typevalue")) {
                    FireFoxWebActions.entervalue(FirefoxConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (actions.equalsIgnoreCase("slideaction")) {
                    FireFoxWebActions.slideraction(FirefoxConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("InputDynamicData")) {
                    FireFoxWebActions.InputDynamicData(FirefoxConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (actions.equalsIgnoreCase("click")) {
                    FireFoxWebActions.clickAction(FirefoxConfig.driver, locator_type, locator_value, description, expectedresult, actualresult);
                }
                if (actions.equalsIgnoreCase("switchtoactive")) {
                    FireFoxWebActions.switchtoactive(FirefoxConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("switchtodefault")) {
                    FireFoxWebActions.switchtodefault(FirefoxConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("click") && actions.equalsIgnoreCase("select")) {
                    FireFoxWebActions.select(FirefoxConfig.driver, locator_type, locator_value, testdata, description);
                }
                if (actions.equalsIgnoreCase("javascriptclick")) {
                    FireFoxWebActions.javascriptClick(FirefoxConfig.driver, locator_value, locator_value, description, expectedresult);
                }
                if (actions.equals("closebrowsertab")) {
                    FireFoxWebActions.closeBrowserTab(FirefoxConfig.driver, description);
                    FirefoxConfig.loggerf.log(LogStatus.PASS, description);
                }
                if (actions.equalsIgnoreCase("checkpagetitle")) {
                    FireFoxWebActions.checkPageTitle(FirefoxConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("reloadpage")) {
                    FireFoxWebActions.reloadPage(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("getpageloadtime")) {
                    FireFoxWebActions.getpageloadtime(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("navigateback")) {
                    FireFoxWebActions.navigateBack(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("navigateforward")) {
                    FireFoxWebActions.navigateForward(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("switchnewtab")) {
                    FireFoxWebActions.switchnewtab(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("closeactivetab")) {
                    FireFoxWebActions.closeactivetab(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("switchbacktab")) {
                    FireFoxWebActions.switchbacktab(FirefoxConfig.driver);
                }
                if (actions.equalsIgnoreCase("verifyURL")) {
                    FireFoxWebActions.verifyURL(FirefoxConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("verifyelement")) {
                    FireFoxWebActions.verifyElement(FirefoxConfig.driver, locator_type, locator_value, description);
                }
                if (object_type.equalsIgnoreCase("CaptureScreeshot") && actions.equals("screenshot")) {
                    FireFoxWebActions.getScreenshot(FirefoxConfig.driver);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equals("mouseclick")) {
                    FireFoxWebActions.mouseclick(FirefoxConfig.driver, locator_type, locator_value, description, expectedresult);
                    FirefoxConfig.loggerf.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitshort")) {
                    FireFoxWebActions.sleepShort(FirefoxConfig.driver);
                    FirefoxConfig.loggerf.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitmedium")) {
                    FireFoxWebActions.sleepMedium(FirefoxConfig.driver);
                    FirefoxConfig.loggerf.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitlong")) {
                    FireFoxWebActions.sleepLong(FirefoxConfig.driver);
                    FirefoxConfig.loggerf.log(LogStatus.PASS, "WaitLong");
                }
                if (actions.equals("scrolldown")) {
                    FireFoxWebActions.scrollDown(FirefoxConfig.driver, description, expectedresult);
                    FirefoxConfig.loggerf.log(LogStatus.PASS, description);
                }
                if (actions.equals("explictwait")) {
                    FireFoxWebActions.explicitwait(FirefoxConfig.driver, locator_type, locator_value);
                }
                if (actions.equalsIgnoreCase("customwait")) {
                    FireFoxWebActions.customWait(FirefoxConfig.driver, testdata);
                }
                if (actions.equalsIgnoreCase("verifytooltip")) {
                    FireFoxWebActions.verifyTooltip(FirefoxConfig.driver, locator_type, locator_value, description, expectedresult, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("draganddrop")) {
                    FireFoxWebActions.MouseDrag(FirefoxConfig.driver, locator_type, locator_value, description, expectedresult, actualresult);
                }
                if (actions.equals("scrollup")) {
                    FireFoxWebActions.scrollUp(FirefoxConfig.driver, description, expectedresult);
                    FirefoxConfig.loggerf.log(LogStatus.PASS, description);
                }
                if (actions.equals("downloadfile")) {
                    FireFoxWebActions.downloadfile(FirefoxConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("uploadfile")) {
                    FireFoxWebActions.uploadfile(FirefoxConfig.driver, testdata, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equalsIgnoreCase("mousehover")) {
                    FireFoxWebActions.MouseHover(FirefoxConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifytext")) {
                    FireFoxWebActions.verifyText(FirefoxConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("verifyimgsrc")) {
                    FireFoxWebActions.verifyimagesrc(FirefoxConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyXcordinate")) {
                    FireFoxWebActions.verifyXCordinate(FirefoxConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyYcordinate")) {
                    FireFoxWebActions.verifyYCordinate(FirefoxConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("Verifybgcolor")) {
                    FireFoxWebActions.verifybgcolor(FirefoxConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("savetext")) {
                    FireFoxWebActions.savetext(FirefoxConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("comparetext")) {
                    FireFoxWebActions.comparetext(FirefoxConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyimagesrc")) {
                    FireFoxWebActions.verifyimagesrc(FirefoxConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("image") && actions.equalsIgnoreCase("captureimage")) {
                    FireFoxWebActions.captureImage(FirefoxConfig.driver, locator_type, locator_value, description, expectedresult, testdata);
                }
                if (object_type.equalsIgnoreCase("image") && actions.equalsIgnoreCase("compareimage")) {
                    FireFoxWebActions.compareImage(FirefoxConfig.driver, locator_type, locator_value, description, expectedresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyhiddenvalue")) {
                    FireFoxWebActions.verifyhiddenvalue(FirefoxConfig.driver, locator_type, locator_value, description, testdata, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyvalue")) {
                    FireFoxWebActions.verifyvalue(FirefoxConfig.driver, locator_type, locator_value, description, testdata, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyselecttext")) {
                    FireFoxWebActions.verifySelectText(FirefoxConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("cleartext")) {
                    FireFoxWebActions.cleartext(FirefoxConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("clearvalue")) {
                    FireFoxWebActions.clearvalue(FirefoxConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyinputtext")) {
                    FireFoxWebActions.verifyInputText(FirefoxConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyalllinks")) {
                    FireFoxWebActions.ClickAllLinks(FirefoxConfig.driver, description, testdata);
                }
                if (object_type.equalsIgnoreCase("getproperty") && actions.equals("getcsscolor")) {
                    FireFoxWebActions.GetCssColor(FirefoxConfig.driver, locator_value, locator_value, expectedresult, description);
                }
            }
            catch (Exception ex) {}
        }
    }
}
