/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.auth;

import com.rtmap.wx.sdk.exp.AesException;
import com.rtmap.wx.sdk.model.InMsgParser;
import com.rtmap.wx.sdk.mp.in.ComponentNotify;
import com.rtmap.wx.sdk.mp.in.ComponentVerifyNotify;
import com.rtmap.wx.sdk.sign.WXBizMsgCrypt;

import junit.framework.TestCase;

/**
 * TestVerifyNotify.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月29日
 * @since 1.0.0
 */
public class TestVerifyNotify extends TestCase{
    
    public void testTicket() throws AesException{
        /**
         *  component_appid=wx40c25aaa171ae8aa
            component_appsecret=4eac638b5107e18d0e5b698674810cce
            component_token=259EDED6DC804F118EA53292028F74EA
            component_encodingkey=MjU5RURFRDZEQzgwNEYxMThFQTUzMjkyMDI4Rjc0RUE
         */
        String appid = "wx40c25aaa171ae8aa";
        String appsecret = "4eac638b5107e18d0e5b698674810cce";
        String token = "259EDED6DC804F118EA53292028F74EA";
        String encodingkey = "MjU5RURFRDZEQzgwNEYxMThFQTUzMjkyMDI4Rjc0RUE";
        
        //msg_signature:2d3663f431b87282818c59738eebbee88bd2cc5a,timestamp:1459235509,nonce:1769292559,encrypt_type:aes
        String postdata = "<xml><AppId><![CDATA[wx40c25aaa171ae8aa]]></AppId><Encrypt><![CDATA[ivEdZanK/ikj8B2aIlS/8D/BLDjPT384xAIzCt4WehKTKLcBMaprxI+BtdwBCjOu8+uxCx3VhFlc1+ytZdJ1c8rYhXPbvYD9DnHhIutsdwUqQBKnubQfl+5Ym5ma++YVYf0ouyHSie1Y8SbRvDXiC7iqL04jRRCvS2hRvLgYYr8Jmz5WO1YyLH6MQ2M4uQAFnVo1cxC9VWugLpFym9JdUdv4JWtq0iwNzmOuB82tkzU83ir3m2Ka3MPfbQXjRamhHsSX+ipWKbYgCwvL0QJmy19Jk/nX3cuIdR/0DiQTy2V0QAdd92laCvwkX8f7qim+EvtNPGLCJwjE2oIOsyVdcDvr2WdiFvRcmdgRx28EfXBMdcg+mmLGPwgYt/ZWxfLBhH7w2yEnYzDfx65GfNLim2k6GtRTNtx/cxciorfjA+NwWrT/9Sab8mmVinD2UBx5ABEx1yAKfDN8SwgiuPjS+g==]]></Encrypt></xml>";
        String msg_signature = "eda51bb5e73c7013701b4f57cfdb2b151a0b75fb";
        String timestamp = "1459236107";
        String nonce = "1379349972";
        WXBizMsgCrypt crypt = new WXBizMsgCrypt(token , encodingkey , appid);
        // 解密
        String result =  crypt.decryptMsg(msg_signature , timestamp, nonce , postdata);
        ComponentNotify notify = InMsgParser.parseNotify(result);           // 获取发送的消息
        if(notify instanceof ComponentVerifyNotify){   
            System.out.println(notify.getInfoType());
        }else{
            System.out.println("nop");
        }
    }
    
    public void testParseXML(){
        String xml = "<xml><AppId><![CDATA[wx40c25aaa171ae8aa]]></AppId>"
                + "<CreateTime>1459249901</CreateTime>"
                + "<InfoType><![CDATA[component_verify_ticket]]></InfoType>"
                + "<ComponentVerifyTicket><![CDATA[ticket@@@SXf1m3nN50Me57hsQvUwM3aVWbBvgvpe7e9gO5lVskvyNU9cU32BvkZaDydz02FIPJAdjLbhQR83-GIEe-h98w]]></ComponentVerifyTicket>"
                + "</xml>";
        
        ComponentNotify notify = InMsgParser.parseNotify(xml);           // 获取发送的消息
        if(notify instanceof ComponentVerifyNotify){    
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }
    
}
