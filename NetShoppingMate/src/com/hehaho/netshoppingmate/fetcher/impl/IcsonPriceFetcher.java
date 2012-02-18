/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl;

import java.util.logging.Logger;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.ParserException;

import com.hehaho.netshoppingmate.Constants;
import com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.icson.IcsonImageTagVistor;
import com.hehaho.netshoppingmate.utils.URLUtils;

/**
 * @author Kevin.Wang
 *
 */
public class IcsonPriceFetcher extends AbstractProductPriceFetcher {

	public static String BASE_FETCH_URL = "http://item.51buy.com/item-";
	
	public static String ROOT_URL = "http://www.51buy.com/";
	
	public static String PAGE_EXTEND_NAME = "html";
	
	public static final String CONTENT_CHARSET = "gb2312";
	
	public static class IDs{
		
		public static String PRICE_IMG_CLASS_NAME = "price_font";	
		
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
	
	/* (non-Javadoc)
	 * @see com.hehaho.netshoppingmate.fetcher.impl.AbstractProductPriceFetcher#getIDbyURL(java.lang.String)
	 */
	@Override
	public String getIDbyURL(String url) {
		String result = null;
		if(url == null || "".equalsIgnoreCase(url.trim())){
			return null;
		}
		
		if(url != null && url.startsWith(Constants.HTTP_PREFIX)){
			String id = null;
			if(url != null){
				String[] parts = url.split("/");
				
				String last = parts[parts.length -1];
				
				String[] idString = last.split("\\.");
				
				String[] item = idString[0].split("-");
				result = item[1];
			}
		}
		
		return result;
	}


	@Override
	public String getCharSet() {
		return CONTENT_CHARSET;
	}
}
