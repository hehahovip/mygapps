/**
 * 
 */
package com.hehaho.googleapp.demo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.hehaho.googleapp.demo.bean.Item;
import com.hehaho.googleapp.demo.service.HTMLMailService;
import com.hehaho.netshoppingmate.fetcher.ProductPriceFetcher;
import com.hehaho.netshoppingmate.fetcher.impl.Buy360PriceFetcher;
import com.hehaho.netshoppingmate.fetcher.impl.IcsonPriceFetcher;
import com.hehaho.netshoppingmate.fetcher.impl.NeweggPriceFetcher;

/**
 * @author Kevin.Wang
 *
 */
public class SendHardwareHTMLMailServlet extends HttpServlet{

	public static String NEXT_LINE = "\n";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MY_MAIL_ADDRESS = "yingkang.wang@gmail.com";
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		//super.service(arg0, arg1);
		
		List<String> toList = new ArrayList<String>();
		toList.add(MY_MAIL_ADDRESS);

		String htmlText = null;
		
		List<Item> list = this.initData();
		list = this.processing(list);
		htmlText = this.getHTMLMail(list);
		
//		System.out.println("html : " + htmlText);
		
		HTMLMailService.sendMail(MY_MAIL_ADDRESS, toList, "PC Hardware Price List", htmlText);
		
	}
	
	public String getHTMLMail(List<Item> list){
		StringBuffer html = new StringBuffer();
		
		html.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01//EN. 'http://www.w3.org/TR/html4/strict.dtd'>").append(NEXT_LINE);
		html.append("<html>		<head>		<title>Price List</title></head>").append(NEXT_LINE);
		html.append("<body>").append(NEXT_LINE);
		html.append("<table>").append(NEXT_LINE);
		
		html.append("<tr><td>产品名称</td><td>京东价</td><td>易讯价</td><td>新蛋价</td></tr>").append(NEXT_LINE);
		for(Item item : list){
			html.append("<tr>").append(NEXT_LINE);
			html.append("<td>" + item.getName() + "</td>").append(NEXT_LINE);
				
			if(item.getUrl_price_360buy() != null){
				html.append("<td><img src='" + item.getUrl_price_360buy() + "' border='0' alt=''></td>").append(NEXT_LINE);
			} else {
				html.append("<td>无</td>").append(NEXT_LINE);
			}
				
			if(item.getUrl_price_icson() != null){
				html.append("<td><img src='" + item.getUrl_price_icson() + "' border='0' alt=''></td>").append(NEXT_LINE);
			} else {
				html.append("<td>无</td>").append(NEXT_LINE);
			}
				
			if(item.getUrl_price_newegg() != null){
				html.append("<td><img src='" + item.getUrl_price_newegg() + "' border='0' alt=''></td>").append(NEXT_LINE);
			} else{
				html.append("<td>无</td>").append(NEXT_LINE);
			}

			html.append("</tr>").append(NEXT_LINE);
		}
		
		
		html.append("</table>").append(NEXT_LINE);
		html.append("</body></html>").append(NEXT_LINE);
		
		return html.toString();
	}
	
	public List<Item> processing(List<Item> list){
		for(Item item : list){
			ProductPriceFetcher newegg = new NeweggPriceFetcher();
			ProductPriceFetcher buy360 = new Buy360PriceFetcher();
			ProductPriceFetcher icson = new IcsonPriceFetcher();
			
			newegg.getPrice(item.getUrl_newegg());
			item.setUrl_price_newegg(newegg.getImageURL());
			
			buy360.getPrice(item.getUrl_360buy());
			item.setUrl_price_360buy(buy360.getImageURL());
			
			icson.getPrice(item.getUrl_icson());
			item.setUrl_price_icson(icson.getImageURL());
		}
		
		return list;
	}
	
	public List<Item> initData(){
		List<Item> list = new ArrayList<Item>();
		
		Item item1 = new Item();
		item1.setUrl_360buy("http://www.360buy.com/product/200803.html");
		item1.setUrl_icson("http://www.icson.com/Products/60827.html");
		item1.setUrl_newegg("http://www.newegg.com.cn/Product/19-c32-131.htm");
		item1.setName("AMD 速龙AthlonII X3 440(3.0GHz/AM3/3*512KB二缓/45纳米)");
		list.add(item1);
		
		Item item2 = new Item();
		item2.setUrl_360buy("http://www.360buy.com/product/209187.html");
		item2.setUrl_icson("http://www.icson.com/Products/68057.html");
		item2.setUrl_newegg("http://www.newegg.com.cn/Product/13-c28-085.htm");
		item2.setName("映泰（Biostar）TA890GXE主板（AMD 890GX/Socket AM3)");
		list.add(item2);
		
		Item item3 = new Item();
		item3.setUrl_360buy("http://www.360buy.com/product/184066.html");
		item3.setUrl_icson("http://www.icson.com/Products/49123.html");
		item3.setUrl_newegg("http://www.newegg.com.cn/Product/25-c06-045.htm");
		item3.setName("V-DATA 威刚 万紫千红 DDR3 1333 2GB");
		list.add(item3);
		
		Item item4 = new Item();
		item4.setUrl_360buy("http://www.360buy.com/product/143076.html");
		item4.setUrl_icson("http://www.icson.com/Products/100227.html");
		item4.setUrl_newegg("http://www.newegg.com.cn/Product/25-c02-140.htm");
		item4.setName("Kingston 金士顿 DDR3 1333 2GB 台式机内存（宽版）");
		list.add(item4);
		
		return list;
	}

}
