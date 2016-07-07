/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.domain;

/**
 * 
 * Poi. 门店实体
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月11日
 * @since 1.0.0
 */
public class Poi extends AbstractEntity {

    /** The serialVersionUID */
    private static final long serialVersionUID = 6317980140167494063L;
    
    private Integer fkAuthId;
    private String sid;
    private String poiId;
    private String businessName;
    private String branchName;
    private String province;
    private String city;
    private String district;
    private String address;
    private String telephone;
    private String categories;
    private Integer offset_type = 1;
    private Double longitude;
    private Double latitude;
    private String photo_list;
    private String recommend;
    private String special;
    private String introduction;
    private String openTime;
    private Integer avgPrice;
    private Integer availableState = 2;
    private Integer updateStatus = 0;
    
    
    public Integer getFkAuthId() {
        return fkAuthId;
    }
    public void setFkAuthId(Integer fkAuthId) {
        this.fkAuthId = fkAuthId;
    }
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }
    public Integer getOffset_type() {
        return offset_type;
    }
    public void setOffset_type(Integer offset_type) {
        this.offset_type = offset_type;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public String getPhoto_list() {
        return photo_list;
    }
    public void setPhoto_list(String photo_list) {
        this.photo_list = photo_list;
    }
    public String getRecommend() {
        return recommend;
    }
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
    public String getSpecial() {
        return special;
    }
    public void setSpecial(String special) {
        this.special = special;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getOpenTime() {
        return openTime;
    }
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
    public Integer getAvgPrice() {
        return avgPrice;
    }
    public void setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
    }
    public Integer getAvailableState() {
        return availableState;
    }
    public void setAvailableState(Integer availableState) {
        this.availableState = availableState;
    }
    public Integer getUpdateStatus() {
        return updateStatus;
    }
    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }
    public String getPoiId() {
        return poiId;
    }
    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }
    
}
