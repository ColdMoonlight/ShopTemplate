package com.atguigu.bean;

public class MlbackFootNav {
    private Integer footnavId;

    private String footnavName;

    private Integer footnavHang;

    private Integer footnavLie;

    private Integer footnavAdminId;

    private String footnavAdminOperatorname;

    private String footnavCreatetime;

    private String footnavMotifytime;

    private String footnavDesc;
    
    private Integer footnavIfIncome;
    
    private Integer footnavIfShow;

    public Integer getFootnavId() {
        return footnavId;
    }

    public void setFootnavId(Integer footnavId) {
        this.footnavId = footnavId;
    }

    public String getFootnavName() {
        return footnavName;
    }

    public void setFootnavName(String footnavName) {
        this.footnavName = footnavName == null ? null : footnavName.trim();
    }

    public Integer getFootnavHang() {
        return footnavHang;
    }

    public void setFootnavHang(Integer footnavHang) {
        this.footnavHang = footnavHang;
    }

    public Integer getFootnavLie() {
        return footnavLie;
    }

    public void setFootnavLie(Integer footnavLie) {
        this.footnavLie = footnavLie;
    }

    public Integer getFootnavAdminId() {
        return footnavAdminId;
    }

    public void setFootnavAdminId(Integer footnavAdminId) {
        this.footnavAdminId = footnavAdminId;
    }

    public String getFootnavAdminOperatorname() {
        return footnavAdminOperatorname;
    }

    public void setFootnavAdminOperatorname(String footnavAdminOperatorname) {
        this.footnavAdminOperatorname = footnavAdminOperatorname == null ? null : footnavAdminOperatorname.trim();
    }

    public String getFootnavCreatetime() {
        return footnavCreatetime;
    }

    public void setFootnavCreatetime(String footnavCreatetime) {
        this.footnavCreatetime = footnavCreatetime == null ? null : footnavCreatetime.trim();
    }

    public String getFootnavMotifytime() {
        return footnavMotifytime;
    }

    public void setFootnavMotifytime(String footnavMotifytime) {
        this.footnavMotifytime = footnavMotifytime == null ? null : footnavMotifytime.trim();
    }

    public String getFootnavDesc() {
        return footnavDesc;
    }

    public void setFootnavDesc(String footnavDesc) {
        this.footnavDesc = footnavDesc == null ? null : footnavDesc.trim();
    }

	public Integer getFootnavIfIncome() {
		return footnavIfIncome;
	}

	public void setFootnavIfIncome(Integer footnavIfIncome) {
		this.footnavIfIncome = footnavIfIncome;
	}

	public Integer getFootnavIfShow() {
		return footnavIfShow;
	}

	public void setFootnavIfShow(Integer footnavIfShow) {
		this.footnavIfShow = footnavIfShow;
	}
    
}