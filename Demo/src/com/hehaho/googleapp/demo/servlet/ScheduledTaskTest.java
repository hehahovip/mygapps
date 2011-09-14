/**
 * 
 */
package com.hehaho.googleapp.demo.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hehaho.googleapp.demo.mail.MailServiceTest;

/**
 * @author Kent.Wang
 *
 */
public class ScheduledTaskTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ScheduledTaskTest.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Date now = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
		log.info("Send mail at " + fmt.format(now) + "...");
		
		MailServiceTest mail = new MailServiceTest();
		mail.sendMail();
		log.info("Mail sent!");
		
		resp.setContentType("text/plain");
        resp.getWriter().println("Hello, yingkang");
	}

}
