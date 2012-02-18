/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kevin.Wang
 *
 */
public class DateUtils {

	private static SimpleDateFormat DateFormatter = new SimpleDateFormat();
	
	static {
		DateFormatter.applyPattern("yyyy-MM-dd");
	}
	
	public static String formatDate(Date date){
		if(date != null){
			return DateFormatter.format(date);
		}
		return null;
	}
	
}
