// 
// Decompiled by Procyon v0.5.36
// 

package util.SendEmail;

import org.jsoup.nodes.Document;
import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.jsoup.Jsoup;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.io.File;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.ArrayList;

public class GenerateReport1
{
    public static int ChromePassStatusCount;
    public static int ChromeFailedStatusCount;
    public static int Chrometestcasecount;
    public static String Testcasename;
    public static String DynamicHTML;
    public static String ChromeStatus;
    public static String Testcasedes;
    public static int ChromeexeCount;
    public static int ChromenotexeCount;
    public static int FFexeCount;
    public static int FFnotexeCount;
    public static int EdgeexeCount;
    public static int EdgenotexeCount;
    public static String chrometestcasePassed;
    public static String chrometestcaseFailed;
    public static String fftestcasePassed;
    public static String fftestcaseFailed;
    public static String edgetestcasePassed;
    public static String edgetestcaseFailed;
    public static int totalTestcases;
    
    static {
        GenerateReport1.DynamicHTML = "";
        GenerateReport1.ChromeStatus = "Not Executed";
        GenerateReport1.ChromeexeCount = 0;
        GenerateReport1.ChromenotexeCount = 0;
        GenerateReport1.FFexeCount = 0;
        GenerateReport1.FFnotexeCount = 0;
        GenerateReport1.EdgeexeCount = 0;
        GenerateReport1.EdgenotexeCount = 0;
    }
    
