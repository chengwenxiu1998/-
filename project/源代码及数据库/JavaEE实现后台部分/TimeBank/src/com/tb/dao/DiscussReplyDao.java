package com.tb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.DiscussReplyBean;

/**
*@author ������
*@version ����ʱ��:2018��12��6�� ����7:32:56
*@ClassName ������
*@Description ������
*/

public class DiscussReplyDao {
	public void addDiscussReply(int dId,int uIdReply,String drReplyContent,Timestamp drReplyTime){
		
		Connection conn=DataBase.getConnection();
		String sql="insert into discuss_reply(d_id,u_id_reply,dr_reply_content,dr_reply_time) values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,dId);
			pstmt.setInt(2,uIdReply);
			pstmt.setString(3,drReplyContent);
			pstmt.setTimestamp(4,drReplyTime);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//���ݿ��ѯ
			public List<DiscussReplyBean> selectDiscussReply(){
				List<DiscussReplyBean> discussReplyBeanList=new ArrayList();
				Connection conn=DataBase.getConnection();
				String sql="Select d_id,u_id_reply,dr_reply_content,dr_reply_time from discuss_reply";
				try {
					PreparedStatement pstmt=conn.prepareStatement(sql);
					ResultSet rs=pstmt.executeQuery();
					while(rs.next()) {
						DiscussReplyBean discussReplyBean=new DiscussReplyBean();
						discussReplyBean.setdId(rs.getInt(1));
						discussReplyBean.setuIdReply(rs.getInt(2));
						discussReplyBean.setDrReplyContent(rs.getString(3));
						discussReplyBean.setDrReplyTime(rs.getDate(4));
						discussReplyBeanList.add(discussReplyBean);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return discussReplyBeanList;
				
			}
			
			//���ݿ����
			public void addDiscussReply(int dId,int uIdReply,String drReplyContent,Date drReplyTime){
				
				Connection conn=DataBase.getConnection();
				String sql="insert into discuss_reply(d_id,u_id_reply,dr_reply_content,dr_reply_time) values(?,?,?,?)";
				PreparedStatement pstmt;
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1,dId);
					pstmt.setInt(2,uIdReply);
					pstmt.setString(3,drReplyContent);
					pstmt.setDate(4,drReplyTime);
					pstmt.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			//���ݿ��޸�
			public void updateDiscussReply(int dId,String column,String changedColumn) {
				
				Connection conn=DataBase.getConnection();
				String sql="update discuss_reply set "+column+"=? where d_id=?";
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
			public void deleteDiscussReply(int id) {
				Connection conn=DataBase.getConnection();
				String sql="delete from discuss_reply where d_id=?";
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
			
			//����id���һظ�����
				public List<DiscussReplyBean> selectDiscussReplys(int did){
					List<DiscussReplyBean> discussReplyBeanList=new ArrayList();
					Connection conn=DataBase.getConnection();
					String sql="Select d_id,u_id_reply,dr_reply_content,dr_reply_time from discuss_reply where d_id=?";
					try {
						PreparedStatement pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, did);
						ResultSet rs=pstmt.executeQuery();
						while(rs.next()) {
							DiscussReplyBean discussReplyBean=new DiscussReplyBean();
							discussReplyBean.setdId(rs.getInt(1));
							discussReplyBean.setuIdReply(rs.getInt(2));
							discussReplyBean.setDrReplyContent(rs.getString(3));
							discussReplyBean.setDrReplyTime(rs.getTimestamp(4));
							discussReplyBeanList.add(discussReplyBean);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					return discussReplyBeanList;
						
				}
}
