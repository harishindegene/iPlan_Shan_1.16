// 
// Decompiled by Procyon v0.5.36
// 

package config;

import lib.ChromeWebActions;
import lib.BrowserAction;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.InputStream;
import java.net.URL;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import dataProvider.ExcelDataProvider;

public class ChromeConfig extends ExcelDataProvider
{
    public static RemoteWebDriver driver;
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
            ChromeConfig.book = WorkbookFactory.create((InputStream)file);
        }
        catch (InvalidFormatException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        ChromeConfig.sheet = ChromeConfig.book.getSheet(sheetName);
        System.out.println(sheetName);
        for (int i = 0; i <= ChromeConfig.sheet.getLastRowNum(); ++i) {
            try {
                final String description = ChromeConfig.sheet.getRow(i).getCell(0).toString().trim();
                final String object_type = ChromeConfig.sheet.getRow(i).getCell(1).toString().trim();
                final String actions = ChromeConfig.sheet.getRow(i).getCell(2).toString().trim();
                final String locator_type = ChromeConfig.sheet.getRow(i).getCell(3).toString().trim();
                final String locator_value = ChromeConfig.sheet.getRow(i).getCell(4).toString().trim();
                final String testdata = ChromeConfig.sheet.getRow(i).getCell(5).toString().trim();
                final String numerictestdata = new DataFormatter().formatCellValue(ChromeConfig.sheet.getRow(i).getCell(5));
                final String expectedresult = ChromeConfig.sheet.getRow(i).getCell(6).toString().trim();
                final String actualresult = ChromeConfig.sheet.getRow(i).getCell(7).toString().trim();
                final String dataprovider = ChromeConfig.sheet.getRow(i).getCell(8).toString().trim();
                if (object_type.equalsIgnoreCase("browser")) {
                    if (testdata.equalsIgnoreCase("NA")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            String URL = "http://192.168.8.175:4444/wd/hub";
									DesiredCapabilities capability = DesiredCapabilities.chrome();
									capability.setBrowserName("chrome");
									capability.setVersion("95");
									capability.setPlatform(Platform.WINDOWS);
									driver = new RemoteWebDriver(new URL(URL), capability);
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            ChromeConfig.driver.quit();
                        }
                    }
                    if (testdata.equalsIgnoreCase("IE") && actions.equalsIgnoreCase("IE")) {
                        System.setProperty("webdriver.ie.driver", ".//BrowserUtilities//IEDriverServer.exe");
                       // ChromeConfig.driver = (WebDriver)new InternetExplorerDriver();
                    }
                    if (actions.equalsIgnoreCase("closeBrowser")) {
                        ChromeConfig.driver.quit();
                    }
                    if (testdata.equalsIgnoreCase("Chrome")) {
                        if (actions.equalsIgnoreCase("startBrowser")) {
                            System.setProperty("webdriver.chrome.driver", ".//BrowserUtilities//chromedriver.exe");
                          //  ChromeConfig.driver = (WebDriver)new ChromeDriver();
                        }
                        if (actions.equalsIgnoreCase("closeBrowser")) {
                            ChromeConfig.driver.quit();
                        }
                    }
                    ChromeConfig.logger.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("OpenApp") && actions.equalsIgnoreCase("navigate")) {
                    BrowserAction.openApplication(ChromeConfig.driver, testdata);
                    ChromeConfig.logger.log(LogStatus.PASS, description);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("type")) {
                    ChromeWebActions.typeAction(ChromeConfig.driver, locator_type, locator_value, testdata, description,expectedresult,dataprovider);
                }
                
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("typehour")) {
                    ChromeWebActions.typeHour(ChromeConfig.driver, locator_type, locator_value, testdata, description,expectedresult,dataprovider);
                }
                
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("typeminute")) {
                    ChromeWebActions.typeMinute(ChromeConfig.driver, locator_type, locator_value, testdata, description,expectedresult,dataprovider);
                }
                
                
                //Type proplocator
                if (object_type.equalsIgnoreCase("typePropLocator") && actions.equalsIgnoreCase("type")) {
                    ChromeWebActions.typeActionProp(ChromeConfig.driver, locator_type, locator_value, testdata, description);
                }
                
                //Type Propertylocator and value
                
                if (object_type.equalsIgnoreCase("typePropLocator") && actions.equalsIgnoreCase("typePropData")) {
                    ChromeWebActions.typeActionPropertyFile(ChromeConfig.driver, locator_type, locator_value, testdata, description);
                }
                
                
                if (actions.equalsIgnoreCase("keyboardTab")) {
                    ChromeWebActions.keyboardTab(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardDownArrow")) {
                    ChromeWebActions.keyboardDownArrow(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("selectall")) {
                    ChromeWebActions.SelectAll(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardUpArrow")) {
                    ChromeWebActions.keyboardUpArrow(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("keyboardEnter")) {
                    ChromeWebActions.keyboardEnter(ChromeConfig.driver);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("typevalue")) {
                    ChromeWebActions.entervalue(ChromeConfig.driver, locator_type, locator_value, description, numerictestdata);
                }
                
                //New Action item for config
                
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("typePropData")) {
                    ChromeWebActions.typePropData(ChromeConfig.driver, locator_type, locator_value, testdata, description);
                }
              
                
                if (actions.equalsIgnoreCase("slideaction")) {
                    ChromeWebActions.slideraction(ChromeConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (object_type.equalsIgnoreCase("typeText") && actions.equalsIgnoreCase("InputDynamicData")) {
                    ChromeWebActions.InputDynamicData(ChromeConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (actions.equalsIgnoreCase("click")) {
                    ChromeWebActions.clickAction(ChromeConfig.driver, locator_type, locator_value, description,testdata, expectedresult, actualresult,dataprovider);
                }
                if (actions.equalsIgnoreCase("switchtoactive")) {
                    ChromeWebActions.switchtoactive(ChromeConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("switchtodefault")) {
                    ChromeWebActions.switchtodefault(ChromeConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("percycapture")) {
                    ChromeWebActions.percyload(ChromeConfig.driver, testdata);
                }
                
               
                
                
                
                if (object_type.equalsIgnoreCase("click") && actions.equalsIgnoreCase("select")) {
                    ChromeWebActions.select(ChromeConfig.driver, locator_type, locator_value, testdata, description);
                }
                if (actions.equalsIgnoreCase("javascriptclick")) {
                    ChromeWebActions.javascriptClick(ChromeConfig.driver, locator_value, locator_value, description, expectedresult);
                }
                if (actions.equals("closebrowsertab")) {
                    ChromeWebActions.closeBrowserTab(ChromeConfig.driver, description);
                    
                }
                if (actions.equalsIgnoreCase("checkpagetitle")) {
                    ChromeWebActions.checkPageTitle(ChromeConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("reloadpage")) {
                    ChromeWebActions.reloadPage(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("getpageloadtime")) {
                    ChromeWebActions.getpageloadtime(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("navigateback")) {
                    ChromeWebActions.navigateBack(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("navigateforward")) {
                    ChromeWebActions.navigateForward(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("switchnewtab")) {
                    ChromeWebActions.switchnewtab(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("closeactivetab")) {
                    ChromeWebActions.closeactivetab(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("switchbacktab")) {
                    ChromeWebActions.switchbacktab(ChromeConfig.driver);
                }
                if (actions.equalsIgnoreCase("verifyURL")) {
                    ChromeWebActions.verifyURL(ChromeConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("verifyTextinPDF")) {
                    ChromeWebActions.verifyTextinPDF(ChromeConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("verifyelement")) {
                    ChromeWebActions.verifyElement(ChromeConfig.driver, locator_type, locator_value, description);
                }
                
                if (actions.equalsIgnoreCase("isElementPresent")) {
                    ChromeWebActions.isElementPresent(ChromeConfig.driver, locator_type, locator_value);
                }
                
                if (object_type.equalsIgnoreCase("CaptureScreeshot") && actions.equals("screenshot")) {
                    ChromeWebActions.getScreenshot(ChromeConfig.driver);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equals("mouseclick")) {
                    ChromeWebActions.mouseclick(ChromeConfig.driver, locator_type, locator_value, description, expectedresult);
                    ChromeConfig.logger.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("rightclick")) {
                    ChromeWebActions.rightclick(ChromeConfig.driver, locator_type, locator_value, description, expectedresult);
                    ChromeConfig.logger.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("ischeckBoxEnabled")) {
                    ChromeWebActions.ischeckBoxEnabled(ChromeConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (actions.equals("islink")) {
                    ChromeWebActions.islink(ChromeConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (actions.equals("waitshort")) {
                    ChromeWebActions.sleepShort(ChromeConfig.driver);
                    ChromeConfig.logger.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitmedium")) {
                    ChromeWebActions.sleepMedium(ChromeConfig.driver);
                    ChromeConfig.logger.log(LogStatus.PASS, "Wait");
                }
                if (actions.equals("waitlong")) {
                    ChromeWebActions.sleepLong(ChromeConfig.driver);
                    ChromeConfig.logger.log(LogStatus.PASS, "WaitLong");
                }
                if (actions.equals("scrolldown")) {
                    ChromeWebActions.scrollDown(ChromeConfig.driver, description, expectedresult);
                    ChromeConfig.logger.log(LogStatus.PASS, description);
                }
                
                if (actions.equals("scrollToElement")) {
					ChromeWebActions.scrollToElement(driver,locator_type, locator_value, description, expectedresult);
					logger.log(LogStatus.PASS, description);
				}

                if (actions.equals("explictwait")) {
                    ChromeWebActions.explicitwait(ChromeConfig.driver, locator_type, locator_value);
                }
                if (actions.equalsIgnoreCase("customwait")) {
                    ChromeWebActions.customWait(ChromeConfig.driver, testdata);
                }
                if (actions.equalsIgnoreCase("verifytooltip")) {
                    ChromeWebActions.verifyTooltip(ChromeConfig.driver, locator_type, locator_value, description, expectedresult, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("draganddrop")) {
                    ChromeWebActions.MouseDrag(ChromeConfig.driver, locator_type, locator_value, description, expectedresult, actualresult);
                }
                
                //New Action item iWAgent  1.8.15
                
                if (actions.equalsIgnoreCase("draganddropbyoffset")) {
                    ChromeWebActions.MouseDragOffset(ChromeConfig.driver, locator_type, locator_value, description, expectedresult, actualresult);
                }
                
                if (actions.equals("scrollup")) {
                    ChromeWebActions.scrollUp(ChromeConfig.driver, description, expectedresult);
                    ChromeConfig.logger.log(LogStatus.PASS, description);
                }
                if (actions.equals("downloadfile")) {
                    ChromeWebActions.downloadfile(ChromeConfig.driver, testdata, description, expectedresult);
                }
                if (actions.equalsIgnoreCase("uploadfile")) {
                    ChromeWebActions.uploadfile(ChromeConfig.driver, testdata, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("move") && actions.equalsIgnoreCase("mousehover")) {
                    ChromeWebActions.MouseHover(ChromeConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifytext")) {
                    ChromeWebActions.verifyText(ChromeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifypartialtext")) {
                    ChromeWebActions.verifyPartialText(ChromeConfig.driver, locator_type, locator_value, description, testdata);
                }
                if (actions.equalsIgnoreCase("verifyimgsrc")) {
                    ChromeWebActions.verifyimagesrc(ChromeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyXcordinate")) {
                    ChromeWebActions.verifyXCordinate(ChromeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyYcordinate")) {
                    ChromeWebActions.verifyYCordinate(ChromeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("Verifybgcolor")) {
                    ChromeWebActions.verifybgcolor(ChromeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("savetext")) {
                    ChromeWebActions.savetext(ChromeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (actions.equalsIgnoreCase("comparetext")) {
                    ChromeWebActions.comparetext(ChromeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyimagesrc")) {
                    ChromeWebActions.verifyimagesrc(ChromeConfig.driver, locator_value, expectedresult, description, testdata);
                }
                if (object_type.equalsIgnoreCase("image") && actions.equalsIgnoreCase("captureimage")) {
                    ChromeWebActions.captureImage(ChromeConfig.driver, locator_type, locator_value, description, expectedresult, testdata);
                }
                if (object_type.equalsIgnoreCase("image") && actions.equalsIgnoreCase("compareimage")) {
                    ChromeWebActions.compareImage(ChromeConfig.driver, locator_type, locator_value, description, expectedresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyhiddenvalue")) {
                    ChromeWebActions.verifyhiddenvalue(ChromeConfig.driver, locator_type, locator_value, description, testdata, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyvalue")) {
                    ChromeWebActions.verifyvalue(ChromeConfig.driver, locator_type, locator_value, description, testdata, expectedresult);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyselecttext")) {
                    ChromeWebActions.verifySelectText(ChromeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("cleartext")) {
                    ChromeWebActions.cleartext(ChromeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (actions.equalsIgnoreCase("clearvalue")) {
                    ChromeWebActions.clearvalue(ChromeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyinputtext")) {
                    ChromeWebActions.verifyInputText(ChromeConfig.driver, locator_value, expectedresult, description, actualresult, testdata);
                }
                if (object_type.equalsIgnoreCase("verify") && actions.equalsIgnoreCase("verifyalllinks")) {
                    ChromeWebActions.ClickAllLinks(ChromeConfig.driver, description, testdata);
                }
                if (object_type.equalsIgnoreCase("getproperty") && actions.equals("getcsscolor")) {
                    ChromeWebActions.GetCssColor(ChromeConfig.driver, locator_value, locator_value, expectedresult, description);
                }
                
                //New changes 1.8.16 (Sreelakshmi)
                
                if (actions.equalsIgnoreCase("isElementSelected")) {
                    ChromeWebActions.isElementSelected(ChromeConfig.driver, locator_type, locator_value,description);
                }
               
                //New changes 1.8.16 (Sreelakshmi)
                if (actions.equalsIgnoreCase("isElementEnabled")) {
                    ChromeWebActions.isEnabled(ChromeConfig.driver, locator_type, locator_value, description, expectedresult);
                }
                //New changes 1.8.16 (Sreelakshmi)
                if (actions.equals("doubleclick")) {
                    ChromeWebActions.doubleclick(ChromeConfig.driver, locator_type, locator_value, description, expectedresult);
                   
                }
            }
            catch (Exception e4) {
                System.out.println(e4);
               
             //   System.out.println("Printing the exception");
                System.out.println(ChromeConfig.sheet.getRow(i).getCell(0).toString().trim());
            }
        }
    }
}
