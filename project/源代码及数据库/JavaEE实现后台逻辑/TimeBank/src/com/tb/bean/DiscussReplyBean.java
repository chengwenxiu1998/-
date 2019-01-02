package com.tb.bean;

import java.util.Date;

/**
*@author ������
*@version ����ʱ��:2018��12��5�� ����10:24:27
*@ClassName ������
*@Description ������
*/

public class DiscussReplyBean {
	private int dId;
	private int uIdReply;
	private String drReplyContent;
	private Date drReplyTime;
	
	public DiscussReplyBean() {
		
	}
	
	public DiscussReplyBean(int dId, int uIdReply, String drReplyContent, Date drReplyTime) {
		
		this.dId = dId;
		this.uIdReply = uIdReply;
		this.drReplyContent = drReplyContent;
		this.drReplyTime = drReplyTime;
	}

	public int getdId() {
		return dId;
	}

	public int getuIdReply() {
		return uIdReply;
	}

	public String getDrReplyContent() {
		return drReplyContent;
	}

	public Date getDrReplyTime() {
		return drReplyTime;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public void setuIdReply(int uIdReply) {
		this.uIdReply = uIdReply;
	}

	public void setDrReplyContent(String drReplyContent) {
		this.drReplyContent = drReplyContent;
	}

	public void setDrReplyTime(Date drReplyTime) {
		this.drReplyTime = drReplyTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dId;
		result = prime * result + ((drReplyContent == null) ? 0 : drReplyContent.hashCode());
		result = prime * result + ((drReplyTime == null) ? 0 : drReplyTime.hashCode());
		result = prime * result + uIdReply;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiscussReplyBean other = (DiscussReplyBean) obj;
		if (dId != other.dId)
			return false;
		if (drReplyContent == null) {
			if (other.drReplyContent != null)
				return false;
		} else if (!drReplyContent.equals(other.drReplyContent))
			return false;
		if (drReplyTime == null) {
			if (other.drReplyTime != null)
				return false;
		} else if (!drReplyTime.equals(other.drReplyTime))
			return false;
		if (uIdReply != other.uIdReply)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DiscussReplyBean [dId=" + dId + ", uIdReply=" + uIdReply + ", drReplyContent=" + drReplyContent
				+ ", drReplyTime=" + drReplyTime + "]";
	}
	
	
	
}
