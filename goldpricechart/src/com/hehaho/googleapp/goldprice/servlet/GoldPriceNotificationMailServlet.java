/**
 * 
 */
package com.hehaho.googleapp.goldprice.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hehaho.googleapp.goldprice.bean.GoldBean;
import com.hehaho.googleapp.goldprice.bean.SilverBean;
import com.hehaho.googleapp.goldprice.jdo.GoldSilverBeanDBService;
import com.hehaho.googleapp.goldprice.jdo.GoldSilverBeanJDOServiceImpl;
import com.hehaho.googleapp.goldprice.mailservice.HTMLMailService;
import com.hehaho.googleapp.goldprice.utils.DateUtils;
import com.hehaho.metalpriceinfofetcher.bean.MetalPriceBean;
import com.hehaho.metalpriceinfofetcher.impl.IShareSilverPriceFetcher;
import com.hehaho.metalpriceinfofetcher.impl.SPDRGoldPriceFetcher;

/**
 * @author Kevin.Wang
 *
 */
public class GoldPriceNotificationMailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String MY_MAIL_ADDRESS = "yingkang.wang@gmail.com";
	public static String NEXT_LINE = "\n";
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
//		super.service(arg0, arg1);
		
		Date now = new Date();
		String nowDate = DateUtils.formatDate(now);
		
		IShareSilverPriceFetcher silverFetcher = new IShareSilverPriceFetcher();
		String content = silverFetcher.getPageContent();
		silverFetcher.parseContent(content);
		MetalPriceBean silver = silverFetcher.getMetalInfo();
		silver.setDate(nowDate);
		
		SPDRGoldPriceFetcher goldFetcher = new SPDRGoldPriceFetcher();
		String goldContent = goldFetcher.getPageContent();
		goldFetcher.parseContent(goldContent);
		MetalPriceBean gold = goldFetcher.getMetalInfo();
		gold.setDate(nowDate);
		
		SilverBean silverBean = new SilverBean(silver);
		GoldBean goldBean = new GoldBean(gold);
		
		GoldSilverBeanDBService service = new GoldSilverBeanJDOServiceImpl();
		service.saveSilverBean(silverBean);
		service.saveGoldBean(goldBean);
		
		
		List<String> toList = new ArrayList<String>();
		toList.add(MY_MAIL_ADDRESS);
		
		String htmlText = this.getHTMLMail(goldBean.getMetalPriceBean(), silverBean.getMetalPriceBean());
		
		HTMLMailService.sendMail(MY_MAIL_ADDRESS, toList, "Gold & Silver Trust", htmlText);
		
		arg1.setStatus(HttpServletResponse.SC_OK);
		arg1.getWriter().println("Request finsihed");
	}
	
	public String getHTMLMail(MetalPriceBean gold, MetalPriceBean silver){
		StringBuffer html = new StringBuffer();
		
		html.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01//EN. 'http://www.w3.org/TR/html4/strict.dtd'>").append(NEXT_LINE);
		html.append("<html>		<head>		<title>Gold&Silver Trust</title></head>").append(NEXT_LINE);
		html.append("<body>").append(NEXT_LINE);
		html.append("<table>").append(NEXT_LINE);
		
		html.append("<tr><td>Gold & Silver</td><td>日期</td><td>持仓量（盎司）</td><td>持仓量（吨）</td><td>总价值</td><td>增减（吨）</td></tr>").append(NEXT_LINE);
		html.append("<tr><td> Gold </td>");
		html.append("<td>" + gold.getDate() + "</td>");
		html.append("<td>" + gold.getValueOnOuncesString() + "</td>");
		html.append("<td>" + gold.getValueOnTonnersString() + "</td>");
		html.append("<td>" + gold.getTotalNetAssetString() + "</td>");
		html.append("<td>" + gold.getDiffOnTonnersString() + "</td> </tr>");
		
		html.append("<tr><td> Silver </td>");
		html.append("<td>" + silver.getDate() + "</td>");
		html.append("<td>" + silver.getValueOnOuncesString() + "</td>");
		html.append("<td>" + silver.getValueOnTonnersString() + "</td>");
		html.append("<td>" + silver.getTotalNetAssetString() + "</td>");
		html.append("<td>" + silver.getDiffOnTonnersString() + "</td> </tr>");
		
		html.append("</table>").append(NEXT_LINE);
		html.append("</body></html>").append(NEXT_LINE);
		
		return html.toString();
	}
}
