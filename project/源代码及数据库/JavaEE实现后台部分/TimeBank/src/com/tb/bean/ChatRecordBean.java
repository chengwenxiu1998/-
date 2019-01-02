package com.tb.bean;
/**
*@author 李美�?
*@version 创建时间:2018�?12�?5�? 上午9:53:14
*@ClassName 类名�?
*@Description 类描�?
*/

public class ChatRecordBean {
	private int uId1;
	private int uId2;
	private String crRecordTextUrl;
	
	public ChatRecordBean() {
	
	}

	public ChatRecordBean(int uId1, int uId2, String crRecordTextUrl) {
		
		this.uId1 = uId1;
		this.uId2 = uId2;
		this.crRecordTextUrl = crRecordTextUrl;
	}

	public int getuId1() {
		return uId1;
	}

	public int getuId2() {
		return uId2;
	}

	public String getCrRecordTextUrl() {
		return crRecordTextUrl;
	}

	public void setuId1(int uId1) {
		this.uId1 = uId1;
	}

	public void setuId2(int uId2) {
		this.uId2 = uId2;
	}

	public void setCrRecordTextUrl(String crRecordTextUrl) {
		this.crRecordTextUrl = crRecordTextUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crRecordTextUrl == null) ? 0 : crRecordTextUrl.hashCode());
		result = prime * result + uId1;
		result = prime * result + uId2;
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
		ChatRecordBean other = (ChatRecordBean) obj;
		if (crRecordTextUrl == null) {
			if (other.crRecordTextUrl != null)
				return false;
		} else if (!crRecordTextUrl.equals(other.crRecordTextUrl))
			return false;
		if (uId1 != other.uId1)
			return false;
		if (uId2 != other.uId2)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChatRecord [uId1=" + uId1 + ", uId2=" + uId2 + ", crRecordTextUrl=" + crRecordTextUrl + "]";
	}
	
	
}

