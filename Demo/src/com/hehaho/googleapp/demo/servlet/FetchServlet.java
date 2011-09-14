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

import com.hehaho.googleapp.demo.jdo.File;
import com.hehaho.googleapp.demo.jdo.JDOServcie;
import com.hehaho.googleapp.demo.service.MailAttachmentBean;
import com.hehaho.googleapp.demo.service.MailService;
import com.hehaho.googleapp.demo.util.BytesUtils;
import com.hehaho.googleapp.demo.util.Constants;

/**
 * @author Kent.Wang
 *
 */
public class FetchServlet extends HttpServlet {
	
	public static final String ENV_TEST = "DEFAULT_ENCODING";
	
	public static final String DESTINATION_VALUE = "/fetch";
	
	public static final int FETCH_SIZE = 1024;
	
	public String IMG_URL = "fetch.image.url";
	
	public static final String MY_MAIL_ADDRESS = "yingkang.wang@gmail.com";
	public static final String TARGET_MAIL_ADDRESS = "wangyingkang@sohu.com";
	
	private static Logger log = Logger.getLogger(FetchServlet.class.getName());
	
	private JDOServcie service = new JDOServcie();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute(Constants.DESTINATION_KEY, DESTINATION_VALUE);

		String env = System.getenv(ENV_TEST);
		log.info("ENV : " + env);
		
		String image_url = System.getProperty(IMG_URL);
		log.info("property fetch.image.url : " + image_url);
		
/*		URL file = FetchServlet.class.getResource("");
		InputStreamReader filereader = new InputStreamReader(file.openStream());
		BufferedReader reader1 = new BufferedReader(filereader);
		String line = null;
		while((line = reader1.readLine()) != null){
			log.info("read from resource line : " + line);
		}*/
		
		
		
		fetchService(image_url, "CNYGOLD-BIG.png", "image/png");
//		fetchService("http://code.google.com/appengine/images/appengine-silver-120x30.gif", "google-app.gif", "image/gif");
		
	}
	
	
	public void fetchService(String fileUrl, String fileName, String MIMEType){
		try{
			URL url = new URL(fileUrl);
			BufferedInputStream reader = new BufferedInputStream(url.openStream()); 

			byte[] filedata = BytesUtils.getBytesFromInputStream(reader);
			if(filedata != null){
				log.info("attached file length : " + filedata.length);
			}
			
			//String fileID = service.saveFile(fileName, filedata);
			
			//File file = service.getFileByID(fileID);
			
			List<String> toList = new ArrayList<String>();
			toList.add(TARGET_MAIL_ADDRESS);
			toList.add(MY_MAIL_ADDRESS);
			toList.add("lijun8305@gmail.com");
			MailAttachmentBean attachment = new MailAttachmentBean(fileName, filedata, MIMEType);
			
			StringBuffer bodyText = new StringBuffer();
			bodyText.append("The attached file is the gold price chart." + "\n");
			Date now = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:MM:ss");
			String time = fmt.format(now);
			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			Date chinaTime = c.getTime();
			bodyText.append("Time(US) :" + time + "\n");
			bodyText.append("Time(CN) : " + fmt.format(chinaTime));
			MailService.sendMail(MY_MAIL_ADDRESS, toList, "Gold Price Chart", bodyText.toString(), attachment);
			
		} catch(MalformedURLException e){
			log.log(Level.SEVERE, e.getMessage(), e);
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
