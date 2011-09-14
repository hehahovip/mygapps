/**
 * 
 */
package com.hehaho.metalpriceinfofetcher;

/**
 * @author Kevin.Wang
 *
 */
public interface MetalPriceFetcher {

	public String getTotalAsset();
	
	public String getValueOnOunces();
	
	public String getValueOnTonners();
	
	public String getDate();
	
	public String getClosedPrice();
	
}
