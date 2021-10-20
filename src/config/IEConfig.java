// 
// Decompiled by Procyon v0.5.36
// 

package config;

import lib.ChromeWebActions;
import lib.IEWebActions;
import lib.BrowserAction;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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

public class IEConfig extends ExcelDataProvider
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
            IEConfig.book = WorkbookFactory.create((InputStream)file);
        }
        catch (InvalidFormatException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        IEConfig.sheet = IEConfig.book.getSheet(sheetName);
        System.out.println(sheetName);
        for (int i = 0; i <= IEConfig.sheet.getLastRowNum(); ++i) {
            try {
                final String description = IEConfig.sheet.getRow(i).getCell(0).toString().trim();
                final String object_type = IEConfig.sheet.getRow(i).getCell(1).toString().trim();
                final String actions = IEConfig.sheet.getRow(i).getCell(2).toString().trim();
                final String locator_type = IEConfig.sheet.getRow(i).getCell(3).toString().trim();
                final String locator_value = IEConfig.sheet.getRow(i).getCell(4).toString().trim();
                final String testdata = IEConfig.sheet.getRow(i).getCell(5).toString().trim();
                final String expectedresult = IEConfig.sheet.getRow(i).getCell(6).toString().trim();
                final String actualresult = IEConfig.sheet.getRow(i).getCell(7).toString().trim();
                if (object_type.equalsIgnoreCase("browser")) {
                    if (testdata.equalsIgnoreCase("NA")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.ie.driver", ".//BrowserUtilities//IEDriverServer.exe");
                            IEConfig.driver = (WebDriver)new InternetExplorerDriver();
                            IEConfig.driver.manage().deleteAllCookies();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            IEConfig.driver.quit();
                        }
                    }
                    if (testdata.equalsIgnoreCase("IE") && actions.equalsIgnoreCase("IE")) {
                        System.setProperty("webdriver.ie.driver", ".//BrowserUtilities//IEDriverServer.exe");
                        IEConfig.driver = (WebDriver)new InternetExplorerDriver();
                    }
                    if (actions.equalsIgnoreCase("closeBrowser")) {
                        IEConfig.driver.quit();
                    }
                    if (testdata.equalsIgnoreCase("Chrome")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.chrome.driver", ".//BrowserUtilities//chromedriver.exe");
                            IEConfig.driver = (WebDriver)new ChromeDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            IEConfig.driver.quit();
                        }
                    }
                    IEConfig.loggerIE.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("OpenApp") && actions.equalsIgnoreCase("navigate")) {
                    BrowserAction.openApplication(IEConfig.driver, testdata);
                    IEConfig.loggerIE.log(LogStatus.PASS, description);
                }
                if (actions.equalsIgnoreCase("keyboardTab")) {
                    IEWebActions.keyboardTab(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardDownArrow")) {
                    IEWebActions.keyboardDownArrow(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardUpArrow")) {
                    IEWebActions.keyboardUpArrow(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardEnter")) {
                    IEWebActions.keyboardEnter(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("selectall")) {
                    IEWebActions.SelectAll(IEConfig.driver);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyvalue")) {
                    IEWebActions.verifyvalue(IEConfig.driver, locator_type, locator_value, description, testdata, expectedresult);
                }
                if (object_type.equalsIgnoreCase("image") && actions.equalsIgnoreCase("captureimage")) {
                    IEWebActions.captureImage(IEConfig.driver, locator_type, locator_value, description, expectedresult, testdata);
                }
                if (object_type.equalsIgnoreCase("image") && actions.equalsIgnoreCase("compareimage")) {
                    IEWebActions.compareImage(IEConfig.driver, locator_type, locator_value, description, expectedresult, testdata);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("typevalue")) {
                    IEWebActions.entervalue(IEConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (actions.equals("explictwait")) {
                    IEWebActions.explicitwait(IEConfig.driver, locator_type, locator_value);
                }
                if (actions.equalsIgnoreCase("savetext")) {
                    IEWebActions.savetext(IEConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("comparetext")) {
                    IEWebActions.comparetext(IEConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("slideaction")) {
                    IEWebActions.slideraction(IEConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (actions.equalsIgnoreCase("getpageloadtime")) {
                    IEWebActions.getpageloadtime(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("clearvalue")) {
                    IEWebActions.clearvalue(IEConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("InputDynamicData")) {
                    IEWebActions.InputDynamicData(IEConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyimagesrc")) {
                    IEWebActions.verifyimagesrc(IEConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyXcordinate")) {
                    IEWebActions.verifyXCordinate(IEConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyYcordinate")) {
                    IEWebActions.verifyYCordinate(IEConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("Verifybgcolor")) {
                    IEWebActions.verifybgcolor(IEConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("switchtab")) {
                    IEWebActions.switchtab(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("closetab")) {
                    IEWebActions.closetab(IEConfig.driver);
                }
                if (object_type.equalsIgnoreCase("click")) {
                    IEWebActions.clickAction(IEConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("click") && actions.equalsIgnoreCase("select")) {
                    IEWebActions.select(IEConfig.driver, locator_type, locator_value, testdata, description);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyselecttext")) {
                    IEWebActions.verifySelectText(IEConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("cleartext")) {
                    IEWebActions.cleartext(IEConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyinputtext")) {
                    IEWebActions.verifyInputText(IEConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("javascriptclick")) {
                    IEWebActions.javascriptClick(IEConfig.driver, locator_value, locator_value, description, expectedresult);
                }
                if (actions.equals("closebrowsertab")) {
                    IEWebActions.closeBrowserTab(IEConfig.driver, description);
                    IEConfig.loggerIE.log(LogStatus.PASS, description);
                }
                if (actions.equalsIgnoreCase("checkpagetitle")) {
                    IEWebActions.checkPageTitle(IEConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("switchnewtab")) {
                    ChromeWebActions.switchnewtab(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("closeactivetab")) {
                    ChromeWebActions.closeactivetab(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("switchbacktab")) {
                    ChromeWebActions.switchbacktab(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("reloadpage")) {
                    IEWebActions.reloadPage(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("navigateback")) {
                    IEWebActions.navigateBack(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("navigateforward")) {
                    IEWebActions.navigateForward(IEConfig.driver);
                }
                if (actions.equalsIgnoreCase("verifyURL")) {
                    IEWebActions.verifyURL(IEConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("verifyelement")) {
                    IEWebActions.verifyElement(IEConfig.driver, locator_type, locator_value, description);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equals("mousehover")) {
                    IEWebActions.MouseHover(IEConfig.driver, locator_value, locator_value, description, expectedresult);
                    IEConfig.loggerIE.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("CaptureScreeshot") && actions.equals("screenshot")) {
                    IEWebActions.getScreenshot(IEConfig.driver);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equals("mouseclick")) {
                    IEWebActions.mouseclick(IEConfig.driver, locator_type, locator_value, description, expectedresult);
                    IEConfig.loggerIE.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitshort")) {
                    IEWebActions.sleepShort(IEConfig.driver);
                    IEConfig.loggerIE.log(LogStatus.PASS, "Wait");
                }
                if (actions.equalsIgnoreCase("customwait")) {
                    IEWebActions.customWait(IEConfig.driver, testdata);
                }
                if (actions.equalsIgnoreCase("verifytooltip")) {
                    IEWebActions.verifyTooltip(IEConfig.driver, locator_type, locator_value, description, expectedresult, actualresult, testdata);
                }
                if (actions.equals("downloadfile")) {
                    IEWebActions.downloadfile(IEConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("draganddrop")) {
                    IEWebActions.MouseDrag(IEConfig.driver, locator_type, locator_value, description, expectedresult, actualresult);
                }
                if (actions.equals("waitmedium")) {
                    IEWebActions.sleepMedium(IEConfig.driver);
                    IEConfig.loggerIE.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitlong")) {
                    ChromeWebActions.sleepLong(IEConfig.driver);
                    IEConfig.loggerIE.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("scrolldown")) {
                    IEWebActions.scrollDown(IEConfig.driver, description, expectedresult);
                    IEConfig.loggerIE.log(LogStatus.PASS, description);
                }
                if (actions.equals("scrollup")) {
                    IEWebActions.scrollUp(IEConfig.driver, description, expectedresult);
                    IEConfig.loggerIE.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equals("mousehover")) {
                    IEWebActions.MouseHover(IEConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifytext")) {
                    IEWebActions.verifyText(IEConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyalllinks")) {
                    IEWebActions.ClickAllLinks(IEConfig.driver, description, testdata);
                }
                if (object_type.equalsIgnoreCase("getproperty") && actions.equals("getcsscolor")) {
                    IEWebActions.GetCssColor(IEConfig.driver, locator_value, locator_value, expectedresult, description);
                }
            }
            catch (Exception ex) {}
        }
    }
}
