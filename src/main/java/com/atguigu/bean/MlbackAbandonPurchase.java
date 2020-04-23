package com.atguigu.bean;

public class MlbackAbandonPurchase {
	
	//弃购id
    private Integer abandonpurchaseId;
    //活动详情
    private String abandonpurchaseActivedetail;
    //优惠券描述
    private String abandonpurchaseCoupondetail;

    private Integer abandonpurchaseStatus;

    private String abandonpurchaseCreatetime;

    private String abandonpurchaseMotifytime;

    public Integer getAbandonpurchaseId() {
        return abandonpurchaseId;
    }

    public void setAbandonpurchaseId(Integer abandonpurchaseId) {
        this.abandonpurchaseId = abandonpurchaseId;
    }

    public String getAbandonpurchaseActivedetail() {
        return abandonpurchaseActivedetail;
    }

    public void setAbandonpurchaseActivedetail(String abandonpurchaseActivedetail) {
        this.abandonpurchaseActivedetail = abandonpurchaseActivedetail == null ? null : abandonpurchaseActivedetail.trim();
    }

    public String getAbandonpurchaseCoupondetail() {
        return abandonpurchaseCoupondetail;
    }

    public void setAbandonpurchaseCoupondetail(String abandonpurchaseCoupondetail) {
        this.abandonpurchaseCoupondetail = abandonpurchaseCoupondetail == null ? null : abandonpurchaseCoupondetail.trim();
    }

    public Integer getAbandonpurchaseStatus() {
        return abandonpurchaseStatus;
    }

    public void setAbandonpurchaseStatus(Integer abandonpurchaseStatus) {
        this.abandonpurchaseStatus = abandonpurchaseStatus;
    }

    public String getAbandonpurchaseCreatetime() {
        return abandonpurchaseCreatetime;
    }

    public void setAbandonpurchaseCreatetime(String abandonpurchaseCreatetime) {
        this.abandonpurchaseCreatetime = abandonpurchaseCreatetime == null ? null : abandonpurchaseCreatetime.trim();
    }

    public String getAbandonpurchaseMotifytime() {
        return abandonpurchaseMotifytime;
    }

    public void setAbandonpurchaseMotifytime(String abandonpurchaseMotifytime) {
        this.abandonpurchaseMotifytime = abandonpurchaseMotifytime == null ? null : abandonpurchaseMotifytime.trim();
    }

	public MlbackAbandonPurchase() {
		super();
	}

	public MlbackAbandonPurchase(Integer abandonpurchaseId, String abandonpurchaseActivedetail,
			String abandonpurchaseCoupondetail, Integer abandonpurchaseStatus, String abandonpurchaseCreatetime,
			String abandonpurchaseMotifytime) {
		super();
		this.abandonpurchaseId = abandonpurchaseId;
		this.abandonpurchaseActivedetail = abandonpurchaseActivedetail;
		this.abandonpurchaseCoupondetail = abandonpurchaseCoupondetail;
		this.abandonpurchaseStatus = abandonpurchaseStatus;
		this.abandonpurchaseCreatetime = abandonpurchaseCreatetime;
		this.abandonpurchaseMotifytime = abandonpurchaseMotifytime;
	}

	@Override
	public String toString() {
		return "MlbackAbandonPurchase [abandonpurchaseId=" + abandonpurchaseId + ", abandonpurchaseActivedetail="
				+ abandonpurchaseActivedetail + ", abandonpurchaseCoupondetail=" + abandonpurchaseCoupondetail
				+ ", abandonpurchaseStatus=" + abandonpurchaseStatus + ", abandonpurchaseCreatetime="
				+ abandonpurchaseCreatetime + ", abandonpurchaseMotifytime=" + abandonpurchaseMotifytime + "]";
	}
    
}