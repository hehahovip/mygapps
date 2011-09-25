/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import org.htmlparser.Parser;

import com.hehaho.metalpriceinfofetcher.MetalPriceFetcher;

/**
 * @author Kevin.Wang
 *
 */
public abstract class AbstractMetalPriceFetcher implements MetalPriceFetcher {

	private static Logger Log = Logger.getLogger(AbstractMetalPriceFetcher.class.toString());
	
	protected Parser pageParser;
	
	/* (non-Javadoc)
	 * @see com.hehaho.metalpriceinfofetcher.MetalPriceFetcher#getPageContent(java.lang.String)
	 */
	@Override
	public String getPageContent(String url) {
		
		String pageContent = null;

		try {
			Log.info("Fetching page with url : " + url);
			URL _url = new URL(url);
			
			URLConnection uc = _url.openConnection();
			uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-GB; rv:1.9.2.9) Gecko/20100824 Firefox/3.6.9");
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
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

}
