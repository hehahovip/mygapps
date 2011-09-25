/**
 * 
 */
package com.hehaho.metalpriceinfofetcher;

import com.hehaho.metalpriceinfofetcher.bean.MetalPriceBean;

/**
 * @author Kevin.Wang
 *
 */
public interface MetalPriceFetcher {

	public MetalPriceBean getMetalInfo();
	
	public String getPageContent(String url);
	
	public void parseContent(String pageContent);
	
}
