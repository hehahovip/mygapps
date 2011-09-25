/**
 * 
 */
package com.hehaho.metalpriceinfofetcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Kevin.Wang
 *
 */
public class HTMLPageFileReader {

	public static String getISharesSilverPageContent(){
		return readFile(SiteHTMLWriter.ISharesSilverFileName, "");
	}
	
	public static String getSPDRGoldPageContent(){
		return readFile(SiteHTMLWriter.SPDRGoldFileName, "");
	}
	
	public static String readFile(String fileName, String date){
		File file = new File("./testdata/" + fileName + date + SiteHTMLWriter.FileExtend);
		FileReader fr;
		StringBuffer content = new StringBuffer();
		String temp = null;
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			
			while((temp = reader.readLine()) != null){
				content.append(temp);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content.toString();
	}

}
