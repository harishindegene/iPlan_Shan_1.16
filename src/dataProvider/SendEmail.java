// 
// Decompiled by Procyon v0.5.36
// 

package dataProvider;

import java.util.List;
import java.util.Properties;
import java.text.DateFormat;
import javax.mail.Transport;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.activation.DataSource;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import javax.mail.Message;
import java.util.ArrayList;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SendEmail extends ExcelDataProvider
{
    public static void mail(final String failMessage) throws Exception {
        final String mailClient = "";
        final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        final Date date = new Date();
        final String dateMod = dateFormat.format(date);
        final String to = "harish.pandalangatt@indegene.com";
        System.out.println(to);
        final String from = "qaautomation@indegene.com";
        final String host = "192.0.0.59";
        final Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        final Session session = Session.getDefaultInstance(properties);
        final MimeMessage message = new MimeMessage(session);
        message.setFrom((Address)new InternetAddress(from));
        if (to.equals("") || to.equals(null)) {
            System.out.println("To field is blank");
        }
        else {
            final String[] toRecipientList = to.split(";");
            final InternetAddress[] toRecipientAddress = new InternetAddress[toRecipientList.length];
            int toCounter = 0;
            final List<InternetAddress> list = new ArrayList<InternetAddress>();
            String[] array;
            for (int length = (array = toRecipientList).length, j = 0; j < length; ++j) {
                final String toRecipient = array[j];
                list.add(toRecipientAddress[toCounter] = new InternetAddress(toRecipient.trim()));
                ++toCounter;
            }
            message.setFrom((Address)new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, (Address[])toRecipientAddress);
        }
        message.setRecipients(Message.RecipientType.CC, "harish.pandalangatt@indegene.com");
        message.setSubject("iW Agent Execution Status  -   - [COMPLETED]");
        String status = "<html><font color='red'>FAIL</font></html>";
        message.setSubject("IWTest");
        status = "<html><font color='green'>PASS</font></html>";
        final String OUTPUTFILE_LOC = "D:\\AutomationReports\\";
        final File f = new File(OUTPUTFILE_LOC);
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
        String body = "<b>Dear Team,</b><br><br> <b></b> RTE Validation for <b></b> has been completed. Find the status below.</br><br><b><u>Highlights:</u></b></br><br>";
        body = String.valueOf(body) + "<table style=width:50% border=1 cellspacing=0 cellpadding=0><tr><td><b>Project Name</b></td> <td></td></tr><tr><td><b>Phase</b></td> <td></td></tr><tr><td><b>GCMA Code</b></td> <td></td></tr><tr><td><b>Execution Date</b></td> <td>" + dateMod + "</td></tr><tr><td><b>Email Clients Covered</b></td> <td>" + mailClient + "</td></tr><tr><td><b>Status</b></td> <td>" + status + "</td></tr></tbody></table>";
        body = String.valueOf(body) + "<br>Please find detailed Status Report (attachment), for your reference.</br><br><b>Note:</b> This is an automated Message. Please contact QA Automation team at QAAutomation@Indegene.com for any further assistance.</br><br><b>Regards,</b><br><b>QA Automation Team</b></br>";
        final BodyPart htmlBodyPart = (BodyPart)new MimeBodyPart();
        htmlBodyPart.setContent((Object)body, "text/html");
        multipart.addBodyPart(htmlBodyPart);
        message.setContent((Multipart)multipart);
        Transport.send((Message)message);
        System.out.println("message sent successfully....");
    }
    
    public static void sendEmail() {
    }
}
