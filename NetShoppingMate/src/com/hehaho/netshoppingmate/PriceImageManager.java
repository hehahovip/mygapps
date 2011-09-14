/**
 * 
 */
package com.hehaho.netshoppingmate;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hehaho.netshoppingmate.utils.BytesUtils;

/**
 * @author Kevin.Wang
 *
 */
public class PriceImageManager {

	private static Logger Log = Logger.getLogger(PriceImageManager.class.getName());
	
	public PriceImageManager() {
		super();
	}

	public byte[] getImage(String id, String url, String filetype){
		Log.info("image url = " + url);
		try {
			Log.info("Status code : " 	+ " when get Product Price Image.");

			URL _url = new URL(url);
			URLConnection uc = _url.openConnection();
			uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-GB; rv:1.9.2.9) Gecko/20100824 Firefox/3.6.9");
			BufferedInputStream reader = new BufferedInputStream(uc.getInputStream());
			
			byte[] data = BytesUtils.getBytesFromInputStream(reader);
			
			return data;
			
		} catch (IOException e) {
//			Log.severe(e.getMessage());
			Log.log(Level.SEVERE, "Error in getImage...", e);
		}
		return null;
	}
	
	public void writeImageFile(String filename, byte[] img){
		
		FileOutputStream os;
		try {
			Log.info("Write file : " + filename);
			os = new FileOutputStream(filename);
			os.write(img);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
	}
	
}
