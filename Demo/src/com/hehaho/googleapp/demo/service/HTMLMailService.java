/**
 * 
 */
package com.hehaho.googleapp.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author Kevin.Wang
 * 
 */
public class HTMLMailService {

	private static final Logger log = Logger.getLogger(HTMLMailService.class
			.getName());

	/**
	 * Send the mail.
	 * 
	 * @param sender
	 * @param recipients
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param bodyText
	 * @param attachements
	 */
	public static void sendMail(String sender, List<String> recipients,
			List<String> cc, List<String> bcc, String subject, String bodyText,
			List<MailAttachmentBean> attachements) {
		Date now = new Date();
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		Date chinaTime = c.getTime();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
		String time = fmt.format(now);
		log.info("Sending mail at " + time + "... at china time : " + fmt.format(chinaTime));

		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
		
		Message message = new MimeMessage(session);
		
		Address from;
		try {
			from = new InternetAddress(sender);
			message.addFrom(new Address[]{from});
			log.info("Mail sender : " + sender);

			message.setSubject(subject);
			log.info("add mail subject : " + subject);

			
			Multipart mp = new MimeMultipart();

	        MimeBodyPart htmlPart = new MimeBodyPart();
	        try {
				htmlPart.setContent(bodyText, "text/html");
				mp.addBodyPart(htmlPart);
			} catch (MessagingException e1) {
				log.info("MessagingException  : " + e1);
			}
			
			Address[] tos = new InternetAddress[recipients.size()];
			if (recipients != null) {
				for (int i = 0; i < recipients.size(); i++) {
					try {
						tos[i] = new InternetAddress(recipients.get(i));
					} catch (AddressException e) {
						e.printStackTrace();
					}
					log.info("add mail recipient : " + recipients.get(i));
				}
			}
			message.addRecipients(RecipientType.TO, tos);

			message.setContent(mp);
			
			Transport.send(message);
		} catch (AddressException e) {
			log.severe(e.getMessage());
		} catch (MessagingException e) {
			log.severe(e.getMessage());
		}
		
	}

	public static void sendMail(String sender, List<String> recipients,
			String subject, String bodyText) {
		sendMail(sender, recipients, null, null, subject, bodyText, null);
	}

	public static void sendMail(String sender, List<String> recipients,
			String subject, String bodyText,
			List<MailAttachmentBean> attachements) {
		sendMail(sender, recipients, null, null, subject, bodyText,
				attachements);
	}

	public static void sendMail(String sender, List<String> recipients,
			String subject, String bodyText, MailAttachmentBean attachement) {
		List<MailAttachmentBean> attachements = new ArrayList<MailAttachmentBean>();
		attachements.add(attachement);
		sendMail(sender, recipients, null, null, subject, bodyText,
				attachements);
	}

}
