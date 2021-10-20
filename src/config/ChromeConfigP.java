// 
// Decompiled by Procyon v0.5.36
// 

package config;

import lib.ChromeWebActions;
import lib.BrowserAction;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class ChromeConfigP extends ExcelDataProvider
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
            ChromeConfigP.book = WorkbookFactory.create((InputStream)file);
        }
        catch (InvalidFormatException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        ChromeConfigP.sheet = ChromeConfigP.book.getSheet(sheetName);
        System.out.println(sheetName);
        for (int i = 0; i <= ChromeConfigP.sheet.getLastRowNum(); ++i) {
            try {
                final String description = ChromeConfigP.sheet.getRow(i).getCell(0).toString().trim();
                final String object_type = ChromeConfigP.sheet.getRow(i).getCell(1).toString().trim();
                final String actions = ChromeConfigP.sheet.getRow(i).getCell(2).toString().trim();
                final String locator_type = ChromeConfigP.sheet.getRow(i).getCell(3).toString().trim();
                final String locator_value = ChromeConfigP.sheet.getRow(i).getCell(4).toString().trim();
                final String testdata = ChromeConfigP.sheet.getRow(i).getCell(5).toString().trim();
                final String numerictestdata = new DataFormatter().formatCellValue(ChromeConfigP.sheet.getRow(i).getCell(5));
                final String expectedresult = ChromeConfigP.sheet.getRow(i).getCell(6).toString().trim();
                final String actualresult = ChromeConfigP.sheet.getRow(i).getCell(7).toString().trim();
                final String dataprovider = ChromeConfig.sheet.getRow(i).getCell(8).toString().trim();
                if (object_type.equalsIgnoreCase("browser")) {
                    if (testdata.equalsIgnoreCase("NA")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.chrome.driver", ".//BrowserUtilities//chromedriver.exe");
                            ChromeConfigP.driver = (WebDriver)new ChromeDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            ChromeConfigP.driver.quit();
                        }
                    }
                    if (testdata.equalsIgnoreCase("IE") && actions.equalsIgnoreCase("IE")) {
                        System.setProperty("webdriver.ie.driver", ".//BrowserUtilities//IEDriverServer.exe");
                        ChromeConfigP.driver = (WebDriver)new InternetExplorerDriver();
                    }
                    if (actions.equalsIgnoreCase("closeBrowser")) {
                        ChromeConfigP.driver.quit();
                    }
                    if (testdata.equalsIgnoreCase("Chrome")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.chrome.driver", ".//BrowserUtilities//chromedriver.exe");
                            ChromeConfigP.driver = (WebDriver)new ChromeDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            ChromeConfigP.driver.quit();
                        }
                    }
                    ChromeConfigP.loggerp.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("OpenApp") && actions.equalsIgnoreCase("navigate")) {
                    BrowserAction.openApplication(ChromeConfigP.driver, testdata);
                    ChromeConfigP.loggerp.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("type")) {
                	ChromeWebActions.typeAction(ChromeConfig.driver, locator_type, locator_value, testdata, description,expectedresult,dataprovider);
                }
                if (actions.equalsIgnoreCase("keyboardTab")) {
                    ChromeWebActions.keyboardTab(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("keyboardDownArrow")) {
                    ChromeWebActions.keyboardDownArrow(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("selectall")) {
                    ChromeWebActions.SelectAll(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("keyboardUpArrow")) {
                    ChromeWebActions.keyboardUpArrow(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("keyboardEnter")) {
                    ChromeWebActions.keyboardEnter(ChromeConfigP.driver);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("typevalue")) {
                    ChromeWebActions.entervalue(ChromeConfigP.driver, locator_type, locator_value, description, numerictestdata);
                }
                if (actions.equalsIgnoreCase("slideaction")) {
                    ChromeWebActions.slideraction(ChromeConfigP.driver, locator_type, locator_value, description, testdata);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("InputDynamicData")) {
                    ChromeWebActions.InputDynamicData(ChromeConfigP.driver, locator_type, locator_value, description, testdata);
                }
                if (actions.equalsIgnoreCase("click")) {
                    ChromeWebActions.clickAction(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult, actualresult, dataprovider, dataprovider);
                }
                if (actions.equals("rightclick")) {
                    ChromeWebActions.rightclick(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult);
                    ChromeConfigP.logger.log(LogStatus.PASS, "Wait");
                }
                if (actions.equalsIgnoreCase("switchtoactive")) {
                    ChromeWebActions.switchtoactive(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("switchtodefault")) {
                    ChromeWebActions.switchtodefault(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("percycapture")) {
                    ChromeWebActions.percyload(ChromeConfigP.driver, testdata);
                }
                if (object_type.equalsIgnoreCase("click") && actions.equalsIgnoreCase("select")) {
                    ChromeWebActions.select(ChromeConfigP.driver, locator_type, locator_value, testdata, description);
                }
                if (actions.equalsIgnoreCase("javascriptclick")) {
                    ChromeWebActions.javascriptClick(ChromeConfigP.driver, locator_value, locator_value, description, expectedresult);
                }
                if (actions.equals("closebrowsertab")) {
                    ChromeWebActions.closeBrowserTab(ChromeConfigP.driver, description);
                    ChromeConfigP.loggerp.log(LogStatus.PASS, description);
                }
                if (actions.equalsIgnoreCase("checkpagetitle")) {
                    ChromeWebActions.checkPageTitle(ChromeConfigP.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("reloadpage")) {
                    ChromeWebActions.reloadPage(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("getpageloadtime")) {
                    ChromeWebActions.getpageloadtime(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("navigateback")) {
                    ChromeWebActions.navigateBack(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("navigateforward")) {
                    ChromeWebActions.navigateForward(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("switchnewtab")) {
                    ChromeWebActions.switchnewtab(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("closeactivetab")) {
                    ChromeWebActions.closeactivetab(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("switchbacktab")) {
                    ChromeWebActions.switchbacktab(ChromeConfigP.driver);
                }
                if (actions.equalsIgnoreCase("verifyURL")) {
                    ChromeWebActions.verifyURL(ChromeConfigP.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("verifyelement")) {
                    ChromeWebActions.verifyElement(ChromeConfigP.driver, locator_type, locator_value, description);
                }
                if (object_type.equalsIgnoreCase("CaptureScreeshot") && actions.equals("screenshot")) {
                    ChromeWebActions.getScreenshot(ChromeConfigP.driver);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equals("mouseclick")) {
                    ChromeWebActions.mouseclick(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult);
                    ChromeConfigP.loggerp.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitshort")) {
                    ChromeWebActions.sleepShort(ChromeConfigP.driver);
                    ChromeConfigP.loggerp.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitmedium")) {
                    ChromeWebActions.sleepMedium(ChromeConfigP.driver);
                    ChromeConfigP.loggerp.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitlong")) {
                    ChromeWebActions.sleepLong(ChromeConfigP.driver);
                    ChromeConfigP.loggerp.log(LogStatus.PASS, "WaitLong");
                }
                if (actions.equals("scrolldown")) {
                    ChromeWebActions.scrollDown(ChromeConfigP.driver, description, expectedresult);
                    ChromeConfigP.loggerp.log(LogStatus.PASS, description);
                }
                if (actions.equals("explictwait")) {
                    ChromeWebActions.explicitwait(ChromeConfigP.driver, locator_type, locator_value);
                }
                if (actions.equalsIgnoreCase("customwait")) {
                    ChromeWebActions.customWait(ChromeConfigP.driver, testdata);
                }
                if (actions.equalsIgnoreCase("verifytooltip")) {
                    ChromeWebActions.verifyTooltip(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("draganddrop")) {
                    ChromeWebActions.MouseDrag(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult, actualresult);
                }
                if (actions.equals("scrollup")) {
                    ChromeWebActions.scrollUp(ChromeConfigP.driver, description, expectedresult);
                    ChromeConfigP.loggerp.log(LogStatus.PASS, description);
                }
                if (actions.equals("downloadfile")) {
                    ChromeWebActions.downloadfile(ChromeConfigP.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("uploadfile")) {
                    ChromeWebActions.uploadfile(ChromeConfigP.driver, testdata, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equalsIgnoreCase("mousehover")) {
                    ChromeWebActions.MouseHover(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifytext")) {
                    ChromeWebActions.verifyText(ChromeConfigP.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("verifyimgsrc")) {
                    ChromeWebActions.verifyimagesrc(ChromeConfigP.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyXcordinate")) {
                    ChromeWebActions.verifyXCordinate(ChromeConfigP.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyYcordinate")) {
                    ChromeWebActions.verifyYCordinate(ChromeConfigP.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("Verifybgcolor")) {
                    ChromeWebActions.verifybgcolor(ChromeConfigP.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("savetext")) {
                    ChromeWebActions.savetext(ChromeConfigP.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("comparetext")) {
                    ChromeWebActions.comparetext(ChromeConfigP.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyimagesrc")) {
                    ChromeWebActions.verifyimagesrc(ChromeConfigP.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("image") && actions.equalsIgnoreCase("captureimage")) {
                    ChromeWebActions.captureImage(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult, testdata);
                }
                if (object_type.equalsIgnoreCase("image") && actions.equalsIgnoreCase("compareimage")) {
                    ChromeWebActions.compareImage(ChromeConfigP.driver, locator_type, locator_value, description, expectedresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyhiddenvalue")) {
                    ChromeWebActions.verifyhiddenvalue(ChromeConfigP.driver, locator_type, locator_value, description, testdata, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyvalue")) {
                    ChromeWebActions.verifyvalue(ChromeConfigP.driver, locator_type, locator_value, description, testdata, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyselecttext")) {
                    ChromeWebActions.verifySelectText(ChromeConfigP.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("cleartext")) {
                    ChromeWebActions.cleartext(ChromeConfigP.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("clearvalue")) {
                    ChromeWebActions.clearvalue(ChromeConfigP.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyinputtext")) {
                    ChromeWebActions.verifyInputText(ChromeConfigP.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyalllinks")) {
                    ChromeWebActions.ClickAllLinks(ChromeConfigP.driver, description, testdata);
                }
                if (object_type.equalsIgnoreCase("getproperty") && actions.equals("getcsscolor")) {
                    ChromeWebActions.GetCssColor(ChromeConfigP.driver, locator_value, locator_value, expectedresult, description);
                }
            }
            catch (Exception e4) {
                System.out.println(e4);
                System.out.println("Printing the exception");
            }
        }
    }
}
