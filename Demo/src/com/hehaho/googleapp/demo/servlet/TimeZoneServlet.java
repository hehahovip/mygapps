package com.hehaho.googleapp.demo.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TimeZoneServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Date now = new Date();
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-8:00"));
		Calendar d = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
		Calendar e = Calendar.getInstance(Locale.CHINA);
		Date chinaTime = c.getTime();
		Date otherTime = d.getTime();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
		String time = fmt.format(now);
		
		System.out.println("Now time : " + now.getTime());
		System.out.println("GMT+8 time : " + chinaTime.getTime());
		System.out.println("GMT+12 time : " + otherTime.getTime());
		System.out.println("local : " + e.getTime().getTime());
		System.out.println("Now time : " + time);
		System.out.println("GMT+8 time : " + fmt.format(chinaTime));
		System.out.println("GMT+12 time : " + fmt.format(otherTime));
		
	}

}
