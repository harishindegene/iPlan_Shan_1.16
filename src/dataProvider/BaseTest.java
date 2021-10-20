// 
// Decompiled by Procyon v0.5.36
// 

package dataProvider;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterMethod;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.ExtentReporter;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest
{
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    
    @BeforeSuite
    public void setUp() {
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        final String Chromereport = "Chrome";
        BaseTest.htmlReporter = new ExtentHtmlReporter("./ChromeTestReport/" + Chromereport + timeStamp + ".html");
        (BaseTest.extent = new ExtentReports()).attachReporter(new ExtentReporter[] { (ExtentReporter)BaseTest.htmlReporter });
        BaseTest.htmlReporter.config().setChartVisibilityOnOpen(true);
        BaseTest.htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
        BaseTest.htmlReporter.config().setReportName("My Own Report");
        BaseTest.htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        BaseTest.htmlReporter.config().setTheme(Theme.DARK);
    }
    
    @AfterMethod
    public void getResult(final ITestResult result) {
        if (result.getStatus() == 2) {
            BaseTest.test.log(Status.FAIL, MarkupHelper.createLabel(String.valueOf(result.getName()) + " Test case FAILED due to below issues:", ExtentColor.RED));
            BaseTest.test.fail(result.getThrowable());
        }
        else if (result.getStatus() == 1) {
            BaseTest.test.log(Status.PASS, MarkupHelper.createLabel(String.valueOf(result.getName()) + " Test Case PASSED", ExtentColor.GREEN));
        }
        else {
            BaseTest.test.log(Status.SKIP, MarkupHelper.createLabel(String.valueOf(result.getName()) + " Test Case SKIPPED", ExtentColor.ORANGE));
            BaseTest.test.skip(result.getThrowable());
        }
    }
    
    @AfterSuite
    public void tearDown() {
        BaseTest.extent.flush();
    }
}
