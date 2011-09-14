/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.htmlparser.Parser;

import com.hehaho.netshoppingmate.Constants;
import com.hehaho.netshoppingmate.fetcher.ProductPriceFetcher;
import com.hehaho.netshoppingmate.utils.URLUtils;

/**
 * @author Kevin.Wang
 * 
 */
public abstract class AbstractProductPriceFetcher implements
		ProductPriceFetcher {

	private static Logger Log = Logger.getLogger(AbstractProductPriceFetcher.class.toString());
	
	protected Parser pageParser;

	protected String id;
	
	protected byte[] imageData;
	
	protected String imageURL;
	
	protected String imageType;
	
	protected String title;
	
	protected String stock_info;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hehaho.netshoppingmate.fetcher.ProductPriceFetcher#getPrice(java.
	 * lang.String)
	 */
	@Override
	public byte[] getPrice(String id) {
		if(id == null || "".equalsIgnoreCase(id.trim())){
			return null;
		}
		
		if(id != null && id.startsWith(Constants.HTTP_PREFIX)){
			id = URLUtils.getIDbyURL(id);
		}
		
		String pageContent = getProductPageContentByID(id);
		parseProductPrice(pageContent);
		
		return this.imageData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.hehaho.netshoppingmate.fetcher.ProductPriceFetcher#
	 * getProductPageContentByID(java.lang.String)
	 */
	@Override
	public String getProductPageContentByID(String id) {
		String url = getProductURL(id);

		imageType = URLUtils.getImageTypebyURL(url);
		
//		HttpMethod method = new GetMethod(url);

		String pageContent = null;

		try {
//			int statusCode = client.executeMethod(method);
			Log.info("Fetching id: " + id + " with url : " + url);
			URL _url = new URL(url);
			
			URLConnection uc = _url.openConnection();
			uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-GB; rv:1.9.2.9) Gecko/20100824 Firefox/3.6.9");
//			pageContent = method.getResponseBodyAsString();
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream(),Charset.forName(this.getCharSet())));
            String line;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            pageContent = result.toString();

		} catch (IOException e) {
			Log.severe(e.getMessage());
		}

		return pageContent;
	}


	public abstract void parseProductPrice(String pageContent);
	
	public abstract String getCharSet();

	/**
	 * @return the imageData
	 */
	public byte[] getImageData() {
		return imageData;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/* (non-Javadoc)
	 * @see com.hehaho.netshoppingmate.fetcher.ProductPriceFetcher#getImageType()
	 */
	@Override
	public String getImageType() {
		return imageType;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the stock_info
	 */
	public String getStock_info() {
		return stock_info;
	}
	
}
