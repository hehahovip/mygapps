/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.hehaho.metalpriceinfofetcher.bean.MetalPriceBean;

/**
 * @author Kevin.Wang
 * 
 * The data information isn't included in the static html page,
 * they are called by ajax. So, we need to parse xml data by http request.
 * 
 * The ajax call path is http://www.spdrgoldshares.com/ajax/home/.
 * 
 */
public class SPDRGoldPriceFetcher extends AbstractMetalPriceFetcher {

	private static Logger Log = Logger.getLogger(SPDRGoldPriceFetcher.class.toString());

	private MetalPriceBean metalBean = new MetalPriceBean();

	public static final String SITE_URL = "http://www.spdrgoldshares.com/ajax/home/";

	public static final String TONNERS_ID = "ajaxTotalTonnes";

	public static final String OUNCES_ID = "ajaxTotalOunces";

	public static final String VALUE_US = "ajaxTotalValue";

	public static final String PRICE_ID = "ajaxUSD";
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hehaho.metalpriceinfofetcher.MetalPriceFetcher#getMetalInfo()
	 */
	@Override
	public MetalPriceBean getMetalInfo() {
		return metalBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hehaho.metalpriceinfofetcher.MetalPriceFetcher#parseContent(java.
	 * lang.String)
	 */
	@Override
	public void parseContent(String pageContent) {
		if (pageContent != null) {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser;
			try {
				saxParser = factory.newSAXParser();
				XMLReader parser = saxParser.getXMLReader();
				parser.setContentHandler(new SDPRXMLHandler(metalBean));
				parser.parse(new InputSource(new StringReader(pageContent)));
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (SAXException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			super.pageParser = Parser.createParser(pageContent, "UTF-8");

			AndFilter tonnerFilter = new AndFilter(new TagNameFilter(TONNERS_ID), new HasAttributeFilter());
			AndFilter ounceFilter = new AndFilter(new TagNameFilter(OUNCES_ID), new HasAttributeFilter());
			AndFilter valueFilter = new AndFilter(new TagNameFilter(VALUE_US), new HasAttributeFilter());
			AndFilter priceFilter = new AndFilter(new TagNameFilter(PRICE_ID), new HasAttributeFilter());

			NodeFilter[] filters = new NodeFilter[] { tonnerFilter, ounceFilter, valueFilter, priceFilter };

			OrFilter allFilter = new OrFilter(filters);
			
			try{
				NodeList nodes = pageParser.parse(allFilter);
				
				for(int i = 0 ; i < nodes.size(); i++){
					Node node = nodes.elementAt(i);
					Tag self = (Tag)node;
					if(TONNERS_ID.equals(self.getTagName())){
						metalBean.setvalueOnTonners(self.toPlainTextString());
					} else if(OUNCES_ID.equals(self.getTagName())){
						metalBean.setValueOnOunces(self.toPlainTextString());
					} else if(VALUE_US.equals(self.getTagName())){
						metalBean.setTotalNetAsset(self.toPlainTextString());
					} else if(PRICE_ID.equals(self.getTagName())){
						metalBean.setClosedPrice(self.toPlainTextString());
					} else{
						Log.warning("Unexcepted tag: " + self.toHtml());
					}
				}
			} catch (ParserException e) {
				Log.log(Level.SEVERE, "Error when parsing", e);
			}
		} else {
			Log.severe("Page content is null!!!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hehaho.metalpriceinfofetcher.impl.AbstractMetalPriceFetcher#
	 * getPageContent(java.lang.String)
	 */
	@Override
	public String getPageContent(String url) {
		return super.getPageContent(url);
	}

	/* (non-Javadoc)
	 * @see com.hehaho.metalpriceinfofetcher.MetalPriceFetcher#getPageContent()
	 */
	@Override
	public String getPageContent() {
		return super.getPageContent(SITE_URL);
	}

}

class SDPRXMLHandler implements ContentHandler{

	private StringBuffer accumulator = new StringBuffer();
	
	private MetalPriceBean bean;
	
	
	public SDPRXMLHandler(MetalPriceBean bean) {
		super();
		this.bean = bean;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		accumulator.append(ch, start, length);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(SPDRGoldPriceFetcher.OUNCES_ID.equals(qName)){
			bean.setValueOnOunces(accumulator.toString().trim());
		} else if(SPDRGoldPriceFetcher.TONNERS_ID.equals(qName)){
			bean.setvalueOnTonners(accumulator.toString().trim());
		} else if(SPDRGoldPriceFetcher.PRICE_ID.equals(qName)){
			bean.setClosedPrice(accumulator.toString().trim());
		} else if(SPDRGoldPriceFetcher.VALUE_US.equals(qName)){
			bean.setTotalNetAsset(accumulator.toString().trim());
		} else{
			
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 */
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
	 */
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
	 */
	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 */
	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	 */
	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		accumulator.setLength(0);
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 */
	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
}
