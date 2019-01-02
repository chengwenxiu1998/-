package com.tb.bean;

import java.sql.Timestamp;

public class ShaiShaiBean {
	private int Sid;//晒晒的Id
	private int Uid;//发送晒晒人的Id
	private String Stext;//晒晒内容
	private Timestamp time;//发表的时间
	private int count;//点赞的个数
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
	public String getStext() {
		return Stext;
	}
	public void setStext(String stext) {
		Stext = stext;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "ShaiShaiBean [Sid=" + Sid + ", Uid=" + Uid + ", Stext=" + Stext + ", time=" + time + ", count=" + count
				+ "]";
	}
}