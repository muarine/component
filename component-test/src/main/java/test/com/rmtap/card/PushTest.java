/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.card;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.http.RestfulTemplate;
import com.rtmap.wx.sdk.exp.AesException;
import com.rtmap.wx.sdk.handler.CardHandler;
import com.rtmap.wx.sdk.sign.SHA1;

import java.io.IOException;

/**
 * TestPush.
 * 
 * @author muarine maoyun@rtmap.com
 * @since 0.1
 */
public class PushTest extends AbstractCardTest {
	
	
	/**
	 * 创建二维码投放券 
	 */
	public void testCreateCode(){
		
		
		
	}
	
	/**
	 * 导入Code
	 */
	public void testImportCode(){
		String card_id = "pWm-rt0pGYVy2XtfsX9A4zNQntlg";
		StringBuffer sb = new StringBuffer();
		sb.append("{").append("\"card_id\":\"").append(card_id).append("\",").append("\"code\":[");
		for (int i = 0; i < 100 ; i++) {
			if(i == 99){
				sb.append("\"").append(generate()).append("\"");
			}else{
				sb.append("\"").append(generate()).append("\",");
			}
		}
		sb.append("]").append("}");
		System.out.println("param:" + sb.toString());
		JsonNode result = RestfulTemplate.INSTANCE.postForObject("http://api.weixin.qq.com/card/code/deposit?access_token=" + access_token , sb.toString() , JsonNode.class)	;
		System.out.println(result);
	}
	
	
	/**
	 * 检查Code列表
	 * @throws IOException 
	 */
	public void testCheckCode() throws IOException{
		//{"card_id":"pWm-rt0pGYVy2XtfsX9A4zNQntlg","code":["011457591553625811","011457591553626419","011457591553627328","011457591553628670","011457591553629224","011457591553630276","011457591553631792","011457591553632991","011457591553634877","011457591553635349","011457591553636983","011457591553637827","011457591553638503","011457591553639392","011457591553640712","011457591553641485","011457591553642313","011457591553643279","011457591553644871","011457591553646359","011457591553647585","011457591553648178","011457591553649677","011457591553650014","011457591553651058","011457591553652902","011457591553653315","011457591553654710","011457591553655202","011457591553656347","011457591553658932","011457591553659645","011457591553660260","011457591553661118","011457591553662665","011457591553663476","011457591553664129","011457591553665411","011457591553666414","011457591553668901","011457591553669267","011457591553670032","011457591553671256","011457591553672051","011457591553673962","011457591553674629","011457591553675971","011457591553676504","011457591553678449","011457591553679532","011457591553680244","011457591553681120","011457591553682409","011457591553683931","011457591553684024","011457591553685291","011457591553686714","011457591553687269","011457591553689117","011457591553690471","011457591553691512","011457591553692269","011457591553693999","011457591553694478","011457591553695921","011457591553696993","011457591553697324","011457591553698573","011457591553699988","011457591553703362","011457591553706353","011457591553707900","011457591553708261","011457591553709112","011457591553710884","011457591553711134","011457591553712076","011457591553713624","011457591553714898","011457591553715145","011457591553716832","011457591553717532","011457591553718215","011457591553719790","011457591553720763","011457591553722335","011457591553723519","011457591553724605","011457591553725311","011457591553726802","011457591553727199","011457591553728330","011457591553729520","011457591553730643","011457591553731115","011457591553732363","011457591553733366","011457591553735778","011457591553736384","011457591553737053"]}
		String param = readFile("/home/muarine/tmp/checkCode.json");
		JsonNode result = RestfulTemplate.INSTANCE.postForObject("http://api.weixin.qq.com/card/code/checkcode?access_token=" + access_token, param, JsonNode.class);
		System.out.println(result.toString());
		// {"errcode":45030,"errmsg":"limit cardid, not support this function hint: [8e9tpa0968ent1]"}
	}
	
	/**
	 * 修改库存
	 */
	public void testModifyStock(){
		String card_id = "pWm-rt0pGYVy2XtfsX9A4zNQntlg";
		Integer increase_stock_value = 100;
		Integer reduce_stock_value = 0;
		String param = "{\"card_id\": \"" + card_id + "\",\"increase_stock_value\": " + increase_stock_value + ",\"reduce_stock_value\": " + reduce_stock_value + "}";
		JsonNode result = RestfulTemplate.INSTANCE.postForObject("https://api.weixin.qq.com/card/modifystock?access_token=" + access_token, param, JsonNode.class);
		
		System.out.println(result.toString());
		// {"errcode":0,"errmsg":"ok"}
	}
	
	/**
	 * 客服消息 投放卡券
	 * 客服消息接口投放卡券仅支持非自定义Code码的卡券
	 * @throws IOException 
	 * @throws AesException 
	 */
	public void testCustomSend() throws IOException, AesException{
		
		String card_id = "pWm-rt0pGYVy2XtfsX9A4zNQntlg";
		String code = "011457591553625811";
		String openid = "oWm-rt5m7DG-uUH3rqgzRImAfzx8";
		Long timestamp = System.currentTimeMillis()/1000;
		
		
		String signature = SHA1.getSHA1("0036404a9c0be3b6fc33f473c1a573de", timestamp+"", "1139775133", "jbdOrl6voivBme9PnO7VOxqWtB0FNcfKhWAA1Lc4xs7");
		
		String param = readFile("/home/muarine/tmp/cardCustomSend.json");
		param = String.format(param, openid, card_id , code , openid , timestamp , signature);
		JsonNode result = RestfulTemplate.INSTANCE.postForObject("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + access_token, param, JsonNode.class);
		
		System.out.println(result.toString());
		
	}
	
	/**
	 * 预览接口
	 * @throws IOException 
	 * @throws AesException 
	 */
	public void testPreview() throws IOException, AesException{
		String card_id = "pWm-rt0pGYVy2XtfsX9A4zNQntlg";
		String code = "011457591553625811";
		String openid = "oWm-rt5m7DG-uUH3rqgzRImAfzx8";
		Long timestamp = System.currentTimeMillis()/1000;
		
		String signature = SHA1.getSHA1("0036404a9c0be3b6fc33f473c1a573de", timestamp+"", "1139775133", "jbdOrl6voivBme9PnO7VOxqWtB0FNcfKhWAA1Lc4xs7");
		
		String param = readFile("/home/muarine/tmp/cardCustomSend.json");
		param = String.format(param, openid , card_id , code , openid , timestamp , signature);
		JsonNode result = RestfulTemplate.INSTANCE.postForObject("https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=" + access_token, param, JsonNode.class);
		System.out.println(result.toString());
	}
	
	/**
	 * 核销自定义code
	 */
	public void testConsumeCode(){
	    
	    JsonNode node = CardHandler.instance.consumeCode(access_token, "pWm-rt5xbJKC7gq5qyFr_Q2kzyWM", "011461150080108274");
	    System.out.println(node.toString());
	    
	}
	
	/**
	 * 查询Code
	 */
	public void testGetCode(){
	    String card_id = "pWm-rt5xbJKC7gq5qyFr_Q2kzyWM";
	    String code = "011461150080108274";
	    JsonNode node = CardHandler.instance.getCode(access_token , card_id , code , false);
	    System.out.println(node.toString());
	    
	}
}
