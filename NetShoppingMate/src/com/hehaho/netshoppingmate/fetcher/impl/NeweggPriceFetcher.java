/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl;

import java.util.logging.Logger;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.ParserException;

import com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.newegg.NeweggPriceImageTagVistor;

/**
 * @author Kevin.Wang
 *
 */
public class NeweggPriceFetcher extends AbstractProductPriceFetcher {

	public static String BASE_FETCH_URL = "http://www.newegg.com.cn/Product/";
	
	public static String ROOT_URL = "http://www.newegg.com.cn";
	
	public static String PAGE_EXTEND_NAME = "htm";
	
	public static final String CONTENT_CHARSET = "gb2312";
	
	public static class IDs{
		
		public static String PRICE_IMG_CLASS_NAME = "neweggPrice";	
		
		public static String PRICE_IMG_SRC = "src";
	}
	
	private static Logger Log = Logger.getLogger(NeweggPriceFetcher.class.getName());
	
	public NeweggPriceFetcher() {
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
			
		if(pageContent != null){
			try {
				pageParser = new Parser(pageContent);
				
				NodeIterator it = pageParser.elements();
				Node node = null;
				
				while (it.hasMoreNodes()) {
					node = it.nextNode();
					Log.info("Parsing the price..." + this.id + " " + ROOT_URL);
					String[] target = { "dd" };
					NeweggPriceImageTagVistor v = new NeweggPriceImageTagVistor(id, target);
					node.accept(v);
					
					if(v.isSuccess()){
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
