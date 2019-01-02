package com.tb.bean;

import java.util.Date;

/**
*@author ������
*@version ����ʱ��:2018��12��5�� ����10:40:25
*@ClassName ������
*@Description ������
*/

public class TaskBean {
	private int tId;
	private int uIdSend;
	private Date uTime;
	private int tcId;
	private String  tDesc;
	private int tCoinCount;
	private String tState;
	private int uIdAccept;
	private int tagId;
	private Date tEndTime;
	private String tImgUrl;
	private Date tAcceptTime;
	private Date tFinishTime;
	
	
	public TaskBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int gettId() {
		return tId;
	}


	public void settId(int tId) {
		this.tId = tId;
	}


	public int getuIdSend() {
		return uIdSend;
	}


	public void setuIdSend(int uIdSend) {
		this.uIdSend = uIdSend;
	}


	public Date getuTime() {
		return uTime;
	}


	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}


	public int getTcId() {
		return tcId;
	}


	public void setTcId(int tcId) {
		this.tcId = tcId;
	}


	public String gettDesc() {
		return tDesc;
	}


	public void settDesc(String tDesc) {
		this.tDesc = tDesc;
	}


	public int gettCoinCount() {
		return tCoinCount;
	}


	public void settCoinCount(int tCoinCount) {
		this.tCoinCount = tCoinCount;
	}


	public String gettState() {
		return tState;
	}


	public void settState(String tState) {
		this.tState = tState;
	}


	public int getuIdAccept() {
		return uIdAccept;
	}


	public void setuIdAccept(int uIdAccept) {
		this.uIdAccept = uIdAccept;
	}


	public int getTagId() {
		return tagId;
	}


	public void setTagId(int tagId) {
		this.tagId = tagId;
	}


	public Date gettEndTime() {
		return tEndTime;
	}


	public void settEndTime(Date tEndTime) {
		this.tEndTime = tEndTime;
	}


	public String gettImgUrl() {
		return tImgUrl;
	}


	public void settImgUrl(String tImgUrl) {
		this.tImgUrl = tImgUrl;
	}


	public Date gettAcceptTime() {
		return tAcceptTime;
	}


	public void settAcceptTime(Date tAcceptTime) {
		this.tAcceptTime = tAcceptTime;
	}


	public Date gettFinishTime() {
		return tFinishTime;
	}


	public void settFinishTime(Date tFinishTime) {
		this.tFinishTime = tFinishTime;
	}


	@Override
	public String toString() {
		return "TaskBean [tId=" + tId + ", uIdSend=" + uIdSend + ", uTime=" + uTime + ", tcId=" + tcId + ", tDesc="
				+ tDesc + ", tCoinCount=" + tCoinCount + ", tState=" + tState + ", uIdAccept=" + uIdAccept + ", tagId="
				+ tagId + ", tEndTime=" + tEndTime + ", tImgUrl=" + tImgUrl + ", tAcceptTime=" + tAcceptTime
				+ ", tFinishTime=" + tFinishTime + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tAcceptTime == null) ? 0 : tAcceptTime.hashCode());
		result = prime * result + tCoinCount;
		result = prime * result + ((tDesc == null) ? 0 : tDesc.hashCode());
		result = prime * result + ((tEndTime == null) ? 0 : tEndTime.hashCode());
		result = prime * result + ((tFinishTime == null) ? 0 : tFinishTime.hashCode());
		result = prime * result + tId;
		result = prime * result + ((tImgUrl == null) ? 0 : tImgUrl.hashCode());
		result = prime * result + ((tState == null) ? 0 : tState.hashCode());
		result = prime * result + tagId;
		result = prime * result + tcId;
		result = prime * result + uIdAccept;
		result = prime * result + uIdSend;
		result = prime * result + ((uTime == null) ? 0 : uTime.hashCode());
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
		TaskBean other = (TaskBean) obj;
		if (tAcceptTime == null) {
			if (other.tAcceptTime != null)
				return false;
		} else if (!tAcceptTime.equals(other.tAcceptTime))
			return false;
		if (tCoinCount != other.tCoinCount)
			return false;
		if (tDesc == null) {
			if (other.tDesc != null)
				return false;
		} else if (!tDesc.equals(other.tDesc))
			return false;
		if (tEndTime == null) {
			if (other.tEndTime != null)
				return false;
		} else if (!tEndTime.equals(other.tEndTime))
			return false;
		if (tFinishTime == null) {
			if (other.tFinishTime != null)
				return false;
		} else if (!tFinishTime.equals(other.tFinishTime))
			return false;
		if (tId != other.tId)
			return false;
		if (tImgUrl == null) {
			if (other.tImgUrl != null)
				return false;
		} else if (!tImgUrl.equals(other.tImgUrl))
			return false;
		if (tState == null) {
			if (other.tState != null)
				return false;
		} else if (!tState.equals(other.tState))
			return false;
		if (tagId != other.tagId)
			return false;
		if (tcId != other.tcId)
			return false;
		if (uIdAccept != other.uIdAccept)
			return false;
		if (uIdSend != other.uIdSend)
			return false;
		if (uTime == null) {
			if (other.uTime != null)
				return false;
		} else if (!uTime.equals(other.uTime))
			return false;
		return true;
	}


	public TaskBean(int tId, int uIdSend, Date uTime, int tcId, String tDesc, int tCoinCount, String tState,
			int uIdAccept, int tagId, Date tEndTime, String tImgUrl, Date tAcceptTime, Date tFinishTime) {
		super();
		this.tId = tId;
		this.uIdSend = uIdSend;
		this.uTime = uTime;
		this.tcId = tcId;
		this.tDesc = tDesc;
		this.tCoinCount = tCoinCount;
		this.tState = tState;
		this.uIdAccept = uIdAccept;
		this.tagId = tagId;
		this.tEndTime = tEndTime;
		this.tImgUrl = tImgUrl;
		this.tAcceptTime = tAcceptTime;
		this.tFinishTime = tFinishTime;
	}




}
