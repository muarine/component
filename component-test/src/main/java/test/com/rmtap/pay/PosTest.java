/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2017 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.pay;

import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.model.MicroPay;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maoyun@rtmap.com
 * @project rtmap-sdk
 * @package com.rtmap.order
 * @date 4/23/17
 */
public class PosTest {

    /**
     * 服务商模式
     */
    @Test
    public void testProviderPos(){

        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("appid", "xxxx");
        orderMap.put("sub_appid", "xxxx");
        orderMap.put("mch_id", "xxxx");
        orderMap.put("sub_mch_id", "xxxx");
        orderMap.put("nonce_str", "qhk39pevst31ycrxlc7f1zac9v3gwnc8");
        orderMap.put("body", "1028");
        orderMap.put("out_trade_no", "T0142016122108405329683198720601");
        orderMap.put("total_fee", 2);
        orderMap.put("spbill_create_ip", "127.0.0.1");
//        orderMap.put("openid", "oWm-rt5m7DG-uUH3rqgzRImAfzx8");
        orderMap.put("auth_code", "xxxx");

        String key = "xxxx";
        try {
            MicroPay microPay = MicroPay.create(orderMap , key);

            System.out.println(microPay.toString());

        } catch (RtmapPayException | RtmapConnectException | RtmapInvalidException e) {
            e.printStackTrace();
        }

    }

}