/**
 * 
 */
package com.hehaho.googleapp.demo.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 * @author Kent.Wang
 * 
 */
public class MailServiceOld {

	private static final Logger log = Logger.getLogger(MailService.class
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
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		Date now = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
		String time = fmt.format(now);
		log.info("Sending mail at " + time + "...");

		InternetAddress[] recipAddresses = null;
		InternetAddress[] ccAddresses = null;
		InternetAddress[] bccAddresses = null;

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender, ""));
			log.info("Mail sender : " + sender);

			if (recipients != null) {
				recipAddresses = new InternetAddress[recipients.size()];

				for (int i = 0; i < recipients.size(); i++) {
					recipAddresses[i] = new InternetAddress(recipients.get(i),
							"");
					log.info("add mail recipient : " + recipients.get(i));
				}
				msg.setRecipients(Message.RecipientType.TO, recipAddresses);
			}

			if (cc != null) {
				ccAddresses = new InternetAddress[cc.size()];
				for (int i = 0; i < cc.size(); i++) {
					ccAddresses[i] = new InternetAddress(cc.get(i), "");
					log.info("add mail cc : " + cc.get(i));
				}
				msg.setRecipients(Message.RecipientType.CC, ccAddresses);
			}

			if (bcc != null) {
				bccAddresses = new InternetAddress[bcc.size()];
				for (int i = 0; i < bcc.size(); i++) {
					bccAddresses[i] = new InternetAddress(bcc.get(i), "");
					log.info("add mail bcc : " + cc.get(i));
				}
				msg.setRecipients(Message.RecipientType.BCC, bccAddresses);
			}

			// Set the multipart content.
			Multipart mp = new MimeMultipart();
			if (attachements != null) {
				for (MailAttachmentBean bean : attachements) {
					MimeBodyPart part = new MimeBodyPart();
					part.setContent(bean.getAttachmentData(), bean.getType());
					if (bean.getFilename() != null) {
						part.setFileName(bean.getFilename());
					}

					ByteArrayDataSource inboundDataSource = new ByteArrayDataSource(bean.getAttachmentData(), bean.getType()); 
					part.setDataHandler(new DataHandler(inboundDataSource));

					mp.addBodyPart(part);
					log.info("Attachment added... " + bean.getFilename());
				}

			}

			msg.setSubject(subject);
			log.info("add mail subject : " + subject);

			// set text content .
			MimeBodyPart body = new MimeBodyPart();
			body.setText(bodyText);
			mp.addBodyPart(body);
			msg.setContent(mp);

			Transport.send(msg);

		} catch (UnsupportedEncodingException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} catch (MessagingException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
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
