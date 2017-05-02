/*
 * RT MAP, Home of Professional MAP
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */

package test.com.rmtap.pay;

import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.utils.string.OrderUtils;
import com.rtmap.wx.sdk.pay.model.Refund;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * com.rtmap.order.TestRefund
 *
 * @author Muarine<maoyun0903@163.com>
 * @date 16/10/25
 * @since 1.0
 */
public class RefundTest {

    /**
     * 普通模式退款
     */
    @Test
    public void testRefund1(){

        Map<String,Object> requestParam = new HashMap<>();
        requestParam.put("appid" , "xxxx");
        requestParam.put("mch_id" , "xxxx");
        requestParam.put("device_info" , "xxxx");
        requestParam.put("nonce_str" , OrderUtils.randomString(32));

        // 二者选其一
        requestParam.put("transaction_id" , "");
        requestParam.put("out_trade_no" , "");

        requestParam.put("out_refund_no" , OrderUtils.randomString(32));
        requestParam.put("total_fee" , "2");
        requestParam.put("refund_fee" , "1");
        requestParam.put("refund_fee_type" , "CNY");
        requestParam.put("op_user_id" , "xxxx");
        requestParam.put("refund_account" , "REFUND_SOURCE_UNSETTLED_FUNDS");

        String key = "xxxx";
        String certPath = "/User/muarine/cert/apiclient_cert.p12";

        try {
            InputStream in = new FileInputStream(certPath);
            Refund refund = Refund.create(requestParam , key , in);
            System.out.println(refund);
        } catch (RtmapPayException | RtmapConnectException | RtmapInvalidException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 受理模式退款
     */
    @Test
    public void testRefund(){

        Map<String,Object> requestParam = new HashMap<>();
        requestParam.put("appid" , "xxxx");
        requestParam.put("mch_id" , "xxxx");      // 开发账号母商户
        requestParam.put("sub_mch_id" , "xxxx");  // 身边商户
        requestParam.put("device_info" , "xxxx");
        requestParam.put("nonce_str" , OrderUtils.randomString(32));

        requestParam.put("transaction_id" , "4000162001201610257650070138");
        requestParam.put("out_trade_no" , "T0022016102510102346011294932833");

        requestParam.put("out_refund_no" , OrderUtils.randomString(32));
        requestParam.put("total_fee" , "2");
        requestParam.put("refund_fee" , "1");
        requestParam.put("refund_fee_type" , "CNY");
        requestParam.put("op_user_id" , "1244220402");
        requestParam.put("refund_account" , "REFUND_SOURCE_UNSETTLED_FUNDS");

        String key = "xxxx";
        String certPath = "/Users/muarine/cert/apiclient_cert.p12";

        try {
            InputStream in = new FileInputStream(certPath);
            Refund refund = Refund.create(requestParam , key , in);
            System.out.println(refund);
        } catch (RtmapPayException | RtmapConnectException | RtmapInvalidException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
