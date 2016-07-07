package com.rtmap.wx.sdk.token;
/**
 * 
 * OauthToken.     网页授权特殊Token
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年3月24日
 * @since 1.0.0
 */
public class OauthToken {
	
	
	private String accessToken ;
	private Integer expiresIn;
	
	//oauth2.0
	private String refreshToken;//刷新token
	private String openid;
	private String nickname;
	private String headimgurl;
	private String unionid;
	private String scope;
	
	private Integer errcode;//错误编码
	private String errmsg;//错误消息
	
	private long time;
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	@Override
	public String toString() {
		return "Token [accessToken=" + accessToken + ", expiresIn=" + expiresIn
				+ ", refreshToken=" + refreshToken + ", openid=" + openid
				+ ", nickname=" + nickname + ", headimgurl=" + headimgurl
				+ ", unionid=" + unionid + ", scope=" + scope + ", errcode="
				+ errcode + ", errmsg=" + errmsg + ", time=" + time + "]";
	}
	
}
