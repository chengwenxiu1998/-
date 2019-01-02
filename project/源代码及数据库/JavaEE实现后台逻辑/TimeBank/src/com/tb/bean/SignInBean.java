package com.tb.bean;
/**
*@author ������
*@version ����ʱ��:2018��12��5�� ����10:36:16
*@ClassName ������
*@Description ������
*/

public class SignInBean {
	private int uId;
	private int signDayCount;
	private byte ifSignIn;
	private int finishCount;
	public SignInBean() {
	
	}
	
	public SignInBean(int uId, int signDayCount, byte ifSignIn, int finishCount) {
		super();
		this.uId = uId;
		this.signDayCount = signDayCount;
		this.ifSignIn = ifSignIn;
		this.finishCount = finishCount;
	}

	public int getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
	}

	public int getuId() {
		return uId;
	}
	public int getSignDayCount() {
		return signDayCount;
	}
	public byte getIfSignIn() {
		return ifSignIn;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public void setSignDayCount(int signDayCount) {
		this.signDayCount = signDayCount;
	}
	public void setIfSignIn(byte ifSignIn) {
		this.ifSignIn = ifSignIn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + finishCount;
		result = prime * result + ifSignIn;
		result = prime * result + signDayCount;
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
		SignInBean other = (SignInBean) obj;
		if (finishCount != other.finishCount)
			return false;
		if (ifSignIn != other.ifSignIn)
			return false;
		if (signDayCount != other.signDayCount)
			return false;
		if (uId != other.uId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SignInBean [uId=" + uId + ", signDayCount=" + signDayCount + ", ifSignIn=" + ifSignIn + ", finishCount="
				+ finishCount + "]";
	}
}
