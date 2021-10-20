// 
// Decompiled by Procyon v0.5.36
// 

package lib;

import org.openqa.selenium.NoSuchElementException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.openqa.selenium.Point;
import org.openqa.selenium.support.Color;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.net.HttpURLConnection;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.regex.Pattern;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import util.SendEmail.ExcelUtils;

import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.File;
import ru.yandex.qatools.ashot.AShot;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;

import java.util.concurrent.TimeUnit;
import io.percy.selenium.Percy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.By;
import java.awt.datatransfer.Clipboard;
import java.awt.AWTException;

import com.google.common.primitives.Chars;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.mail.util.BASE64EncoderStream;

import java.awt.Robot;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import org.openqa.selenium.WebDriver;
import config.ChromeConfig;

public class ChromeWebActions extends ChromeConfig
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
        ChromeWebActions.logger.log(LogStatus.PASS, description);
        return expectedresult;
    }
    
    public static String uploadfile(final WebDriver driver, final String testdata, final String description, final String expectedresult) throws AWTException {
        Robot robot = null;
        try {
            robot = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
        StringSelection str = new StringSelection(testdata);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
     
         // press Contol+V for pasting
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
     
        // release Contol+V for pasting
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
     
        // for pressing and releasing Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    	 
       
        ChromeWebActions.logger.log(LogStatus.PASS, description);
        return expectedresult;
    }
    
    /**
	 * @param driver
	 * @param description
	 * @param expectedresult
	 * @return
	 * @throws ElementNotVisibleException
	 */
	public static String scrollToElement(WebDriver driver,String locatorType, String locatorValue,  String description, String expectedresult)
			throws ElementNotVisibleException {

		try {

			Thread.sleep(2000);
			
			WebElement element = driver.findElement(By.xpath(locatorValue));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:"
					+ expectedresult + "</span>");
			logger.log(LogStatus.INFO,
					"<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Scroll Down Success</span>");

			return "pass";
		} catch (Exception e) {

			return e.getMessage();
		}

	}
    
    
    public static String checkPageTitle(final WebDriver driver, final String testdata, final String description, final String expectedresult) throws Exception {
        final String title = driver.getTitle();
        if (testdata.equalsIgnoreCase(title)) {
            ChromeWebActions.logger.log(LogStatus.PASS, description);
            ChromeWebActions.logger.log(LogStatus.PASS, "Browser Title Is Displayed As Expected");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + title + "</span>");
        }
        else {
            ChromeConfig.logger.log(LogStatus.FAIL, "Page Title incorrect");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]" + title + "</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
        }
        return "pass";
    }
    
    public static String MouseDrag(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult, final String actualresult) throws Exception {
        try {
            final WebElement dragfrom = driver.findElement(By.xpath(locatorValue));
            
            System.out.println(locatorValue);
            System.out.println(actualresult);
            final WebElement dropto = driver.findElement(By.xpath(actualresult));
            
            
            Actions builder = new Actions(driver);
            builder.clickAndHold(dragfrom);
            Thread.sleep(10000);
            builder.moveToElement(dropto).perform();
            Thread.sleep(10000);// add 2 sec wait
            builder.release(dropto).build().perform();
            
            /*String xto=Integer.toString(dropto.getLocation().x);
		    String yto=Integer.toString(dropto.getLocation().y);
		    
		    System.out.println(xto);
		    
		    System.out.println(yto);
           
            JavascriptExecutor executor = (JavascriptExecutor)driver;
		    executor.executeScript("function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
		    "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
		    dragfrom,xto,yto);
            
            
           
            
            
           // final Actions a1 = new Actions(driver);
            //Thread.sleep(2000L);
            
           // Action dragAndDrop =
            		//  a1.clickAndHold(dragfrom).moveToElement(dropto, 2, 2)
            		  //  .release(dropto).build();
            		//dragAndDrop.perform();
            
          //  Robot r1 = new Robot();
            
           // dragfrom.sendKeys(Keys.LEFT_SHIFT);
            
          /*  Action drag =
                    a1
                        .moveToElement(dragfrom)
                               .clickAndHold(dragfrom)
                               .moveToElement(dropto)
                               .build();
            drag.perform();
           
           
            Action release =
                    a1
                             .clickAndHold(dragfrom)
                             .release(dropto)
                             .build();*/
     
     
          
            
            
            
           /* Point coordinates1 = dragfrom.getLocation();
            Point coordinates2 = dropto.getLocation();  
            Robot robot = new Robot();           
            robot.mouseMove(coordinates1.getX(), coordinates1.getY());
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(2000);
            robot.mouseMove(coordinates2.getX(), coordinates2.getY());
           
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);*/
            Thread.sleep(2000);
            
         //  a1.clickAndHold(dragfrom).moveToElement(dropto).release(dropto).build().perform();
           
          /* a1.moveToElement(dragfrom).pause(Duration.ofSeconds(2))
           .clickAndHold(dragfrom)
           .pause(Duration.ofSeconds(5))
           .moveByOffset(1, 0);
           Thread.sleep(3000);
           WebDriverWait wait = new WebDriverWait(driver, 3000);
           wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(actualresult))));
           Thread.sleep(3000);
         
           a1.moveToElement(dropto).pause(Duration.ofSeconds(3)).perform();
           Thread.sleep(3000);
           a1.pause(Duration.ofSeconds(5));
       
           Thread.sleep(3000);
          
           
           a1.release(dropto).build().perform();*/
           
           
           // a1.dragAndDrop(dragfrom, dropto).build().perform();
            ChromeWebActions.logger.log(LogStatus.PASS, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Drag and Drop Success</span>");
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return actualresult;
    }
    
    public static String MouseDragOffset(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult, final String actualresult) throws Exception {
        try {
            final WebElement dragfrom = driver.findElement(By.xpath(locatorValue));
            final WebElement dropto = driver.findElement(By.xpath(actualresult));
            final Actions a1 = new Actions(driver);
            Thread.sleep(4000);
            
            
         // a1.dragAndDropBy(dragfrom, 747, 178);
            
            
            
            int xOffset1 = dragfrom.getLocation().getX();
    		
    		int yOffset1 =  dragfrom.getLocation().getY();
            
            int xOffset = dropto.getLocation().getX();
			
    		int yOffset =  dropto.getLocation().getY();
    		
    		System.out.println("xOffset--->"+xOffset+" yOffset--->"+yOffset);
    		
    		//Find the xOffset and yOffset difference to find x and y offset needed in which from object required to dragged and dropped
    		xOffset =(xOffset-xOffset1)+10;
    		yOffset=(yOffset-yOffset1)+20;
    		

    		//Perform dragAndDropBy 
    		a1.dragAndDropBy(dragfrom, xOffset,yOffset).perform();
           // a1.dragAndDrop(dragfrom, dropto).build().perform();*/
            
            
            ChromeWebActions.logger.log(LogStatus.PASS, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Drag and Drop Success</span>");
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return actualresult;
    }
    
    public static String typeAction(final WebDriver driver, final String locatorType, final String locatorValue, final String testdata, final String description, final String expectedresult,final String dataprovider) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { testdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
            	if(!dataprovider.equalsIgnoreCase("NA"))
            	{
            	ExcelUtils.setExcelFile("./TestData/TestData.xlsx");	
            
            	char row=dataprovider.charAt(1);
            	//System.out.println("*************"+row);
            	
            	int rowValue = Character.getNumericValue(row);
            	char col = 0;
            
            	if(dataprovider.length()==5)
            	{
            	col=dataprovider.charAt(3);
            	int colValue = Character.getNumericValue(col);
            	String data = ExcelUtils.getCellData(testdata,rowValue,colValue).toString().trim();
            	System.out.println(data);
            	 driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { data });
                 ChromeWebActions.logger.log(LogStatus.PASS, description);
            	}
            	
            	else if(dataprovider.length()==6)
            	{
            		char col1 = dataprovider.charAt(3);
            		char col2 = dataprovider.charAt(4);
            		String colval =""+col1 + col2;
            		int coloval = Integer.parseInt(colval);
            		System.out.println(coloval);
            		String data = ExcelUtils.getCellData(testdata,rowValue,coloval).toString().trim();
            		driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { data });
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
            		
            		
            	}
     
            	
            	}
            	
            	else
            	{
            		 driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { testdata });
                     ChromeWebActions.logger.log(LogStatus.PASS, description);
            	}
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    //New Action to Enter Hour
    
    public static String typeHour(final WebDriver driver, final String locatorType, final String locatorValue, final String testdata, final String description, final String expectedresult,final String dataprovider) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { testdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
            	
            	int hour=java.util.Calendar.getInstance().get(Calendar.HOUR);
            	
            	if(hour>12)
            	{
            		String HourTime = String.valueOf(hour);
                	
                	System.out.println("*************" +HourTime);
                	
                		 driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { HourTime });
                         ChromeWebActions.logger.log(LogStatus.PASS, description);
            	}
            	
            	else if(hour<12)
            	{
            	int newhour = hour + 12;
            	
            	String HourTime = String.valueOf(newhour);
            	
            	System.out.println("*************" +HourTime);
            	
            		 driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { HourTime });
                     ChromeWebActions.logger.log(LogStatus.PASS, description);
            	}
            	
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String typeMinute(final WebDriver driver, final String locatorType, final String locatorValue, final String testdata, final String description, final String expectedresult,final String dataprovider) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { testdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
            	
            	int Minute=java.util.Calendar.getInstance().get(Calendar.MINUTE);
            	
            	int newMinute = Minute+1;
            	
            	String MinTime = String.valueOf(newMinute);
            	
            		 driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { MinTime });
                     ChromeWebActions.logger.log(LogStatus.PASS, description);
            	
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
// New Action item to get value from property file for Type Action
    
    /**
     * @param driver
     * @param locatorType
     * @param locatorValue
     * @param testdata
     * @param description
     * @return
     * @throws Exception
     */
    
    public static String typeActionProp(final WebDriver driver, final String locatorType, final String locatorValue, final String testdata, final String description) throws Exception {
        try {
        	
        	File file = new File("./PageObjects/HomePage.properties");
        	  
    		FileInputStream fileInput = null;
    		try {
    			fileInput = new FileInputStream(file);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    		Properties prop = new Properties();
    		
    		//load properties file
    		try {
    			prop.load(fileInput);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	
        	
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(prop.getProperty(locatorValue))).sendKeys(new CharSequence[] { testdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
            
                driver.findElement(By.xpath(prop.getProperty(locatorValue))).sendKeys(new CharSequence[] { testdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    
    public static String typeActionPropertyFile(final WebDriver driver, final String locatorType, final String locatorValue, final String testdata, final String description) throws Exception {
        try {
        	
        	File file = new File("./PageObjects/HomePage.properties");
        	  
    		FileInputStream fileInput = null;
    		try {
    			fileInput = new FileInputStream(file);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    		Properties prop = new Properties();
    		
    		//load properties file
    		try {
    			prop.load(fileInput);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    		File file1 = new File("./TestData/TestData.properties");
      	  
    		FileInputStream fileInput1 = null;
    		try {
    			fileInput1 = new FileInputStream(file1);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    		Properties prop1 = new Properties();
    		
    		//load properties file
    		try {
    			prop1.load(fileInput1);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	
        	
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(prop.getProperty(locatorValue))).sendKeys(prop1.getProperty(testdata));;
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
            
                driver.findElement(By.xpath(prop.getProperty(locatorValue))).sendKeys(prop1.getProperty(testdata));;
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    
    // New Action item to get value from property file
    
    /**
     * @param driver
     * @param locatorType
     * @param locatorValue
     * @param testdata
     * @param description
     * @return
     * @throws Exception
     */
    public static String typePropData(final WebDriver driver, final String locatorType, final String locatorValue, final String testdata, final String description) throws Exception {
        try {
        	
        	File file = new File("./TestData/TestData.properties");
      	  
    		FileInputStream fileInput = null;
    		try {
    			fileInput = new FileInputStream(file);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    		Properties prop = new Properties();
    		
    		//load properties file
    		try {
    			prop.load(fileInput);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        		
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(prop.getProperty(testdata));
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.findElement(By.xpath(locatorValue)).sendKeys(prop.getProperty(testdata));
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    
    public static String select(final WebDriver driver, final String locatorType, final String locatorValue, final String testdata, final String description) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final Select comboBox = new Select(driver.findElement(By.id(locatorValue)));
                comboBox.selectByValue(testdata);
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final Select comboBox = new Select(driver.findElement(By.xpath(locatorValue)));
               // System.out.println(testdata);
                comboBox.selectByVisibleText(testdata);
                ChromeWebActions.logger.log(LogStatus.PASS, description);
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
            ChromeWebActions.logger.log(LogStatus.PASS, "Page Reloaded Successfully");
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
            ChromeWebActions.logger.log(LogStatus.PASS, String.valueOf(testdata) + "Percy Page Capture sucess");
            return "pass";
        }
        catch (Exception e) {
            ChromeWebActions.logger.log(LogStatus.FAIL, "Unable to capture Page screenshot");
            return e.getMessage();
        }
    }
    
    public static void sikulidragdrop(final WebDriver driver, final String testdata, final String expectedresult) {
        try {
        	
        	Screen screen = new Screen();
        	final File dir = new File("./ChromeTestImages");
        	final File[] dir_contents = dir.listFiles();
            if (dir.listFiles().length != 0) {
                for (int i = 0; i < dir_contents.length; ++i) {
                    if (dir_contents[i].getName().equalsIgnoreCase(String.valueOf(testdata) + ".png")) {
                        ChromeWebActions.logger.log(LogStatus.FAIL, "Problem with the file name given. Duplicates Found");
                    }
                }
            }
           
            final File dragfrom  = new File("./ChromeTestImages/" + testdata + ".png");
            final File dropto  = new File("./ChromeTestImages/" + expectedresult + ".png");
        	try {
        	screen.dragDrop(dragfrom, dropto);
        	}
        	catch(Exception e)
        	{
        		System.out.println(e);
        	}
        	
           
        }
        catch (Exception e) {
            ChromeWebActions.logger.log(LogStatus.FAIL, "Unable to capture Page screenshot");
           
        }
    }
    
    public static String getpageloadtime(final WebDriver driver) {
        try {
            final long start = System.currentTimeMillis();
            driver.navigate().refresh();
            ChromeWebActions.logger.log(LogStatus.PASS, "Page Reloaded Successfully");
            final long finish = System.currentTimeMillis();
            final long totalTime = finish - start;
            final long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);
           // System.out.println("Total Time for page load - " + totalTime);
            ChromeWebActions.logger.log(LogStatus.INFO, "Total Time for page load -" + seconds + "Seconds");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String navigateBack(final WebDriver driver) {
        try {
            driver.navigate().back();
            ChromeWebActions.logger.log(LogStatus.PASS, "Back Page navigation success");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String navigateForward(final WebDriver driver) {
        try {
            driver.navigate().forward();
            ChromeWebActions.logger.log(LogStatus.PASS, "Forward Page navigation success");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static void explicitwait(final WebDriver driver, final String locatorType, final String locatorValue) {
        
    	final WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
       
    }
    
    public static void switchnewtab(final WebDriver driver) {
        final ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window((String)tabs.get(1));
        ChromeWebActions.logger.log(LogStatus.PASS, "Switch New Tab Success");
    }
    
    public static String captureImage(final WebDriver driver, final String locator, final String locatorValue, final String description, final String actualresult, final String testdata) throws IOException {
        ChromeWebActions.logger.log(LogStatus.INFO, "Image Capture Method");
        try {
            final WebElement logoImageElement = driver.findElement(By.xpath(locatorValue));
            final ru.yandex.qatools.ashot.Screenshot s1 = new AShot().takeScreenshot(driver, logoImageElement);
            final BufferedImage actualImage = s1.getImage();
            final File dir = new File("./ChromeTestImages");
            final File[] dir_contents = dir.listFiles();
            if (dir.listFiles().length != 0) {
                for (int i = 0; i < dir_contents.length; ++i) {
                    if (dir_contents[i].getName().equalsIgnoreCase(String.valueOf(testdata) + ".jpg")) {
                        ChromeWebActions.logger.log(LogStatus.FAIL, "Problem with the file name given. Duplicates Found");
                    }
                }
            }
            ImageIO.write(s1.getImage(), "png", new File("./ChromeTestImages/" + testdata + ".jpg"));
            final File f = new File("./ChromeTestImages/" + testdata + ".png");
            if (f.exists()) {
              //  System.out.println("Image File Captured");
                ChromeWebActions.logger.log(LogStatus.PASS, "Image Capture Successfully");
            }
            else {
              //  System.out.println("Image File NOT exist");
                ChromeWebActions.logger.log(LogStatus.FAIL, "Image Capture Failed");
                
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return testdata;
    }
    
    public static String compareImage(final WebDriver driver, final String locator, final String locatorValue, final String description, final String actualresult, final String testdata) throws IOException {
        try {
            final BufferedImage expectedImage = ImageIO.read(new File("./ChromeTestImages/" + testdata + ".jpg"));
            final WebElement logoImageElement = driver.findElement(By.xpath(locatorValue));
            final ru.yandex.qatools.ashot.Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImageElement);
            final BufferedImage actualImage = logoImageScreenshot.getImage();
            final ImageDiffer imgDiff = new ImageDiffer();
            final ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            
            String expectedImageValue = null;
   
            
            
            if (diff.hasDiff()) {
                System.out.println("Images are Not Same");
                ChromeWebActions.logger.log(LogStatus.FAIL, "Images are not same");
                final String screenshot = Screenshot.getScreenshot(driver);
                final String expectedImagepath = "./ChromeTestImages/"+testdata+".jpg";
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" +testdata+ "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" +ChromeWebActions.logger.addScreenCapture(screenshot) + "</span>");
                
                
               // ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(imagescreenshot));
                
            }
            else {
                System.out.println("Images are Same");
                ChromeWebActions.logger.log(LogStatus.PASS, "Images are same");
                final String expectedImagepath = "./ChromeTestImages/"+testdata+".jpg";
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" +testdata+ "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" +ChromeWebActions.logger.addScreenCapture(screenshot) + "</span>");
                
                
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
            ChromeWebActions.logger.log(LogStatus.PASS, "Tab Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(getScreenshot(driver)));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void SelectAll(final WebDriver driver) throws Exception {
        try {
            final Robot r1 = new Robot();
            r1.keyPress(65);
            r1.keyRelease(65);
            ChromeWebActions.logger.log(LogStatus.PASS, "Select Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(getScreenshot(driver)));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void keyboardDownArrow(final WebDriver driver) throws Exception {
        try {
            final Robot r1 = new Robot();
            r1.keyPress(40);
            r1.keyRelease(40);
            ChromeWebActions.logger.log(LogStatus.PASS, "Down Arrow Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(getScreenshot(driver)));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void keyboardUpArrow(final WebDriver driver) throws Exception {
        try {
            final Robot r1 = new Robot();
            r1.keyPress(38);
            r1.keyRelease(38);
            ChromeWebActions.logger.log(LogStatus.PASS, "Up Arrow Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(getScreenshot(driver)));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void keyboardEnter(final WebDriver driver) throws Exception {
        try {
            final Robot r1 = new Robot();
            r1.keyPress(10);
            r1.keyRelease(10);
            ChromeWebActions.logger.log(LogStatus.PASS, "Enter Action Success");
        }
        catch (AWTException e) {
            e.printStackTrace();
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(getScreenshot(driver)));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
    }
    
    public static void switchbacktab(final WebDriver driver) throws InterruptedException, AWTException {
        final ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window((String)tabs.get(0));
        ChromeWebActions.logger.log(LogStatus.PASS, "Switch Back Tab Success");
    }
    
    public static void closeactivetab(final WebDriver driver) throws InterruptedException, AWTException {
        final ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window((String)tabs.get(1));
        driver.close();
        ChromeWebActions.logger.log(LogStatus.PASS, "Active Tab Is Closed");
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
        Thread.sleep(20000L);
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
      //  System.out.println("@#@#@#@#@#@# Actual Result" + actualresult);
        Thread.sleep(2000L);
        if (actualresult.equalsIgnoreCase(testdata)) {
            ChromeWebActions.logger.log(LogStatus.PASS, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + actualresult + "</span>");
        }
        else {
            ChromeWebActions.logger.log(LogStatus.FAIL, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            actualresult = tooltiparea.getAttribute("title");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult + "</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
        }
        return testdata;
    }
    
    public static String clickAction(final WebDriver driver, final String locatorType, final String locatorValue, final String description,final String testdata, final String expectedresult, final String actualresult, final String dataprovider) throws Exception {
        try {
        	
        	
        	
        	
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).click();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
            	
            	
            	
            	if(dataprovider.equalsIgnoreCase("CurrentDate"))
            	{
            	
            	int date=java.util.Calendar.getInstance().get(Calendar.DATE);
            	
            	String data = String.valueOf(date);
            	System.out.println(data);
            	Thread.sleep(2000);
            	try {
            		
            	String xpath = locatorValue.replace("testdata",data);
            	System.out.println("*************" +xpath);
            	
            	System.out.println(xpath);
           	 	driver.findElement(By.xpath(xpath)).click();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            	}
            	catch(Exception e)
            	{
            		System.out.println(e);
            	}
            	
            	
            	}
            	
            	else if(dataprovider.equalsIgnoreCase("NextDay"))
            	{
            	
            	int date=java.util.Calendar.getInstance().get(Calendar.DATE);
            	
            	int newDate = date+1;
            	
            	String data = String.valueOf(newDate);
            	Thread.sleep(2000);
            	try {
            		
            	String xpath = locatorValue.replace("testdata",data);
            	System.out.println("*************************"+xpath);
           	 	driver.findElement(By.xpath(xpath)).click();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            	}
            	catch(Exception e)
            	{
            		System.out.println(e);
            	}
            	
            	
            	}
            	
            	else if(!dataprovider.equalsIgnoreCase("NA"))
            	{
            	ExcelUtils.setExcelFile("./TestData/TestData.xlsx");
            	char row=dataprovider.charAt(1);
            	//System.out.println("*************"+row);
            	
            	int rowValue = Character.getNumericValue(row);
            	char col = 0;
            
            	if(dataprovider.length()==5)
            	{
            	col=dataprovider.charAt(3);
            	System.out.println("********"+col);
            	int colValue = Character.getNumericValue(col);
            	String data = ExcelUtils.getCellData(testdata,rowValue,colValue).toString().trim();
            	System.out.println(data);
            	Thread.sleep(2000);
            	try {
            		
            	String xpath = locatorValue.replace("testdata",data);
            	System.out.println(xpath);
           	 	driver.findElement(By.xpath(xpath)).click();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            	}
            	catch(Exception e)
            	{
            		System.out.println(e);
            	}
            	
            	
            	}
            	
            	else if(dataprovider.length()==6)
            	{
            		char col1 = dataprovider.charAt(3);
            		char col2 = dataprovider.charAt(4);
            		String colval =""+col1 + col2;
            		int coloval = Integer.parseInt(colval);
            		System.out.println(coloval);
            		String data = ExcelUtils.getCellData(testdata,rowValue,coloval).toString().trim();
            		String xpath = locatorValue.replace("testdata", data);
                	System.out.println(xpath);
               	 	driver.findElement(By.xpath(xpath)).click();
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
            		
            		
            	}
            	
            	
            	}
     	
            	else
            	{
            	
            	
                driver.findElement(By.xpath(locatorValue)).click();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Click Action Failure </span>");
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    
    
    
    
    public static String gettooltiptext(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", new Object[] { locatorValue });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", new Object[] { locatorValue });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Click Action Failure </span>");
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String javascriptClick(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", new Object[] { locatorValue });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final JavascriptExecutor js = (JavascriptExecutor)driver;
                WebElement element = driver.findElement(By.xpath(locatorValue));
                js.executeScript("arguments[0].click();", element);
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Click Action Failure </span>");
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String closeBrowserTab(final WebDriver driver, final String description) throws Exception {
        final ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window((String)tabs.get(1));
        driver.close();
        driver.switchTo().window((String)tabs.get(0));
        ChromeWebActions.logger.log(LogStatus.PASS, description);
        return "pass";
    }
    
    public static String InputDynamicData(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String testdata) throws Exception {
        try {
            final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { String.valueOf(timeStamp) + testdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { String.valueOf(timeStamp) + testdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String slideraction(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String testdata) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final WebElement slider = driver.findElement(By.id(locatorValue));
                final Actions move = new Actions(driver);
                move.click(slider).sendKeys(new CharSequence[] { (CharSequence)Keys.ARROW_RIGHT }).perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final WebElement slider = driver.findElement(By.id(locatorValue));
                final Actions move = new Actions(driver);
                move.click(slider).sendKeys(new CharSequence[] { (CharSequence)Keys.ARROW_RIGHT }).perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String entervalue(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String numerictestdata) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { numerictestdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.findElement(By.xpath(locatorValue)).sendKeys(new CharSequence[] { numerictestdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String GetCssColor(final WebDriver driver, final String locatorType, final String locatorValue, final String expectedresult, final String description) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).getCssValue("border-top");
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final String css = driver.findElement(By.id(locatorValue)).getCssValue("border-top");
              //  System.out.println(css);
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.getMessage();
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String MouseHover(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.id(locatorValue));
                a1.moveToElement(element).build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Hover Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Hover Action Successful</span>");
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
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).click().build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).click().build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
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
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).contextClick().build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Actions a1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                a1.moveToElement(element).contextClick().build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String verifyPartialText(final WebDriver driver, final String locatorType, final String locatorValue, final String description,final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locatorValue));
            final String actualresult1 = element.getText().replaceAll("\\s+", "");
           
           // System.out.println(actualresult1);
            
            final String testdata2 = testdata.replaceAll("\\s+", "");
           // System.out.println(testdata2);
            if (actualresult1.contains(testdata2)) {
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + testdata + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" +element.getText()+ "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + testdata + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + element.getText() + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot2));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String ischeckBoxEnabled(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final Boolean status = driver.findElement(By.id(locatorValue)).isSelected();
                if (Boolean.TRUE) {
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Check box is enabled</span>");
                }
                else {
                    ChromeWebActions.logger.log(LogStatus.FAIL, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Check box is disabled</span>");
                }
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final Boolean status = driver.findElement(By.xpath(locatorValue)).isSelected();
             //   System.out.println(status);
                if (status.equals("True")) {
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Check box is enabled</span>");
                }
                else {
                    ChromeWebActions.logger.log(LogStatus.FAIL, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Check box is disabled</span>");
                }
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Boolean status = driver.findElement(By.name(locatorValue)).isSelected();
                if (Boolean.TRUE) {
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
                }
                else {
                    ChromeWebActions.logger.log(LogStatus.FAIL, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
                }
            }
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String islink(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final WebElement link = driver.findElement(By.id(locatorValue));
                if (link.getAttribute("href").isEmpty()) {
                    ChromeWebActions.logger.log(LogStatus.FAIL, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:The element is not hyperlinked</span>");
                }
                else {
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:The element is hyperlinked</span>");
                }
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final WebElement link = driver.findElement(By.xpath(locatorValue));
                if (link.getAttribute("href").isEmpty()) {
                    ChromeWebActions.logger.log(LogStatus.FAIL, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:The element is not hyperlinked</span>");
                }
                else {
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:The element is hyperlinked</span>");
                }
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Boolean status = driver.findElement(By.name(locatorValue)).isSelected();
                if (Boolean.TRUE) {
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
                }
                else {
                    ChromeWebActions.logger.log(LogStatus.FAIL, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
                }
            }
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String verifyTextinPDF(final WebDriver driver, final String testdata, final String description, final String expectedresult) throws Exception {
        final URL pdfUrl = new URL(driver.getCurrentUrl());
        final InputStream in = pdfUrl.openStream();
        final BufferedInputStream bf = new BufferedInputStream(in);
        final PDDocument doc = PDDocument.load((InputStream)bf);
        final String content = new PDFTextStripper().getText(doc);
        if (content.contains(testdata)) {
            ChromeWebActions.logger.log(LogStatus.PASS, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]: Test is Present in the PDF</span>");
        }
        else {
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Text is not present in the PDF</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
        }
        return "pass";
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
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Scroll Down Success</span>");
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
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Scroll Up Success</span>");
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String verifyURL(final WebDriver driver, final String testdata, final String description, final String expectedresult) throws Exception {
        final String url = driver.getCurrentUrl();
        if (testdata.equalsIgnoreCase(url)) {
            ChromeWebActions.logger.log(LogStatus.PASS, description);
            ChromeWebActions.logger.log(LogStatus.PASS, "URL Is Correct");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + url + "</span>");
        }
        else {
            ChromeConfig.logger.log(LogStatus.FAIL, "Page Title incorrect");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + url + "</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
        }
        return "pass";
    }
    
    public static String ClickAllLinks(final WebDriver driver, final String description, final String testdata) throws MalformedURLException, IOException {
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        final List<WebElement> link = (List<WebElement>)driver.findElements(By.tagName("a"));
        ChromeWebActions.logger.log(LogStatus.INFO, "Total no. of links are " + link.size());
        final Iterator<WebElement> it = link.iterator();
        while (it.hasNext()) {
            url = it.next().getAttribute("href");
          //  System.out.println(url);
            if (url == null || url.isEmpty()) {
             //   System.out.println("URL is either not configured for anchor tag or it is empty");
                ChromeWebActions.logger.log(LogStatus.FAIL, String.valueOf(url) + "- URL is either not configured for anchor tag or it is empty");
            }
            else if (url == "javascript:void(0)" || url.startsWith("javascript")) {
            //    System.out.println("Javascript URL" + url);
                int k = 1;
                ChromeWebActions.logger.log(LogStatus.FAIL, String.valueOf(url) + "- Not a URL");
                ++k;
            }
            else if (url.startsWith(testdata)) {
                ChromeWebActions.logger.log(LogStatus.PASS, String.valueOf(url) + "- URL with same domain");
                huc = (HttpURLConnection)new URL(url).openConnection();
           //     System.out.println("Normal link - " + url);
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
            //    System.out.println(respCode);
                if (respCode != 200) {
                    ChromeWebActions.logger.log(LogStatus.FAIL, String.valueOf(url) + "<b>- Broken Link or Warning<b>");
                    ChromeWebActions.logger.log(LogStatus.FAIL, "<b>Response Code:</b>" + respCode);
                }
                else {
                    ChromeWebActions.logger.log(LogStatus.PASS, String.valueOf(url) + "- Valid Link");
                    ChromeWebActions.logger.log(LogStatus.PASS, "<b>Response Code:</b>" + respCode);
                }
            }
            else {
                ChromeWebActions.logger.log(LogStatus.PASS, String.valueOf(url) + "- URL with different domain");
                huc = (HttpURLConnection)new URL(url).openConnection();
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
           //     System.out.println(respCode);
                if (respCode != 200) {
                    ChromeWebActions.logger.log(LogStatus.FAIL, String.valueOf(url) + "<b>- Broken Link or Warning<b>");
                    ChromeWebActions.logger.log(LogStatus.FAIL, "<b>Response Code:</b>" + respCode);
                }
                else {
                    ChromeWebActions.logger.log(LogStatus.PASS, String.valueOf(url) + "- Valid Link");
                    ChromeWebActions.logger.log(LogStatus.PASS, "<b>Response Code:</b>" + respCode);
                }
            }
        }
        return url;
    }
    
    public static String enterText(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String testdata) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { testdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.findElement(By.id(locatorValue)).sendKeys(new CharSequence[] { testdata });
                ChromeWebActions.logger.log(LogStatus.PASS, description);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String verifybgcolor(final WebDriver driver, final String locator, final String expectedresult, final String description, final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locator));
            final String color = element.getCssValue("background-color");
   //         System.out.println(color);
            final String hex = Color.fromString(color).asHex();
   //         System.out.println(hex);
            if (testdata.equalsIgnoreCase(hex)) {
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + hex + "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + hex + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot2));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
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
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + yvalue + "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + yvalue + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot2));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
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
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + xvalue + "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + xvalue + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot2));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String verifyText(final WebDriver driver, final String locator, final String expectedresult, final String description, String actualresult, final String testdata) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locator));
            actualresult = element.getText();
            final String actualresult2 = actualresult.replaceAll("\\s+", "");
   //         System.out.println(actualresult2);
            final String testdata2 = testdata.replaceAll("\\s+", "");
     //       System.out.println(testdata2);
            if (testdata2.equalsIgnoreCase(actualresult2)) {
                ChromeWebActions.logger.log(LogStatus.PASS, description);
     //           System.out.println(actualresult);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + testdata + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + actualresult + "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + testdata + "</span>");
    //            System.out.println(actualresult);
                final String actualresult3 = element.getText();
      //          System.out.println(actualresult3);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot2));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
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
                        ChromeWebActions.logger.log(LogStatus.PASS, description);
                        ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                        ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + data + "</span>");
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
            ChromeWebActions.logger.log(LogStatus.FAIL, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]: Unable to Save</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            io.printStackTrace();
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)io);
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
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                    ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + data + "</span>");
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
            ChromeWebActions.logger.log(LogStatus.FAIL, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]: Unable to Save</span>");
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            io.printStackTrace();
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)io);
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
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + src + "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + src + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot2));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "fail";
    }
    
    public static String verifyvalue(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String testdata, final String expectedresult) throws Exception {
        try {
            final WebElement element = driver.findElement(By.xpath(locatorValue));
            final String result = element.getText();
            final String convertdata = testdata.replaceAll("\\.0*$", "");
            if (convertdata.equalsIgnoreCase(element.getText())) {
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                System.out.println("++Actualllll Resullttt" + result);
                System.out.println("++Tessstttt Data Resullttt" + convertdata);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + result + "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                System.out.println("++Actualllll Resullttt" + result);
                System.out.println("++Tessstttt Data Resullttt" + testdata);
                final String actualresult2 = element.getText();
                System.out.println("++++harish+++" + actualresult2);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + result + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot2));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
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
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + elementval + "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + elementval + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot2 = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot2));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
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
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + actualresult + "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                final Select comboBox2 = new Select(element);
                actualresult = comboBox.getFirstSelectedOption().getText();
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
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
            ChromeWebActions.logger.log(LogStatus.PASS, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]: Text Clear successfully</span>");
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
            ChromeWebActions.logger.log(LogStatus.PASS, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]: Text Clear successfully</span>");
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
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:" + text + "</span>");
            }
            else {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                final String actualresult2 = element.getAttribute("value");
                System.out.println(actualresult2);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult2 + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
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
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                actualresult = element.getText();
                System.out.println(actualresult);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]: Text is not getting displayed</span>");
            }
            else if (testdata.equalsIgnoreCase(element.getText())) {
                ChromeWebActions.logger.log(LogStatus.FAIL, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                System.out.println(actualresult);
                final String actualresult2 = element.getText();
                System.out.println(actualresult2);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:" + actualresult2 + "</span>");
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            }
        }
        return "pass";
    }
    
    public static String verifyElement(final WebDriver driver, final String locatortype, final String locatorvalue, final String description) throws Exception {
        try {
            if (locatortype.equalsIgnoreCase("id")) {
                final List<WebElement> dynamicElement = (List<WebElement>)driver.findElements(By.id(locatorvalue));
                if (dynamicElement.size() != 0) {
                    ChromeWebActions.logger.log(LogStatus.PASS, "Element Present In The Page");
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
                    ChromeWebActions.logger.log(LogStatus.PASS, description);
                }
                else {
                    ChromeWebActions.logger.log(LogStatus.FAIL, "Elment Not Present In The Page");
                }
                System.out.println("***************" + dynamicElement);
                final String screenshot = Screenshot.getScreenshot(driver);
                ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
                driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ChromeConfig.logger.log(LogStatus.FAIL, description);
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static boolean isElementPresent(final WebDriver driver, final String locatortype, final String locatorvalue) {
        try {
            if (locatortype.equalsIgnoreCase("xpath")) {
                driver.findElement(By.xpath(locatorvalue));
                ChromeWebActions.logger.log(LogStatus.PASS, "WebElement present in the page");
                return true;
            }
            if (locatortype.equalsIgnoreCase("id")) {
                driver.findElement(By.xpath(locatorvalue));
                ChromeWebActions.logger.log(LogStatus.PASS, "WebElement present in the page");
                return true;
            }
        }
        catch (NoSuchElementException e) {
            ChromeWebActions.logger.log(LogStatus.FAIL, "WebElement not present in the page");
            return false;
        }
        return false;
    }
    
    public static String switchtoactive(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final WebElement frame = driver.findElement(By.id(locatorValue));
                driver.switchTo().frame(frame);
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Switch To Video Frame Successfull</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                final WebElement frame = driver.findElement(By.xpath(locatorValue));
                driver.switchTo().frame(frame);
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Switch To Video Frame Successfull</span>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Switch To Video Frame Failure </span>");
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    public static String switchtodefault(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws Exception {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                driver.switchTo().defaultContent();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Switch To Video Frame Successfull</span>");
            }
            else if (locatorType.equalsIgnoreCase("xpath")) {
                driver.switchTo().defaultContent();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Switch To Video Frame Successfull</span>");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#FF0000;'>[Actual Result]:Switch To Video Frame Failure </span>");
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return "pass";
    }
    
    
    
    /**
     * @param driver
     * @param locatorvalue
     * @param locatortype
     * @param description
     * @author Sreelakshmi H
     * @return
     * @throws Exception
     */
    public static boolean isElementSelected(final WebDriver driver, final String locatortype, final String locatorvalue,final String description) throws Exception {
        try {
        	WebElement checkBoxElement = driver.findElement(By.xpath(locatorvalue));
        	boolean isSelected = checkBoxElement.isSelected();
        	if(isSelected == true) {
        	ChromeWebActions.logger.log(LogStatus.PASS, description);
        	ChromeWebActions.logger.log(LogStatus.PASS,"Element is selected");
        	}
        	//performing click operation if element is not checked
        	if(isSelected == false) {
        	checkBoxElement.click();
        	ChromeWebActions.logger.log(LogStatus.FAIL, description);
        	ChromeWebActions.logger.log(LogStatus.FAIL, "Element is not selected");
        	}
        }
        
        catch (Exception e) {
            e.printStackTrace();
            final String screenshot = Screenshot.getScreenshot(driver);
            ChromeWebActions.logger.log(LogStatus.FAIL, description);
            ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" +description+ "</span>");
            ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        }
        return true;
        
    }  
    
    /**
     * @param driver
     * @param locatorType
     * @param locatorValue
     * @param description
     * @param expectedresult
     * @author Sreelakshmi H
     * @return
     * @throws Exception
     */
    public static String isEnabled(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws Exception {
        try {
        	   if(locatorType.equalsIgnoreCase("id"))
        	   {
                WebElement button=driver.findElement(By.id(locatorValue));
                button.getAttribute("disabled").toString();
                String buttonvalue = button.getAttribute("disabled");
				if(buttonvalue.equalsIgnoreCase("True")) {
                ChromeWebActions.logger.log(LogStatus.PASS, "Element is enabled");
				}
                else {
                ChromeWebActions.logger.log(LogStatus.PASS, "Element is not enabled");
                }
        	   }
				else if(locatorType.equalsIgnoreCase("xpath"))
				{
                WebElement button1=driver.findElement(By.xpath(locatorValue));
                button1.getAttribute("disabled").toString();
                String buttonvalue1 = button1.getAttribute("disabled");
                if(buttonvalue1.equalsIgnoreCase("True")) {
                ChromeWebActions.logger.log(LogStatus.PASS, "Element is enabled");
               }
			    else {
	            ChromeWebActions.logger.log(LogStatus.PASS, "Element is not enabled");
	            }
            }
        }
        catch (Exception e) {
        	final String screenshot = Screenshot.getScreenshot(driver);
        	ChromeWebActions.logger.log(LogStatus.FAIL, "Element is not displayed on the page");
        	ChromeWebActions.logger.log(LogStatus.FAIL, "Snapshot below: " + ChromeWebActions.logger.addScreenCapture(screenshot));
            ChromeWebActions.logger.log(LogStatus.FAIL, (Throwable)e);
        
        }
        return "pass";
    }
    
    /**
     * @param driver
     * @param locatorType
     * @param locatorValue
     * @param description
     * @param expectedresult
     * @return
     * @author Sreelakshmi H
     * @throws ElementNotVisibleException
     */
    public static String doubleclick(final WebDriver driver, final String locatorType, final String locatorValue, final String description, final String expectedresult) throws ElementNotVisibleException {
        try {
            if (locatorType.equalsIgnoreCase("id")) {
                final Actions b1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                b1.doubleClick(element).build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("xpath")) {
                final Actions b1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                b1.doubleClick(element).build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            if (locatorType.equalsIgnoreCase("name")) {
                final Actions b1 = new Actions(driver);
                final WebElement element = driver.findElement(By.xpath(locatorValue));
                b1.doubleClick(element).build().perform();
                ChromeWebActions.logger.log(LogStatus.PASS, description);
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Expected Result]:" + expectedresult + "</span>");
                ChromeWebActions.logger.log(LogStatus.INFO, "<span style='font-weight:bold;font-size:15px;color:#008000;'>[Actual Result]:Click Action Successful</span>");
            }
            return "pass";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    
    
    
}
