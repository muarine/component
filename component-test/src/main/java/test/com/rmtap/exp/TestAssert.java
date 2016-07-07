/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.exp;

import org.springframework.util.Assert;

import test.com.rmtap.core.AbstractTest;

/**
 * TestAssert.
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月30日
 * @since 1.0.0
 */
public class TestAssert extends AbstractTest{
    
    public void testAssert(){
        
        String var = "aaa";
        try {
            Assert.isTrue(!"aaa".equals(var));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("completed");
    }
    
}
