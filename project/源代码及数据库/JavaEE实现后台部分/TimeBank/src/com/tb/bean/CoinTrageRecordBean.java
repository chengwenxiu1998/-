package com.tb.bean;

import java.util.Date;

/**
*@author ������
*@version ����ʱ��:2018��12��5�� ����10:10:28
*@ClassName ������
*@Description ������
*/

public class CoinTrageRecordBean {
	private int uId;
	private Date ctrFinishTime;
	private int ctrCount;
	private byte AddOrReduce;
	public CoinTrageRecordBean() {
		
	}
	public CoinTrageRecordBean(int uId, Date ctrFinishTime, int ctrCount, byte addOrReduce) {
		
		this.uId = uId;
		this.ctrFinishTime = ctrFinishTime;
		this.ctrCount = ctrCount;
		AddOrReduce = addOrReduce;
	}
	public int getuId() {
		return uId;
	}
	public Date getCtrFinishTime() {
		return ctrFinishTime;
	}
	public int getCtrCount() {
		return ctrCount;
	}
	public byte getAddOrReduce() {
		return AddOrReduce;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public void setCtrFinishTime(Date ctrFinishTime) {
		this.ctrFinishTime = ctrFinishTime;
	}
	public void setCtrCount(int ctrCount) {
		this.ctrCount = ctrCount;
	}
	public void setAddOrReduce(byte addOrReduce) {
		AddOrReduce = addOrReduce;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + AddOrReduce;
		result = prime * result + ctrCount;
		result = prime * result + ((ctrFinishTime == null) ? 0 : ctrFinishTime.hashCode());
		result = prime * result + uId;
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
		CoinTrageRecordBean other = (CoinTrageRecordBean) obj;
		if (AddOrReduce != other.AddOrReduce)
			return false;
		if (ctrCount != other.ctrCount)
			return false;
		if (ctrFinishTime == null) {
			if (other.ctrFinishTime != null)
				return false;
		} else if (!ctrFinishTime.equals(other.ctrFinishTime))
			return false;
		if (uId != other.uId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CoinTrageRecordBean [uId=" + uId + ", ctrFinishTime=" + ctrFinishTime + ", ctrCount=" + ctrCount
				+ ", AddOrReduce=" + AddOrReduce + "]";
	}
	
	

}
