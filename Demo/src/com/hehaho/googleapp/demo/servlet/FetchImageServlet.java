/**
 * 
 */
package com.hehaho.googleapp.demo.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hehaho.googleapp.demo.service.MailAttachmentBean;
import com.hehaho.googleapp.demo.service.MailService;
import com.hehaho.googleapp.demo.util.BytesUtils;

/**
 * @author Kevin.Wang
 *
 */
public class FetchImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int FETCH_SIZE = 1024;
	
	public String IMG_URL = "fetch.smallimage.url";
	
	public static final String MY_MAIL_ADDRESS = "yingkang.wang@gmail.com";
	public static final String TARGET_MAIL_ADDRESS = "wangyingkang@sohu.com";
	
	private static Logger log = Logger.getLogger(FetchImageServlet.class.getName());
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String image_url = System.getProperty(IMG_URL);
		log.info("property fetch.image.url : " + image_url);
		fetchService(image_url, "t24_au_ch_usoz.gif", "image/gif");
	}

	
	public void fetchService(String fileUrl, String fileName, String MIMEType){
		try{
			URL url = new URL(fileUrl);
			BufferedInputStream reader = new BufferedInputStream(url.openStream()); 

			byte[] filedata = BytesUtils.getBytesFromInputStream(reader);
			if(filedata != null){
				log.info("attached file length : " + filedata.length);
			}
			
			List<String> toList = new ArrayList<String>();
			toList.add(TARGET_MAIL_ADDRESS);
			toList.add(MY_MAIL_ADDRESS);
//			toList.add("lijun8305@gmail.com");
			MailAttachmentBean attachment = new MailAttachmentBean(fileName, filedata, MIMEType);
			
			StringBuffer bodyText = new StringBuffer();
			bodyText.append("The attached file is the gold price chart." + "\n");
			Date now = new Date();
			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			Date chinaTime = c.getTime();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
			String time = fmt.format(now);
			bodyText.append("Time(US) :" + time + "\n");
			bodyText.append("Time(CN) : " + fmt.format(chinaTime));
			
			System.out.println(bodyText.toString());
			
			MailService.sendMail(MY_MAIL_ADDRESS, toList, "Gold Price Chart(EveryDay Once)", bodyText.toString(), attachment);
			
		} catch(MalformedURLException e){
			log.log(Level.SEVERE, e.getMessage(), e);
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}
}
