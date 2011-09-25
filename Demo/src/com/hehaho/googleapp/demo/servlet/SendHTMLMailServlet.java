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
public class SendHTMLMailServlet extends HttpServlet{

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
		
		HTMLMailService.sendMail(MY_MAIL_ADDRESS, toList, "TEST HTML Mail", htmlText);
		
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
		item1.setUrl_360buy("http://www.360buy.com/product/146147.html");
		item1.setUrl_icson("");
		item1.setUrl_newegg("http://www.newegg.com.cn/Product/98-C01-148.htm");
		item1.setName("红心挂烫机 RH2025");
		list.add(item1);
		
		Item item2 = new Item();
		item2.setUrl_360buy("http://www.360buy.com/product/120535.html");
		item2.setUrl_icson("http://www.icson.com/Products/58570.html");
		item2.setUrl_newegg("");
		item2.setName("西门子C670（Siemens）2.4G数字无绳电话子母机（白色");
		list.add(item2);
		
		Item item3 = new Item();
		item3.setUrl_360buy("http://www.360buy.com/product/177010.html");
		item3.setUrl_icson("http://www.icson.com/Products/54907.html");
		item3.setUrl_newegg("");
		item3.setName("Galanz 格兰仕 G80F23CSP-Q5(RO)");
		list.add(item3);
		
		
		return list;
	}

}
