/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtmap.utils.json.JsonMapper;
import com.rtmap.utils.string.IdGenerator;
import com.rtmap.wx.sdk.api.CardAPI;
import com.rtmap.wx.sdk.handler.CardHandler;
import com.rtmap.wx.sdk.handler.PoiHandler;
import com.rtmap.wx.sdk.model.PostJsonBuilder;
import com.rtmap.wx.sdk.model.card.Abstract;
import com.rtmap.wx.sdk.model.card.MBusiness;
import com.rtmap.wx.sdk.model.card.MCard;
import com.rtmap.wx.sdk.model.card.TextImg;
import com.rtmap.wx.sdk.sign.Sign;

/**
 * TestFreemakerBuild.      卡券、POI创建
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月8日
 * @since 1.0.0
 */
public class TestFreemakerBuild extends TestAbstractCard{
    
    /**
     * 创建POI
     */
    public void testBuildPoi(){
        
        MBusiness business = new MBusiness();
        business.setSid(IdGenerator.generateSid());
        business.setBusiness_name("肯德基");
        business.setBranch_name("yeah");
        business.setProvince("北京市");
        business.setCity("北京市");
        business.setDistrict("海淀区");
        business.setAddress("车公庄西路13号");
        business.setTelephone("0104510123");
        business.setCategories(new String[]{"美食,小吃快餐"});
        business.setLongitude(115.32375);
        business.setLatitude(25.097486);
        business.setAvg_price(30);
        business.setOpen_time("8:00-20:00");
        business.setOffset_type(1);
        List<String> photos = new ArrayList<String>();
        photos.add("https://mmbiz.qlogo.cn/mmbiz/M4KMicPnIicSZ0X1rJ2CdtO5c61CTJH6FIRGK6r5BSicueWto1JOvPtDZByqUuydCrSw1qR7VnSLf3xBUDBrxHibtw/0?wx_fmt=png");
        photos.add("https://mmbiz.qlogo.cn/mmbiz/M4KMicPnIicSZ0X1rJ2CdtO5c61CTJH6FI4rkKdH1VGpibN67ZGjBghStEZ6gTjY06saqtzfUGPGpL7CjXaGicgS8g/0?wx_fmt=png");
        business.setPhotos(photos);
        business.setSpecial("免费wifi，外卖服务");
        business.setIntroduction("麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上"
                + "大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、 水果等快餐食品");
        business.setRecommend("麦辣鸡腿堡套餐，麦乐鸡，全家桶");
        JsonNode node = PoiHandler.instance.createPoi(access_token , business);
        System.out.println(node.toString());
        
    }
    
