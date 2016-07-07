package com.rtmap.wx.sdk.sign;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.rtmap.utils.string.StringUtils;
import com.rtmap.wx.sdk.exp.AesException;


/**
 * 微信签名工具类
 * @author maoyun@rtmap.com
 *
 */
public class Sign {
	
    private static final Logger log = LoggerFactory.getLogger(Sign.class);
    
	/**
	 * 验证签名
	 * @param sign
	 * @param token
	 * @param timestamp
	 * @param nonce
	 */
	public static boolean signature(String signature , String token , String timestamp , String nonce){
		if(!StringUtils.isNotBlank(signature,token,timestamp,nonce)){
			return false;
		}
		String [] strs = new String[]{token,timestamp,nonce};
		Arrays.sort(strs);
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(strs[0]).append(strs[1]).append(strs[2]);
		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(buffer.toString().getBytes());// 对接后的字符串进行sha1加密
			ciphertext = byteToString(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将sha1加密后的字符串与  signature 进行比较
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
		
	}
	
	/**
	 * 字节数组转为字符串
	 * @return
	 */
	public static String byteToString(byte[] param){
		String rst = "";
		for (int i = 0; i < param.length; i++) {
			rst += byteToHex(param[i]);
		}
		return rst;
		
	}
	
	/**
	 * 字节转为十六进制
	 * @param param
	 * @return
	 */
	public static String byteToHex(byte param){
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(param >>> 4) & 0X0F];
		tempArr[1] = Digit[param & 0X0F];
		String s = new String(tempArr);
		return s;
		
	}
	
	/**
     * 
     * JSAPI签名
     * 
     * @param jsapi_ticket
     * @param url
     * @return
     */
    public static Map<String, String> sign(String appid,String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
                + "&timestamp=" + timestamp + "&url=" + url;
//      System.out.println(string1);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ret.put("appid", appid);
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
    /**
     * 创建随机数
     * @return
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * 创建时间戳 
     * @return
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    /**
     * 提取出xml数据包中的加密消息
     * @param xmltext 待提取的xml字符串
     * @return 提取出的加密消息字符串
     * @throws AesException 
     */
    public static Object[] extract(String xmltext) throws AesException     {
        Object[] result = new Object[3];
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xmltext);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Encrypt");
//            NodeList nodelist2 = root.getElementsByTagName("AppId");
            result[0] = 0;
            result[1] = nodelist1.item(0).getTextContent();
//          result[2] = nodelist2.item(0).getTextContent();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }
    
    /**
     * 生成xml消息
     * @param encrypt 加密后的消息密文
     * @param signature 安全签名
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @return 生成的xml字符串
     */
    public static String generate(String encrypt, String signature, String timestamp, String nonce) {

        String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
                + "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n"
                + "<TimeStamp>%3$s</TimeStamp>\n" + "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
        return String.format(format, encrypt, signature, timestamp, nonce);

    }

    /**
     * 指定openid领取，非自定义code
     * @param api_ticket    ticket
     * @param timestamp     精确到秒的时间戳
     * @param card_id       卡券id
     * @param code          卡券code
     * @param openid        粉丝openid
     * @param nonce_str     随机数
     */
    public static Map<String,Object> sign(String api_ticket, String timestamp, String card_id, String code, String openid,
            String nonce_str) {
        // 自定义code + 指定用户领取
        String[] arr = null;
        if(openid == null && code == null){
            arr = new String[] { api_ticket, timestamp, card_id , nonce_str };
        }else if(openid == null){
            arr = new String[] { api_ticket, timestamp, card_id , code , nonce_str };
        }else if(code == null){
            arr = new String[] { api_ticket, timestamp, card_id , openid , nonce_str };
        }else{
            arr = new String[] { api_ticket, timestamp, card_id , code , openid , nonce_str };
        }
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        // 自定义code
        String string1 = content.toString();
        System.out.println(string1);
        String signature = null;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("timestamp", timestamp);
        map.put("nonce_str", nonce_str);
        map.put("card_id", card_id);
        map.put("code", code);
        map.put("openid", openid);
        map.put("signature", signature);
        return map;
    }
    
    /**
     * 不指定openid，自定义code
     * @param api_ticket
     * @param timestamp
     * @param card_id
     * @param code
     * @param nonce_str
     * @return
     */
    public static Map<String,Object> signCardNoOpenid(String api_ticket, String timestamp, String card_id, String code,String nonce_str){
        return sign(api_ticket, timestamp, card_id, code, null, nonce_str);
    }
    /**
     * 不指定openid，非自定义code
     * @param api_ticket
     * @param timestamp
     * @param card_id
     * @param nonce_str
     * @return
     */
    public static Map<String,Object> signCardNoOpenidCode(String api_ticket, String timestamp, String card_id,String nonce_str){
        return sign(api_ticket, timestamp, card_id, null , null, nonce_str);
    }
    /**
     * 指定openid，非自定义code
     * @param api_ticket
     * @param timestamp
     * @param card_id
     * @param openid
     * @param nonce_str
     * @return
     */
    public static Map<String,Object> signCardNoCode(String api_ticket, String timestamp, String card_id,String openid,String nonce_str){
        return sign(api_ticket, timestamp, card_id, null , openid, nonce_str);
    }
}
