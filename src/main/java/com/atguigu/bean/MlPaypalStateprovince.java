package com.atguigu.bean;

public class MlPaypalStateprovince {
    private Integer stateprovinceId;

    private String stateprovinceNameCode;

    private String stateprovinceName;

    private String stateprovinceCountryCode;

    private String stateprovinceCountry;

    private String stateprovinceCreatetime;

    private String stateprovinceMotifytime;

    public Integer getStateprovinceId() {
        return stateprovinceId;
    }

    public void setStateprovinceId(Integer stateprovinceId) {
        this.stateprovinceId = stateprovinceId;
    }

    public String getStateprovinceNameCode() {
        return stateprovinceNameCode;
    }

    public void setStateprovinceNameCode(String stateprovinceNameCode) {
        this.stateprovinceNameCode = stateprovinceNameCode == null ? null : stateprovinceNameCode.trim();
    }

    public String getStateprovinceName() {
        return stateprovinceName;
    }

    public void setStateprovinceName(String stateprovinceName) {
        this.stateprovinceName = stateprovinceName == null ? null : stateprovinceName.trim();
    }

    public String getStateprovinceCountryCode() {
        return stateprovinceCountryCode;
    }

    public void setStateprovinceCountryCode(String stateprovinceCountryCode) {
        this.stateprovinceCountryCode = stateprovinceCountryCode == null ? null : stateprovinceCountryCode.trim();
    }

    public String getStateprovinceCountry() {
        return stateprovinceCountry;
    }

    public void setStateprovinceCountry(String stateprovinceCountry) {
        this.stateprovinceCountry = stateprovinceCountry == null ? null : stateprovinceCountry.trim();
    }

    public String getStateprovinceCreatetime() {
        return stateprovinceCreatetime;
    }

    public void setStateprovinceCreatetime(String stateprovinceCreatetime) {
        this.stateprovinceCreatetime = stateprovinceCreatetime == null ? null : stateprovinceCreatetime.trim();
    }

    public String getStateprovinceMotifytime() {
        return stateprovinceMotifytime;
    }

    public void setStateprovinceMotifytime(String stateprovinceMotifytime) {
        this.stateprovinceMotifytime = stateprovinceMotifytime == null ? null : stateprovinceMotifytime.trim();
    }
}