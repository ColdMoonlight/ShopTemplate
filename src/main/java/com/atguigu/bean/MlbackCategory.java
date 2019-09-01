package com.atguigu.bean;

import java.util.Date;

public class MlbackCategory {
    private Integer categoryId;

    private String categoryName;

    private String categoryImgurl;
    
    private String categoryImgPcurl;

    private Integer categoryParentId;

    private String categoryParentName;

    private Integer categoryStatus;
    
    private Integer categoryLable;

    private String categorySeo;

    private Integer categorySortOrder;

    private Integer categoryAdminId;

    private String categoryAdminOperatername;

    private Date categoryCreatetime;

    private Date categoryMotifytime;

    private String categoryDesc;
    
    private String categoryProductIds;
    
    private String categoryProductNames;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryImgurl() {
        return categoryImgurl;
    }

    public void setCategoryImgurl(String categoryImgurl) {
        this.categoryImgurl = categoryImgurl == null ? null : categoryImgurl.trim();
    }
    
    public String getCategoryImgPcurl() {
		return categoryImgPcurl;
	}

	public void setCategoryImgPcurl(String categoryImgPcurl) {
		this.categoryImgPcurl = categoryImgPcurl == null ? null : categoryImgPcurl.trim();
	}

	public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public String getCategoryParentName() {
        return categoryParentName;
    }

    public void setCategoryParentName(String categoryParentName) {
        this.categoryParentName = categoryParentName == null ? null : categoryParentName.trim();
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
    
    public Integer getCategoryLable() {
		return categoryLable;
	}

	public void setCategoryLable(Integer categoryLable) {
		this.categoryLable = categoryLable;
	}

	public String getCategorySeo() {
        return categorySeo;
    }

    public void setCategorySeo(String categorySeo) {
        this.categorySeo = categorySeo == null ? null : categorySeo.trim();
    }

    public Integer getCategorySortOrder() {
        return categorySortOrder;
    }

    public void setCategorySortOrder(Integer categorySortOrder) {
        this.categorySortOrder = categorySortOrder;
    }

    public Integer getCategoryAdminId() {
        return categoryAdminId;
    }

    public void setCategoryAdminId(Integer categoryAdminId) {
        this.categoryAdminId = categoryAdminId;
    }

    public String getCategoryAdminOperatername() {
        return categoryAdminOperatername;
    }

    public void setCategoryAdminOperatername(String categoryAdminOperatername) {
        this.categoryAdminOperatername = categoryAdminOperatername == null ? null : categoryAdminOperatername.trim();
    }

    public Date getCategoryCreatetime() {
        return categoryCreatetime;
    }

    public void setCategoryCreatetime(Date categoryCreatetime) {
        this.categoryCreatetime = categoryCreatetime;
    }

    public Date getCategoryMotifytime() {
        return categoryMotifytime;
    }

    public void setCategoryMotifytime(Date categoryMotifytime) {
        this.categoryMotifytime = categoryMotifytime;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

	public String getCategoryProductIds() {
		return categoryProductIds;
	}

	public void setCategoryProductIds(String categoryProductIds) {
		this.categoryProductIds = categoryProductIds == null ? null : categoryProductIds.trim();
	}

	public String getCategoryProductNames() {
		return categoryProductNames;
	}

	public void setCategoryProductNames(String categoryProductNames) {
		this.categoryProductNames = categoryProductNames == null ? null : categoryProductNames.trim();
	}
    
}