/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.card;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.http.HttpClient;
import com.rtmap.utils.http.RestfulTemplate;
import com.rtmap.wx.sdk.handler.CardHandler;

import java.io.File;
import java.io.IOException;

/**
 * CreateCard.	创建卡券相关接口	单元测试
 * 
 * @author muarine maoyun@rtmap.com
 * @since 0.1
 */
public class CreateCardTest extends AbstractCardTest {
	
	/**
	 * 上传卡券logo
	 * @throws IOException 
	 */
	public void testUploadLogo() throws IOException{
		
		File file = new File("/home/muarine/tmp/images/card_intro_big29bc6c.jpg");
		
		String params = "buffer=@card_intro_big29bc6c.jpg ";
		
		String result = HttpClient.uploadMedia("https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + access_token, file, params);
		
		System.out.println("上传卡券logo返回：" + result);
		
		// 上传卡券logo返回：{"url":"http:\/\/mmbiz.qpic.cn\/mmbiz\/M4KMicPnIicSb8cdCajbTgYibggcat9ibjTtjA0ChnJIVEia1cO3p7pqIxjWA9Pj8Jg3icckUBkxiaQ6MA83d5iaL5lTSg\/0"}
	}
	
	public void testUploadURLImage() throws IOException{
	    
	    JsonNode node = CardHandler.instance.uploadLogo(access_token, "http://banbao.chazidian.com/uploadfile/2016-01-25/s145368924044608.jpg");
	    
	    System.out.println(node.toString());
	    
	}
	
	/**
	 * 创建门店 
	 */
	public void testCreatePoi(){
		
		// http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=TOKEN
		String param = "{\"business\":{"
						+ "\"base_info\":{"
							+ "\"sid\":\"292350695\","
							+ "\"business_name\":\"麦当劳\","
							+ "\"branch_name\":\"大仓木胡同\","
							+ "\"province\":\"北京市\","
							+ "\"city\":\"北京市\","
							+ "\"district\":\"西城区\","
							+ "\"address\":\"大仓木胡同\","
							+ "\"telephone\":\"020-12345678\","
							+ "\"categories\":[\"美食,小吃快餐\"],"
							+ "\"offset_type\":1,"
							+ "\"longitude\":110.32375,"
							+ "\"latitude\":20.097486,"
							+ "\"photo_list\":["
									+ "{\"photo_url\":\"http://mmbiz.qpic.cn/mmbiz/M4KMicPnIicSb8cdCajbTgYibggcat9ibjTtjA0ChnJIVEia1cO3p7pqIxjWA9Pj8Jg3icckUBkxiaQ6MA83d5iaL5lTSg/0\"}"
								+ "],"
							+ "\"recommend\":\"麦辣鸡腿堡套餐，全家桶\","
							+ "\"special\":\"免费wifi，外卖服务\","
							+ "\"introduction\":\"麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、 水果等快餐食品\","
							+ "\"open_time\":\"8:00-20:00\","
							+ "\"avg_price\":35"
							+ "}"
					+ "}"
				+ "}";
		System.out.println(param);
		JsonNode result = RestfulTemplate.INSTANCE.postForObject("http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=" + access_token, param, JsonNode.class);
		
		// 查询某个门店
		
		System.out.println(result.toString());
	}
	
	/**
	 * 创建卡券
	 * @throws IOException 
	 */
	public void testCreate() throws IOException{
		
		// https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN
		
		String buffer = this.readFile("/home/muarine/tmp/card.json");
		
		System.out.println(buffer);
		JsonNode node = RestfulTemplate.INSTANCE.postForObject("https://api.weixin.qq.com/card/create?access_token=" + access_token, buffer.toString(), JsonNode.class);
		System.out.println(node.toString());
		//	demo 返回值：{"errcode":0,"errmsg":"ok","card_id":"pWm-rt_m55yVxbToRkM7FGXPxCVs"}
		//  自定义Code优惠券 返回值：{"errcode":0,"errmsg":"ok","card_id":"pWm-rt0pGYVy2XtfsX9A4zNQntlg"}
	}
	
	/**
	 * 设置领取卡券白名单,优惠券在审核时也可领取核销 
	 */
	public void testSetWhiteList(){
		
		//https://api.weixin.qq.com/card/testwhitelist/set?access_token=TOKEN
		String param = "{\"openid\":[\"oWm-rt5m7DG-uUH3rqgzRImAfzx8\"],\"username\":[\"maoyun19900818\"]}";
		JsonNode result = RestfulTemplate.INSTANCE.postForObject("https://api.weixin.qq.com/card/testwhitelist/set?access_token=" + access_token, param, JsonNode.class);
		
		System.out.println(result.toString());
	}
	
	
	

}
