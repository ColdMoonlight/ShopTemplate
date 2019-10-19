package com.atguigu.bean;

public class MlfrontUser {
    private Integer userId;

    private String userEmail;

    private String userPassword;

    private String userTelephone;

    private String userAddressCountry;

    private String userAddressProvince;

    private String userAddressCity;

    private String userAddressStreetaddress;

    private String userAddressPostalcode;

    private String userFirstname;

    private String userLastname;

    private String userCreatetime;

    private String userMotifytime;

    private String userLastonlinetime;

    private Integer userPoint;

    private String userCouponidstr;
    
    private Integer userTimes;	//购买次数
    
    private Integer userVipLevel;	//VIP等级
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone == null ? null : userTelephone.trim();
    }

    public String getUserAddressCountry() {
        return userAddressCountry;
    }

    public void setUserAddressCountry(String userAddressCountry) {
        this.userAddressCountry = userAddressCountry == null ? null : userAddressCountry.trim();
    }

    public String getUserAddressProvince() {
        return userAddressProvince;
    }

    public void setUserAddressProvince(String userAddressProvince) {
        this.userAddressProvince = userAddressProvince == null ? null : userAddressProvince.trim();
    }

    public String getUserAddressCity() {
        return userAddressCity;
    }

    public void setUserAddressCity(String userAddressCity) {
        this.userAddressCity = userAddressCity == null ? null : userAddressCity.trim();
    }

    public String getUserAddressStreetaddress() {
        return userAddressStreetaddress;
    }

    public void setUserAddressStreetaddress(String userAddressStreetaddress) {
        this.userAddressStreetaddress = userAddressStreetaddress == null ? null : userAddressStreetaddress.trim();
    }

    public String getUserAddressPostalcode() {
        return userAddressPostalcode;
    }

    public void setUserAddressPostalcode(String userAddressPostalcode) {
        this.userAddressPostalcode = userAddressPostalcode == null ? null : userAddressPostalcode.trim();
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname == null ? null : userFirstname.trim();
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname == null ? null : userLastname.trim();
    }

    public String getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(String userCreatetime) {
        this.userCreatetime = userCreatetime == null ? null : userCreatetime.trim();
    }

    public String getUserMotifytime() {
        return userMotifytime;
    }

    public void setUserMotifytime(String userMotifytime) {
        this.userMotifytime = userMotifytime == null ? null : userMotifytime.trim();
    }

    public String getUserLastonlinetime() {
        return userLastonlinetime;
    }

    public void setUserLastonlinetime(String userLastonlinetime) {
        this.userLastonlinetime = userLastonlinetime == null ? null : userLastonlinetime.trim();
    }

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public String getUserCouponidstr() {
        return userCouponidstr;
    }

    public void setUserCouponidstr(String userCouponidstr) {
        this.userCouponidstr = userCouponidstr == null ? null : userCouponidstr.trim();
    }

	public Integer getUserVipLevel() {
		return userVipLevel;
	}

	public void setUserVipLevel(Integer userVipLevel) {
		this.userVipLevel = userVipLevel;
	}

	public Integer getUserTimes() {
		return userTimes;
	}

	public void setUserTimes(Integer userTimes) {
		this.userTimes = userTimes;
	}
    
}