/**
 * 
 */
package com.hehaho.googleapp.goldprice.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hehaho.googleapp.goldprice.utils.DateUtils;

/**
 * @author Kevin.Wang
 *
 */
public class DataUtilsTest {

	private static SimpleDateFormat sdf;
	
	@BeforeClass
	public static void beforeClass(){
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@Test
	public void testGetFirstDayofMonth() throws ParseException {
		Date firstDay = DateUtils.getFirstDayofMonth(sdf.parse("2011-10-05"));
		Assert.assertEquals("2011-10-01", sdf.format(firstDay));
	}
	
	@Test
	public void testGetLastMonthofMonth() throws ParseException {
		Date lastDay = DateUtils.getLastMonthofMonth(sdf.parse("2011-10-05"));
		Assert.assertEquals("2011-10-31", sdf.format(lastDay));
		
		lastDay = DateUtils.getLastMonthofMonth(sdf.parse("2011-11-05"));
		Assert.assertEquals("2011-11-30", sdf.format(lastDay));
	}

}
