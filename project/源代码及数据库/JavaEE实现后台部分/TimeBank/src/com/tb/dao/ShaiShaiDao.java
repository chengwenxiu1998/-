package com.tb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tb.bean.ShaiShaiBean;

/**
*@author ������
*@version ����ʱ��:2018��12��6�� ����8:19:47
*@ClassName ������
*@Description ������
*/

public class ShaiShaiDao {
	//���ݿ��ѯ
			public List<ShaiShaiBean> selectShaiShai(){
				List<ShaiShaiBean> shaishaiBeanList=new ArrayList();
				Connection conn=DataBase.getConnection();
				String sql="Select s_id,u_id,s_text,s_time,s_count from shaishai";
				try {
					PreparedStatement pstmt=conn.prepareStatement(sql);
					ResultSet rs=pstmt.executeQuery();
					while(rs.next()) {
						ShaiShaiBean shaishaiBean=new  ShaiShaiBean();
						shaishaiBean.setSid(rs.getInt(1));
						shaishaiBean.setUid(rs.getInt(2));
						shaishaiBean.setStext(rs.getString(3));
						shaishaiBean.setTime(rs.getTimestamp(4));
						shaishaiBean.setCount(rs.getInt(5));
						shaishaiBeanList.add(shaishaiBean);
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(shaishaiBeanList);
				return shaishaiBeanList;
				
			}
			
			//���ݿ����
			public void addShaiShai(int sId,int uId,String content,Timestamp time,int sLikeCount){
				
				Connection conn=DataBase.getConnection();
				String sql="insert into shaishai(s_id,u_id,s_text,s_time,s_count) values(?,?,?,?,?)";
				PreparedStatement pstmt;
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1,sId);
					pstmt.setInt(2,uId);
					pstmt.setString(3,content);
					pstmt.setTimestamp(4,time);
					pstmt.setInt(5,sLikeCount);
					pstmt.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
//			//���ݿ��޸�
//			public void updateShaiShai(int sId,String column,String changedColumn) {
//				
//				Connection conn=DataBase.getConnection();
//				String sql="update shaishai set "+column+"=? where s_id=?";
//				PreparedStatement pstmt;
//				try {
//					pstmt = conn.prepareStatement(sql);
//					pstmt.setString(1,changedColumn);
//					pstmt.setInt(2,sId);
//					pstmt.executeUpdate();
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				
//			}
//			
//			//���ݿ�ɾ��
//			public void deleteShaiShai(int id) {
//				Connection conn=DataBase.getConnection();
//				String sql="delete from shaishai where s_id=?";
//				PreparedStatement pstmt;
//				try {
//					pstmt = conn.prepareStatement(sql);
//					pstmt.setInt(1, id);
//					pstmt.executeUpdate();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			
			public int updateZan(int sid,int count) {
				int change=0;
				Connection conn=DataBase.getConnection();
				String sql="update shaishai set s_count=? where s_id=?";
				PreparedStatement pstmt;
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, count);
					pstmt.setInt(2, sid);
					change=pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return change;
			}
}
