/**
 * 
 */
package com.hehaho.metalpriceinfofetcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Kevin.Wang
 *
 */
public class SiteHTMLWriter {

	public static final String ISharesSilverFileName = "ISharesSilver";
	public static final String ISILVER_PAGE_URL = "http://us.ishares.com/product_info/fund/overview/SLV.htm";

	public static final String SPDRGoldFileName = "SPDRGold";
	public static final String SPDRGoldShares_PAGE_URL = "http://www.spdrgoldshares.com/#usa";
	
	
	public static final String FileExtend = ".html";
	
	
	public void writeSilverFile(String url, String date){

		this.writeFile(url, ISharesSilverFileName, date);
	}
	
	public void writeGoldFile(String url, String date){
		this.writeFile(url, SPDRGoldFileName, date);
	}
	
	public void writeFile(String url, String fileName, String date){
		String content = this.getPageContent(url);

		this.writeFile("./testdata/" + fileName + date + FileExtend, content);
	}
	
	private void writeFile(String fileName, String content){
		File file = new File(fileName);
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getPageContent(String url){
		String pageContent = null;

		try {
			URL _url = new URL(url);
			
			URLConnection uc = _url.openConnection();
			uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-GB; rv:1.9.2.9) Gecko/20100824 Firefox/3.6.9");
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            pageContent = result.toString();

		} catch (IOException e) {
		}
		return pageContent;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SiteHTMLWriter writer = new SiteHTMLWriter();
//		writer.writeSilverFile(ISILVER_PAGE_URL, "20110925");
		writer.writeGoldFile(SPDRGoldShares_PAGE_URL, "20110925");
	}

}
