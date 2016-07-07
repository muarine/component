/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.http.RestfulTemplate;
import com.rtmap.wx.sdk.api.ComponentAPI;

import junit.framework.TestCase;

/**
 * TestHttps.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月1日
 * @since 1.0.0
 */
public class TestHttps extends TestCase{
    
    public void testHttps(){
        
        String postdata = "{\"component_appid\":\"\",\"authorizer_appid\":\"\",\"authorizer_refresh_token\":\"\"}";
        JsonNode cac = RestfulTemplate.INSTANCE.postForObject(ComponentAPI.getAuthorizertoken("asd"), postdata , JsonNode.class);
        System.out.println(cac.toString());
        
    }
    
    
}
