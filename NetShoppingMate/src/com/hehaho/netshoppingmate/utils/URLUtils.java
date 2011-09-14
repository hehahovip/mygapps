/**
 * 
 */
package com.hehaho.netshoppingmate.utils;

/**
 * @author Kevin.Wang
 * 
 */
public class URLUtils {

	public static String SPACE_SHIFT = "%20";
	
	public static String SPACE = " ";

	public static String replaceSpace(String url){
		return url.replace(SPACE, SPACE_SHIFT);
	}
	
	/**
	 * Get the item's id from the URL.
	 * eg: http://www.360buy.com/product/224873.html return : 224873
	 * 
	 * @param url
	 * @return
	 */
	public static String getIDbyURL(String url){
		String id = null;
		if(url != null){
			String[] parts = url.split("/");
			
			String last = parts[parts.length -1];
			
			String[] idString = last.split("\\.");
			id = idString[0];
		}
		
		return id;
	}
	
	public static String getFileNamebyURL(String url){
		String filename = null;
		if(url != null){
			String[] parts = url.split("/");
			filename = parts[parts.length -1];
		}
		
		return filename;
	}
	
	public static String getImageTypebyURL(String url){
		String type = null;
		if(url != null){
			String[] parts = url.split("/");
			
			String last = parts[parts.length -1];
			
			String[] idString = last.split("\\.");
			type = idString[idString.length -1];
		}
		
		return type;
	}

}
