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
		byte[] data = fetcher.getPrice("76009");
		PriceImageManager manager = new PriceImageManager();
		manager.writeImageFile("./testdata/76009." + Constants.IMG_FILE_EXTENSIONS.PNG_IMG, data);
	}
	
}