    /**
     * 代金券
     * @throws IOException 
     * @throws JsonProcessingException 
     */
    public void testBuildCashCard() throws JsonProcessingException, IOException{
        
        MCard card = new MCard().initCash(1000, 1000);
        // baseinfo
        card.setLogo_url("https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFia8GjPEJHicTYzuVTohicbfuabc35U0TUVr4EysTQGCamqUFdyMOnPKoWJsvECKTurttXLh06IlJ0FQ/0?wx_fmt=png");
        card.setCode_type("CODE_TYPE_ONLY_QRCODE");
        card.setBrand_name("西单大悦城测试券");
        card.setTitle("双人100元套餐券");
        card.setColor("Color020");
        card.setNotice("卡券使用提醒");
        card.setDescription("卡券使用说明，字数上限为1024个汉字。");
        card.setSku(0);  // 上限1亿张
        card.setDate_info_Range(1460044800L, 1460736000L);
        
        card.setUse_custom_code(true);
        card.setBind_openid(true);
        card.setService_phone("40012234");
//        card.setLocation_id_list(location_id_list);
        card.setSource("寻鹿");
        card.setGet_limit(1);
        card.setCan_share(false);
        card.setCan_give_friend(false);
        
        // advance  
        card.setUse_condition("快餐", "吉野家", true);
        Abstract ab = new Abstract();
        ab.setAbstract_name("封面摘要简介。");
        ab.setIcon_url_list(new String[]{
                "https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFia9iaV4xpD9oYF5Uf1SODxib5nDt1P1CF0Om9BVYkvzsU1zibzRv8ZE7cLeDibMf6sP5cSt6O1lA1DRBQ/0?wx_fmt=jpeg",
                "https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFhoQM2tCxGzp6gBkq5ibx1MAVMibXqJwcBqHWViavUicr5fgiaXiaCmGyjjhv0I9VTQupsFraXWacMVMWRw/0?wx_fmt=jpeg",
                "https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFhoQM2tCxGzp6gBkq5ibx1MAsHR8Xk5Sw0ZOnr08WjkLyRrLUF9N0UQzMiadppEknbZm85cFJve1iaQw/0?wx_fmt=jpeg"
        });
        card.setStract(ab);
        List<TextImg> textImges = new ArrayList<TextImg>();
        textImges.add(new TextImg("https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFhoQM2tCxGzp6gBkq5ibx1MAe5R05XW9w0JmUKOky8Usia46eM1L3DibUwaS3WHbLKSIhlWLaGUXLq6w/0?wx_fmt=jpeg",
                                    "MONDAY 周一 TUESDAY 周二 WEDNESDAY 周三 THURSDAY 周四 FRIDAY 周五 SATURDAY 周六 SUNDAY 周日 此处只控制显示，不控制实际使用逻辑，不填默认不显示,图文描述，5000字以内"));
        textImges.add(new TextImg("https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFhoQM2tCxGzp6gBkq5ibx1MAAqcde3gRnjgazK5MlqMtEqfaS5ZTdIbo9lLB2EbVbvPxTEWWOBSyPQ/0?wx_fmt=jpeg",
                "封面图片列表，仅支持填入一个封面图片链接，上传图片接口上传获取图片获得链接，填写非CDN链接会报错，并在此填入。建议图片尺寸像素850*350 图文描述，5000字以内"));
        card.setText_img_list(textImges);
        
        String post = PostJsonBuilder.build(card);
        System.out.println(post);
//        JSONObject postCardJson = JSON.parseObject(post, JSONObject.class);
        JsonNode postCardJson = JsonMapper.fromJsonString(post, JsonNode.class);
        System.out.println(postCardJson);
        JsonNode advancedInfo = postCardJson.get("card").get(card.getCard_type()).get("advanced_info");
        card.setAdvancedInfo(advancedInfo == null ? null:advancedInfo.toString());
        card.setBaseInfo(postCardJson.get("card").get(card.getCard_type()).get("base_info").toString());
        System.out.println(card.getAdvancedInfo());
        System.out.println(card.getBaseInfo());
//        JsonNode node = CardHandler.instance.createCard(access_token , card);
//        System.out.println(node.toString());
        
        // {"errcode":0,"errmsg":"ok","card_id":"pWm-rt6TjvXskZ1vJY41YWQTna3w"}
        
    }
    
    /**
     * 兑换券
     */
    public void testBuildGiftCard(){
        
        MCard card = new MCard().initGift("可兑换音乐木盒一个。");
        // baseinfo
        card.setLogo_url("https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFia8GjPEJHicTYzuVTohicbfuabc35U0TUVr4EysTQGCamqUFdyMOnPKoWJsvECKTurttXLh06IlJ0FQ/0?wx_fmt=png");
        card.setCode_type("CODE_TYPE_ONLY_QRCODE");
        card.setBrand_name("10元测试兑换券");
        card.setTitle("10元兑换券");
        card.setColor("Color020");
        card.setNotice("卡券使用提醒");
        card.setDescription("卡券使用说明，字数上限为1024个汉字。");
        card.setSku(0);  // 上限1亿张
        card.setDate_info_Range(1460044800L, 1460736000L);
        
        card.setUse_custom_code(true);
        card.setBind_openid(true);
        card.setService_phone("40012234");
//        card.setLocation_id_list(location_id_list);
        card.setSource("寻鹿");
        card.setGet_limit(1);
        card.setCan_share(false);
        card.setCan_give_friend(false);
        
        // advance  
//        card.setUse_condition("快餐", "吉野家", true);
//        Abstract ab = new Abstract();
//        ab.setAbstract_name("封面摘要简介。");
//        ab.setIcon_url_list(new String[]{
//                "https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFia9iaV4xpD9oYF5Uf1SODxib5nDt1P1CF0Om9BVYkvzsU1zibzRv8ZE7cLeDibMf6sP5cSt6O1lA1DRBQ/0?wx_fmt=jpeg",
//                "https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFhoQM2tCxGzp6gBkq5ibx1MAVMibXqJwcBqHWViavUicr5fgiaXiaCmGyjjhv0I9VTQupsFraXWacMVMWRw/0?wx_fmt=jpeg",
//                "https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFhoQM2tCxGzp6gBkq5ibx1MAsHR8Xk5Sw0ZOnr08WjkLyRrLUF9N0UQzMiadppEknbZm85cFJve1iaQw/0?wx_fmt=jpeg"
//        });
//        card.setStract(ab);
//        List<TextImg> textImges = new ArrayList<TextImg>();
//        textImges.add(new TextImg("https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFhoQM2tCxGzp6gBkq5ibx1MAe5R05XW9w0JmUKOky8Usia46eM1L3DibUwaS3WHbLKSIhlWLaGUXLq6w/0?wx_fmt=jpeg",
//                "MONDAY 周一 TUESDAY 周二 WEDNESDAY 周三 THURSDAY 周四 FRIDAY 周五 SATURDAY 周六 SUNDAY 周日 此处只控制显示，不控制实际使用逻辑，不填默认不显示,图文描述，5000字以内"));
//        textImges.add(new TextImg("https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFhoQM2tCxGzp6gBkq5ibx1MAAqcde3gRnjgazK5MlqMtEqfaS5ZTdIbo9lLB2EbVbvPxTEWWOBSyPQ/0?wx_fmt=jpeg",
//                "封面图片列表，仅支持填入一个封面图片链接，上传图片接口上传获取图片获得链接，填写非CDN链接会报错，并在此填入。建议图片尺寸像素850*350 图文描述，5000字以内"));
//        card.setText_img_list(textImges); 
        JsonNode node = CardHandler.instance.createCard(access_token , card);
        System.out.println(node.toString());
        
        // {"errcode":0,"errmsg":"ok","card_id":"pWm-rt9AKJ1JoHIMP8MiT436YvjE"}
    }
    
