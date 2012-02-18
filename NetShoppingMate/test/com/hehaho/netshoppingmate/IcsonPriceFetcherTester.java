/**
 * 
 */
package com.hehaho.netshoppingmate;

import com.hehaho.netshoppingmate.fetcher.impl.IcsonPriceFetcher;

/**
 * @author Kevin.Wang
 *
 */
public class IcsonPriceFetcherTester {

	public static void main(String[] args){
		IcsonPriceFetcher fetcher = new IcsonPriceFetcher();
		byte[] data = fetcher.getPrice("http://item.51buy.com/item-143424.html");
		PriceImageManager manager = new PriceImageManager();
		manager.writeImageFile("./testdata/143424." + Constants.IMG_FILE_EXTENSIONS.PNG_IMG, data);
	}
	
}
