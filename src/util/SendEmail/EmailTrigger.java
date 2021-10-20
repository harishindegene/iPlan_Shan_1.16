package util.SendEmail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sendgrid.helpers.mail.objects.Content;

public class EmailTrigger {
	
	 public static int ChromePassStatusCount;
	    public static int ChromeFailedStatusCount;
	    public static int Chrometestcasecount;
	    public static String ChromeStatus;
	    public static int FFPassStatusCount;
	    public static int FFFailedStatusCount;
	    public static int FFtestcasecount;
	    public static String FFStatus;
	    public static int IEPassStatusCount;
	    public static int IEFailedStatusCount;
	    public static int IEtestcasecount;
	    public static String IEStatus;
	    public static int EdgePassStatusCount;
	    public static int EdgeFailedStatusCount;
	    public static int Edgetestcasecount;
	    public static String EdgeStatus;
	    public static int TotalTestcasesexecuted;
	    public static int TotalTestcasespassed;
	    public static int TotalTestcasesfailed;
	    public static String Overallstatus;
	    public static Content content;
	    
	    static {
	        GenerateReport.ChromeStatus = "Not Executed";
	        GenerateReport.FFStatus = "Not Executed";
	        GenerateReport.IEStatus = "Not Executed";
	    }
	    
	    public static void generatereport() throws Exception {
	        final File chromefolder = new File("./ChromeTestReport/");
	        final List<String> filesContainingSubstring = new ArrayList<String>();
	        final File fffolder = new File("./FireFoxTestReport");
	        final List<String> filesContainingSubstringff = new ArrayList<String>();
	        final File iefolder = new File("./IETestReport");
	        final List<String> filesContainingSubstringie = new ArrayList<String>();
	        final String substring = "Chrome";
	        if (chromefolder.exists() && chromefolder.isDirectory()) {
	            final String[] files = chromefolder.list();
	            String[] array;
	            for (int length = (array = files).length, l = 0; l < length; ++l) {
	                final String fileName = array[l];
	                if (fileName.contains(substring)) {
	                    filesContainingSubstring.add(fileName);
	                }
	            }
	        }
	        for (final String fileName2 : filesContainingSubstring) {
	            final FileReader reader = new FileReader("./ChromeTestReport/" + fileName2);
	            final StringBuilder sb = new StringBuilder();
	            final BufferedReader br = new BufferedReader(reader);
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	            final String textOnly = Jsoup.parse(sb.toString()).html();
	            final Document html = Jsoup.parse(textOnly);
	            GenerateReport.ChromePassStatusCount = html.body().getElementsByClass("test-status label right outline capitalize pass").size();
	            GenerateReport.ChromeFailedStatusCount = html.body().getElementsByClass("test-status label right outline capitalize fail").size();
	            GenerateReport.Chrometestcasecount = html.body().getElementsByClass("test-name").size();
	            if (GenerateReport.Chrometestcasecount == 0) {
	                GenerateReport.ChromeStatus = "Not Executed";
	            }
	            else if (GenerateReport.ChromeFailedStatusCount == 0) {
	                GenerateReport.ChromeStatus = "PASS";
	            }
	            else {
	                GenerateReport.ChromeStatus = "FAIL";
	            }
	        }
	        final String ffsubstring = "Firefox";
	        if (fffolder.exists() && fffolder.isDirectory()) {
	            final String[] files2 = fffolder.list();
	            String[] array2;
	            for (int length2 = (array2 = files2).length, n = 0; n < length2; ++n) {
	                final String fileName3 = array2[n];
	                if (fileName3.contains(ffsubstring)) {
	                    filesContainingSubstringff.add(fileName3);
	                }
	            }
	        }
	        final Iterator<String> iterator2 = filesContainingSubstringff.iterator();
	        while (iterator2.hasNext()) {
	            final String fileName = iterator2.next();
	            final FileReader reader2 = new FileReader("./FireFoxTestReport/" + fileName);
	            final StringBuilder sb2 = new StringBuilder();
	            final BufferedReader br2 = new BufferedReader(reader2);
	            String line2;
	            while ((line2 = br2.readLine()) != null) {
	                sb2.append(line2);
	            }
	            final String textOnly2 = Jsoup.parse(sb2.toString()).html();
	            final Document html2 = Jsoup.parse(textOnly2);
	            GenerateReport.FFPassStatusCount = html2.body().getElementsByClass("test-status label right outline capitalize pass").size();
	            GenerateReport.FFFailedStatusCount = html2.body().getElementsByClass("test-status label right outline capitalize fail").size();
	            GenerateReport.FFtestcasecount = html2.body().getElementsByClass("test-name").size();
	            if (GenerateReport.FFtestcasecount == 0) {
	                GenerateReport.FFStatus = "Not Executed";
	            }
	            else if (GenerateReport.IEFailedStatusCount == 0) {
	                GenerateReport.FFStatus = "PASS";
	            }
	            else {
	                GenerateReport.FFStatus = "FAIL";
	            }
	        }
	        final String iesubstring = "InternetExplorer";
	        if (iefolder.exists() && iefolder.isDirectory()) {
	            final String[] files3 = iefolder.list();
	            String[] array3;
	            for (int length3 = (array3 = files3).length, n2 = 0; n2 < length3; ++n2) {
	                final String fileName4 = array3[n2];
	                if (fileName4.contains(iesubstring)) {
	                    filesContainingSubstringie.add(fileName4);
	                }
	            }
	        }
	        final Iterator<String> iterator3 = filesContainingSubstringie.iterator();
	        while (iterator3.hasNext()) {
	            final String fileName3 = iterator3.next();
	            final FileReader reader3 = new FileReader("./IETestReport/" + fileName3);
	            final StringBuilder sb3 = new StringBuilder();
	            final BufferedReader br3 = new BufferedReader(reader3);
	            String line3;
	            while ((line3 = br3.readLine()) != null) {
	                sb3.append(line3);
	            }
	            final String textOnly3 = Jsoup.parse(sb3.toString()).html();
	            final Document html3 = Jsoup.parse(textOnly3);
	            GenerateReport.IEPassStatusCount = html3.body().getElementsByClass("test-status label right outline capitalize pass").size();
	            GenerateReport.IEFailedStatusCount = html3.body().getElementsByClass("test-status label right outline capitalize fail").size();
	            GenerateReport.IEtestcasecount = html3.body().getElementsByClass("test-name").size();
	            System.out.println("Total number of testcases" + GenerateReport.IEtestcasecount);
	            System.out.println("Chrome Pass count" + GenerateReport.IEPassStatusCount);
	            System.out.println("Chrome Fail count" + GenerateReport.IEFailedStatusCount);
	            if (GenerateReport.IEtestcasecount == 0) {
	                GenerateReport.IEStatus = "Not Executed";
	            }
	            else if (GenerateReport.IEFailedStatusCount == 0) {
	                GenerateReport.IEStatus = "PASS";
	            }
	            else {
	                GenerateReport.IEStatus = "FAIL";
	            }
	        }
	        GenerateReport.TotalTestcasesexecuted = GenerateReport.Chrometestcasecount + GenerateReport.FFtestcasecount + GenerateReport.IEtestcasecount + GenerateReport.Edgetestcasecount;
	        GenerateReport.TotalTestcasespassed = GenerateReport.ChromePassStatusCount + GenerateReport.FFPassStatusCount + GenerateReport.IEPassStatusCount;
	        GenerateReport.TotalTestcasesfailed = GenerateReport.ChromeFailedStatusCount + GenerateReport.FFFailedStatusCount + GenerateReport.IEFailedStatusCount;
	        if (GenerateReport.TotalTestcasesfailed == 0) {
	            GenerateReport.Overallstatus = "PASS";
	        }
	        else {
	            GenerateReport.Overallstatus = "FAIL";
	        }
	   
	    
	    
	    }
	    
	    
	

}
