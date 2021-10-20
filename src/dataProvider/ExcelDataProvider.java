// 
// Decompiled by Procyon v0.5.36
// 

package dataProvider;

import org.testng.annotations.AfterSuite;
import util.SendEmail.GenerateReport;
import util.SendEmail.GenerateReport1;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.io.File;
import config.EdgeConfig;
import config.IEConfig;
import config.ChromeConfigP;
import config.ChromeConfig;
import config.FirefoxConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelDataProvider
{
    public final String SCENARIO_SHEET_PATH = "./TestCases/Testcases.xlsx";
    public static Workbook book;
    public static Sheet sheet;
    public static Cell cell;
    public static XSSFRow Row;
    public static ExtentReports report;
    public static ExtentReports reportf;
    public static ExtentReports reportIE;
    public static ExtentReports reportEdge;
    public static ExtentTest logger;
    public static ExtentTest loggerp;
    public static ExtentTest loggerf;
    public static ExtentTest loggerIE;
    public static ExtentTest loggerEC;
    public FirefoxConfig firefoxConfig;
    public ChromeConfig chromeConfig;
    public ChromeConfigP chromeConfigp;
    public IEConfig ieconfig;
    public EdgeConfig ecconfig;
    
    @BeforeSuite
    public static void startTest() throws IOException {
        FileUtils.cleanDirectory(new File("./Screenshots"));
        FileUtils.cleanDirectory(new File("./FireFoxTestReport"));
        FileUtils.cleanDirectory(new File("./ChromeTestReport"));
        FileUtils.cleanDirectory(new File("./IETestReport"));
        FileUtils.cleanDirectory(new File("./EdgeTestReport"));
        FileUtils.cleanDirectory(new File("./EmailOutput"));
      //  FileUtils.cleanDirectory(new File("./ChromeTestImages"));
        FileUtils.cleanDirectory(new File("./FirefoxTestImages"));
        FileUtils.cleanDirectory(new File("./IETestImages"));
        FileUtils.cleanDirectory(new File("./EdgeTestImages"));
        FileUtils.cleanDirectory(new File("\\Archieve_iWAgentReports\\ChromeTestReports\\CustChrome"));
        FileUtils.cleanDirectory(new File("\\Archieve_iWAgentReports\\FFTestReports\\CustFF"));
        FileUtils.cleanDirectory(new File("\\Archieve_iWAgentReports\\EdgeTestReports\\CustEdge"));
        System.out.println("Starting the Test Suite");
    }
    
    @Parameters({ "browser" })
    @Test
    public void getSheet(final String browser) throws Exception {
        FileInputStream file = null;
        try {
            file = new FileInputStream("./TestCases/Testcases.xlsx");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ExcelDataProvider.book = WorkbookFactory.create((InputStream)file);
        }
        catch (InvalidFormatException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        ExcelDataProvider.sheet = ExcelDataProvider.book.getSheet("Testcases");
        final int k = 0;
        final String chrometestbrowser = ExcelDataProvider.sheet.getRow(10).getCell(1).toString().trim();
        final String fftestbrowser = ExcelDataProvider.sheet.getRow(11).getCell(1).toString().trim();
        final String iebrowser = ExcelDataProvider.sheet.getRow(12).getCell(1).toString().trim();
        final String edgetestbrowser = ExcelDataProvider.sheet.getRow(13).getCell(1).toString().trim();
        final String chromeparalleltest = ExcelDataProvider.sheet.getRow(10).getCell(2).toString().trim();
        final String ffparalleltest = ExcelDataProvider.sheet.getRow(11).getCell(2).toString().trim();
        final String Chromereport = "Chrome";
        final String Firefoxreport = "Firefox";
        final String IEReport = "InternetExplorer";
        final String EdgeReport = "EdgeChromium";
        final String timeStamp1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        if (chrometestbrowser.equalsIgnoreCase("Y") && chromeparalleltest.equalsIgnoreCase("Y")) {
            ExcelDataProvider.report = new ExtentReports("./ChromeTestReport/" + Chromereport + timeStamp1 + ".html", Boolean.valueOf(true));
        }
        else if (chrometestbrowser.equalsIgnoreCase("Y")) {
            ExcelDataProvider.report = new ExtentReports("./ChromeTestReport/" + Chromereport + timeStamp1 + ".html", Boolean.valueOf(true));
        }
        if (fftestbrowser.equalsIgnoreCase("Y")) {
            ExcelDataProvider.reportf = new ExtentReports("./FireFoxTestReport/" + Firefoxreport + timeStamp1 + ".html", Boolean.valueOf(true));
        }
        if (iebrowser.equalsIgnoreCase("Y")) {
            ExcelDataProvider.reportIE = new ExtentReports("./IETestReport/" + IEReport + timeStamp1 + ".html", Boolean.valueOf(true));
        }
        if (edgetestbrowser.equalsIgnoreCase("Y")) {
            ExcelDataProvider.reportEdge = new ExtentReports("./EdgeTestReport/" + EdgeReport + timeStamp1 + ".html", Boolean.valueOf(true));
        }
        for (int i = 15; i < ExcelDataProvider.sheet.getLastRowNum(); ++i) {
            try {
                final String tobeexecutured = ExcelDataProvider.sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                if (chrometestbrowser.equalsIgnoreCase("Y") && browser.equalsIgnoreCase("Chrome")) {
                    if (tobeexecutured.equalsIgnoreCase("Y")) {
                        final String testcase = ExcelDataProvider.sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                        this.chromeConfig = new ChromeConfig();
                        ExcelDataProvider.logger = ExcelDataProvider.report.startTest(testcase);
                        ChromeConfig.startTest(testcase);
                    }
                }
                else if (chromeparalleltest.equalsIgnoreCase("Y") && browser.equalsIgnoreCase("ChromeParallel")) {
                    final String tobeexecuturedparallel = ExcelDataProvider.sheet.getRow(i + 1).getCell(3).toString().trim();
                    if (tobeexecuturedparallel.equalsIgnoreCase("P")) {
                        final String testcase2 = ExcelDataProvider.sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                        this.chromeConfigp = new ChromeConfigP();
                        ExcelDataProvider.loggerp = ExcelDataProvider.report.startTest(testcase2);
                        ChromeConfigP.startTest(testcase2);
                    }
                }
                else if (fftestbrowser.equalsIgnoreCase("Y") && browser.equalsIgnoreCase("Firefox")) {
                    if (tobeexecutured.equalsIgnoreCase("Y")) {
                        final String testcase = ExcelDataProvider.sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                        ExcelDataProvider.loggerf = ExcelDataProvider.reportf.startTest(testcase);
                        this.firefoxConfig = new FirefoxConfig();
                        FirefoxConfig.startTest(testcase);
                    }
                }
                else if (ffparalleltest.equalsIgnoreCase("Y") && browser.equalsIgnoreCase("ChromeParallel")) {
                    final String tobeexecuturedparallel = ExcelDataProvider.sheet.getRow(i + 1).getCell(3).toString().trim();
                    if (tobeexecuturedparallel.equalsIgnoreCase("P")) {
                        final String testcase2 = ExcelDataProvider.sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                        this.chromeConfigp = new ChromeConfigP();
                        ExcelDataProvider.loggerp = ExcelDataProvider.report.startTest(testcase2);
                        ChromeConfigP.startTest(testcase2);
                    }
                }
                else if (iebrowser.equalsIgnoreCase("Y") && browser.equalsIgnoreCase("IE")) {
                    if (tobeexecutured.equalsIgnoreCase("Y")) {
                        final String testcase = ExcelDataProvider.sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                        ExcelDataProvider.loggerIE = ExcelDataProvider.reportIE.startTest(testcase);
                        this.ieconfig = new IEConfig();
                        IEConfig.startTest(testcase);
                    }
                }
                else if (edgetestbrowser.equalsIgnoreCase("Y") && browser.equalsIgnoreCase("Edge") && tobeexecutured.equalsIgnoreCase("Y")) {
                    final String testcase = ExcelDataProvider.sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                    ExcelDataProvider.loggerEC = ExcelDataProvider.reportEdge.startTest(testcase);
                    this.ecconfig = new EdgeConfig();
                    EdgeConfig.startTest(testcase);
                }
            }
            catch (Exception ex) {}
            if (chrometestbrowser.equalsIgnoreCase("Y")) {
                ExcelDataProvider.report.endTest(ExcelDataProvider.logger);
                ExcelDataProvider.report.endTest(ExcelDataProvider.loggerp);
                ExcelDataProvider.report.flush();
            }
            if (fftestbrowser.equalsIgnoreCase("Y")) {
                ExcelDataProvider.reportf.endTest(ExcelDataProvider.loggerf);
                ExcelDataProvider.reportf.flush();
            }
            if (iebrowser.equalsIgnoreCase("Y")) {
                ExcelDataProvider.reportIE.endTest(ExcelDataProvider.loggerIE);
                ExcelDataProvider.reportIE.flush();
            }
            if (edgetestbrowser.equalsIgnoreCase("Y")) {
                ExcelDataProvider.reportEdge.endTest(ExcelDataProvider.loggerEC);
                ExcelDataProvider.reportEdge.flush();
            }
        }
        final File srcDir = new File("./ChromeTestReport");
        final File emaildesDirChrome = new File("./EmailOutput");
        final File scrPrint = new File("./Screenshots");
        final File chromeimgTest = new File("./ChromeTestImages");
        final String timeStamp2 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        final File destDir = new File("\\Archieve_iWAgentReports\\ChromeTestReports\\" + timeStamp2);
        final File destCusChrome = new File("\\Archieve_iWAgentReports\\ChromeTestReports\\CustChrome");
        final File destImg = new File("\\Archieve_iWAgentReports\\Images\\Chrome" + timeStamp2);
        FileUtils.copyDirectory(srcDir, destDir);
        FileUtils.copyDirectory(srcDir, destCusChrome);
        FileUtils.copyDirectory(srcDir, emaildesDirChrome);
        FileUtils.copyDirectory(scrPrint, destDir);
        FileUtils.copyDirectory(chromeimgTest, destImg);
        final File srcDirFirefox = new File("./FireFoxTestReport");
        final File emaildesDirFirefox = new File("./EmailOutput");
        final File scrPrintFirefox = new File("./Screenshots");
        final File ffimgTest = new File("./FirefoxTestImages");
        final File destDirFirefox = new File("\\Archieve_iWAgentReports\\FirefoxTestReports\\" + timeStamp2);
        final File destCusFF = new File("\\Archieve_iWAgentReports\\FirefoxTestReports\\CustFF");
        final File ffdestImg = new File("\\Archieve_iWAgentReports\\Images\\Firefox" + timeStamp2);
        FileUtils.copyDirectory(srcDirFirefox, destDirFirefox);
        FileUtils.copyDirectory(srcDir, destCusFF);
        FileUtils.copyDirectory(srcDirFirefox, emaildesDirFirefox);
        FileUtils.copyDirectory(scrPrintFirefox, destDirFirefox);
        FileUtils.copyDirectory(ffimgTest, ffdestImg);
        final File srcDirIE = new File("./IETestReport");
        final File emaildesDirIE = new File("./EmailOutput");
        final File scrPrintIE = new File("./Screenshots");
        final File ieimgTest = new File("./IETestImages");
        final File destDirIE = new File("\\Archieve_iWAgentReports\\IETestReports\\" + timeStamp2);
        final File iedestImg = new File("\\Archieve_iWAgentReports\\Images\\IE" + timeStamp2);
        final File destCusIE = new File("\\Archieve_iWAgentReports\\IETestReports\\CustEdge");
        FileUtils.copyDirectory(srcDirFirefox, destDirFirefox);
        FileUtils.copyDirectory(srcDir, destCusFF);
        FileUtils.copyDirectory(srcDirIE, destDirIE);
        FileUtils.copyDirectory(srcDirIE, emaildesDirIE);
        FileUtils.copyDirectory(scrPrintIE, destDirIE);
        FileUtils.copyDirectory(ieimgTest, iedestImg);
        final File srcDirEdge = new File("./EdgeTestReport");
        final File emaildesDirEdge = new File("./EmailOutput");
        final File scrPrintEdge = new File("./Screenshots");
        final File edgeimgTest = new File("./EdgeTestImages");
        final File destDirEdge = new File("\\Archieve_iWAgentReports\\EdgeTestReports\\" + timeStamp2);
        final File destCusEdge = new File("\\Archieve_iWAgentReports\\EdgeTestReports\\CustEdge");
        final File edgedestImg = new File("\\Archieve_iWAgentReports\\Images\\Edge" + timeStamp2);
        FileUtils.copyDirectory(srcDirEdge, destDirEdge);
        FileUtils.copyDirectory(srcDirEdge, destCusEdge);
        FileUtils.copyDirectory(destDirEdge, emaildesDirEdge);
        FileUtils.copyDirectory(scrPrintEdge, destDirEdge);
        FileUtils.copyDirectory(edgeimgTest, edgedestImg);
    }
    
    @AfterSuite
    public void generateReport() {
        try {
            GenerateReport.generatereport();
            GenerateReport1.generateCustomReport();
        }
        catch (Exception e) {
            System.out.println("After suite exception" + e);
        }
    }
    
    public static String getCellData(final int RowNum, final int ColNum) throws Exception {
        try {
            FileInputStream file = null;
            try {
                file = new FileInputStream("./TestCases/Testcases.xlsx");
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                ExcelDataProvider.book = WorkbookFactory.create((InputStream)file);
            }
            catch (InvalidFormatException e2) {
                e2.printStackTrace();
            }
            catch (IOException e3) {
                e3.printStackTrace();
            }
            ExcelDataProvider.sheet = ExcelDataProvider.book.getSheet("Testcases");
            ExcelDataProvider.cell = ExcelDataProvider.sheet.getRow(RowNum).getCell(ColNum);
            final String CellData = ExcelDataProvider.cell.getStringCellValue();
            return CellData;
        }
        catch (Exception e4) {
            return "";
        }
    }
}