    /**
     * 团购券
     */
    public void testBuildGrouponCard(){
        MCard card = new MCard().initGroupon("双人套餐 -进口红酒一支。孜然牛肉一份。");
        // baseinfo
        card.setLogo_url("https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFia8GjPEJHicTYzuVTohicbfuabc35U0TUVr4EysTQGCamqUFdyMOnPKoWJsvECKTurttXLh06IlJ0FQ/0?wx_fmt=png");
        card.setCode_type("CODE_TYPE_ONLY_QRCODE");
        card.setBrand_name("10元测试团购券");
        card.setTitle("10元团购券");
        card.setColor("Color020");
        card.setNotice("卡券使用提醒");
        card.setDescription("卡券使用说明，字数上限为1024个汉字。");
        card.setSku(0);  // 上限1亿张
        card.setDate_info_Range(1460044800L, 1460736000L);
        
        card.setUse_custom_code(true);
        card.setBind_openid(true);
        card.setService_phone("40012234");
//        card.setLocation_id_list(location_id_list);
        card.setSource("寻鹿");
        card.setGet_limit(1);
        card.setCan_share(false);
        card.setCan_give_friend(false);
        
        // advance  
        JsonNode node = CardHandler.instance.createCard(access_token , card);
        System.out.println(node.toString());
        
        // {"errcode":0,"errmsg":"ok","card_id":"pWm-rt9AKJ1JoHIMP8MiT436YvjE"}
    }
    
    /**
     * 折扣券
     */
    public void testBuildDiscountCard(){
        MCard card = new MCard().initDiscount(5);
        // baseinfo
        card.setLogo_url("https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFia8GjPEJHicTYzuVTohicbfuabc35U0TUVr4EysTQGCamqUFdyMOnPKoWJsvECKTurttXLh06IlJ0FQ/0?wx_fmt=png");
        card.setCode_type("CODE_TYPE_ONLY_QRCODE");
        card.setBrand_name("95折折扣券");
        card.setTitle("95折折扣券");
        card.setColor("Color020");
        card.setNotice("卡券使用提醒");
        card.setDescription("卡券使用说明，字数上限为1024个汉字。");
        card.setSku(0);  // 上限1亿张
        card.setDate_info_Range(1460044800L, 1460736000L);
        
        card.setUse_custom_code(true);
        card.setBind_openid(true);
        card.setService_phone("40012234");
//        card.setLocation_id_list(location_id_list);
        card.setSource("寻鹿");
        card.setGet_limit(1);
        card.setCan_share(false);
        card.setCan_give_friend(false);
        
        // advance  
        JsonNode node = CardHandler.instance.createCard(access_token , card);
        System.out.println(node.toString());
        
        // {"errcode":0,"errmsg":"ok","card_id":"pWm-rt9AKJ1JoHIMP8MiT436YvjE"}
    }
    
