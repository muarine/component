/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.api;

/**
 * CardAPI. 卡券API
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class CardAPI {
    
    /**
     * 上传图片
     */
    private static final String MEDIA_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=%s";
    /**
     * 创建卡券
     */
    private static final String CARD_CREATE = "https://api.weixin.qq.com/card/create?access_token=%s";
    /**
     * 获取用户已领取的卡券
     */
    private static final String USER_CARDS = "https://api.weixin.qq.com/card/user/getcardlist?access_token=%s";
    /**
     * 导入code接口
     */
    private static final String DEPOSITE_CODE = "http://api.weixin.qq.com/card/code/deposit?access_token=%s";
    /**
     * 查询导入code数目接口
     */
    private static final String DEPOSITE_COUNT = "http://api.weixin.qq.com/card/code/getdepositcount?access_token=%s";
    /**
     * 核查code接口
     */
    private static final String CHECK_CODE = "http://api.weixin.qq.com/card/code/checkcode?access_token=%s";
    /**
     * 查询Code接口
     */
    private static final String GET_CODE = "https://api.weixin.qq.com/card/code/get?access_token=%s";
    /**
     * 核销Code接口
     */
    private static final String CONSUME_CODE = "https://api.weixin.qq.com/card/code/consume?access_token=%s";
    /**
     * 修改库存接口
     */
    private static final String MODIFY_STOCK = "https://api.weixin.qq.com/card/modifystock?access_token=%s";
    /**
     * 修改Code接口
     */
    private static final String MODIFY_CODE = "https://api.weixin.qq.com/card/code/update?access_token=%s";
    /**
     * 删除卡券接口
     */
    private static final String DELETE_CARD = "https://api.weixin.qq.com/card/delete?access_token=%s";
    /**
     * 设置卡券接口
     */
    private static final String CODE_UNAVAILIABLE = "https://api.weixin.qq.com/card/code/unavailable?access_token=%s";
    
    
    /**
     * 设置卡券失败
     * @param access_token
     * @return
     */
    public static String codeUnavailiable(String access_token) {
        return String.format(CODE_UNAVAILIABLE , access_token);
    }
    /**
     * 删除卡券
     * @param access_token
     * @return
     */
    public static String deleteCard(String access_token) {
        return String.format(DELETE_CARD , access_token);
    }
    /**
     * 修改Code
     * @param access_token
     * @return
     */
    public static String modifyCode(String access_token) {
        return String.format(MODIFY_CODE , access_token);
    }
    /**
     * 修改库存 
     * @param access_token
     * @return
     */
    public static String modifyStock(String access_token) {
        return String.format(MODIFY_STOCK , access_token);
    }
    /**
     * 多媒体上传 
     * @param access_token
     * @return
     */
    public static String getMediaUpload(String access_token) {
        return String.format(MEDIA_UPLOAD , access_token);
    }
    /**
     * 创建卡券 
     * @param access_token
     * @return
     */
    public static String getCardCreate(String access_token) {
        return String.format(CARD_CREATE , access_token);
    }
    /**
     * 获取用户已领取的卡券列表 
     * @param access_token
     * @return
     */
    public static String getUserCards(String access_token){
        return String.format(USER_CARDS, access_token);
    }
    /**
     * 导入code 
     * @param access_token
     * @return
     */
    public static String depositeCode(String access_token){
        return String.format(DEPOSITE_CODE, access_token);
    }
    /**
     * 查询导入code数目接口 
     * @param access_token
     * @return
     */
    public static String getDepositeCount(String access_token){
        return String.format(DEPOSITE_COUNT, access_token);
    }
    /**
     * 核查code接口
     * @param access_token
     * @return
     */
    public static String checkCode(String access_token){
        return String.format(CHECK_CODE, access_token);
    }
    /**
     * 查询code接口
     * @param access_token
     * @return
     */
    public static String getCode(String access_token){
        return String.format(GET_CODE, access_token);
    }
    /**
     * 核销code接口
     * @param access_token
     * @return
     */
    public static String consumeCode(String access_token){
        return String.format(CONSUME_CODE, access_token);
    }
    
    
    
    
}
