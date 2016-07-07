/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.api;

/**
 * ShakeAroundAPI.  摇周边API
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @since 1.0.0
 */
public class ShakeAroundAPI {
    
    /**
     * 申请设备ID
     */
    private static final String DEVICE_APPLY = "https://api.weixin.qq.com/shakearound/device/applyid?access_token=%s";
    /**
     * 编辑设备信息
     */
    private static final String DEVICE_UPDATE = "https://api.weixin.qq.com/shakearound/device/update?access_token=%s";
    /**
     * 配置设备与门店的关联关系
     */
//  private static final String DEVICE_BINDLOCATION = "https://api.weixin.qq.com/shakearound/device/bindlocation?access_token=%s";
    /**
     * 查询设备列表
     */
    private static final String DEVICE_SEARCH = "https://api.weixin.qq.com/shakearound/device/search?access_token=%s";
    
    
    /**
     * 新增页面
     */
    private static final String PAGE_ADD = "https://api.weixin.qq.com/shakearound/page/add?access_token=%s";
    /**
     * 修改页面
     */
    private static final String PAGE_UPDATE = "https://api.weixin.qq.com/shakearound/page/update?access_token=%s";
    /**
     * 查询页面列表
     */
    private static final String PAGE_SEARCH = "https://api.weixin.qq.com/shakearound/page/search?access_token=%s";
    /**
     * 删除页面
     */
    private static final String PAGE_DELETE = "https://api.weixin.qq.com/shakearound/page/delete?access_token=%s";
    
    
    /**
     * 配置设备与页面的关联关系
     */
    private static final String DEVICE_BINDPAGE = "https://api.weixin.qq.com/shakearound/device/bindpage?access_token=%s";
    
    
    /**
     * 获取摇周边的设备及用户信息
     */
    private static final String USER_GETSHAKEINFO = "https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=%s";
    
    
    /**
     * 以设备为维度的数据统计接口
     */
    private static final String STATISTIC_DEVICE = "https://api.weixin.qq.com/shakearound/statistics/device?access_token=%s";
    /**
     * 以页面为维度的数据统计接口
     */
    private static final String STATISTIC_PAGE = "https://api.weixin.qq.com/shakearound/statistics/page?access_token=%s";
    
    // *******************************************************************DEVICE*************************************************************************************
    
    /**
     * 申请设备接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getDeviceApply(String access_token){
        return String.format(DEVICE_APPLY,access_token);
    }
    /**
     * 更新设备接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getDeviceUpdate(String access_token){
        return String.format(DEVICE_UPDATE,access_token);
    }
    
    /**
     * 查询设备列表接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getDeviceSearch(String access_token){
        return String.format(DEVICE_SEARCH,access_token);
    }
    /**
     * 设备绑定页面接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getDeviceBindPage(String access_token){
        return String.format(DEVICE_BINDPAGE,access_token);
    }
    
    // **********************************************************PAGE**********************************************************************************************
    
    /**
     * 添加页面接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getPageAdd(String access_token){
        return String.format(PAGE_ADD,access_token);
    }
    /**
     * 删除页面接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getPageDelete(String access_token){
        return String.format(PAGE_DELETE,access_token);
    }
    /**
     * 查询页面列表接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getPageSearch(String access_token){
        return String.format(PAGE_SEARCH,access_token);
    }
    /**
     * 更新页面接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getPageUpdate(String access_token){
        return String.format(PAGE_UPDATE,access_token);
    }
    
    // *************************************************************************************************************************************
    
    /**
     * 获取摇周边的设备及用户信息
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getUserShakeInfo(String access_token){
        return String.format(USER_GETSHAKEINFO,access_token);
    }
    /**
     * 以设备为维度的数据统计接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getStatisticDevice(String access_token){
        return String.format(STATISTIC_DEVICE,access_token);
    }
    /**
     * 以页面为维度的数据统计接口
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getStatisticPage(String access_token){
        return String.format(STATISTIC_PAGE,access_token);
    }
    
}
