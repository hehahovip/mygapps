/**
 * 
 */
package com.hehaho.netshoppingmate;

import com.hehaho.netshoppingmate.fetcher.impl.Buy360PriceFetcher;
import com.hehaho.netshoppingmate.utils.URLUtils;

/**
 * @author Kevin.Wang
 *
 */
public class Buy360PriceFetcherTester {

	public static void main(String[] args){
		Buy360PriceFetcher fetcher = new Buy360PriceFetcher();
		byte[] data = fetcher.getPrice("http://www.360buy.com/product/355842.html");
		System.out.println(fetcher.getTitle());
		System.out.println(fetcher.getStock_info());
		PriceImageManager manager = new PriceImageManager();
		manager.writeImageFile("./testdata/355842." + URLUtils.getImageTypebyURL(fetcher.getImageURL()), data);
	}
	
}
