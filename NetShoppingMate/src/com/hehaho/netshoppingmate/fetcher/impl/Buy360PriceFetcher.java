/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl;

import java.util.logging.Logger;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.ParserException;

import com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.Buy360PriceImageTagVistor;

/**
 * @author Kevin.Wang
 * 
 */
public class Buy360PriceFetcher extends AbstractProductPriceFetcher {

	public static String BASE_FETCH_URL = "http://www.360buy.com/product/";

	public static String PAGE_EXTEND_NAME = "html";

	public static final String CONTENT_CHARSET = "gb2312";

	public static class IDs {

		public static String PRICE_STRONG_CLASS_NAME = "price";

		public static String PRICE_IMG_SRC = "src";
		
		public static String TITLE_DIV_ID = "name";
		
		public static String STOCK_CLASS_ID = "i-storeinfo";
	}

	private static Logger Log = Logger.getLogger(Buy360PriceFetcher.class
			.getName());

	public Buy360PriceFetcher() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hehaho.netshoppingmate.fetcher.ProductPriceFetcher#getProductURL(
	 * java.lang.String)
	 */
	@Override
	public String getProductURL(String id) {
		this.id = id;
		return BASE_FETCH_URL + id + "." + PAGE_EXTEND_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hehaho.netshoppingmate.fetcher.ProductPriceFetcher#parseProductPrice
	 * (java.lang.String)
	 */
	@Override
	public void parseProductPrice(String pageContent) {
		
		if (pageContent != null) {
			try {
				pageParser = new Parser(pageContent);

				NodeIterator it = pageParser.elements();
				Node node = null;

				while (it.hasMoreNodes()) {
					node = it.nextNode();
					Log.info("Parsing the price..." + this.id + " "
							+ BASE_FETCH_URL);
					String[] target = { "strong" };
					Buy360PriceImageTagVistor v = new Buy360PriceImageTagVistor(
							id, target);
					node.accept(v);

					if (v.isSuccess()) {
						this.imageURL = v.getPriceURL();
						this.imageData = v.getImageData();
						this.title = v.getTitle();
					}
					
					String[] target1 = { "div" };
//					Buy360StockInfoTagVistor v1 = new Buy360StockInfoTagVistor(target1);
					/*if(v1.isSuccess()){
						this.stock_info = v1.getStock_info();
					}*/
					
				}

			} catch (ParserException e) {
				e.printStackTrace();
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.hehaho.netshoppingmate.fetcher.ProductPriceFetcher#getCharSet()
	 */
	@Override
	public String getCharSet() {
		return CONTENT_CHARSET;
	}

}
