/**
 * 
 */
package com.hehaho.googleapp.demo.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kevin.Wang
 * 
 */
public class BytesUtils {

	private static Logger log = Logger.getLogger(BytesUtils.class.getName());

	public static final int FETCH_SIZE = 1024;

	public static byte[] getBytesFromInputStream(
			BufferedInputStream reader) throws IOException {
		byte[] result = null;

		byte[] bytes = new byte[FETCH_SIZE];
		int len;
		int cached_len = 0;
		int count = 0;
		if (reader != null) {
			List<byte[]> cache = new ArrayList<byte[]>();
			while ((len = reader.read(bytes)) > 0) {
				byte[] temp = new byte[len];
				for(int i = 0 ; i < len; i++){
					temp[i] = bytes[i];
				}
				cache.add(temp);
				cached_len = len;
				count += len;
			}
			try{
				reader.close();
			} catch(IOException e){
				log.log(Level.SEVERE, "Closing input stream error!", e);
			}
				
			log.info("total bytes : " + count);
			int total = FETCH_SIZE * (cache.size() - 1) + cached_len;
			result = new byte[count];
			log.info("cached_len bytes :" + cached_len);
			log.info("read bytes :" + total);

			int index = 0;
			for (int i = 0; i < cache.size(); i++) {
				byte[] temp = cache.get(i);

				for (int j = 0; j < temp.length; j++) {
					result[index++] = temp[j];
				}
			}
		}

		return result;
	}

}
