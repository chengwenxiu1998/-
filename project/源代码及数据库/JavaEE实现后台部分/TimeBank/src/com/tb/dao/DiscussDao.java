package com.tb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.DiscussBean;

/**
*@author ������
*@version ����ʱ��:2018��12��6�� ����7:03:50
*@ClassName ������
*@Description ������
*/

public class DiscussDao {
	//���ݿ��ѯ
		public List<DiscussBean> selectDiscuss(){
			List<DiscussBean> discussBeanList=new ArrayList();
			Connection conn=DataBase.getConnection();
			String sql="Select d_id,tag_id,u_id_louzhu,d_topic_content from discuss";
			try {
				PreparedStatement pstmt=conn.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					DiscussBean discussBean=new DiscussBean();
					discussBean.setdId(rs.getInt(1));
					discussBean.setTagId(rs.getInt(2));
					discussBean.setuIdLouZhu(rs.getInt(3));
					discussBean.setdTopicCoutent(rs.getString(4));
					discussBeanList.add(discussBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return discussBeanList;
			
		}
		
		//���ݿ����
		public void addDiscuss(int dId,int tagId,int uIdLouzhu,String dTopicContent){
			
			Connection conn=DataBase.getConnection();
			String sql="insert into discuss(d_id,tag_id,u_id_louzhu,d_topic_content) values(?,?,?,?)";
			PreparedStatement pstmt;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,dId);
				pstmt.setInt(2,tagId);
				pstmt.setInt(3,uIdLouzhu);
				pstmt.setString(4,dTopicContent);
				pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//���ݿ��޸�
		public void updateDiscuss(int dId,String column,String changedColumn) {
			
			Connection conn=DataBase.getConnection();
			String sql="update discuss set "+column+"=? where d_id=?";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,changedColumn);
				pstmt.setInt(2,dId);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		//���ݿ�ɾ��
		public void deleteDiscuss(int id) {
			Connection conn=DataBase.getConnection();
			String sql="delete from discuss where d_id=?";
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
}
