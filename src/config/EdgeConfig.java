// 
// Decompiled by Procyon v0.5.36
// 

package config;

import lib.ChromeWebActions;
import lib.EdgeWebActions;
import lib.BrowserAction;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;
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

public class EdgeConfig extends ExcelDataProvider
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
            EdgeConfig.book = WorkbookFactory.create((InputStream)file);
        }
        catch (InvalidFormatException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        EdgeConfig.sheet = EdgeConfig.book.getSheet(sheetName);
        System.out.println(sheetName);
        for (int i = 0; i <= EdgeConfig.sheet.getLastRowNum(); ++i) {
            try {
                final String description = EdgeConfig.sheet.getRow(i).getCell(0).toString().trim();
                final String object_type = EdgeConfig.sheet.getRow(i).getCell(1).toString().trim();
                final String actions = EdgeConfig.sheet.getRow(i).getCell(2).toString().trim();
                final String locator_type = EdgeConfig.sheet.getRow(i).getCell(3).toString().trim();
                final String locator_value = EdgeConfig.sheet.getRow(i).getCell(4).toString().trim();
                final String testdata = EdgeConfig.sheet.getRow(i).getCell(5).toString().trim();
                final String expectedresult = EdgeConfig.sheet.getRow(i).getCell(6).toString().trim();
                final String actualresult = EdgeConfig.sheet.getRow(i).getCell(7).toString().trim();
                if (object_type.equalsIgnoreCase("browser")) {
                    if (testdata.equalsIgnoreCase("NA")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.edge.driver", ".//BrowserUtilities//msedgedriver.exe");
                            EdgeConfig.driver = (WebDriver)new EdgeDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            EdgeConfig.driver.quit();
                        }
                    }
                    if (testdata.equalsIgnoreCase("IE") && actions.equalsIgnoreCase("IE")) {
                        System.setProperty("webdriver.ie.driver", ".//BrowserUtilities//IEDriverServer.exe");
                        EdgeConfig.driver = (WebDriver)new InternetExplorerDriver();
                    }
                    if (actions.equalsIgnoreCase("closeBrowser")) {
                        EdgeConfig.driver.quit();
                    }
                    if (testdata.equalsIgnoreCase("Chrome")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.chrome.driver", ".//BrowserUtilities//chromedriver.exe");
                            EdgeConfig.driver = (WebDriver)new ChromeDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            EdgeConfig.driver.quit();
                        }
                    }
                    EdgeConfig.loggerEC.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("OpenApp") && actions.equalsIgnoreCase("navigate")) {
                    BrowserAction.openApplication(EdgeConfig.driver, testdata);
                    EdgeConfig.loggerEC.log(LogStatus.PASS, description);
                }
                if (actions.equalsIgnoreCase("keyboardTab")) {
                    EdgeWebActions.keyboardTab(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardDownArrow")) {
                    EdgeWebActions.keyboardDownArrow(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardUpArrow")) {
                    EdgeWebActions.keyboardUpArrow(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardEnter")) {
                    EdgeWebActions.keyboardEnter(EdgeConfig.driver);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyvalue")) {
                    EdgeWebActions.verifyvalue(EdgeConfig.driver, locator_type, locator_value, description, testdata, expectedresult);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("typevalue")) {
                    EdgeWebActions.entervalue(EdgeConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (object_type.equalsIgnoreCase("click")) {
                    EdgeWebActions.clickAction(EdgeConfig.driver, locator_type, locator_value, description, expectedresult, actualresult);
                }
                if (object_type.equalsIgnoreCase("click") && actions.equalsIgnoreCase("select")) {
                    EdgeWebActions.select(EdgeConfig.driver, locator_type, locator_value, testdata, description);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyselecttext")) {
                    EdgeWebActions.verifySelectText(EdgeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("cleartext")) {
                    EdgeWebActions.cleartext(EdgeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyinputtext")) {
                    EdgeWebActions.verifyInputText(EdgeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("javascriptclick")) {
                    EdgeWebActions.javascriptClick(EdgeConfig.driver, locator_value, locator_value, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("type")) {
                    EdgeWebActions.typeAction(EdgeConfig.driver, locator_type, locator_value, testdata, description);
                }
                if (actions.equalsIgnoreCase("getpageloadtime")) {
                    EdgeWebActions.getpageloadtime(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("savetext")) {
                    EdgeWebActions.savetext(EdgeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("slideaction")) {
                    EdgeWebActions.slideraction(EdgeConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (actions.equalsIgnoreCase("clearvalue")) {
                    EdgeWebActions.clearvalue(EdgeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("InputDynamicData")) {
                    EdgeWebActions.InputDynamicData(EdgeConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (actions.equalsIgnoreCase("comparetext")) {
                    EdgeWebActions.comparetext(EdgeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyimagesrc")) {
                    EdgeWebActions.verifyimagesrc(EdgeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyXcordinate")) {
                    EdgeWebActions.verifyXCordinate(EdgeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyYcordinate")) {
                    EdgeWebActions.verifyYCordinate(EdgeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("Verifybgcolor")) {
                    EdgeWebActions.verifybgcolor(EdgeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equals("closebrowsertab")) {
                    EdgeWebActions.closeBrowserTab(EdgeConfig.driver, description);
                    EdgeConfig.loggerEC.log(LogStatus.PASS, description);
                }
                if (actions.equalsIgnoreCase("checkpagetitle")) {
                    EdgeWebActions.checkPageTitle(EdgeConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("reloadpage")) {
                    EdgeWebActions.reloadPage(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("switchnewtab")) {
                    ChromeWebActions.switchnewtab(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("closeactivetab")) {
                    ChromeWebActions.closeactivetab(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("switchbacktab")) {
                    ChromeWebActions.switchbacktab(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("navigateback")) {
                    EdgeWebActions.navigateBack(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("verifyURL")) {
                    EdgeWebActions.verifyURL(EdgeConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("verifyelement")) {
                    EdgeWebActions.verifyElement(EdgeConfig.driver, locator_type, locator_value, description);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equals("mousehover")) {
                    EdgeWebActions.MouseHover(EdgeConfig.driver, locator_value, locator_value, description, expectedresult);
                    EdgeConfig.loggerEC.log(LogStatus.PASS, description);
                }
                if (actions.equals("downloadfile")) {
                    EdgeWebActions.downloadfile(EdgeConfig.driver, testdata, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("CaptureScreeshot") && actions.equals("screenshot")) {
                    EdgeWebActions.getScreenshot(EdgeConfig.driver);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equals("mouseclick")) {
                    EdgeWebActions.mouseclick(EdgeConfig.driver, locator_type, locator_value, description, expectedresult);
                    EdgeConfig.loggerEC.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitshort")) {
                    EdgeWebActions.sleepShort(EdgeConfig.driver);
                    EdgeConfig.loggerEC.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitmedium")) {
                    EdgeWebActions.sleepMedium(EdgeConfig.driver);
                    EdgeConfig.loggerEC.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitlong")) {
                    ChromeWebActions.sleepLong(EdgeConfig.driver);
                    EdgeConfig.loggerEC.log(LogStatus.PASS, "Wait");
                }
                if (actions.equalsIgnoreCase("customwait")) {
                    EdgeWebActions.customWait(EdgeConfig.driver, testdata);
                }
                if (actions.equalsIgnoreCase("verifytooltip")) {
                    EdgeWebActions.verifyTooltip(EdgeConfig.driver, locator_type, locator_value, description, expectedresult, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("draganddrop")) {
                    EdgeWebActions.MouseDrag(EdgeConfig.driver, locator_type, locator_value, description, expectedresult, actualresult);
                }
                if (actions.equals("scrolldown")) {
                    EdgeWebActions.scrollDown(EdgeConfig.driver, description, expectedresult);
                    EdgeConfig.loggerEC.log(LogStatus.PASS, description);
                }
                if (actions.equals("scrollup")) {
                    EdgeWebActions.scrollUp(EdgeConfig.driver, description, expectedresult);
                    EdgeConfig.loggerEC.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equals("mousehover")) {
                    EdgeWebActions.MouseHover(EdgeConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifytext")) {
                    EdgeWebActions.verifyText(EdgeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyalllinks")) {
                    EdgeWebActions.ClickAllLinks(EdgeConfig.driver, description, testdata);
                }
                if (actions.equals("explictwait")) {
                    EdgeWebActions.explicitwait(EdgeConfig.driver, locator_type, locator_value);
                }
                if (actions.equalsIgnoreCase("navigateback")) {
                    EdgeWebActions.navigateBack(EdgeConfig.driver);
                }
                if (actions.equalsIgnoreCase("navigateforward")) {
                    EdgeWebActions.navigateForward(EdgeConfig.driver);
                }
                actions.equalsIgnoreCase("switchtab");
                actions.equalsIgnoreCase("closetab");
                if (object_type.equalsIgnoreCase("getproperty") && actions.equals("getcsscolor")) {
                    EdgeWebActions.GetCssColor(EdgeConfig.driver, locator_value, locator_value, expectedresult, description);
                }
            }
            catch (Exception ex) {}
        }
    }
}
