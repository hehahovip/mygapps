/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher;

/**
 * @author Kevin.Wang
 *
 */
public interface ProductPriceFetcher {

	public String getProductPageContentByID(String id);
	
	public String getProductURL(String id);
	
	public void parseProductPrice(String pageContent);
	
	public byte[] getPrice(String id);
	
	public String getImageType();
	
	public String getImageURL();
	
	public String getCharSet();
	
}
