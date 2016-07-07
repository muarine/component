/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.token;

import java.util.Map;

import com.rtmap.wx.sdk.sign.Sign;

import test.com.rmtap.core.AbstractTest;

/**
 * TestTicket.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月13日
 * @since 1.0.0
 */
public class TestTicket extends AbstractTest{
    
    public void testCardSign(){
        //"{"code":"1460540251389291",
        //"openid":"oWm-rt5m7DG-uUH3rqgzRImAfzx8",
        //"timestamp":"1460542067",
        //"nonce_str":"fa8a5a6f1d0a44b5b3fdb8b536046a5e",
        //"signature":"92e26cbd3c57986ec40baac8ee1cd533ec040dcc"}"
        Map<String,Object> map = Sign.sign(
                    "E0o2-at6NcC2OsJiQTlwlNHQgQRsLuZfhfjOypia1QUH8yVuUXL_3V1FrGsGP7D9e8SNiRFLrqZu5Wo5jFylMw", 
                    "1460553711",
                    "pWm-rt42dHl7SQZ6mKYA9Zfjtnxg",
                    "1460526365019475", 
                    null, 
                    "8f9d6df66910433e978b2b695cebc513");
        System.out.println(map.get("signature"));
        
    }
    
}
