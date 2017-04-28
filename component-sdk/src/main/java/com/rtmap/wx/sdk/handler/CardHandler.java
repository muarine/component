/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.file.FileUtils;
import com.rtmap.utils.json.JsonMapper;
import com.rtmap.wx.sdk.api.CardAPI;
import com.rtmap.wx.sdk.model.PostJsonBuilder;
import com.rtmap.wx.sdk.model.card.MCard;
import com.rtmap.wx.sdk.utils.UploadMedia;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * CardHandler. 微信卡券对接处理
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月5日
 * @since 1.0.0
 */
public class CardHandler extends AbstractHandler{
    
    private static class SingleTonHolder{
        private final static CardHandler INSTANCE = new CardHandler(); 
    }
    
    private CardHandler(){}
    
    public static CardHandler instance = SingleTonHolder.INSTANCE;
    
    /**
     * 图片大小限制1M 
     * 
     * @param access_token  票据
     * @param url   图片地址
     * @return
     * <pre>
     *      {
     *          "url":"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0"
     *      }
     * </pre>
     * @throws IOException
     */
    public JsonNode uploadLogo(String access_token , String url) throws IOException{
//        this.valiUploadImage(file);
        URL uri = new URL(url);
        String filename = FileUtils.getFileName(url);
        logDebug("request:{}", "buffer=@" + filename);
        String result = UploadMedia.upload(CardAPI.getMediaUpload(access_token) , uri.openStream() , filename, "buffer=@" + filename);
        logDebug("reponse:{}", result);
        JsonNode node = JsonMapper.fromJsonString(result , JsonNode.class);
        return node;
    }
    
