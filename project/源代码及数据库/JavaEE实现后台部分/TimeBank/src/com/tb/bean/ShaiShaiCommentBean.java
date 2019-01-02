package com.tb.bean;
/**
*@author ������
*@version ����ʱ��:2018��12��5�� ����10:32:29
*@ClassName ������
*@Description ������
*/

import java.util.Date;

public class ShaiShaiCommentBean {
	private int sId;
	private int uIdComment;
	private Date cCommentTime;
	private String cCommentContent;
	public ShaiShaiCommentBean() {
		
	}
	public ShaiShaiCommentBean(int sId, int uIdComment, Date cCommentTime, String cCommentContent) {
		
		this.sId = sId;
		this.uIdComment = uIdComment;
		this.cCommentTime = cCommentTime;
		this.cCommentContent = cCommentContent;
	}
	public int getsId() {
		return sId;
	}
	public int getuIdComment() {
		return uIdComment;
	}
	public Date getcCommentTime() {
		return cCommentTime;
	}
	public String getcCommentContent() {
		return cCommentContent;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public void setuIdComment(int uIdComment) {
		this.uIdComment = uIdComment;
	}
	public void setcCommentTime(Date cCommentTime) {
		this.cCommentTime = cCommentTime;
	}
	public void setcCommentContent(String cCommentContent) {
		this.cCommentContent = cCommentContent;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cCommentContent == null) ? 0 : cCommentContent.hashCode());
		result = prime * result + ((cCommentTime == null) ? 0 : cCommentTime.hashCode());
		result = prime * result + sId;
		result = prime * result + uIdComment;
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
		ShaiShaiCommentBean other = (ShaiShaiCommentBean) obj;
		if (cCommentContent == null) {
			if (other.cCommentContent != null)
				return false;
		} else if (!cCommentContent.equals(other.cCommentContent))
			return false;
		if (cCommentTime == null) {
			if (other.cCommentTime != null)
				return false;
		} else if (!cCommentTime.equals(other.cCommentTime))
			return false;
		if (sId != other.sId)
			return false;
		if (uIdComment != other.uIdComment)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ShaiShaiCommentBean [sId=" + sId + ", uIdComment=" + uIdComment + ", cCommentTime=" + cCommentTime
				+ ", cCommentContent=" + cCommentContent + "]";
	}
	
	
}
