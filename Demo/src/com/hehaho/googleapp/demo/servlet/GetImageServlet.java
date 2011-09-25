/**
 * 
 */
package com.hehaho.googleapp.demo.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hehaho.googleapp.demo.util.BytesUtils;

/**
 * @author Kevin.Wang
 *
 */
public class GetImageServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(GetImageServlet.class.getName());

	public static final int FETCH_SIZE = 1024;
	
	public String IMG_URL = "fetch.image.url";
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try{
			String image_url = System.getProperty(IMG_URL);
			URL url = new URL(image_url);
			BufferedInputStream reader = new BufferedInputStream(url.openStream()); 

			byte[] filedata = BytesUtils.getBytesFromInputStream(reader);
			 
			resp.setContentType("image/png");
			resp.getOutputStream().write(filedata);
			resp.flushBuffer();
			
		} catch(MalformedURLException e){
			log.log(Level.SEVERE, e.getMessage(), e);
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public void fetchService(String fileUrl, String fileName, String MIMEType){
		
	}
	
}
