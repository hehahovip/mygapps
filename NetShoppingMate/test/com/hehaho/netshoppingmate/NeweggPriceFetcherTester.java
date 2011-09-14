/**
 * 
 */
package com.hehaho.netshoppingmate;

import com.hehaho.netshoppingmate.fetcher.impl.NeweggPriceFetcher;

/**
 * @author Kevin.Wang
 *
 */
public class NeweggPriceFetcherTester {

	public static void main(String[] args){
		NeweggPriceFetcher fetcher = new NeweggPriceFetcher();
		byte[] data = fetcher.getPrice("24-c13-208");
		PriceImageManager manager = new PriceImageManager();
		manager.writeImageFile("./testdata/24-c13-208." + Constants.IMG_FILE_EXTENSIONS.GIF_IMG, data);
	}
	
}
