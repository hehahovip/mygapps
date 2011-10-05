/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.hehaho.metalpriceinfofetcher.bean.MetalPriceBean;

/**
 * @author Kevin.Wang
 * 
 */
public class IShareSilverPriceFetcher extends AbstractMetalPriceFetcher {

	private static Logger Log = Logger.getLogger(IShareSilverPriceFetcher.class.toString());

	private MetalPriceBean metalBean = new MetalPriceBean();

	public static final String CSS_CLASS_STYLE = "module overview-profile";

	public static final String SITE_URL = "http://us.ishares.com/product_info/fund/overview/SLV.htm";

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
			super.pageParser = Parser.createParser(pageContent, "UTF-8");

			AndFilter filter = new AndFilter(new TagNameFilter("table"), new HasAttributeFilter("class",
					"module overview-profile"));

			List<Node> nodeList = new ArrayList<Node>();
			boolean flag = false;
			int count = 0;
			try {
				NodeList nodes = pageParser.parse(filter);
				if (nodes.size() == 1) {

					Node table = nodes.elementAt(0);
					NodeList children = table.getChildren();
					for (int i = 0; i < children.size(); i++) {

						Node node = children.elementAt(i);
						if (node instanceof Tag) {
							Tag tag = (Tag) node;
							if ("tbody".equalsIgnoreCase(tag.getTagName()) && count == 0) {
								flag = true;
								count++;
								continue;
							}

							if ("tbody".equalsIgnoreCase(tag.getTagName()) && count == 1) {
								break;
							}

							if (flag) {
								nodeList.add(node);
							}
						}

					}

					for (int i = 0; i < nodeList.size(); i++) {
						Node trNode = nodeList.get(i);
						// System.out.println(trNode.toHtml());
						if (i == 0) {
							// Total Net Assets
							metalBean.setTotalNetAsset(trNode.getLastChild().toPlainTextString());
						} else if (i == 4) {
							// Ounces of Silver
							metalBean.setValueOnOunces(trNode.getLastChild().toPlainTextString());
						} else if (i == 5) {
							// Tonnes of Silver
							metalBean.setvalueOnTonners(trNode.getLastChild().toPlainTextString());
						} else if (i == 10) {
							// Price & Date
							this.parsePriceNode(trNode);
						}
					}
				}

			} catch (ParserException e) {
				Log.log(Level.SEVERE, "Error when parsing", e);
			}
		} else {
			Log.severe("Page content is null!!!");
		}

	}

	private void parsePriceNode(Node node) {
		NodeList list = node.getChildren();

		for (int i = 0; i < list.size(); i++) {
			if (i == 1) {
				// date
				Node dateNode = list.elementAt(i);
				String plainString = dateNode.toPlainTextString();

				String dateString = plainString.substring(11);
				metalBean.setDate(dateString.trim());
			} else if (i == 3) {
				// price
				// TODO: we didn't need this info urgently, implement this
				// lately.
				Node price = list.elementAt(i);
			}
		}
	}

}
