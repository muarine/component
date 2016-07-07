package com.rtmap.utils.string;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author maoyun@rtmap.com
 * @version 1.1.5
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{
	
	/**
	* 判断字符串是否为数字
	* 
	* @param str
	* @return
	* @return boolean    
	* @throws
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
	
	/**
	* 检测字符串数组是否为空
	* 
	* @return boolean
	* @see org.apache.commons.lang.StringUtils.isBlank()
	 */
	public static boolean isNotBlank(String... strs){
		boolean b = true;
		
		for (int i = 0; i < strs.length; i++) {
			if (isBlank(strs[i])) {
				b = false;
				break;
			}
		}
		return b;
	}

    /**
     * 生成UUID
     * <pre>无中划线：af9b6ac1593b483db37688471edfda14</pre>
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    public static void main(String[] args) {
//        System.out.println(generateUUID());
        System.out.println(StringUtils.checkCardColor("Color000"));
        
        
    }

    /**
     * 检测创建时卡券传递的Color参数是否合法 
     * @param color
     * @return
     */
    public static boolean checkCardColor(String color) {
        Pattern pattern = Pattern.compile("Color[0-1][0-9][0-2]"); 
        return pattern.matcher(color).matches();   
    }
    
}
