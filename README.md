## 微信公众号三方平台

* **Maven 模块聚合**
```xml
<modules>
	<module>component-sdk</module>
	<module>component-utils</module>
	<module>component-test</module>
	<module>component-web</module>
</modules>
```


* **component-sdk	微信SDK**
* **component-util	常用工具类**
* **component-test	单元测试**
* **component-web	Web应用**

## 如何接入使用
* 导入数据库component-web/doc/database/component.sql
* 更新component-web/src/main/resources/system.properties配置文件,具体操作参考文件中的注释


## Compile && Deploy
```bash
cd ****/component
mvn install
```
* 注：更新component-utils和component-sdk组件包需重新打包,生成带版本号的jar

```bash
mvn clean
mvn install
```
* 若使用Eclipse重新打包后工程出现编译错误的小红叉，重新编译一次即可(Project->Clean->选中component所有组件项目->OK)

## 缓存KEY列表
```java

    /**
     * 平台相关key
     * Component_Key.
     * 
     * @author Muarine <maoyun@rtmap.com>
     * @since 1.0.0
     */
    public static class Component_Key{
        /**
         * 公众号平台access_token
         */
        public static final String Component_A_T    = "Component_A_T";
        /**
         * 公众号平台verify_ticket
         */
        public static final String Component_V_T    = "Component_V_T";
        /**
         * 授权公众号的auth_access_token
         */
        public static final String Component_A_A_T  = "Component_Authorier_%s_A_T";
        /**
         * 授权公众号的auth_refresh_token
         */
        public static final String Component_A_R_T  = "Component_Authorier_%s_R_T";
    }
    
    /**
     * 授权账户相关key
     * Authorizer_key.
     * 
     * @author Muarine <maoyun@rtmap.com>
     * @since 1.0.0
     */
    public static class Authorizer_key{
        /**
         * 授权信息
         * %s 表示authAppid
         */
        public static final String INFO         = "Component_Authorier_%s_Info";
        /**
         * 主键ID
         * %s 表示authAppid
         */
        public static final String PRIMARY_ID   = "Component_Authorier_Appid_%s";
        /**
         * 是否有效
         * %s 表示authAppid
         */
        public static final String ISLEGAL      = "Component_Authorier_Appid_%s_Islegal";
    }
    
    /**
     * 
     * JSSDK_key.   JSSDK相关缓存key
     * 
     * @author Muarine <maoyun@rtmap.com>
     * @since 1.0.0
     */
    public static class JSSDK_key{
        
        /**
         * jsapi_ticket 缓存key
         */
        public static final String JS_TICKET_KEY    = "Component_Authorier_%s_Jsapi_Ticket";
        /**
         * wx_card ticket 缓存key
         */
        public static final String CARD_TICKET_KEY  = "Component_Authorier_%s_Card_Ticket";
        /**
         * 网页授权的access_token，这与基础支持中的access_token不同
         */
        public static final String JS_ACCESS_TOKEN  = "Component_Authorier_%s_Web_A_T";
        /**
         * 网页授权前置跳转时存入自定义参数：State
         */
        public static final String Oauth_State      = "Component_Authorizer_%s_State_%s";
        
    }

    /**
     * LBS 5+1beacon回调定位,共享cache
     */
    public static class Lbs_BeaconInfo {
        /**
         * 公众号下粉丝摇一摇定位结果
         */
        public static final String Authorizer_Openid_Shake_Result    = "Component_Authorier_%s_%s_Shake_Result";
    }

```



