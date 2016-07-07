package com.rtmap.utils.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * 校验URL是否为有效的链接
 * 
 * @author hexuan
 *
 */
public class CheckUrlUtil {
    
    /**
     * 请求地址是否有效
     * @param url
     * @return
     */
	public static boolean isEffectiveUrl(String url) {
		boolean flag = false;
		if (null == url || url.length() <= 0) {
			return flag;
		}
		//网络原因循环调用3次
		try {
			HttpURLConnection.setFollowRedirects(false);
		    HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		    con.setRequestMethod("HEAD");
		    con.setConnectTimeout(2000); //set timeout to 2 seconds
			int state = con.getResponseCode();
			if (state == 200) {
				flag = true;
			}
		} catch (Exception e) {
		}
		return flag;
	}
	
	/**
	 * 
	 *  
	 * @param url
	 * @return
	 */
	public static boolean isImageUrl(String url){
	    Pattern pattern = Pattern.compile("^.*?\\.(jpg|jpeg|bmp|gif|png)$"); 
        return pattern.matcher(url).matches();   
	}
}
