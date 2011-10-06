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
		fetcher.parseContent(content);
		
		System.out.println("Price: " + fetcher.getMetalInfo().getClosedPriceString());
		System.out.println("Total Assert: " + fetcher.getMetalInfo().getTotalNetAssetString());
		System.out.println("Ounce: " + fetcher.getMetalInfo().getValueOnOuncesString());
		System.out.println("Tonners: " + fetcher.getMetalInfo().getValueOnTonnersString());
		System.out.println("Date: " + fetcher.getMetalInfo().getDate());
		
	}
}
