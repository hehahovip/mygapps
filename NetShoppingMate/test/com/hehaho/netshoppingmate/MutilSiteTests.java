/**
 * 
 */
package com.hehaho.netshoppingmate;

import com.hehaho.netshoppingmate.fetcher.impl.Buy360PriceFetcher;
import com.hehaho.netshoppingmate.fetcher.impl.IcsonPriceFetcher;
import com.hehaho.netshoppingmate.fetcher.impl.NeweggPriceFetcher;
import com.hehaho.netshoppingmate.utils.URLUtils;

import junit.framework.TestCase;

/**
 * @author Kevin.Wang
 *
 */
public class MutilSiteTests extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testNeweggTest(){
		NeweggPriceFetcher fetcher = new NeweggPriceFetcher();
		byte[] data = fetcher.getPrice("24-c12-257");
		
		PriceImageManager manager = new PriceImageManager();
		manager.writeImageFile("24-c12-257." + Constants.IMG_FILE_EXTENSIONS.GIF_IMG, data);
	}
	
	public void testIcsonTest(){
		IcsonPriceFetcher fetcher = new IcsonPriceFetcher();
		byte[] data = fetcher.getPrice("63343");
		PriceImageManager manager = new PriceImageManager();
		manager.writeImageFile("63343." + Constants.IMG_FILE_EXTENSIONS.PNG_IMG, data);
	}
	
	public void test360BuyTest(){
		Buy360PriceFetcher fetcher = new Buy360PriceFetcher();
		byte[] data = fetcher.getPrice("194901");
		PriceImageManager manager = new PriceImageManager();
		manager.writeImageFile("194901." + URLUtils.getImageTypebyURL(fetcher.getImageURL()), data);
	}
	
	public void testNullURL(){
		Buy360PriceFetcher fetcher_360buy = new Buy360PriceFetcher();
		byte[] data_360buy = fetcher_360buy.getPrice(null);
		
		NeweggPriceFetcher fetcher_newegg = new NeweggPriceFetcher();
		byte[] data_newegg = fetcher_newegg.getPrice(null);
		
		IcsonPriceFetcher fetcher_icson = new IcsonPriceFetcher();
		byte[] data_icson = fetcher_icson.getPrice(null);
	}

}
