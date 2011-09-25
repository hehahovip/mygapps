/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.impl;

import com.hehaho.metalpriceinfofetcher.HTMLPageFileReader;
import com.hehaho.metalpriceinfofetcher.impl.IShareSilverPriceFetcher;

/**
 * @author Kevin.Wang
 *
 */
public class IShareSiliverPriceFetcherTest {

	
	public static void main(String[] args){
		IShareSilverPriceFetcher fetcher = new IShareSilverPriceFetcher();
		
		String content = null;
		content = HTMLPageFileReader.getISharesSilverPageContent();
//		content = fetcher.getPageContent(IShareSilverPriceFetcher.SITE_URL);
		fetcher.parse(content);
		
		System.out.println(fetcher.getMetalInfo().getClosedPriceString());
		System.out.println(fetcher.getMetalInfo().getTotalNetAssetString());
		System.out.println(fetcher.getMetalInfo().getValueOnOuncesString());
		System.out.println(fetcher.getMetalInfo().getValueOnTonnersString());
		System.out.println(fetcher.getMetalInfo().getDateString());
		
	}
}
