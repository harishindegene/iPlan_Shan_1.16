// 
// Decompiled by Procyon v0.5.36
// 

package util.SendEmail;

import java.text.DateFormat;
import java.nio.file.Path;
import org.jsoup.nodes.Document;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import com.sendgrid.Method;
import com.sendgrid.helpers.mail.Mail;
import java.util.Arrays;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Email;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.sendgrid.helpers.mail.objects.Attachments;
import org.jsoup.Jsoup;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;
import com.sendgrid.helpers.mail.objects.Content;
import dataProvider.ExcelDataProvider;

public class GenerateReport extends ExcelDataProvider
{
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
        final String to = ExcelDataProvider.getCellData(6, 1);
        final String cc = ExcelDataProvider.getCellData(7, 1);
        final Attachments attachments = new Attachments();
        final File f = new File("./EmailOutput/");
        final File[] listOfFiles = f.listFiles();
        for (int i = 0; i < listOfFiles.length; ++i) {
            if (listOfFiles[i].isFile()) {
                final Path file = Paths.get("./EmailOutput/" + listOfFiles[i].getName(), new String[0]);
                attachments.setDisposition("attachment");
                final byte[] attachmentContentBytes = Files.readAllBytes(file);
                final String attachmentContent = Base64.getEncoder().encodeToString(attachmentContentBytes);
                attachments.setType("text/html");
                attachments.setContent(attachmentContent);
                attachments.setFilename("Test Execution Report.html");
                System.out.println("File " + listOfFiles[i].getName());
            }
            else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        try {
            final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            final Date date = new Date();
            final String dateMod = dateFormat.format(date);
            final String projectName = ExcelDataProvider.getCellData(1, 1);
            if (projectName == null) {
                final String subject = "iWAgent Test Execution - FAIL";
                final MimeMultipart multipart = new MimeMultipart();
                final MimeBodyPart messageBodyPart = new MimeBodyPart();
                String body = "<b>Dear Team,</b><br><br> <b> iWAgent Automation for <b>" + projectName + "</b> project could not be completed due to unforeseen reason. Request you to review your Input file and try again.</br>If issue Persists, Please contact QA Automation team at QAAutomation@Indegene.com for assistance.<br></br>";
                body = String.valueOf(body) + "<br></br><b>Possible Reason for Failure: </b>Missing Project Name.<br></br><b>Note:</b> This is an automated Message.</br><br><b>Regards,</b><br><b>QA Automation Team</b></br>";
                GenerateReport.content = new Content("text/html", body);
            }
            final String phase = ExcelDataProvider.getCellData(2, 1);
            final String Testsuitename = ExcelDataProvider.getCellData(4, 1);
            final String subject2 = "iW Agent Test Execution Status";
            String body = "<b>Dear Team,</b><br><br> <b>" + phase + "</b> iWebAgent Suite for <b>" + projectName + "</b> has been completed. Find the status below.</br><br><b><u>Highlights:</u></b></br><br>";
            body = String.valueOf(body) + "<table style=width:45% border=1 cellspacing=0 cellpadding=0><tr><td><b>Project Name</b></td> <td>" + projectName + "</td></tr><tr><td><b>Phase</b></td> <td>" + phase + "</td></tr><tr><td><b>Test Suite Name</b></td> <td>" + Testsuitename + "</td></tr><tr><td><b>Execution Date</b></td> <td>" + dateMod + "</td></tr><tr><td><b>Total Number of Test Cases EXECUTED</b></td><td>" + GenerateReport.TotalTestcasesexecuted + "</td></tr>" + "<tr><td><b>Total Number of Test Cases PASSED</b></td> <td>" + GenerateReport.TotalTestcasespassed + "</td></tr><tr><td><b>Total Number of Test Cases FAILED</b></td> <td>" + GenerateReport.TotalTestcasesfailed + "</td></tr><tr><td><b>Status</b></td> <td>" + GenerateReport.Overallstatus + "</td></tr></table>" + "<br><br><table style=width:25% border=1 cellspacing=0 cellpadding=0><tr><td align =center><b>CHROME Browser</b></td><td><b>Count</b></td></tr><tr><td><b>Total Number of Test Cases EXECUTED</b></td><td align =center>" + GenerateReport.Chrometestcasecount + "</td></tr>" + "<tr><td><b>Total Number of Test Cases PASSED</b></td> <td align =center>" + GenerateReport.ChromePassStatusCount + "</td></tr><tr><td><b>Total Number of Test Cases FAILED</b></td> <td align =center>" + GenerateReport.ChromeFailedStatusCount + "</td></tr><tr><td><b>Status</b></td> <td align =center>" + GenerateReport.ChromeStatus + "</td></tr></tbody></table>" + "<br><br><table style=width:25% border=1 cellspacing=0 cellpadding=0><tr><td align =center><b>MOZILLA FIREFOX Browser</b></td><td align =center><b>Count</b></td></tr> <tr><td><b>Total Number of Test Cases EXECUTED</b></td> <td align =center>" + GenerateReport.FFtestcasecount + "</td></tr>" + "<tr><td><b>Total Number of Test Cases PASSED</b></td> <td align =center >" + GenerateReport.FFPassStatusCount + "</td></tr><tr><td><b>Total Number of Test Cases FAILED</b></td> <td align =center>" + GenerateReport.FFFailedStatusCount + "</td></tr><tr><td><b>Status</b></td> <td align =center>" + GenerateReport.FFStatus + "</td></tr></tbody></table>" + "<br><br><table style=width:25% border=1 cellspacing=0 cellpadding=0><tr><td align =center><b>IE 11 Browser</b></td><td align =center><b>Count</b></td></tr> <tr><td><b>Total Number of Test Cases EXECUTED</b></td> <td align =center>" + GenerateReport.IEtestcasecount + "</td></tr>" + "<tr><td><b>Total Number of Test Cases PASSED</b></td> <td align =center>" + GenerateReport.IEPassStatusCount + "</td></tr><tr><td><b>Total Number of Test Cases FAILED</b></td> <td align =center>" + GenerateReport.IEFailedStatusCount + "</td></tr><tr><td><b>Status</b></td> <td align =center>" + GenerateReport.IEStatus + "</td></tr></tbody></table></table>";
            body = String.valueOf(body) + "<br>Please find the individual execution reports inside the separate folders for Chrome(ChromeTestReport),Firefox(FireFoxTestReport) & IE(IETestReport) inside the project execution folder.</br>";
            body = String.valueOf(body) + "<br><b>Note:</b> This is an automated Message. Please contact QA Automation team at QAAutomation@Indegene.com for any further assistance.</br><br><b>Regards,</b><br><b>QA Automation Team</b></br>";
            final Email from = new Email("qaautomation@indegene.com");
            GenerateReport.content = new Content("text/html", body);
            final SendGrid sg = new SendGrid("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");
            final Request request = new Request();
            final Personalization personalization = new Personalization();
            final String[] str = to.split(",");
            List<String> al = new ArrayList<String>();
            al = Arrays.asList(str);
            for (int j = 0; j < al.size(); ++j) {
                personalization.addTo(new Email((String)al.get(j)));
            }
            if (cc.equals("")) {
                System.out.println("CC field not given");
            }
            else {
                final String[] strcc = cc.split(",");
                List<String> alc = new ArrayList<String>();
                alc = Arrays.asList(strcc);
                for (int k = 0; k < alc.size(); ++k) {
                    personalization.addCc(new Email((String)alc.get(k)));
                }
            }
            final Mail mail = new Mail();
            mail.addPersonalization(personalization);
            mail.addAttachments(attachments);
            mail.setFrom(from);
            mail.addContent(GenerateReport.content);
            mail.setSubject(subject2);
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                sg.api(request);
            }
            catch (IOException ex) {
                throw ex;
            }
        }
        catch (Exception mex) {
            System.out.println(mex);
            System.out.println("Message failed to deliver");
        }
        System.out.println("message sent successfully....");
    }
}
