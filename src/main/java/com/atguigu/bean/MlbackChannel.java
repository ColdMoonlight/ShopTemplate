package com.atguigu.bean;

public class MlbackChannel {
    private Integer channelId;

    private String channelUname;

    private String channelNumber;

    private String channelCreatetime;

    private String channelMotifytime;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelUname() {
        return channelUname;
    }

    public void setChannelUname(String channelUname) {
        this.channelUname = channelUname == null ? null : channelUname.trim();
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber == null ? null : channelNumber.trim();
    }

    public String getChannelCreatetime() {
        return channelCreatetime;
    }

    public void setChannelCreatetime(String channelCreatetime) {
        this.channelCreatetime = channelCreatetime == null ? null : channelCreatetime.trim();
    }

    public String getChannelMotifytime() {
        return channelMotifytime;
    }

    public void setChannelMotifytime(String channelMotifytime) {
        this.channelMotifytime = channelMotifytime == null ? null : channelMotifytime.trim();
    }
}