package com.atguigu.bean;

public class MlbackCountDown {
    private Integer countdownId;

    private String countdownTitle;

    private String countdownDescription;

    private String countdownStarttime;

    private String countdownEndtime;

    private String countdownCreatetime;

    private String countdownMotifytime;

    public Integer getCountdownId() {
        return countdownId;
    }

    public void setCountdownId(Integer countdownId) {
        this.countdownId = countdownId;
    }

    public String getCountdownTitle() {
        return countdownTitle;
    }

    public void setCountdownTitle(String countdownTitle) {
        this.countdownTitle = countdownTitle == null ? null : countdownTitle.trim();
    }

    public String getCountdownDescription() {
        return countdownDescription;
    }

    public void setCountdownDescription(String countdownDescription) {
        this.countdownDescription = countdownDescription == null ? null : countdownDescription.trim();
    }

    public String getCountdownStarttime() {
        return countdownStarttime;
    }

    public void setCountdownStarttime(String countdownStarttime) {
        this.countdownStarttime = countdownStarttime == null ? null : countdownStarttime.trim();
    }

    public String getCountdownEndtime() {
        return countdownEndtime;
    }

    public void setCountdownEndtime(String countdownEndtime) {
        this.countdownEndtime = countdownEndtime == null ? null : countdownEndtime.trim();
    }

    public String getCountdownCreatetime() {
        return countdownCreatetime;
    }

    public void setCountdownCreatetime(String countdownCreatetime) {
        this.countdownCreatetime = countdownCreatetime == null ? null : countdownCreatetime.trim();
    }

    public String getCountdownMotifytime() {
        return countdownMotifytime;
    }

    public void setCountdownMotifytime(String countdownMotifytime) {
        this.countdownMotifytime = countdownMotifytime == null ? null : countdownMotifytime.trim();
    }
}