
package com.rtmap.utils.security;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * SignUtil 签名工具类
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/22/16 15:03
 * @since 2.0.0
 */
public class SignUtil {

    private final static String CHARSET = "UTF-8";

    /**
     * 检测Map签名是否正确
     *
     * @param resultMap 需要检测的签名Map
     * @param key       密钥
     * @return
     */
    public static boolean checkMapSign(Map<String, Object> resultMap, String key) {
        Object signFromAPIResponse = resultMap.get("sign");
        if(signFromAPIResponse == null || signFromAPIResponse.equals("")){
            return false;
        }
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        resultMap.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = getMapSign(resultMap , key);

        return signForAPIResponse.equals(signFromAPIResponse);
    }

    /**
     * XML to Map
     * @param result    XML字符串
     * @return
     * @throws RtmapInvalidException
     */
    public static Map<String, Object> xmlToMap(String result) throws RtmapInvalidException {
        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        try {
            builder = factory.newDocumentBuilder();
            InputStream is =  stringToStream(result);
            document = builder.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RtmapInvalidException(String.format("解析Dom出错 , 错误原因:%s" , e.getMessage()) , e);
        }
        if(document == null) throw new RtmapInvalidException("document 不能为空");
        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, Object> map = new HashMap<String, Object>();
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return map;
    }

    /**
     * Map转XML
     *
     * @param map   需要转换的Map集合
     * @return
     */
    public static String mapToXML(Map<String, Object> map) {
        final StringBuilder builder = new StringBuilder("<xml>");
        for(Map.Entry<String,Object> entry : map.entrySet()){
            if(entry.getValue() != null){
                builder.append("<").append(entry.getKey()).append(">");
                if(entry.getValue() instanceof String){
                    builder.append("<![CDATA[").append(entry.getValue()).append("]]>");
                }else{
                    builder.append(entry.getValue());
                }
                builder.append("</").append(entry.getKey()).append(">");
            }
        }
        builder.append("</xml>");
        return builder.toString();
    }

    /**
     * 对Map集合签名
     *
     * @param map   需要签名的Map集合
     * @param key   密钥
     * @return
     */
    public static String getMapSign(Map<String, Object> map, String key){
        map.remove("sign");
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=null && !entry.getValue().equals("")){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        result = MD5Utils.getMD5String(result).toUpperCase();
        return result;
    }

    /**
     * String to ByteArrayInputStream
     * @param string    需要转换的字符串
     * @return
     */
    public static InputStream stringToStream(String string) {
        ByteArrayInputStream tInputStringStream = null;
        if (string != null && !string.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(string.getBytes());
        }
        return tInputStringStream;
    }

    /**
     * 解析响应报文
     *
     * @param in    输入流
     * @return
     * @throws IOException
     */
    public static String inputStreamToString(InputStream in)
            throws IOException {
        StringBuilder out = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(in, CHARSET);
        char[] buffer = new char[4096];
        int bytesRead;
        while ((bytesRead = reader.read(buffer)) != -1) {
            out.append(buffer, 0, bytesRead);
        }
        return out.toString();
    }

}
