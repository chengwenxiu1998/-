package com.tb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.TaskCategoryBean;

/**
*@author ������
*@version ����ʱ��:2018��12��7�� ����8:57:54
*@ClassName ������
*@Description ������
*/

public class TaskCategoryDao {
	//���ݿ��ѯ
	public List<TaskCategoryBean> selectTaskCategory(){
		List<TaskCategoryBean> taskCategoryBeanList=new ArrayList();
		Connection conn=DataBase.getConnection();
		String sql="Select tc_id,tc_name from task_category";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				TaskCategoryBean taskCategoryBean=new TaskCategoryBean();
				taskCategoryBean.setTcId(rs.getInt(1));
				taskCategoryBean.setTcName(rs.getString(2));
				taskCategoryBeanList.add(taskCategoryBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(taskCategoryBeanList);
		return taskCategoryBeanList;
		
	}
	
	//���ݿ����
	public void addTaskCategory(int tcId,String tcName){
		
		Connection conn=DataBase.getConnection();
		String sql="insert into task_category(tc_id,tc_name) values(?,?)";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,tcId);
			pstmt.setString(2, tcName);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//���ݿ��޸�
	public void updateTaskCategory(int tcId,String column,String changedColumn) {
		
		Connection conn=DataBase.getConnection();
		String sql="update task_category set "+column+"=? where tc_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,changedColumn);
			pstmt.setInt(2,tcId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//���ݿ�ɾ��
	public void deleteTaskCategory(int id) {
		Connection conn=DataBase.getConnection();
		String sql="delete from task_category where tc_id=?";
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
