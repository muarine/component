/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.wx.sdk.model;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.rtmap.wx.sdk.model.card.AbstractModel;
import com.rtmap.wx.sdk.model.card.MBusiness;
import com.rtmap.wx.sdk.model.card.MCard;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * PostJsonBuilder.     构建post请求中的json数据报文
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月6日
 * @since 1.0.0
 */
public class PostJsonBuilder {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(PostJsonBuilder.class);
    
    private static String encoding = "utf-8";
    private static Configuration config = initFreeMarkerConfiguration();
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String build(AbstractModel model) {
        Assert.notNull(model , "model parameter must not be null");
        //TODO 检测卡券必填属性和高级功能里必填项
        
        Map root = new HashMap();
        // 供 OutMsg 里的 TEMPLATE 使用
        root.put("__data", model);
        if(LOGGER.isDebugEnabled()) LOGGER.debug("template data:{}" , JSON.toJSONString(root));
        try {
            Template template = config.getTemplate(model.getClass().getSimpleName(), encoding);
            StringWriter sw = new StringWriter();
            template.process(root, sw);
            String build = sw.toString();
            if(LOGGER.isDebugEnabled()) LOGGER.debug(build);
            return build;
        } catch (freemarker.core.InvalidReferenceException e) {
            throw new RuntimeException("可能是 " + model.getClass().getSimpleName()+  " 对象中的某些属性未赋值，请仔细检查", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static Configuration initFreeMarkerConfiguration() {
        Configuration config = new Configuration();
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        initStringTemplateLoader(stringTemplateLoader);
        config.setTemplateLoader(stringTemplateLoader);
        
        // 模板缓存更新时间，对于json 在类文件目录下的模板来说已有热加载保障了更新
        config.setTemplateUpdateDelay(999999);
        // - Set an error handler that prints errors so they are readable with
        //   a HTML browser.
        // config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        // - Use beans wrapper (recommmended for most applications)
        config.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        // - Set the default charset of the template files
        config.setDefaultEncoding(encoding);        // config.setDefaultEncoding("ISO-8859-1");
        // - Set the charset of the output. This is actually just a hint, that
        //   templates may require for URL encoding and for generating META element
        //   that uses http-equiv="Content-type".
        config.setOutputEncoding(encoding);         // config.setOutputEncoding("UTF-8");
        // - Set the default locale
        config.setLocale(Locale.getDefault() /* Locale.CHINA */ );      // config.setLocale(Locale.US);
        config.setLocalizedLookup(false);
        
        // 去掉int型输出时的逗号, 例如: 123,456
        // config.setNumberFormat("#");     // config.setNumberFormat("0"); 也可以
        config.setNumberFormat("#0.#####");
        config.setDateFormat("yyyy-MM-dd");
        config.setTimeFormat("HH:mm:ss");
        config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        return config;
    }
    
    private static void initStringTemplateLoader(StringTemplateLoader loader) {
        // business 门店
        loader.putTemplate(MBusiness.class.getSimpleName(), MBusiness.TEMPLATE);
        // card 卡券
        loader.putTemplate(MCard.class.getSimpleName(), MCard.TEMPLATE);
    }
    
    public static void setEncoding(String encoding) {
        PostJsonBuilder.encoding = encoding;
    }
    
    public static String getEncoding() {
        return encoding;
    }
    
    
}
