package mailSender;

/*
 * MailNotification class has been written in order to send the report after getting the extent reports
 * 
 * @author Abhishek Shandilya
 */

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import commonUtilities.PropertyManager;

public class MailNotification {
	public static Properties props;
	public static Session session;
	public static Message message;
	public static BodyPart messageBodyPart;
	public static Multipart multipart;
	public static DataSource source;

	public final static String mailUsrername = PropertyManager.prop.getProperty("mailUsername");
	public final static String mailPassword = PropertyManager.prop.getProperty("mailPassword");
	public final static String host = PropertyManager.prop.getProperty("smtpHost");
	public final static String port = PropertyManager.prop.getProperty("smtpPort");
	public static String to = PropertyManager.prop.getProperty("mailTo");
	public static String from = PropertyManager.prop.getProperty("mailFrom");
	public static String cc = PropertyManager.prop.getProperty("mailCc");
	public static String bcc = PropertyManager.prop.getProperty("mailBcc");
	public static String subject = PropertyManager.prop.getProperty("subject");
	public static String body = PropertyManager.prop.getProperty("body");

	private static String reportFileName = "Test-Automaton-Report" + ".html";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "ExecutionReport";
	private static String executionReportFileLocation = reportFilepath + fileSeperator + reportFileName;

	/*
	 * The use of this method is to get the session object by authenticating the
	 * credentials then creating a mime message object then set up the address in
	 * form of recipient as to, cc, bcc and having a subject of the message.
	 * BodyPart class to be used to set the body of the message DataSource class is
	 * used to send the attachment after the execution in form of extent report
	 */
	public static void sendMailWithAttachment() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailUsrername, mailPassword);
			}
		});
		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
			message.setSubject(subject);
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(body);
			multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			source = new FileDataSource(executionReportFileLocation);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(executionReportFileLocation);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
