/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.impl;

import com.hehaho.metalpriceinfofetcher.HTMLPageFileReader;

/**
 * @author Kevin.Wang
 *
 */
public class SPDRGoldPriceFetcherRemoteTest {

	
	public static void main(String[] args){
		SPDRGoldPriceFetcher fetcher = new SPDRGoldPriceFetcher();
		
		String content = fetcher.getPageContent();
//		content = HTMLPageFileReader.getSPDRGoldPageContent();
		fetcher.parseContent(content);
		
		System.out.println("Price: " + fetcher.getMetalInfo().getClosedPriceString());
		System.out.println("Total Assert: " + fetcher.getMetalInfo().getTotalNetAssetString());
		System.out.println("Ounce: " + fetcher.getMetalInfo().getValueOnOuncesString());
		System.out.println("Tonners: " + fetcher.getMetalInfo().getValueOnTonnersString());
//		System.out.println(fetcher.getMetalInfo().getDateString());
		
//		String url = "http://www.spdrgoldshares.com/ajax/home/";
//		String pageContent = fetcher.getPageContent(url);
//		
//		System.out.println(pageContent);
		
	}
}
