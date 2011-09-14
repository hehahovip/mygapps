package com.hehaho.netshoppingmate.utils;

import junit.framework.TestCase;

public class URLUtilsTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testGetIDbyURL(){
		String newegg = "http://www.newegg.com.cn/Product/36-c01-128.htm";
		assertEquals("36-c01-128", URLUtils.getIDbyURL(newegg));
		
		String url360buy = "http://www.360buy.com/product/224873.html";
		assertEquals("224873", URLUtils.getIDbyURL(url360buy));
		
		String icson = "http://www.icson.com/Products/76706.html";
		assertEquals("76706", URLUtils.getIDbyURL(icson));
		
		assertNull(URLUtils.getIDbyURL(null));
	}
	
}
