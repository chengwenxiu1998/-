package com.tb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.TaskBean;

import java.sql.Timestamp;

/**
*@author ������
*@version ����ʱ��:2018��12��7�� ����8:29:46
*@ClassName ������
*@Description ������
*/

public class TaskDao {
	//���ݿ����
	public void addTask(int uIdSend,Timestamp date,int tcId,String tDesc,int tCoinCount,String tState,int uIdAccept,int tagId,Timestamp tEndtime,String t_imgurl,Timestamp date2,Timestamp date3){
		
		Connection conn=DataBase.getConnection();
		String sql="insert into task(u_id_send,u_time,tc_id,t_desc,t_coin_count,t_state,u_id_accept,tag_id,t_endtime,t_accept_time,t_finish_time,t_imgurl) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, uIdSend);
			pstmt.setTimestamp(2,date);
			pstmt.setInt(3,tcId);
			pstmt.setString(4,tDesc);
			pstmt.setInt(5,tCoinCount);
			pstmt.setString(6,tState);
			pstmt.setInt(7,uIdAccept);
			pstmt.setInt(8,tagId);
			pstmt.setTimestamp(9,tEndtime);
			
			pstmt.setTimestamp(10,date2);
			pstmt.setTimestamp(11,date3);
			pstmt.setString(12,t_imgurl);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//��ѯ���û��ѷ���������,���������գ�����ɺͽ�����
	public List<TaskBean> findMySend(int uid) {
		List<TaskBean> tasks = new ArrayList();
		Connection conn = DataBase.getConnection();
		String sql = "Select * from task where u_id_send=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TaskBean task = new TaskBean();
				task.settId(rs.getInt(1));
				task.setuIdSend(rs.getInt(2));
				task.setuTime(rs.getDate(3));
				task.setTcId(rs.getInt(4));
				task.settDesc(rs.getString(5));
				task.settCoinCount(rs.getInt(6));
				task.settState(rs.getString(7));
				task.setuIdAccept(rs.getInt(8));
				task.setTagId(rs.getInt(9));
				task.settEndTime(rs.getDate(10));
				task.settAcceptTime(rs.getDate(11));
				task.settFinishTime(rs.getDate(12));
				task.settImgUrl(rs.getString(13));
				tasks.add(task);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tasks;
	}
	//��ѯ���û��ѽ��յ�����,��������ɺͽ�����
	public List<TaskBean> findMyReceive(int uid){
		List<TaskBean> tasks = new ArrayList();
		Connection conn = DataBase.getConnection();
		String sql = "Select * from task where u_id_accept=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TaskBean task = new TaskBean();
				
				task.settId(rs.getInt(1));
				task.setuIdSend(rs.getInt(2));
				task.setuTime(rs.getDate(3));
				task.setTcId(rs.getInt(4));
				task.settDesc(rs.getString(5));
				task.settCoinCount(rs.getInt(6));
				task.settState(rs.getString(7));
				task.setuIdAccept(rs.getInt(8));
				task.setTagId(rs.getInt(9));
				task.settEndTime(rs.getDate(10));
				task.settAcceptTime(rs.getDate(11));
				task.settFinishTime(rs.getDate(12));
				task.settImgUrl(rs.getString(13));
				
				tasks.add(task);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tasks;
		
	}
	//���ݿ��ѯ
			public List<TaskBean> selectTask(){
				List<TaskBean> taskBeanList=new ArrayList();
				Connection conn=DataBase.getConnection();
				String sql="Select t_id,u_id_send,u_time,tc_id,t_desc,t_coin_count,t_state,u_id_accept,tag_id from task";
				try {
					PreparedStatement pstmt=conn.prepareStatement(sql);
					ResultSet rs=pstmt.executeQuery();
					while(rs.next()) {
						TaskBean taskBean=new TaskBean();
						taskBean.setTagId(rs.getInt(1));
						taskBean.setuIdSend(rs.getInt(2));
						taskBean.setuTime(rs.getDate(3));
						taskBean.setTcId(rs.getInt(4));
						taskBean.settDesc(rs.getString(5));
						taskBean.settCoinCount(rs.getInt(6));
						taskBean.settState(rs.getString(7));
						taskBean.setuIdAccept(rs.getInt(8));
						taskBean.setTagId(rs.getInt(9));
						
						taskBeanList.add(taskBean);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(taskBeanList);
				return taskBeanList;
				
			}
			
			//���ݿ����
			public void addTask(int tId,int uIdSend,Date uTime,int tcId,String tDesc,int tCoinCount,String tState,int uIdAccept,int tagId){
				
				Connection conn=DataBase.getConnection();
				String sql="insert into task(t_id,u_id_send,u_time,tc_id,t_desc,t_coin_count,t_state,u_id_accept,tag_id) values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt;
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1,tId);
					pstmt.setInt(2, uIdSend);
					pstmt.setDate(3,uTime);
					pstmt.setInt(4,tcId);
					pstmt.setString(5,tDesc);
					pstmt.setInt(6,tCoinCount);
					pstmt.setString(7,tState);
					pstmt.setInt(8,uIdAccept);
					pstmt.setInt(9,tagId);
					pstmt.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			//���ݿ��޸�
			public int updateTask(int tId,String column,Object changedColumn) {
				int update = 0;
				Connection conn=DataBase.getConnection();
				String sql="update task set "+column+"=? where t_id=?";
				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setObject(1,changedColumn);
					pstmt.setInt(2,tId);
					update = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return update;
				
			}
			
			//���ݿ�ɾ��
			public void deleteTask(int id) {
				Connection conn=DataBase.getConnection();
				String sql="delete from task where t_id=?";
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
			//���Ľ���ʱ��
			public void updateTaskTime1(String tId,String column,Timestamp changedColumn) {
				
				Connection conn=DataBase.getConnection();
				String sql="update task set "+column+"=? where t_id=?";
				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setTimestamp(1,changedColumn);
					pstmt.setString(2,tId);
					pstmt.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
}