    public static void generateCustomReport() throws Exception {
        ExcelUtils.setExcelFile("./TestCases/Testcases.xlsx");
        final List<String> filesContainingSubstring = new ArrayList<String>();
        System.setProperty("webdriver.chrome.driver", "./BrowserUtilities/chromedriver.exe");
        final ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[] { "--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors" });
        final WebDriver driver = (WebDriver)new ChromeDriver(options);
        if (ExcelUtils.getCellData("Testcases", 10, 1).equalsIgnoreCase("Y")) {
            String filename = null;
            final File folder = new File("D:\\Archieve_iWAgentReports\\ChromeTestReports\\CustChrome");
            final File[] listOfFiles = folder.listFiles();
            File[] array;
            for (int length = (array = listOfFiles).length, n = 0; n < length; ++n) {
                final File file = array[n];
                if (file.isFile()) {
                    filename = file.getName();
                    if (filename.contains(".html")) {
                        driver.get("D:\\Archieve_iWAgentReports\\ChromeTestReports\\CustChrome\\" + filename);
                    }
                    System.out.println(file.getName());
                }
            }
            for (int i = 16; !ExcelUtils.getCellData("Testcases", i, 1).equalsIgnoreCase("End"); ++i) {
                if (ExcelUtils.getCellData("Testcases", i, 2).equalsIgnoreCase("Y")) {
                    ++GenerateReport1.ChromeexeCount;
                }
                else {
                    ++GenerateReport1.ChromenotexeCount;
                }
            }
        }
        if (ExcelUtils.getCellData("Testcases", 11, 1).equalsIgnoreCase("Y")) {
            String filename = null;
            final File folder = new File("\\Archieve_iWAgentReports\\FirefoxTestReports\\CustFF");
            final File[] listOfFiles = folder.listFiles();
            File[] array2;
            for (int length2 = (array2 = listOfFiles).length, n2 = 0; n2 < length2; ++n2) {
                final File file = array2[n2];
                if (file.isFile()) {
                    filename = file.getName();
                    if (filename.contains(".html")) {
                        driver.get("D:\\Archieve_iWAgentReports\\ChromeTestReports\\CustFF\\" + filename);
                    }
                    System.out.println(file.getName());
                }
            }
            for (int i = 16; !ExcelUtils.getCellData("Testcases", i, 1).equalsIgnoreCase("End"); ++i) {
                if (ExcelUtils.getCellData("Testcases", i, 2).equalsIgnoreCase("Y")) {
                    ++GenerateReport1.FFexeCount;
                }
                else {
                    ++GenerateReport1.FFnotexeCount;
                }
            }
        }
        final List<WebElement> testcase = (List<WebElement>)driver.findElements(By.xpath("//span[@class='test-name']"));
        if (testcase.size() == 1) {
            final String testcasename = driver.findElement(By.xpath("//span[@class='test-name']")).getText();
            final String startime = driver.findElement(By.xpath("(//div[@class='details-container']/div/div/span)[1]")).getText();
            final String endtime = driver.findElement(By.xpath("(//div[@class='details-container']/div/div/span)[2]")).getText();
            final String status = driver.findElement(By.xpath("(//span[@class='test-name'])[1]/following-sibling::span[1]")).getText();
            for (int j = 16; !ExcelUtils.getCellData("Testcases", j, 2).equalsIgnoreCase("End"); ++j) {
                if (ExcelUtils.getCellData("Testcases", j, 2).equalsIgnoreCase("Y")) {
                    GenerateReport1.Testcasedes = ExcelUtils.getCellData("Testcases", j, 4);
                }
            }
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<tr>";
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style='width: 10%; text-align:center';>1</td>";
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style= 'text-align: center';>" + testcasename + "</td>";
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style='word-break:break-word;'>" + GenerateReport1.Testcasedes + "</td>";
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style='word-break:break-word; text-align: center';>" + startime + "</td>";
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style='word-break:break-word; text-align: center';>" + endtime + "</td>";
            if (status.equalsIgnoreCase("PASS")) {
                GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style= 'text-align: center; font-size:initial';><font style=\"font-weight:bold\" color=\"green\" ;>PASS</font>";
            }
            else {
                GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style= 'text-align: center; font-size:initial';><font style=\"font-weight:bold\" color=\"red\" ;=\"\">FAIL</font></td>";
            }
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "</tr>";
        }
        else {
            for (int k = 0; k < testcase.size(); ++k) {
                System.out.println(testcase.get(k).getText());
                final String testcasename2 = testcase.get(k).getText();
                final int tc = k + 1;
                final WebElement e1 = driver.findElement(By.xpath("(//ul[@class='test-collection']/li)[" + tc + "]"));
                e1.click();
                final String startime2 = driver.findElement(By.xpath("(//div[@class='details-container']/div/div/span)[1]")).getText();
                final String endtime2 = driver.findElement(By.xpath("(//div[@class='details-container']/div/div/span)[2]")).getText();
                final int l = k + 1;
                final String status2 = driver.findElement(By.xpath("(//span[@class='test-name'])[" + l + "]/following-sibling::span[1]")).getText();
                System.out.println(status2);
                for (int m = 16; !ExcelUtils.getCellData("Testcases", m, 2).equalsIgnoreCase("End"); ++m) {
                    if (ExcelUtils.getCellData("Testcases", m, 1).equalsIgnoreCase(testcasename2)) {
                        GenerateReport1.Testcasedes = ExcelUtils.getCellData("Testcases", m, 4);
                    }
                }
                GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<tr>";
                GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style='width: 10%; text-align:center';>" + l + "</td>";
                GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style= 'text-align: center';>" + testcasename2 + "</td>";
                GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style='word-break:break-word;'>" + GenerateReport1.Testcasedes + "</td>";
                GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style='word-break:break-word; text-align: center';>" + startime2 + "</td>";
                GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style='word-break:break-word; text-align: center';>" + endtime2 + "</td>";
                if (status2.equalsIgnoreCase("PASS")) {
                    GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style= 'text-align: center; font-size:initial';><font style=\"font-weight:bold\" color=\"green\" ;>PASS</font>";
                }
                else {
                    GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style= 'text-align: center; font-size:initial';><font style=\"font-weight:bold\" color=\"red\" ;=\"\">FAIL</font></td>";
                }
                GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "</tr>";
            }
        }
        Thread.sleep(2000L);
        driver.findElement(By.xpath("//a[@class='dashboard-view']")).click();
        final String passpercentage = driver.findElement(By.xpath("//span[@class='pass-percentage panel-lead']")).getText();
        final String totaltestcase = driver.findElement(By.xpath("//span[@class='total-tests']/span")).getText();
        GenerateReport1.chrometestcasePassed = driver.findElement(By.xpath("//span[@class='t-pass-count weight-normal']")).getText();
        GenerateReport1.chrometestcaseFailed = driver.findElement(By.xpath("//span[@class='t-fail-count weight-normal']")).getText();
        driver.close();
        final String substring = "Chrome";
        for (final String fileName : filesContainingSubstring) {
            final FileReader reader = new FileReader("D:\\iWAgent Release Plans\\Report\\" + fileName);
            final StringBuilder sb = new StringBuilder();
            final BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            final String textOnly = Jsoup.parse(sb.toString()).html();
            final Document html = Jsoup.parse(textOnly);
            final String title = html.title();
            System.out.println(title);
            GenerateReport1.ChromePassStatusCount = html.body().getElementsByClass("test-status label right outline capitalize pass").size();
            GenerateReport1.ChromeFailedStatusCount = html.body().getElementsByClass("test-status label right outline capitalize fail").size();
            System.out.println(GenerateReport1.ChromePassStatusCount);
            System.out.println(GenerateReport1.ChromeFailedStatusCount);
            GenerateReport1.Chrometestcasecount = html.body().getElementsByClass("test-name").size();
            GenerateReport1.Testcasename = html.body().getElementsByClass("test-name").text();
            System.out.println(GenerateReport1.Testcasename);
            if (GenerateReport1.Chrometestcasecount == 0) {
                GenerateReport1.ChromeStatus = "Not Executed";
            }
            else if (GenerateReport1.ChromeFailedStatusCount == 0) {
                GenerateReport1.ChromeStatus = "PASS";
            }
            else {
                GenerateReport1.ChromeStatus = "FAIL";
            }
        }
        final String projectName = ExcelUtils.getCellData("Testcases", 1, 1);
        final String projectPhase = ExcelUtils.getCellData("Testcases", 2, 1);
        for (int i2 = 0; i2 < GenerateReport1.Chrometestcasecount; ++i2, ++i2) {
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<tr>";
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "<td style='width: 10%; text-align:center';>" + i2 + "</td>";
            GenerateReport1.DynamicHTML = String.valueOf(GenerateReport1.DynamicHTML) + "</tr>";
        }
        String HTMLReport_1 = "";
        String HTMLReport_2 = "";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<html>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><title>iWAgent Execution Output</title>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<meta name=\"description\" content=\"Bootstrap.\">";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<style>.inv {display: none;}</style>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<link href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\" rel=\"stylesheet\">";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\"></script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script src=\"https://code.jquery.com/jquery-1.10.2.js\"></script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<link rel=\"stylesheet\" href=\"http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css\"></style>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type=\"text/javascript\" src=\"http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js\"></script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type=\"text/javascript\" src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<style>.open .menu-icon-toggle::after,.open .menu-icon-toggle::before{-webkit-transition:top .3s,bottom .3s,-webkit-transform .3s .3s;transition:top .3s,bottom .3s,-webkit-transform .3s .3s;transition:top .3s,bottom .3s,transform .3s .3s;transition:top .3s,bottom .3s,transform .3s .3s,-webkit-transform .3s .3s}.menu-background{visibility:hidden;width:300%;height:780%;position:absolute;left:-130%;background-color:#fff;-webkit-transition:background-position .1s,visibility .1s 1s,-webkit-transform .1s .1s;transition:background-position .1s,visibility .1s 1s,-webkit-transform .1s .1s;transition:background-position .1s,transform .1s .1s,visibility .1s 1s;transition:background-position .1s,transform .1s .1s,visibility .1s 1s,-webkit-transform .1s .1s}.open .menu-background{visibility:visible;-webkit-transition:background-position .1s,-webkit-transform .1s .1s;transition:background-position .1s,-webkit-transform .1s .1s;transition:background-position .1s,transform .1s .1s;transition:background-position .1s,transform .1s .1s,-webkit-transform .1s .1s}.menu-background.top{-webkit-transform:rotate(-45deg) translateY(-150%);transform:rotate(-45deg) translateY(-150%);background:-webkit-gradient(linear,left bottom,left top,color-stop(50%,#6191d1),color-stop(50%,#fff));background:linear-gradient(to top,#6191d1 50%,#fff 50%);background-size:100% 200%;background-position:-100% 100%}.open .menu-background.top{-webkit-transform:rotate(-45deg) translateY(-49%);transform:rotate(-45deg) translateY(-49%);background-position:0 0}.menu-background.middle{-webkit-transform:rotate(-45deg) translateY(50%) scaleY(0);transform:rotate(-45deg) translateY(50%) scaleY(0);background:#fff}.open .menu-background.middle{-webkit-transform:rotate(-45deg) translateY(50%) scaleY(1);transform:rotate(-45deg) translateY(50%) scaleY(1)}.menu-background.bottom{-webkit-transform:rotate(-45deg) translateY(250%);transform:rotate(-45deg) translateY(250%);background:-webkit-gradient(linear,left top,left bottom,color-stop(50%,#fff),color-stop(50%,#6191d1));background:linear-gradient(to bottom,#fff 50%,#6191d1 50%);background-size:100% 200%;background-position:0 -100%}.open .menu-background.bottom{-webkit-transform:rotate(-45deg) translateY(149%);transform:rotate(-45deg) translateY(149%);background-position:0 0}.menu{position:absolute;top:50%;left:50%;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%);visibility:hidden;list-style-type:none;margin:0;padding:0;z-index:99999}.open .menu{visibility:visible}.menu li{opacity:0;margin-bottom:5px;-webkit-transform:translateX(20px);transform:translateX(20px);-webkit-transition:all .1s;transition:all .1s}.menu li:nth-child(18){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(18) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(17){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(17) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(16){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(16) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(15){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(15) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(14){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(14) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(13){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(13) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(12){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(12) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(11){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(11) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(10){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(10) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(9){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(9) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(8){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(8) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(7){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(7) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(6){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(6) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(5){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(5) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(4){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(4) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(3){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(3) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(2){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(2) span{-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(1){-webkit-transition-delay:.1s;transition-delay:.1s}.menu li:nth-child(1) span{-webkit-transition-delay:.1s;transition-delay:.1s}.open .menu li{opacity:1;-webkit-transform:translateX(0);transform:translateX(0);-webkit-backface-visibility:hidden;backface-visibility:hidden}.open .menu li:nth-child(1){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(1) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(2){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(2) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(3){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(3) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(4){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(4) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(5){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(5) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(6){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(6) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(7){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(7) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(8){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(8) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(9){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(9) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(10){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(10) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(11){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(11) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(12){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(12) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(13){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(13) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(14){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(14) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(15){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(15) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(16){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(16) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(17){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(17) span{-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(18){-webkit-transition-delay:.5s;transition-delay:.5s}.open .menu li:nth-child(18) span{-webkit-transition-delay:.5s;transition-delay:.5s}.menu a{position:relative;font-size:19px;text-decoration:none}.menu a::before{content:'';display:block;height:2px;width:0;position:absolute;left:-30px;top:50%;background-color:#6191d1;-webkit-transform:translateY(-50%);transform:translateY(-50%);-webkit-transition:width .3s;transition:width .3s}.menu a:hover::before{width:15px}.content{position:absolute;top:50%;left:50%;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%);width:100%;text-align:center;color:#fff;-webkit-transition:all .3s 1s;transition:all .3s 1s}.open .content{opacity:0;-webkit-transition:all .3s;transition:all .3s}.transition-normal{-webkit-transition:.5s all;transition:.5s all}</style>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<style>h1{font-size: 30px; color: #5ef22c; font-weight: bolder; text-align: center; margin-bottom: 15px; text-decoration: underline;}table{width:100%;";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "table-layout: fixed;}.tbl-header{ background-color: #f7f7f7; } th{padding: 5px 15px; text-align: center; font-weight: bold;color:#1d76bc;text-transform:uppercase;";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "font-size: 12px; color: #007bff; text-transform: uppercase;}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + ".doubleLines{border-top: 5px solid #8ac441;border-bottom: 1px solid #8ac441;height:.5em;width: 100%;margin-bottom: .7em;margin-top: .4em;}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "img{vertical-align: middle;padding-top: 10px;padding-left: 10px;}td{padding: 4px;text-align: left;vertical-align: middle;font-weight: 300;font-size: 12px;color: black;border-bottom: solid 1px #1d76bc;border-right: solid 1px #1d76bc;border-left: solid 1px #1d76bc; ";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "1px cornflowerblue;border-right: solid 1px cornflowerblue;border-left: solid 1px cornflowerblue;}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "h2{font-size: 20px; color:black}h3{font-size: 1.9em;color: dimgrey;font-family:Raleway,sans-serif;font-weight: 800;text-align: center;}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "label{margin:25px;}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "body{background-color:#0059a0;}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + ".logo{background-image: url('https://www.indegene.com/img2018/logo-white.svg');}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "section{ margin: 0px;background: white}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + ".made-with-love {padding: 1px 9px 1px; clear: left; text-align: center; font-size: 10px; font-family: arial; color: #fff;background-color:#0059a0}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + ".made-with-love i {font-style: normal; color: #F50057; font-size: 14px; position: relative; top: 2px;}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + ".made-with-love a {color: #fff; text-decoration: none;}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + ".made-with-love a:hover {text-decoration: underline;}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "::-webkit-scrollbar {width: 6px;} ";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "::-webkit-scrollbar-track {-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); } ";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "::-webkit-scrollbar-thumb {-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); }";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "</style></head>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<body>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div><img src='https://myindegene.indegene.com/employeecompetencyinternal/images/indegeneLogo.png'/></div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<section>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div><h3>iWAgent Test Case Execution Summary</h3><div class=\"doubleLines\"></div><div class=\"tbl-header\" style=\"width: 40%;text-align: center; margin: auto;\">";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border: 2px solid #1d76bc;\";><thead><tr style='width: 10%'><th style=\"width: 20%; font-size:20px; text-align:center\">Label</th><th style=\"width:20%;font-size:20px;text-align: center;\">";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "Details</th></tr></thead></table></div><div class=\"tbl-content\" style=\"width: 40%;margin: auto;";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "\"><table cellpadding=\"0\" cellspacing=\"0\" style=\"border: 2px solid;\"><tbody><tr><td style=\"font-size:14px;font-weight:bolder;\">Project Name</td><td style=\"";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " font-size:14px;font-weight:bolder;\">" + projectName + "</td></tr>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<tr><td style=\"font-size:14px;font-weight:bolder;\">Phase</td><td style=\"font-size:14px;font-weight:bolder;\">" + projectPhase + "</td></tr>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<tr><td style=\"font-size:14px;font-weight:bolder;\">Total Test Cases Executed</td><td style=\"font-size:14px;font-weight:bolder;\">" + totaltestcase + "</td></tr>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<tr><td style=\"font-size:14px;font-weight:bolder;\">Pass Percentage</td><td style=\"font-size:14px;font-weight:bolder;\">" + passpercentage + "</td></tr>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<tr><td style=\"font-size:14px;font-weight:bolder;\">Test Cases Passed</td><td style=\"font-size:14px;font-weight:bolder;\">" + GenerateReport1.chrometestcasePassed + "</td></tr>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<tr><td style=\"font-size:14px;font-weight:bolder;\">Test Cases Failed</td><td style=\"font-size:14px;font-weight:bolder;\">" + GenerateReport1.chrometestcaseFailed + "</td></tr>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "</tbody></table></div></div></div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<h3>Individual Test Case Report</h3><div class=\"doubleLines\"></div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<select id='target' style='margin-left:20px;'><option value=''>Select...</option><option value='content_1'>Chrome</option><option value='content_2'>Firefox</option>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<option value='content_3'>Edge Chromium</option><select>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script>document.getElementById('target').addEventListener('change', function () {'use strict';var vis = document.querySelector('.vis'),";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "target = document.getElementById(this.value);if (vis !== null) {vis.className = 'inv';}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "if (target !== null ) {target.className = 'vis';}});</script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div id='content_1' class='inv'>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<h2>Chrome Execution Report</h2>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div id='donutchart' style='width:30%;float:left'></div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type='text/javascript'>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "google.charts.load('current', {'packages':['corechart']});";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "google.charts.setOnLoadCallback(drawChart);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "function drawChart() {";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var data = google.visualization.arrayToDataTable([";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ['Status', 'Count'],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ['PASS', " + GenerateReport1.chrometestcasePassed + "],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ['FAIL', " + GenerateReport1.chrometestcaseFailed + "]";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ]);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var options = {'title':'PASS Vs FAIL',pieHole: 0.4, 'width':550, 'height':400};";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var chart = new google.visualization.PieChart(document.getElementById('donutchart'));";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "chart.draw(data, options);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "</script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "</div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div id='content_2' class='inv'>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<h2>Firefox Execution Report</h2>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div id='donutchart1' style='width:30%;float:left'></div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type='text/javascript'>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "google.charts.load('current', {'packages':['corechart']});";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "google.charts.setOnLoadCallback(drawChart);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "function drawChart() {";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var data = google.visualization.arrayToDataTable([";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ['FireFox Status', 'Count'],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ['Firefox PASS', " + GenerateReport1.fftestcasePassed + "],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ['Firefox FAIL', " + GenerateReport1.fftestcaseFailed + "]";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ]);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var options = {'title':'PASS Vs FAIL',pieHole: 0.4, 'width':550, 'height':400};";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var chart = new google.visualization.PieChart(document.getElementById('donutchart1'));";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "chart.draw(data, options);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "</script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "</div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div id='content_3' class='inv'>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<h2>Edge Chromium Execution Report</h2>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div id='donutchart2' style='width:30%;float:left'></div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type='text/javascript'>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "google.charts.load('current', {'packages':['corechart']});";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "google.charts.setOnLoadCallback(drawChart);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "function drawChart() {";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var data = google.visualization.arrayToDataTable([";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ['Status', 'Count'],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ['Edge PASS', " + GenerateReport1.edgetestcasePassed + "],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ['Edge FAIL', " + GenerateReport1.edgetestcaseFailed + "]";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + " ]);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var options = {'title':'PASS Vs FAIL',pieHole: 0.4, 'width':550, 'height':400};";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var chart = new google.visualization.PieChart(document.getElementById('donutchart2'));";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "chart.draw(data, options);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "</script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "</div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<script type='text/javascript'>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "google.charts.load('current', {'packages':['bar']});";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "google.charts.setOnLoadCallback(drawStuff);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "function drawStuff() {";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var data = new google.visualization.arrayToDataTable([";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "['Overall Test Cases', 'Count'],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "['Executed - Chrome', " + GenerateReport1.ChromeexeCount + "],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "['Not Run - Chrome', " + GenerateReport1.ChromenotexeCount + "],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "['Executed - Firefox', " + GenerateReport1.FFexeCount + "],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "['Not Run - Firefox', " + GenerateReport1.FFnotexeCount + "],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "['Executed - Edge Chromium', " + GenerateReport1.EdgeexeCount + "],";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "['Not Run - Edge Chromium', " + GenerateReport1.EdgenotexeCount + "]";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "]);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var options = {";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "title: 'Test Cases Attempted Vs Test Cases Run',";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "width: 900,";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "legend: { position: 'none' },";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "chart: { title: '',";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "subtitle: ''},";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "bars: 'horizontal',";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "axes: {";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "x: {";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "0: { side: 'top', label: 'Count'}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "}";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "},";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "bar: { groupWidth: '90%' }";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "};";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "var chart = new google.charts.Bar(document.getElementById('top_x_div'));";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "chart.draw(data, options);";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "};";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "</script>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div id=\"top_x_div\" style=\"width: 900px; height: 500px; float:right;\"></div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div class=\"tbl-header1\"><table cellpadding=\"0 \" cellspacing=\"0 \" border=\"0 \" id=\"myTable\" class=\"table table-striped\">";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<thead><tr style='width: 10%'><th style=\"width: 30px;text-align:center\">S No</th><th style=\"text-align:center\">Testcase Name</th><th style=\"text-align:center\">Test Case Objective</th><th style=\"text-align:center\">Start Date-Time</th><th style=\"text-align:center\">End Date-Time</th>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<th style=\"text-align:center\">Status</th></tr></thead></div>";
        HTMLReport_1 = String.valueOf(HTMLReport_1) + "<div class=\"tbl-content\"><tbody>";
        HTMLReport_2 = String.valueOf(HTMLReport_2) + "</tbody></table></div></section><div class=\"made-with-love\"><footer><h2 style='color:white';>iWAgent Automation Framework Indegene Pvt. Ltd</h2> </footer></body>";
        HTMLReport_2 = String.valueOf(HTMLReport_2) + "</div><script>$(document).ready(function(){ $('#myTable').dataTable();});</script></html>";
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        final FileWriter fw_Expected = new FileWriter("./Business Report/Report.html");
        final BufferedWriter bw_Expected = new BufferedWriter(fw_Expected);
        bw_Expected.append((CharSequence)(String.valueOf(HTMLReport_1) + GenerateReport1.DynamicHTML + HTMLReport_2));
        bw_Expected.flush();
        bw_Expected.close();
    }
}
