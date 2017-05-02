package com.rtmap.wx.sdk.pay.model;


import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.core.PayAPI;
import com.rtmap.wx.sdk.pay.core.PayHandler;

import java.util.Map;

/**
 * CloseOrder   关闭订单
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/21/16 17:53
 * @since 2.0.0
 */
public class CloseOrder extends PayHandler {


    /**
     * 关闭订单
     *
     * @param requestParam
     * @return
     */
    public static CloseOrder create(Map<String, Object> requestParam, String key) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {

        return request(PayAPI.getCloseOrder(), requestParam, key, CloseOrder.class);
    }

}
