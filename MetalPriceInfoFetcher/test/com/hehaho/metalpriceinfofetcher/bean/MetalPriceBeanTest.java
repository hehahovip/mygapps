package com.hehaho.metalpriceinfofetcher.bean;

import junit.framework.Assert;

import org.junit.Test;

public class MetalPriceBeanTest {

	@Test
	public void testSetDateString() {

		MetalPriceBean bean = new MetalPriceBean();
		
		bean.setDateString("9/23/2011");
		
		Assert.assertEquals("2011-09-23", bean.getDate());
	
	}

}
