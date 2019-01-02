package com.tb.bean;
/**
*@author ������
*@version ����ʱ��:2018��12��5�� ����10:57:21
*@ClassName ������
*@Description ������
*/

public class TaskImageBean {
	private int tiId;
	private String itImage;
	public TaskImageBean() {
	
	}
	public TaskImageBean(int tiId, String itImage) {
		
		this.tiId = tiId;
		this.itImage = itImage;
	}
	public int getTiId() {
		return tiId;
	}
	public String getItImage() {
		return itImage;
	}
	public void setTiId(int tiId) {
		this.tiId = tiId;
	}
	public void setItImage(String itImage) {
		this.itImage = itImage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itImage == null) ? 0 : itImage.hashCode());
		result = prime * result + tiId;
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
		TaskImageBean other = (TaskImageBean) obj;
		if (itImage == null) {
			if (other.itImage != null)
				return false;
		} else if (!itImage.equals(other.itImage))
			return false;
		if (tiId != other.tiId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TaskImageBean [tiId=" + tiId + ", itImage=" + itImage + "]";
	}
	
	
}
