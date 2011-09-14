/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl;

import java.util.logging.Logger;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.ParserException;

import com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.icson.IcsonImageTagVistor;

/**
 * @author Kevin.Wang
 *
 */
public class IcsonPriceFetcher extends AbstractProductPriceFetcher {

	public static String BASE_FETCH_URL = "http://www.icson.com/Products/";
	
	public static String ROOT_URL = "http://www.icson.com/";
	
	public static String PAGE_EXTEND_NAME = "html";
	
	public static final String CONTENT_CHARSET = "gb2312";
	
	public static class IDs{
		
		public static String PRICE_IMG_CLASS_NAME = "price_icson";	
		
		public static String PRICE_IMG_SRC = "src";
	}
	
	private static Logger Log = Logger.getLogger(IcsonPriceFetcher.class.getName());
	
	public IcsonPriceFetcher() {
		super();
	}


	/* (non-Javadoc)
	 * @see com.hehaho.netshoppingmate.ProductPriceFetcher#getProductURL(java.lang.String)
	 */
	@Override
	public String getProductURL(String id) {
		this.id = id;
		return BASE_FETCH_URL + id + "." + PAGE_EXTEND_NAME;
	}

	/* (non-Javadoc)
	 * @see com.hehaho.netshoppingmate.ProductPriceFetcher#parseProductPrice(java.lang.String)
	 */
	@Override
	public void parseProductPrice(String pageContent) {
		
		if (pageContent != null) {
			try {
				pageParser = new Parser(pageContent);

				NodeIterator it = pageParser.elements();
				Node node = null;
				while (it.hasMoreNodes()) {
					Log.info("Parsing the price..." + this.id + " " + ROOT_URL);
					node = it.nextNode();
					String[] target = { "strong" };
					IcsonImageTagVistor v = new IcsonImageTagVistor(id, target);
					node.accept(v);

					if (v.isSuccess()) {
						this.imageURL = v.getPriceURL();
						this.imageData = v.getImageData();
					}

				}
			} catch (ParserException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public String getCharSet() {
		// TODO Auto-generated method stub
		return CONTENT_CHARSET;
	}
}
