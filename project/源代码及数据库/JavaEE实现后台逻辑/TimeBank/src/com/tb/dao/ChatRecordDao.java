package com.tb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.ChatRecordBean;

/**
*@author ������
*@version ����ʱ��:2018��12��6�� ����10:06:00
*@ClassName ������
*@Description ������
*/

public class ChatRecordDao {
	//���ݿ��ѯ
	public List<ChatRecordBean> selectChatRecord(){
		List<ChatRecordBean> chatRecordList=new ArrayList();
		Connection conn=DataBase.getConnection();
		String sql="Select u_id_1,u_id_2,cr_record_text_url from chat_record";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				ChatRecordBean chatRecordBean=new ChatRecordBean();
				chatRecordBean.setuId1(rs.getInt(1));
				chatRecordBean.setuId2(rs.getInt(2));
				chatRecordBean.setCrRecordTextUrl(rs.getString(3));
				chatRecordList.add(chatRecordBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chatRecordList;
	}
	
	//���ݿ����
	public void addChatRecord(int uId1,int uId2,String crRecordTextUrl){
		
		Connection conn=DataBase.getConnection();
		String sql="insert into chat_record(u_id_1,u_id_2,cr_record_text_url) values(?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,uId1);
			pstmt.setInt(2, uId2);
			pstmt.setString(3,crRecordTextUrl);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//���ݿ��޸�
	public void updateChatRecord(int uId1,String column,String changedColumn) {
		
		Connection conn=DataBase.getConnection();
		String sql="update chat_record set "+column+"=? where u_id_1=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,changedColumn);
			pstmt.setInt(2,uId1);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//���ݿ�ɾ��
	public void deleteChatRecord(int id) {
		Connection conn=DataBase.getConnection();
		String sql="delete from chat_record where u_id_1=?";
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
