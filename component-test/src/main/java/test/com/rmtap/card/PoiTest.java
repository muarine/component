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

/**
 * TestPoi. 微信门店 		单元测试
 * 
 * @author muarine maoyun@rtmap.com
 * @since 0.1
 */
public class PoiTest extends AbstractCardTest {
	
	/**
	 * 上传门店图片
	 */
	public void testUploadImage(){
		
//		HttpClient.uploadMedia("", file, params)
		
	}
	
	/**
	 * 创建门店
	 */
	public void testCreate(){
		
		
		
	}
	/**
	 * 查询某个门店
	 */
	public void testSelectOne(){
		// http://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=TOKEN
		String param = "{\"poi_id\":\"292350692\"}";
		JsonNode node = RestfulTemplate.INSTANCE.postForObject("http://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=" + access_token , param , JsonNode.class);
		System.out.println(node.toString());
		
		// {"errcode":0,"errmsg":"ok","business":{"base_info":{"sid":"","business_name":"致真大厦","branch_name":"","address":"知春路7","telephone":"18910715931","categories":["公司企业,企业/工厂"],"city":"北京市","province":"","offset_type":1,"longitude":116.351882935,"latitude":39.9772148132,"photo_list":[{"photo_url":"http://mmbiz.qpic.cn/mmbiz/M4KMicPnIicSZ0X1rJ2CdtO5c61CTJH6FI4rkKdH1VGpibN67ZGjBghStEZ6gTjY06saqtzfUGPGpL7CjXaGicgS8g/0?wx_fmt=png"}],"introduction":"北京智慧图科技有限责任公司","recommend":"室内定位、室内地图、大数据","special":"微信生态开发，wifi，ibeacon","open_time":"9:00-18:30","avg_price":0,"poi_id":"292350692","available_state":3,"district":"海淀区","update_status":1}}}
	}
	/**
	 * 查询门店列表
	 */
	public void testSelectList(){
		
		//https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token=TOKEN
		
	}
	
	/**
	 * 更新数据
	 */
	public void testUpdate(){
		
		//	https://api.weixin.qq.com/cgi-bin/poi/updatepoi?access_token=TOKEN
		
	}
	
	/**
	 *	删除门店
	 */
	public void testDelete(){
		
		//	https://api.weixin.qq.com/cgi-bin/poi/delpoi?access_token=TOKEN
		
	}
	
	/**
	 *	查询门店类目表
	 */
	public void testCategory(){
		
		//	http://api.weixin.qq.com/cgi-bin/poi/getwxcategory?access_token=TOKEN
		
	}
	
}
