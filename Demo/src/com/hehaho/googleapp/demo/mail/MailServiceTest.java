/**
 * 
 */
package com.hehaho.googleapp.demo.mail;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;


/**
 * @author Kent.Wang
 *
 */
public class MailServiceTest {

	public static final String MY_MAIL_ADDRESS = "yingkang.wang@gmail.com";
	
	private Logger log = Logger.getLogger(MailServiceTest.class.getName());
	
	public MailServiceTest(){
		
	}
	
	public void sendMail(){
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		
		Date now = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
		String time = fmt.format(now);
		log.info("Send mail at " + time + "...");
		
		String msgBody = "Test Mail Service. Please ignore it!" + time;
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(MY_MAIL_ADDRESS, "Gmail Account"));
			
//			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(MY_MAIL_ADDRESS, "Gmail Account"));

			InternetAddress[] addresses = new InternetAddress[1];
			addresses[0] = new InternetAddress(MY_MAIL_ADDRESS, "Gmail Account");
//			addresses[1] = new InternetAddress("lijun8305@gmail.com", "Gmail Account");
			
			
			msg.setRecipients(Message.RecipientType.TO, addresses);
			msg.setSubject("This is test mail for Gmail Service!");
			
			msg.setText(msgBody);
			
			Transport.send(msg);
			
		} catch (UnsupportedEncodingException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} catch (MessagingException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
}
