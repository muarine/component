## 微信公众号三方平台

* Maven 模块聚合

```xml
<modules>
	<module>common-config</module>
	<module>common-core</module>
	<module>component-sdk</module>
	<module>component-utils</module>
	<module>component-test</module>
	<module>component-web</module>
</modules>
```


* **common-config   环境配置**
* **common-core	    核心模块**
* **component-sdk	微信SDK**
* **component-util  常用工具类**
* **component-test  单元测试**
* **component-web   Web应用**

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




### component-sdk
```text
封装微信SDK
api 		微信API
exp		SDK内部异常
handler		对外开放Handler
model		消息解析/封装处理类，freemaker模板解析
mp.*		回调消息封装类
sign		消息加解密
utils		SDK内部工具类
pay		    微信支付相关
```

支持的权限集API

| fid  | 权限集       | SDK API        |
| ---- | --------- | -------------- |
| 1    | 消息管理权限    | MpAPI          |
| 2    | 用户管理权限    | UserAPI        |
| 3    | 帐号服务权限    | UserAPI        |
| 4    | 网页服务权限    | WebServiceAPI  |
| 5    | 微信小店权限    | 暂无             |
| 6    | 微信多客服权限   | 暂无             |
| 7    | 群发与通知权限   | NotifyAPI      |
| 8    | 微信卡券权限    | CardAPI        |
| 9    | 微信扫一扫权限   | 暂无             |
| 10   | 微信连WIFI权限 | 暂无             |
| 11   | 素材管理权限    | MaterialAPI    |
| 12   | 微信摇周边权限   | ShakeAroundAPI |
| 13   | 微信门店权限    | PoiAPI         |
| 14   | 微信支付权限    | 已完成支付、订单、退款、企业红包、现金红包           |
| 15   | 自定义菜单权限   | MenuAPI        |
