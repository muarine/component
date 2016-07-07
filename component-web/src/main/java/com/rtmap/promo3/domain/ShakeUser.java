package com.rtmap.promo3.domain;

import java.util.Date;

/**
 * 
 * LogApi. api执行Log
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月24日
 * @since 2.0
 */
public class ShakeUser{
	private Long id;
	private String appid;
    private String openid;
    private String nick_name;
    private String head;
    private Date create_time;
    private Long update_time;
    
    // 新增字段
    private Integer state;
    private Integer sex;
    private String city;
    private String province;
    private String country;
    private Long subscribe_time;
    private Long unsubscribe_time;
    private String unionid;
    private String remark;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Long getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Long update_time) {
		this.update_time = update_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(Long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public Long getUnsubscribe_time() {
		return unsubscribe_time;
	}
	public void setUnsubscribe_time(Long unsubscribe_time) {
		this.unsubscribe_time = unsubscribe_time;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}