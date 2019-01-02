package com.cwx.timebank.bean;

import java.util.Date;

public class TaskBean {
    //tId是自增的先不写了
    private int uIdSend;//发布者ID
    private Date uTime;//发布时间
    private int tcId;//卖时间或者买时间的ID
    private String tDesc;//描述的内容
    private int tCoinCount;//时间币数目
    private String tState;//任务状态
    private int uIdAccept;//接收者ID
    private int tagId;//标签的ID
    private Date tEndtime;
    private Date tAcceptTime;
    private Date tFinishTime;
    private String t_imgurl;

    public TaskBean() {
    }

    public int getuIdSend() {
        return uIdSend;
    }

    public Date getuTime() {
        return uTime;
    }

    public int getTcId() {
        return tcId;
    }

    public String gettDesc() {
        return tDesc;
    }

    public int gettCoinCount() {
        return tCoinCount;
    }

    public String gettState() {
        return tState;
    }

    public int getuIdAccept() {
        return uIdAccept;
    }

    public int getTagId() {
        return tagId;
    }

    public Date gettEndtime() {
        return tEndtime;
    }

    public Date gettAcceptTime() {
        return tAcceptTime;
    }

    public Date gettFinishTime() {
        return tFinishTime;
    }

    public String getT_imgurl() {
        return t_imgurl;
    }

    public void setuIdSend(int uIdSend) {
        this.uIdSend = uIdSend;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public void setTcId(int tcId) {
        this.tcId = tcId;
    }

    public void settDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    public void settCoinCount(int tCoinCount) {
        this.tCoinCount = tCoinCount;
    }

    public void settState(String tState) {
        this.tState = tState;
    }

    public void setuIdAccept(int uIdAccept) {
        this.uIdAccept = uIdAccept;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void settEndtime(Date tEndtime) {
        this.tEndtime = tEndtime;
    }

    public void settAcceptTime(Date tAcceptTime) {
        this.tAcceptTime = tAcceptTime;
    }

    public void settFinishTime(Date tFinishTime) {
        this.tFinishTime = tFinishTime;
    }

    public void setT_imgurl(String t_imgurl) {
        this.t_imgurl = t_imgurl;
    }

    @Override
    public String toString() {
        return "TaskBean{" +
                "uIdSend=" + uIdSend +
                ", uTime=" + uTime +
                ", tcId=" + tcId +
                ", tDesc='" + tDesc + '\'' +
                ", tCoinCount=" + tCoinCount +
                ", tState='" + tState + '\'' +
                ", uIdAccept=" + uIdAccept +
                ", tagId=" + tagId +
                ", tEndtime=" + tEndtime +
                ", tAcceptTime=" + tAcceptTime +
                ", tFinishTime=" + tFinishTime +
                ", t_imgurl='" + t_imgurl + '\'' +
                '}';
    }
}
