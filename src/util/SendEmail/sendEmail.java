// 
// Decompiled by Procyon v0.5.36
// 

package util.SendEmail;

import java.text.DateFormat;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.Multipart;
import dataProvider.ExcelDataProvider;
import lib.ZipUtils;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.mail.BodyPart;
import javax.activation.DataSource;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;

public class sendEmail extends GenerateReport
{
    public static void sendEmail() throws Exception {
        final String to = "harish.pandalangatt@indegene.com";
        final String from = "qaautomation@indegene.com";
        final String host = "192.0.0.59";
        
        String OUTPUT_ZIP_FILE = "./EmailOutput/Output.zip";
        String SOURCE_FOLDER = "./ChromeTestReport"; // SourceFolder path
        final Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        final Session session = Session.getDefaultInstance(properties);
        final GenerateReport report = new GenerateReport();
        try {
            final MimeMessage message = new MimeMessage(session);
            message.setFrom((Address)new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(to));
            message.setSubject("iW Agent Test Execution Status");
            final File f = new File("D:\\iWAgent\\TestCases\\");
            final File[] attachments = f.listFiles();
            final MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            for (int i = 0; i < attachments.length; ++i) {
                messageBodyPart = new MimeBodyPart();
                final FileDataSource fileDataSource = new FileDataSource(attachments[i]);
                messageBodyPart.setDataHandler(new DataHandler((DataSource)fileDataSource));
                messageBodyPart.setFileName(attachments[i].getName());
                multipart.addBodyPart((BodyPart)messageBodyPart);
            }
            final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            final Date date = new Date();
            final String dateMod = dateFormat.format(date);
            final String projectName = ExcelDataProvider.getCellData(1, 1);
            System.out.println(projectName);
            final String phase = ExcelDataProvider.getCellData(2, 1);
            System.out.println(phase);
            final String Testsuitename = ExcelDataProvider.getCellData(3, 1);
            System.out.println(Testsuitename);
            String body = "<b>Dear Team,</b><br><br> <b>" + phase + "</b> Automation Test Run For <b>" + projectName + "</b> has been completed. Find the status below.</br><br><b><u>Highlights:</u></b></br><br>";
            body = String.valueOf(body) + "<table style=width:50% border=1 cellspacing=0 cellpadding=0><tr><td><b>Project Name</b></td> <td>" + projectName + "</td></tr><tr><td><b>Phase</b></td> <td>" + phase + "</td></tr><tr><td><b>Test Suite Name</b></td> <td>" + Testsuitename + "</td></tr><tr><td><b>Execution Date</b></td> <td>" + dateMod + "</td></tr><tr><td><b>Total Number of Test Case Exeucted</b></td> <td></td></tr>" + "<tr><td><b>Total Number of Test Case Passed</b></td> <td></td></tr><tr><td><b>Total Number of Test Case Failed</b></td> <td></td></tr><tr><td><b>Status</b></td> <td></td></tr>" + "</table><table style=width:50% border=1 cellspacing=0 cellpadding=0><tr><td><b>ChromeExecutionReport</b></td><td>Count</td></tr> <tr><td><b>Number of Test Case Exeucted In Chrome Browser</b></td> <td></td></tr>" + "<tr><td><b>Number of Test Case Passed In Chrome Browser</b></td> <td></td></tr><tr><td><b>Number of Test Case Failed In Chrome Browser</b></td> <td></td></tr><tr><td><b>Chrome Status</b></td> <td></td></tr></tbody></table>" + "</table><table style=width:50% border=1 cellspacing=0 cellpadding=0><tr><td><b>FirefoxExecutionReport</b></td><td>Count</td></tr> <tr><td><b>Number of Test Case Exeucted In Chrome Browser</b></td> <td></td></tr>" + "<tr><td><b>Number of Test Case Passed In FireFox Browser</b></td> <td></td></tr><tr><td><b>Number of Test Case Failed In FireFox Browser</b></td> <td></td></tr><tr><td><b>FireFox Status</b></td> <td></td></tr></tbody></table>" + "</table><table style=width:50% border=1 cellspacing=0 cellpadding=0><tr><td><b>IEExecutionReport</b></td><td>Count</td></tr> <tr><td><b>Number of Test Case Exeucted In Chrome Browser</b></td> <td></td></tr>" + "<tr><td><b>Number of Test Case Passed In IE Browser</b></td> <td></td></tr><tr><td><b>Number of Test Case Failed In IE Browser</b></td> <td></td></tr><tr><td><b>IE Status</b></td> <td></td></tr></tbody></table></table>";
            body = String.valueOf(body) + "<br>Please find detailed Status Report (attachment), for your reference.</br><br><b>Note:</b> This is an automated Message. Please contact QA Automation team at QAAutomation@Indegene.com for any further assistance.</br><br><b>Regards,</b><br><b>QA Automation Team</b></br>";
            final BodyPart htmlBodyPart = (BodyPart)new MimeBodyPart();
            htmlBodyPart.setContent((Object)body, "text/html");
            multipart.addBodyPart(htmlBodyPart);
            message.setContent((Multipart)multipart);
            
            ZipUtils appZip = new ZipUtils();
            appZip.generateFileList(new File(SOURCE_FOLDER));
            appZip.zipIt(OUTPUT_ZIP_FILE);
            Transport.send((Message)message);
            System.out.println("message sent successfully....");
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
