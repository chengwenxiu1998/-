package com.tb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tb.bean.UserBean;

/**
 * @author ������
 * @version ����ʱ��:2018��12��7�� ����9:20:45
 * @ClassName ������
 * @Description ������
 */

public class UserDao {
	public void addUser1(int id,String uNickname,String uPhone,String uPassword) {
		Connection conn = DataBase.getConnection();
		String sql = "insert into users(u_id,u_nickname,u_phone,u_password) values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, uNickname);
			pstmt.setString(3, uPhone);
			pstmt.setString(4, uPassword);
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//�һ�����ʱ������������ֻ����޸�����
	public int updatePasswordByPhone(String phone, String password) {
		int execute = 0;
		Connection conn = DataBase.getConnection();
		String sql = "update users set u_password=? where u_phone=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, phone);
			execute = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBase.close(conn,pstmt,null);
		}
		
		return execute;
		
	}

	//�����ֻ��ź������ѯ���û�����ʵ�ֵ�¼����
	public UserBean findUser(String phone,String pwd) {
		UserBean user = null;
		String sql = "select * from users where u_phone=? and u_password=?";
		Connection conn = DataBase.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, pwd);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				int uId = res.getInt("u_id");
				String uName = res.getString("u_name");
				String uPhone = res.getString("u_phone");
				byte uSex = res.getByte("u_sex");
				String uArea = res.getString("u_area");
				String uNickName = res.getString("u_nickname");
				String uImage = res.getString("u_image");
				String uPassword = res.getString("u_password");
				String uIdCard = res.getString("u_id_card");
				int uCoin = res.getInt("u_coin");
				
				user = new UserBean(uId,uName,uPhone,uSex,uArea,uNickName,uImage,uPassword,uIdCard,uCoin);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	// ���ݿ��ѯ
	public List<UserBean> selectUser() {
		List<UserBean> userBeanList = new ArrayList<>();
		Connection conn = DataBase.getConnection();
		String sql = "Select u_id,u_name,u_phone,u_sex,u_area,u_nickname,u_image,u_password,u_id_card from users";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserBean userBean = new UserBean();
				userBean.setuId(rs.getInt(1));
				userBean.setuName(rs.getString(2));
				userBean.setuPhone(rs.getString(3));
				userBean.setuSex(rs.getByte(4));
				userBean.setuArea(rs.getString(5));
				userBean.setuNickName(rs.getString(6));
				userBean.setuImage(rs.getString(7));
				userBean.setuPassword(rs.getString(8));
				userBean.setuIdCard(rs.getString(9));
				userBeanList.add(userBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userBeanList);
		return userBeanList;

	}

	// ���ݿ����
	public void addUser(int uId, String uName, String uPhone, byte uSex, String uArea, String uNickname, String uImage,
			String uPassword, String uIdCard) {

		Connection conn = DataBase.getConnection();
		String sql = "insert into users(u_id,u_name,u_phone,u_sex,u_area,u_nickname,u_image,u_password,u_id_card) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uId);
			pstmt.setString(2, uName);
			pstmt.setString(3, uPhone);
			pstmt.setByte(4, uSex);
			pstmt.setString(5, uArea);
			pstmt.setString(6, uNickname);
			pstmt.setString(7, uImage);
			pstmt.setString(8, uPassword);
			pstmt.setString(9, uIdCard);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ���ݿ��޸�
	public void updateUser(int uId, String column, String changedColumn) {

		Connection conn = DataBase.getConnection();
		String sql = "update users set " + column + "=? where u_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, changedColumn);
			pstmt.setInt(2, uId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBase.close(conn, pstmt, null);
		}

	}

	// ���ݿ�ɾ��
	public void deleteUser(int id) {
		Connection conn = DataBase.getConnection();
		String sql = "delete from users where u_id=?";
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

	//�����ݿ��в��Ҹ��ֻ����Ƿ���ע��
	public boolean findPhone(String phone) {
		boolean isHaveThePhone = false;
		String sql = "select * from users where u_phone=?";
		Connection conn = DataBase.getConnection();
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, phone);
			res = stmt.executeQuery();
			if(res.next()) {//�鵽���û�
				isHaveThePhone = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBase.close(conn,stmt,res);
		}
		return isHaveThePhone;
	}

	//�����û�uid�޸�����
	public int updatePasswordByUid(String uid, String password) {
		int execute = 0;
		Connection conn = DataBase.getConnection();
		String sql = "update users set u_password=? where u_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, uid);
			execute = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBase.close(conn,pstmt,null);
		}
		
		return execute;
		
	}

	//�����û�uid�޸ĵ绰
	public int updatePhoneByUid(String uid, String phone) {
		int execute = 0;
		Connection conn = DataBase.getConnection();
		String sql = "update users set u_phone=? where u_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, uid);
			execute = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBase.close(conn,pstmt,null);
		}
		
		return execute;
	}

	//�����û�uid�޸ĵ���
	public int updateAreaByUid(String uid, String area) {
		int execute = 0;
		Connection conn = DataBase.getConnection();
		String sql = "update users set u_area=? where u_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, area);
			pstmt.setString(2, uid);
			execute = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBase.close(conn,pstmt,null);
		}
		
		return execute;
	}

	//����uid��ѯ�ǳ�
	public String findNicknameByUid(int uid) {
		String nickname = null;
		Connection conn = DataBase.getConnection();
		String sql = "select u_nickname from users where u_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				nickname = res.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBase.close(conn,pstmt,null);
		}
		
		return nickname;
	}
	
	//����uid��ѯͷ��
		public String findHeadImgByUid(int uid) {
			String image = null;
			Connection conn = DataBase.getConnection();
			String sql = "select u_image from users where u_id=?";
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, uid);
				
				ResultSet res = pstmt.executeQuery();
				while (res.next()) {
					image = res.getString(1);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DataBase.close(conn,pstmt,null);
			}
			
			return image;
		}
	
		
		//����id�����ǳ�
		public String selectPetName(int uID) {
			String text=null;
			Connection conn=DataBase.getConnection();
			String sql="select u_nickname from users where u_id=?";
			PreparedStatement pstmt=null;
		    ResultSet rs = null;
		    try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, uID);
				rs=pstmt.executeQuery();
				while(rs.next()){
					text=rs.getString("u_nickname");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
					return text;
		}
		
		//����id�����ǳ�
		public String selectName(int uid) {
			String name=null;
			Connection conn=DataBase.getConnection();
			String sql="select u_nickname from users where u_id=?";
			PreparedStatement pstmt=null;
		    ResultSet rs = null;
		    try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,uid);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					name=rs.getString("u_nickname");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return name;
		}
		//����uid��ѯ��ַ
				public String findAddressByUid(int uid) {
					String address = null;
					Connection conn = DataBase.getConnection();
					String sql = "select u_area from users where u_id=?";
					PreparedStatement pstmt = null;
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, uid);
						
						ResultSet res = pstmt.executeQuery();
						while (res.next()) {
							address = res.getString(1);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						DataBase.close(conn,pstmt,null);
					}
					
					return address;
				}

				//����uid����ʵ����֤��������ʵ���������֤��
				public int realNameAuthenticationByUid(int uid, String name, String idCard) {
					int execute = 0;
					Connection conn = DataBase.getConnection();
					String sql = "update users set u_id_card=?,u_name=? where u_id=?";
					PreparedStatement pstmt = null;
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, idCard);
						pstmt.setString(2, name);
						pstmt.setInt(3, uid);
						
						execute = pstmt.executeUpdate();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						DataBase.close(conn,pstmt,null);
					}
					
					return execute;
					
				}
}
