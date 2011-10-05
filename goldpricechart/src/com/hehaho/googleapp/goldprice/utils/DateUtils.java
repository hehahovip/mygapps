/**
 * 
 */
package com.hehaho.googleapp.goldprice.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Kevin.Wang
 *
 */
public class DateUtils {

	public static Date getFirstDayofMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		return c.getTime();
	}
	
	public static String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static Date getLastMonthofMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DATE, lastDay);
		return c.getTime();
	}
}
