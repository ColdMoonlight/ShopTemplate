package com.atguigu.bean;

public class MlbackSlide {
    private Integer slideId;

    private String slideName;

    private Integer slideArea;

    private Integer slideFirthNum;

    private Integer slideWapstatus;

    private Integer slidePcstatus;

    private String slideWapimgurl;

    private String slidePcimgurl;

    private Integer slideProid;

    private String slideProname;

    private String slideSeoname;

    private Integer slideIfinto;

    private String slideCreatetime;

    private String slideMotifytime;

    public Integer getSlideId() {
        return slideId;
    }

    public void setSlideId(Integer slideId) {
        this.slideId = slideId;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName == null ? null : slideName.trim();
    }

    public Integer getSlideArea() {
        return slideArea;
    }

    public void setSlideArea(Integer slideArea) {
        this.slideArea = slideArea;
    }

    public Integer getSlideFirthNum() {
        return slideFirthNum;
    }

    public void setSlideFirthNum(Integer slideFirthNum) {
        this.slideFirthNum = slideFirthNum;
    }

    public Integer getSlideWapstatus() {
        return slideWapstatus;
    }

    public void setSlideWapstatus(Integer slideWapstatus) {
        this.slideWapstatus = slideWapstatus;
    }

    public Integer getSlidePcstatus() {
        return slidePcstatus;
    }

    public void setSlidePcstatus(Integer slidePcstatus) {
        this.slidePcstatus = slidePcstatus;
    }

    public String getSlideWapimgurl() {
        return slideWapimgurl;
    }

    public void setSlideWapimgurl(String slideWapimgurl) {
        this.slideWapimgurl = slideWapimgurl == null ? null : slideWapimgurl.trim();
    }

    public String getSlidePcimgurl() {
        return slidePcimgurl;
    }

    public void setSlidePcimgurl(String slidePcimgurl) {
        this.slidePcimgurl = slidePcimgurl == null ? null : slidePcimgurl.trim();
    }

    public Integer getSlideProid() {
        return slideProid;
    }

    public void setSlideProid(Integer slideProid) {
        this.slideProid = slideProid;
    }

    public String getSlideProname() {
        return slideProname;
    }

    public void setSlideProname(String slideProname) {
        this.slideProname = slideProname == null ? null : slideProname.trim();
    }

    public String getSlideSeoname() {
        return slideSeoname;
    }

    public void setSlideSeoname(String slideSeoname) {
        this.slideSeoname = slideSeoname == null ? null : slideSeoname.trim();
    }

    public Integer getSlideIfinto() {
        return slideIfinto;
    }

    public void setSlideIfinto(Integer slideIfinto) {
        this.slideIfinto = slideIfinto;
    }

    public String getSlideCreatetime() {
        return slideCreatetime;
    }

    public void setSlideCreatetime(String slideCreatetime) {
        this.slideCreatetime = slideCreatetime == null ? null : slideCreatetime.trim();
    }

    public String getSlideMotifytime() {
        return slideMotifytime;
    }

    public void setSlideMotifytime(String slideMotifytime) {
        this.slideMotifytime = slideMotifytime == null ? null : slideMotifytime.trim();
    }
}