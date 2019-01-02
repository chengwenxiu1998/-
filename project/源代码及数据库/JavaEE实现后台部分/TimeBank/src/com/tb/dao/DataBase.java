package com.tb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
*@author ������
*@version ����ʱ��:2018��12��6�� ����10:07:05
*@ClassName ������
*@Description ������
*/

public class DataBase {
	/*
	 * ��ȡ�����ݿ������
	 */
	public static Connection getConnection() {
		Connection conn=null;
		try {
			//1����������
			Class.forName("com.mysql.jdbc.Driver");
			//2����������
//			conn=DriverManager.getConnection("jdbc:mysql://106.12.151.7:3306/timebank?useUnicode=true&characterEncoding=UTF-8","root","timebank");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/timebank?useUnicode=true&characterEncoding=UTF-8","root","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	//�ر����ݿ�
	public static void close(Connection conn,Statement stmt,ResultSet res) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(res!=null) {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
