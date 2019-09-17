package com.atguigu.bean;

import java.math.BigDecimal;

public class MlbackCoupon {
    private Integer couponId;

    private String couponName;//满100减10块   手动输入

    private BigDecimal couponPrice;// 5  手动输入

    private BigDecimal couponPriceBaseline;//100     使用的底钱，，减前满100.   手动输入

    private Integer couponStatus;//  0不生效,1生效中，，下拉选

    private String couponCode;  //优惠码  如  OFF15,,,,OFF15%    手动输入

    private Integer couponAdminId;

    private String couponAdminOperatorname;

    private String couponCreatetime;

    private String couponMotifytime;
    
    private String couponImgwapurl;	//优惠券wap端图

    private String couponImgpcurl;	//优惠券PC端图
    
    private Integer couponAreaNum;	//优惠券展示位置

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getCouponPriceBaseline() {
        return couponPriceBaseline;
    }

    public void setCouponPriceBaseline(BigDecimal couponPriceBaseline) {
        this.couponPriceBaseline = couponPriceBaseline;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public Integer getCouponAdminId() {
        return couponAdminId;
    }

    public void setCouponAdminId(Integer couponAdminId) {
        this.couponAdminId = couponAdminId;
    }

    public String getCouponAdminOperatorname() {
        return couponAdminOperatorname;
    }

    public void setCouponAdminOperatorname(String couponAdminOperatorname) {
        this.couponAdminOperatorname = couponAdminOperatorname == null ? null : couponAdminOperatorname.trim();
    }

    public String getCouponCreatetime() {
        return couponCreatetime;
    }

    public void setCouponCreatetime(String couponCreatetime) {
        this.couponCreatetime = couponCreatetime == null ? null : couponCreatetime.trim();
    }

    public String getCouponMotifytime() {
        return couponMotifytime;
    }

    public void setCouponMotifytime(String couponMotifytime) {
        this.couponMotifytime = couponMotifytime == null ? null : couponMotifytime.trim();
    }

	public String getCouponImgwapurl() {
		return couponImgwapurl;
	}

	public void setCouponImgwapurl(String couponImgwapurl) {
		this.couponImgwapurl = couponImgwapurl == null ? null : couponImgwapurl.trim();
	}

	public String getCouponImgpcurl() {
		return couponImgpcurl;
	}

	public void setCouponImgpcurl(String couponImgpcurl) {
		this.couponImgpcurl = couponImgpcurl == null ? null : couponImgpcurl.trim();
	}

	public Integer getCouponAreaNum() {
		return couponAreaNum;
	}

	public void setCouponAreaNum(Integer couponAreaNum) {
		this.couponAreaNum = couponAreaNum;
	}
    
}