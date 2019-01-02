package com.cwx.timebank.bean;

import java.util.Date;

public class DisussReplyBean {
    private int did;//讨论id
    private int rid;//回复人id
    private String petName;//回复人昵称
    private String replyContent;//回复内容
    private Date replyTime;//回复时间

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    @Override
    public String toString() {
        return "DisussReplyBean{" +
                "did=" + did +
                ", rid=" + rid +
                ", petName='" + petName + '\'' +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime=" + replyTime +
                '}';
    }
}

