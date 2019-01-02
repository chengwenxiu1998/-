package com.cwx.timebank.bean;

import java.util.Date;

public class ShaishaiBean {
    private int Sid;//晒晒id
    private int Uid;//发表人id
    private String petName;//发表人的昵称
    private String text;//发表的内容
    private Date time;//发表时间
    private int count;//点赞的数
    private int reply;//回复的个数

    public int getSid() {
        return Sid;
    }

    public void setSid(int sid) {
        Sid = sid;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "ShaishaiBean{" +
                "Sid=" + Sid +
                ", Uid=" + Uid +
                ", petName='" + petName + '\'' +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", count=" + count +
                ", reply=" + reply +
                '}';
    }
}