    /**
     * 优惠券
     * @throws IOException 
     */
    public void testBuildGeneralCouponCard() throws IOException{
        MCard card = new MCard().initGeneralCoupon("这里是优惠券噢，填写的内容是优惠券详情");
        // baseinfo
        card.setLogo_url("https://mmbiz.qlogo.cn/mmbiz/uyMfKK4ZKFia8GjPEJHicTYzuVTohicbfuabc35U0TUVr4EysTQGCamqUFdyMOnPKoWJsvECKTurttXLh06IlJ0FQ/0?wx_fmt=png");
        card.setCode_type("CODE_TYPE_ONLY_QRCODE");
        card.setBrand_name("乌龙山伯爵");
        card.setTitle("开心麻花门票");
        card.setSub_title("周末狂欢必备券");
        card.setColor("Color070");
        card.setNotice("卡券使用提醒");
        card.setDescription("乌龙山伯爵由开心麻花策划及演出，以");
        card.setSku(5);  // 上限1亿张
        card.setDate_info_Range(1460044800L, 1460936000L);
        card.setUse_custom_code(true);
//        card.setGet_custom_code_mode("GET_CUSTOM_CODE_MODE_DEPOSIT");
        card.setBind_openid(true);
        card.setService_phone("40012234");
//        card.setLocation_id_list(location_id_list);
        card.setSource("寻鹿");
        card.setGet_limit(2);
        card.setCan_share(false);
        card.setCan_give_friend(false);
//        JsonNode img = CardHandler.instance.uplaodLogo(access_token, new File("/Users/Muarine/Desktop/card_intro_big29bc6c.jpg"));
//        System.out.println(img);
//        Abstract ab = new Abstract();
//          ab.setAbstract_name("封面摘要简介。");
//          ab.setIcon_url_list(new String[]{
//              img.get("url").asText()
//          });
//        card.setStract(ab);
//        card.setLocation_id_list(new String[]{"404198305","404198263","292350692"});
//        List<TimeLimit> limits = new ArrayList<TimeLimit>();
//        TimeLimit limit = new TimeLimit();
//        limit.setType("");
//        limits.add(limit);
//        card.setTime_limit(limits);
        // advance  
        JsonNode node = CardHandler.instance.createCard(access_token , card);
        System.out.println(node.toString());
        
        // {"errcode":0,"errmsg":"ok","card_id":"pWm-rt9AKJ1JoHIMP8MiT436YvjE"}
    }
    
    /**
     * 投放卡券
     */
    public void testPushCard(){
        String cardid = "pWm-rt_5DxvajeockuvyAwVZWRqM";
        List<String> codes = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            codes.add(IdGenerator.generateSid());
        }
        // 1. 导入卡券
        JsonNode deposteCode = CardHandler.instance.depositCode(access_token, cardid, codes);
        System.out.println("导入卡券：" + deposteCode.toString());
        Assert.notNull(deposteCode, "request weixin deposite code api return null");
        Assert.isTrue(deposteCode.get("errcode").asInt() == 0, deposteCode.toString());
        // 2. 检查导入的code
//        JsonNode checkCodes = CardHandler.instance.checkCodes(access_token, cardid, codes);
//        System.out.println("检查code" + checkCodes.toString());
//        Assert.notNull(checkCodes , "request weixin check code api return null");
//        Integer failSize = checkCodes.get("not_exist_code").size();
//        if(failSize > 0){
//            System.out.println(failSize);
//        }
//        Integer succSize = checkCodes.get("exist_code").size();
        Integer succSize = deposteCode.get("succ_code").size();
        // 3. 查询成功导入code条数
        JsonNode depositeCount = CardHandler.instance.getDepositeCount(access_token, cardid);
        System.out.println(depositeCount.get("count").asInt());
        // 4. 修改库存
        JsonNode modifyStock = CardHandler.instance.modifyStock(access_token , cardid, succSize, 0);
        System.out.println(modifyStock.toString());
    }
    
    public void testCardTicket(){
        
        // 获取ticket
//        JsonNode node = WebServiceHandler.instance.getCardTicket(access_token);
//        String api_ticket = node.get("ticket").asText();
        String api_ticket = "E0o2-at6NcC2OsJiQTlwlNHQgQRsLuZfhfjOypia1QWRYol2gg1Eo2Tv3y2ZsflhI0eCT3ku5XXnjMuIu6pCFw";
        // 签名
        String timestamp = "1460434344";
        String card_id = "pWm-rtxxTgn4NBlpbI9YvX4UAvJM";
        String code = "11460431402487596";
        String openid = "oWm-rt5m7DG-uUH3rqgzRImAfzx8";
        String nonce_str = "314a12321de247cb8b5734e57d9dcb8e";
        System.out.println(timestamp);
        System.out.println(nonce_str);
        Map<String,Object> map = Sign.sign(api_ticket, timestamp, card_id , code , openid , nonce_str);
//        Map<String,Object> map = Sign.signCardNoCode(api_ticket, timestamp, card_id, openid, nonce_str);
//        Map<String,Object> map = Sign.signCardNoOpenid(api_ticket, timestamp, card_id, code, nonce_str);
//        Map<String,Object> map = Sign.signCardNoOpenidCode(api_ticket, timestamp, card_id, nonce_str);
        
        System.out.println(map.get("signature"));
        
    }
    
}
