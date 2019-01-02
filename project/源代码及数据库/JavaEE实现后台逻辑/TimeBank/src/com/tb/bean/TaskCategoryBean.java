package com.tb.bean;
/**
*@author ������
*@version ����ʱ��:2018��12��5�� ����10:53:59
*@ClassName ������
*@Description ������
*/

public class TaskCategoryBean {
	private int tcId;
	private String tcName;
	public TaskCategoryBean() {
		
	}
	public TaskCategoryBean(int tcId, String tcName) {
		
		this.tcId = tcId;
		this.tcName = tcName;
	}
	public int getTcId() {
		return tcId;
	}
	public String getTcName() {
		return tcName;
	}
	public void setTcId(int tcId) {
		this.tcId = tcId;
	}
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tcId;
		result = prime * result + ((tcName == null) ? 0 : tcName.hashCode());
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
		TaskCategoryBean other = (TaskCategoryBean) obj;
		if (tcId != other.tcId)
			return false;
		if (tcName == null) {
			if (other.tcName != null)
				return false;
		} else if (!tcName.equals(other.tcName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TaskCategory [tcId=" + tcId + ", tcName=" + tcName + "]";
	}
	
	
}
