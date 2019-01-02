package com.tb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.TagBean;

/**
*@author ������
*@version ����ʱ��:2018��12��7�� ����7:54:50
*@ClassName ������
*@Description ������
*/

public class TagDao {
	//��������id��ѯ�����ǩ
	public String findTagnameByTid(int tid) {
		String tagName = null;
		Connection conn = DataBase.getConnection();
		String sql = "select tag_text from tag where tag_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tid);
			
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				tagName = res.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBase.close(conn,pstmt,null);
		}
		
		return tagName;
	}
	//���ݿ��ѯ
		public List<TagBean> selectTag(){
			List<TagBean> tagBeanList=new ArrayList();
			Connection conn=DataBase.getConnection();
			String sql="Select tag_id,tag_text from tag";
			try {
				PreparedStatement pstmt=conn.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					TagBean tagBean=new  TagBean();
					tagBean.setTagId(rs.getInt(1));
					tagBean.setTagText(rs.getString(2));
					tagBeanList.add(tagBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(tagBeanList);
			return tagBeanList;
			
		}
		
		//���ݿ����
		public void addTag(int tagId,String tagText){
			
			Connection conn=DataBase.getConnection();
			String sql="insert into tag(tag_id,tag_text) values(?,?)";
			PreparedStatement pstmt;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,tagId);
				pstmt.setString(2,tagText);
				pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//���ݿ��޸�
		public void updateTag(int tagId,String column,String changedColumn) {
			
			Connection conn=DataBase.getConnection();
			String sql="update tag set "+column+"=? where tag_id=?";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,changedColumn);
				pstmt.setInt(2,tagId);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		//���ݿ�ɾ��
		public void deleteTag(int id) {
			Connection conn=DataBase.getConnection();
			String sql="delete from tag where tag_id=?";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//����Tid����Tag����
				public String selectContent(int tID) {
					String text=null;
					Connection conn=DataBase.getConnection();
					String sql="select tag_text from tag where tag_id=?";
					PreparedStatement pstmt=null;
				    ResultSet rs = null;
				    try {
						pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, tID);
						rs=pstmt.executeQuery();
						while(rs.next()){
							text=rs.getString("tag_text");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
							return text;
				}
}
