package com.rtmap.core.domain;


/**
 * 
 * Authorizer. 公众号授权表
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月24日
 * @since 2.0
 */
public class Authorizer extends AbstractEntity{
    
    
    /** The serialVersionUID */
    private static final long serialVersionUID = -6065215648878295889L;
    private String appid;
    private String refreshToken;
    private String nickName;
    private String headImg;
    private Integer serviceTypeInfo;
    private Integer verifyTypeInfo;
    private String userName;
    private String alias;
    private String qrcodeUrl;
    private String funcInfo;
    private Integer state = 1;      // 1. 已授权   2. 取消授权
    
    /**
     * Create a new Authorizer.
     * 
     * @param appid
     * @param refreshToken
     * @param nickName
     * @param headImg
     * @param serviceTypeInfo
     * @param verifyTypeInfo
     * @param userName
     * @param alias
     * @param qrcodeUrl
     * @param funcInfo
     * @param state
     */
    public Authorizer(String appid, String refreshToken, String nickName, String headImg, Integer serviceTypeInfo,
            Integer verifyTypeInfo, String userName, String alias, String qrcodeUrl, String funcInfo,
            Integer state) {
        super();
        this.appid = appid;
        this.refreshToken = refreshToken;
        this.nickName = nickName;
        this.headImg = headImg;
        this.serviceTypeInfo = serviceTypeInfo;
        this.verifyTypeInfo = verifyTypeInfo;
        this.userName = userName;
        this.alias = alias;
        this.qrcodeUrl = qrcodeUrl;
        this.funcInfo = funcInfo;
        this.state = state;
    }
    
	/**
     * Create a new Authorizer.
     * 
     */
    public Authorizer() {
        // FIXME Authorizer constructor
        super();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getServiceTypeInfo() {
        return serviceTypeInfo;
    }

    public void setServiceTypeInfo(Integer serviceTypeInfo) {
        this.serviceTypeInfo = serviceTypeInfo;
    }

    public Integer getVerifyTypeInfo() {
        return verifyTypeInfo;
    }

    public void setVerifyTypeInfo(Integer verifyTypeInfo) {
        this.verifyTypeInfo = verifyTypeInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getFuncInfo() {
        return funcInfo;
    }

    public void setFuncInfo(String funcInfo) {
        this.funcInfo = funcInfo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    
}