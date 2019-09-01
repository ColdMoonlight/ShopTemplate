package com.atguigu.bean;

import java.math.BigDecimal;

public class MlbackProduct {
    private Integer productId;

    private String productName;
    
    private String productColor;

    private String productSeo;

    private Integer productCategoryid;
    
    private String productCategoryname;

    private String productMainimgurl;
    
    private String productMainFuimgurl;

    private BigDecimal productOriginalprice;

    private Integer productHavesalenum;

    private Integer productStatus;

    private String productCreatetime;

    private String productMotifytime;

    private Integer productAdminId;

    private String productAdminOperatorname;

    private Integer productLable;
    
    private Integer productFirsth;
    
    private Integer productActoffid;

    private Integer productActoffoff;

    private Integer productActoffIf;
    
    private Integer productReviewnum;

    private String productDesc;
    
    private String productCategoryIdsStr;
    
    private String productCategoryNamesStr;
    
    private String productMetaDesc;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }
    
    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor == null ? null : productColor.trim();
    }

    public String getProductSeo() {
        return productSeo;
    }

    public void setProductSeo(String productSeo) {
        this.productSeo = productSeo == null ? null : productSeo.trim();
    }

    public Integer getProductCategoryid() {
        return productCategoryid;
    }

    public void setProductCategoryid(Integer productCategoryid) {
        this.productCategoryid = productCategoryid;
    }
    
    public String getproductCategoryname() {
        return productCategoryname;
    }

    public void setproductCategoryname(String productCategoryname) {
        this.productCategoryname = productCategoryname;
    }

    public String getProductMainimgurl() {
        return productMainimgurl;
    }

    public void setProductMainimgurl(String productMainimgurl) {
        this.productMainimgurl = productMainimgurl == null ? null : productMainimgurl.trim();
    }
    
    public String getproductMainFuimgurl() {
        return productMainFuimgurl;
    }

    public void setproductMainFuimgurl(String productMainFuimgurl) {
        this.productMainFuimgurl = productMainFuimgurl == null ? null : productMainFuimgurl.trim();
    }

    public BigDecimal getProductOriginalprice() {
        return productOriginalprice;
    }

    public void setProductOriginalprice(BigDecimal productOriginalprice) {
        this.productOriginalprice = productOriginalprice;
    }

    public Integer getProductHavesalenum() {
        return productHavesalenum;
    }

    public void setProductHavesalenum(Integer productHavesalenum) {
        this.productHavesalenum = productHavesalenum;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductCreatetime() {
        return productCreatetime;
    }

    public void setProductCreatetime(String productCreatetime) {
        this.productCreatetime = productCreatetime == null ? null : productCreatetime.trim();
    }

    public String getProductMotifytime() {
        return productMotifytime;
    }

    public void setProductMotifytime(String productMotifytime) {
        this.productMotifytime = productMotifytime == null ? null : productMotifytime.trim();
    }

    public Integer getProductAdminId() {
        return productAdminId;
    }

    public void setProductAdminId(Integer productAdminId) {
        this.productAdminId = productAdminId;
    }

    public String getProductAdminOperatorname() {
        return productAdminOperatorname;
    }

    public void setProductAdminOperatorname(String productAdminOperatorname) {
        this.productAdminOperatorname = productAdminOperatorname == null ? null : productAdminOperatorname.trim();
    }

    public Integer getProductLable() {
        return productLable;
    }

    public void setProductLable(Integer productLable) {
        this.productLable = productLable;
    }
    
    public Integer getProductFirsth() {
		return productFirsth;
	}

	public void setProductFirsth(Integer productFirsth) {
		this.productFirsth = productFirsth;
	}

	public Integer getProductActoffid() {
        return productActoffid;
    }

    public void setProductActoffid(Integer productActoffid) {
        this.productActoffid = productActoffid;
    }

    public Integer getProductActoffoff() {
        return productActoffoff;
    }

    public void setProductActoffoff(Integer productActoffoff) {
        this.productActoffoff = productActoffoff;
    }
    
    public Integer getProductActoffIf() {
		return productActoffIf;
	}

	public void setProductActoffIf(Integer productActoffIf) {
		this.productActoffIf = productActoffIf;
	}

    public Integer getProductReviewnum() {
        return productReviewnum;
    }

    public void setProductReviewnum(Integer productReviewnum) {
        this.productReviewnum = productReviewnum;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }
    
    public String getProductCategoryIdsStr() {
		return productCategoryIdsStr;
	}

	public void setProductCategoryIdsStr(String productCategoryIdsStr) {
		this.productCategoryIdsStr = productCategoryIdsStr == null ? null : productCategoryIdsStr.trim();
	}

	public String getProductCategoryNamesStr() {
		return productCategoryNamesStr;
	}

	public void setProductCategoryNamesStr(String productCategoryNamesStr) {
		this.productCategoryNamesStr = productCategoryNamesStr == null ? null : productCategoryNamesStr.trim();
	}
	
	public String getProductMetaDesc() {
		return productMetaDesc;
	}

	public void setProductMetaDesc(String productMetaDesc) {
		this.productMetaDesc = productMetaDesc == null ? null : productMetaDesc.trim();
	}

	@Override
    public String toString() {
    	return super.toString();
    }
}