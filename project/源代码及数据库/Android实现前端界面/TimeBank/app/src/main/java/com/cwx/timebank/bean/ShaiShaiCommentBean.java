package com.cwx.timebank.bean;

import java.util.Date;

public class ShaiShaiCommentBean {
    private int sid;
    private int rid;
    private String rNickName;
    private String content;
    private Date date;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getrNickName() {
        return rNickName;
    }

    public void setrNickName(String rNickName) {
        this.rNickName = rNickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShaiShaiCommentBean{" +
                "sid=" + sid +
                ", rid=" + rid +
                ", rNickName='" + rNickName + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
