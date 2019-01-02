package com.tb.bean;
/**
*@author ������
*@version ����ʱ��:2018��12��5�� ����10:38:22
*@ClassName ������
*@Description ������
*/

public class TagBean {
	private int tagId;
	private String tagText;
	
	public TagBean() {
		
	}

	public TagBean(int tagId, String tagText) {
		
		this.tagId = tagId;
		this.tagText = tagText;
	}

	public int getTagId() {
		return tagId;
	}

	public String getTagText() {
		return tagText;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tagId;
		result = prime * result + ((tagText == null) ? 0 : tagText.hashCode());
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
		TagBean other = (TagBean) obj;
		if (tagId != other.tagId)
			return false;
		if (tagText == null) {
			if (other.tagText != null)
				return false;
		} else if (!tagText.equals(other.tagText))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TagBean [tagId=" + tagId + ", tagText=" + tagText + "]";
	}
	
	
	
}
