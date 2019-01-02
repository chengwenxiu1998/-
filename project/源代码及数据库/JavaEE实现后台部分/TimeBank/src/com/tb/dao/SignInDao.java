package com.tb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.SignInBean;

/**
*@author ������
*@version ����ʱ��:2018��12��7�� ����7:32:02
*@ClassName ������
*@Description ������
*/

public class SignInDao {
	//����uid��ѯ���û���ǩ�����
	public SignInBean findSignInByUid(int uid) {
		SignInBean signInInfo = new SignInBean();
		Connection conn=DataBase.getConnection();
		String sql = "select * from sign_in where u_id=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int int1 = res.getInt(1);
				int int2 = res.getInt(2);
				byte byte1 = res.getByte(3);
				int int3 = res.getInt(4);
				signInInfo.setuId(int1);
				signInInfo.setSignDayCount(int2);
				signInInfo.setIfSignIn(byte1);
				signInInfo.setFinishCount(int3);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return signInInfo;
	}
	//���ݿ��ѯ
	public List<SignInBean> selectSignIn(){
		List<SignInBean> signInBeanList=new ArrayList();
		Connection conn=DataBase.getConnection();
		String sql="Select u_id,sign_day_count,if_sign_in from sign_in";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				SignInBean signInBean=new  SignInBean();
				signInBean.setuId(rs.getInt(1));
				signInBean.setSignDayCount(rs.getInt(2));
				signInBean.setIfSignIn(rs.getByte(3));
				signInBeanList.add(signInBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(signInBeanList);
		return signInBeanList;
		
	}
	
	//���ݿ����
	public void addSignIn(int uId,int signDayCount,byte ifSignIn){
		
		Connection conn=DataBase.getConnection();
		String sql="insert into sign_in(u_id,sign_day_count,if_sign_in) values(?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,uId);
			pstmt.setInt(2,signDayCount);
			pstmt.setByte(3,ifSignIn);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//���ݿ��޸�
	public void updateSignIn(int uId,String column,String changedColumn) {
		
		Connection conn=DataBase.getConnection();
		String sql="update sign_in set "+column+"=? where u_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,changedColumn);
			pstmt.setInt(2,uId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//���ݿ�ɾ��
	public void deleteShaiShai(int id) {
		Connection conn=DataBase.getConnection();
		String sql="delete from sign_in where u_id=?";
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
