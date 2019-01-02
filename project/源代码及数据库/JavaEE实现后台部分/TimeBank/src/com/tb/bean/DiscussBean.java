package com.tb.bean;
/**
*@author ������
*@version ����ʱ��:2018��12��5�� ����10:18:37
*@ClassName ������
*@Description ������
*/

public class DiscussBean {
private int dId;
private int tagId;
private int uIdLouZhu;
private String dTopicCoutent;
public DiscussBean() {
	
}
public DiscussBean(int dId, int tagId, int uIdLouZhu, String dTopicCoutent) {
	
	this.dId = dId;
	this.tagId = tagId;
	this.uIdLouZhu = uIdLouZhu;
	this.dTopicCoutent = dTopicCoutent;
}
public int getdId() {
	return dId;
}
public int getTagId() {
	return tagId;
}
public int getuIdLouZhu() {
	return uIdLouZhu;
}
public String getdTopicCoutent() {
	return dTopicCoutent;
}
public void setdId(int dId) {
	this.dId = dId;
}
public void setTagId(int tagId) {
	this.tagId = tagId;
}
public void setuIdLouZhu(int uIdLouZhu) {
	this.uIdLouZhu = uIdLouZhu;
}
public void setdTopicCoutent(String dTopicCoutent) {
	this.dTopicCoutent = dTopicCoutent;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + dId;
	result = prime * result + ((dTopicCoutent == null) ? 0 : dTopicCoutent.hashCode());
	result = prime * result + tagId;
	result = prime * result + uIdLouZhu;
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
	DiscussBean other = (DiscussBean) obj;
	if (dId != other.dId)
		return false;
	if (dTopicCoutent == null) {
		if (other.dTopicCoutent != null)
			return false;
	} else if (!dTopicCoutent.equals(other.dTopicCoutent))
		return false;
	if (tagId != other.tagId)
		return false;
	if (uIdLouZhu != other.uIdLouZhu)
		return false;
	return true;
}
@Override
public String toString() {
	return "DiscussBean [dId=" + dId + ", tagId=" + tagId + ", uIdLouZhu=" + uIdLouZhu + ", dTopicCoutent="
			+ dTopicCoutent + "]";
}

}
