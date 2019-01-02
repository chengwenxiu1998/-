package com.tb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.TaskImageBean;

/**
*@author 鏉庣編鎯�
*@version 鍒涘缓鏃堕棿:2018骞�12鏈�7鏃� 涓嬪崍9:09:35
*@ClassName 绫诲悕绉�
*@Description 绫绘弿杩�
*/

public class TaskImageDao {
	//鏁版嵁搴撴煡璇�
		public List<TaskImageBean> selectTaskImage(){
			List<TaskImageBean> taskImageBeanList=new ArrayList();
			Connection conn=DataBase.getConnection();
			String sql="Select ti_id,it_image from task_image";
			try {
				PreparedStatement pstmt=conn.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					TaskImageBean taskImageBean=new TaskImageBean();
					taskImageBean.setTiId(rs.getInt(1));
					taskImageBean.setItImage(rs.getString(2));
					taskImageBeanList.add(taskImageBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(taskImageBeanList);
			return taskImageBeanList;
			
		}
		
		//鏁版嵁搴撴坊鍔�
		public void addTaskImage(String itImage){
			
			Connection conn=DataBase.getConnection();
			String sql="insert into task_image(it_image) values(?)";
			PreparedStatement pstmt;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,itImage);
				
				pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//鏁版嵁搴撲慨鏀�
		public void updateTaskImage(int tiId,String column,String changedColumn) {
			
			Connection conn=DataBase.getConnection();
			String sql="update task_image set "+column+"=? where ti_id=?";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,changedColumn);
				pstmt.setInt(2,tiId);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		//鏁版嵁搴撳垹闄�
		public void deleteTask(int id) {
			Connection conn=DataBase.getConnection();
			String sql="delete from task_image where ti_id=?";
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