    /**
     * 创建卡券 
     * @param access_token  票据
     * @param card          卡券
     * @return
     * <pre>
     *      {
     *          "errcode":0,
     *          "errmsg":"ok",
     *          "card_id":"p1Pj9jr90_SQRaVqYI239Ka1erkI"
     *      }
     * </pre>
     */
    public JsonNode createCard(String access_token , MCard card){
        // 构建卡券的freemaker模板
        String post = PostJsonBuilder.build(card);
        logDebug("request:{}", post);
        JsonNode postCardJson = JsonMapper.fromJsonString(post, JsonNode.class);
        JsonNode advancedInfo = postCardJson.get("card").get(card.getCard_type()).get("advanced_info");
        card.setAdvancedInfo(advancedInfo == null ? null:advancedInfo.toString());
        card.setBaseInfo(postCardJson.get("card").get(card.getCard_type()).get("base_info").toString());
        JsonNode node = RESTFUL.postForObject(CardAPI.getCardCreate(access_token), post, JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }
    
    /**
     * 批量导入code
     * @param access_token  票据
     * @param cardid        卡券ID
     * @param codes         自定义code集合
     * @return
     * <pre>
     *      {
     *          "errcode":0,
     *          "errmsg":"ok"
     *      }
     * </pre>
     */
    public JsonNode depositCode(String access_token , String cardid , List<String> codes){
        Assert.notEmpty(codes , "code list must not be empty");
        
        String post = "{\"card_id\":\"%s\",\"code\":[%s]}";
        StringBuilder builder = new StringBuilder();
        for (String code : codes) {
            builder.append(code).append(",");
        }
        post = String.format(post, cardid , builder.substring(0, builder.length() -1));
        System.out.println(post);
        logDebug("request:{}", post);
        JsonNode node = RESTFUL.postForObject(CardAPI.depositeCode(access_token), post, JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }
    
    /**
     * 获取用户已领取卡券接口
     *  
     * @param access_token  票据
     * @param openid        用户标识
     * @param cardid        卡券ID
     * @return 
     * <pre>
     *      {
     *           "errcode":0,
     *           "errmsg":"ok",
     *           "card_list": [
     *                 {"code": "xxx1434079154", "card_id": "xxxxxxxxxx"},
     *                 {"code": "xxx1434079155", "card_id": "xxxxxxxxxx"}
     *                 ]
     *           }
     * </pre>
     */
    public JsonNode getUserCards(String access_token , String openid , String cardid){
        String post = "{\"openid\":\"%s\",\"card_id\":\"%s\"}";
        post = String.format(post, openid , cardid);
        logDebug("request:{}", post);
        JsonNode node = RESTFUL.postForObject(CardAPI.getUserCards(access_token), post, JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }
    
    /**
     * 查询导入code数目 
     * @param access_token  票据
     * @param cardid        卡券ID
     * @return
     * <pre>
     *      {
     *         "errcode":0,
     *         "errmsg":"ok"，
     *         "count":123
     *       }
     * </pre>
     */
    public JsonNode getDepositeCount(String access_token , String cardid){
        
        String post = "{\"card_id\":\"%s\"}";
        post = String.format(post, cardid);
        logDebug("request:{}", post);
        JsonNode node = RESTFUL.postForObject(CardAPI.getDepositeCount(access_token), post, JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }

    /**
     * 检查导入code
     * 
     * @param access_token  票据
     * @param cardid        卡券ID
     * @param codes         自定义code集合
     * @return
     * <pre>
     * {
     *      "errcode":0,
     *      "errmsg":"ok",
     *      "exist_code":["11111","22222","33333"],
     *      "not_exist_code":["44444","55555"]
     * }
     * </pre>
     */
    public JsonNode checkCodes(String access_token , String cardid , List<String> codes){
        String post = "{\"card_id\":\"%s\",\"code\":[%s]}";
        StringBuilder builder = new StringBuilder();
        for (String code : codes) {
            builder.append(code).append(",");
        }
        post = String.format(post,cardid, builder.substring(0, builder.length() -1));
        logDebug("request:{}", post);
        JsonNode node = RESTFUL.postForObject(CardAPI.checkCode(access_token), post , JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }
    
    /**
     * 修改卡券库存
     * 
     * @param access_token  票据
     * @param cardid        卡券ID
     * @param increase      库存增加
     * @param reduce        减少
     * @return
     * <pre>
     * {
     *      "errcode":0,
     *      "errmsg":"ok",
     *      "exist_code":["11111","22222","33333"],
     *      "not_exist_code":["44444","55555"]
     * }
     * </pre>
     */
    public JsonNode modifyStock(String access_token , String cardid , Integer increase , Integer reduce){
        String post = "{\"card_id\":\"%s\",\"increase_stock_value\":%d,\"reduce_stock_value\":%d}";
        post = String.format(post, cardid , increase , reduce);
        logDebug("request:{}", post);
        JsonNode node = RESTFUL.postForObject(CardAPI.modifyStock(access_token), post , JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }
    
    /**
     * 核销Code接口
     * 
     * @param access_token  票据
     * @param cardid        卡券ID
     * @param code          券码
     * @return
     * <pre>
     * {
     *      "errcode":0,
     *      "errmsg":"ok",
     *      "exist_code":["11111","22222","33333"],
     *      "not_exist_code":["44444","55555"]
     * }
     * </pre>
     */
    public JsonNode consumeCode(String access_token , String cardid , String code){
        String post = "{\"code\":\"%s\",\"card_id\":\"%s\"}";
        post = String.format(post , code , cardid);
        logDebug("request:{}", post);
        JsonNode node = RESTFUL.postForObject(CardAPI.consumeCode(access_token), post , JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }

    /**
     * 查询Code
     * @param access_token  票据
     * @param cardid        卡券ID
     * @param code          券码
     * @return
     * <pre>
     *      {
     *         "errcode": 0,
     *         "errmsg": "ok",
     *         "card": {
     *         "card": {
     *           "card_id": "pbLatjk4T4Hx-QFQGL4zGQy27_Qg",
     *           "begin_time": 1457452800,
     *           "end_time": 1463155199
     *         },
     *         "openid": "obLatjm43RA5C6QfMO5szKYnT3dM",
     *         "can_consume": true,
     *         "user_card_status": "NORMAL"
     *       }
     * </pre>
     */
    public JsonNode getCode(String access_token, String cardid, String code , Boolean checkConsume) {
        String post = "{\"card_id\":\"%s\",\"code\":\"%s\",\"check_consume\" : %s}";
        post = String.format(post , cardid , code , checkConsume);
        logDebug("request:{}", post);
        JsonNode node = RESTFUL.postForObject(CardAPI.getCode(access_token), post , JsonNode.class);
        logDebug("reponse:{}", node);
        return node;
    }
    
    
}
