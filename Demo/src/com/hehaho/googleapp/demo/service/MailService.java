/**
 * 
 */
package com.hehaho.googleapp.demo.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.mail.MailServiceFactory;

/**
 * @author Kent.Wang
 * 
 */
public class MailService {

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
		Date now = new Date();
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		Date chinaTime = c.getTime();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
		String time = fmt.format(now);
		log.info("Sending mail at " + time + "... at china time : " + fmt.format(chinaTime));

		com.google.appengine.api.mail.MailService.Message message = new com.google.appengine.api.mail.MailService.Message();

		message.setSender(sender);
		log.info("Mail sender : " + sender);

		message.setSubject(subject);
		log.info("add mail subject : " + subject);

		message.setTextBody(bodyText);

		if (recipients != null) {
			message.setTo(recipients);
			for (int i = 0; i < recipients.size(); i++) {
				log.info("add mail recipient : " + recipients.get(i));
			}
		}

		if (cc != null) {
			message.setCc(cc);
			for (int i = 0; i < cc.size(); i++) {
				log.info("add mail cc : " + cc.get(i));
			}
		}

		if (bcc != null) {
			message.setBcc(bcc);
			for (int i = 0; i < bcc.size(); i++) {
				log.info("add mail bcc : " + cc.get(i));
			}
		}

		if (attachements != null) {
			List<com.google.appengine.api.mail.MailService.Attachment> atts = new ArrayList<com.google.appengine.api.mail.MailService.Attachment>();
			for (MailAttachmentBean bean : attachements) {

				com.google.appengine.api.mail.MailService.Attachment att = new com.google.appengine.api.mail.MailService.Attachment(
						bean.getFilename(), bean.getAttachmentData());
				atts.add(att);
				log.info("Attachment added... " + bean.getFilename());
			}

			message.setAttachments(atts);
		}

		try {
			MailServiceFactory.getMailService().send(message);
		} catch (IOException e) {
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
