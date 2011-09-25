/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.hehaho.metalpriceinfofetcher.bean.MetalPriceBean;
import com.hehaho.metalpriceinfofetcher.impl.htmltagvistors.MetalPriceInfoTagFindingVisitor;
import com.hehaho.metalpriceinfofetcher.impl.htmltagvistors.isharesilver.ISharesSiliverTagFindingVistor;

/**
 * @author Kevin.Wang
 *
 */
public class IShareSilverPriceFetcher extends AbstractMetalPriceFetcher {
	
	private static Logger Log = Logger.getLogger(IShareSilverPriceFetcher.class.toString());
	
	private Parser parser;
	
	private MetalPriceBean metalBean = new MetalPriceBean();
	
	public static final String CSS_CLASS_STYLE = "module overview-profile";
	
	public static final String SITE_URL = "http://us.ishares.com/product_info/fund/overview/SLV.htm";

	/* (non-Javadoc)
	 * @see com.hehaho.metalpriceinfofetcher.MetalPriceFetcher#getMetalInfo()
	 */
	@Override
	public MetalPriceBean getMetalInfo() {
		return metalBean;
	}

	/* (non-Javadoc)
	 * @see com.hehaho.metalpriceinfofetcher.impl.AbstractMetalPriceFetcher#getPageContent(java.lang.String)
	 */
	@Override
	public String getPageContent(String url) {
		return super.getPageContent(SITE_URL);
	}

	/* (non-Javadoc)
	 * @see com.hehaho.metalpriceinfofetcher.MetalPriceFetcher#parseContent(java.lang.String)
	 */
	@Override
	public void parseContent(String pageContent) {
		if (pageContent != null) {
			try {
				pageParser = new Parser(pageContent);

				NodeIterator it = pageParser.elements();
				Node node = null;
				int count = 0;
				while (it.hasMoreNodes()) {
					Log.info("Parsing the page...");
					node = it.nextNode();
					String[] target = { "table" };
					MetalPriceInfoTagFindingVisitor v= new ISharesSiliverTagFindingVistor(target);
					node.accept(v);
					System.out.println("Node: " + node.toHtml());
					count++;
					
				}
				System.out.println("Count = " + count);
			} catch (ParserException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void parse(String pageContent){
		
		this.parser = Parser.createParser(pageContent, "UTF-8");
		
		AndFilter filter = new AndFilter(new TagNameFilter("table"),
				new HasAttributeFilter("class", "module overview-profile"));
		
		
		List<Node> nodeList = new ArrayList<Node>();
		boolean flag = false;
		int count = 0;
		try {
			NodeList nodes = parser.parse(filter);
			if(nodes.size() ==1){
				
				Node table = nodes.elementAt(0);
				NodeList children = table.getChildren();
				for(int i =0; i < children.size(); i++){
					
					Node node = children.elementAt(i);
					if(node instanceof Tag){
						Tag tag = (Tag)node;
						System.out.println("TagName : " + tag.getTagName());
						if("tbody".equalsIgnoreCase(tag.getTagName()) && count == 0){
							flag = true;
							count++;
							continue;
						}
						
						if("tbody".equalsIgnoreCase(tag.getTagName()) && count ==1){
							System.out.println("break");
							break;
						}
						
						if(flag){
							nodeList.add(node);
						}
					}
					
				}
				
				for(int i = 0; i < nodeList.size(); i++){
					Node trNode = nodeList.get(i);
//					System.out.println(trNode.toHtml());
					if(i == 0){
						// Total Net Assets
						System.out.println(trNode.getLastChild().toPlainTextString());
						metalBean.setTotalNetAsset(trNode.getLastChild().toPlainTextString());
					} else if(i == 4){
						// Ounces of Silver
						System.out.println(trNode.getLastChild().toPlainTextString());
						metalBean.setValueOnOunces(trNode.getLastChild().toPlainTextString());
					} else if(i == 5){
						// Tonnes of Silver
						System.out.println(trNode.getLastChild().toPlainTextString());
						metalBean.setvalueOnTonners(trNode.getLastChild().toPlainTextString());
					} else if(i == 10){
						// Price & Date
						this.parsePriceNode(trNode);
					}
				}
			}
			
			
		} catch (ParserException e) {
			
			e.printStackTrace();
		}
	}

	private void parsePriceNode(Node node){
		NodeList list = node.getChildren();
		
		for(int i = 0; i < list.size(); i++){
			if(i == 1){
				// date
				Node dateNode = list.elementAt(i);
				String plainString = dateNode.toPlainTextString();
				
				
				String dateString = plainString.substring(11);
				System.out.println(dateString);
				metalBean.setDate(dateString.trim());
			} else if(i == 3){
				// price
				// TODO: we didn't need this info urgently, implement this lately.
				Node price = list.elementAt(i);
				System.out.println(price.toPlainTextString());
			}
		}
	}

}
