package com.tb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.ShaiShaiCommentBean;

/**
*@author ������
*@version ����ʱ��:2018��12��6�� ����8:44:21
*@ClassName ������
*@Description ������
*/

public class ShaiShaiCommentDao {
	public void addComent(int sid,int uid,Timestamp time,String content) {
		Connection conn=DataBase.getConnection();
		String sql="insert into shaishai_comment(s_id,u_id_comment,c_comment_time,c_comment_content) values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,sid);
			pstmt.setInt(2, uid);
			pstmt.setTimestamp(3, time);
			pstmt.setString(4, content);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List commentCountt() {
		List<ShaiShaiCommentBean> list=new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql="select s_id,u_id_comment,c_comment_time,c_comment_content  from shaishai_comment";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				ShaiShaiCommentBean shaishaicomment=new ShaiShaiCommentBean();
				shaishaicomment.setsId(rs.getInt(1));
				shaishaicomment.setuIdComment(rs.getInt(2));
				shaishaicomment.setcCommentTime(rs.getTimestamp(3));
				shaishaicomment.setcCommentContent(rs.getString(4));
				list.add(shaishaicomment);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	//����Sid���Ҹ���
	public List commentCount(int Sid) {
		List<ShaiShaiCommentBean> list=new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql="select s_id,u_id_comment,c_comment_time,c_comment_content  from shaishai_comment where s_id=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Sid);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				ShaiShaiCommentBean shaishaicomment=new ShaiShaiCommentBean();
				shaishaicomment.setsId(rs.getInt(1));
				shaishaicomment.setuIdComment(rs.getInt(2));
				shaishaicomment.setcCommentTime(rs.getTimestamp(3));
				shaishaicomment.setcCommentContent(rs.getString(4));
				list.add(shaishaicomment);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//���ݿ��ѯ
	public List<ShaiShaiCommentBean> selectShaiShaiComment(){
		List<ShaiShaiCommentBean> shaishaiCommentBeanList=new ArrayList();
		Connection conn=DataBase.getConnection();
		String sql="Select s_id,u_id_comment,c_comment_time,c_comment_content from shaishai_comment";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				ShaiShaiCommentBean shaishaiCommentBean=new  ShaiShaiCommentBean();
				shaishaiCommentBean.setsId(rs.getInt(1));
				shaishaiCommentBean.setuIdComment(rs.getInt(2));
				shaishaiCommentBean.setcCommentTime(rs.getDate(3));
				shaishaiCommentBean.setcCommentContent(rs.getString(4));
				shaishaiCommentBeanList.add(shaishaiCommentBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(shaishaiCommentBeanList);
		return shaishaiCommentBeanList;
		
	}
	
	//���ݿ����
	public void addShaiShaiComment(int sId,int uIdComment,Date cCommentTime,String cCommentContent){
		
		Connection conn=DataBase.getConnection();
		String sql="insert into shaishai_comment(s_id,u_id_comment,c_comment_time,c_comment_content) values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,sId);
			pstmt.setInt(2,uIdComment);
			pstmt.setDate(3,cCommentTime);
			pstmt.setString(4,cCommentContent);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//���ݿ��޸�
	public void updateShaiShaiComment(int sId,String column,String changedColumn) {
		
		Connection conn=DataBase.getConnection();
		String sql="update shaishai_comment set "+column+"=? where s_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,changedColumn);
			pstmt.setInt(2,sId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//���ݿ�ɾ��
	public void deleteShaiShai(int id) {
		Connection conn=DataBase.getConnection();
		String sql="delete from shaishai_comment where s_id=?";
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
