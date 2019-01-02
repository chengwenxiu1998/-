package com.tb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.tb.bean.CoinTrageRecordBean;

/**
 * @author ������
 * @version ����ʱ��:2018��12��6�� ����5:19:18
 * @ClassName ������
 * @Description ������
 */

public class CoinTrageRecordDao {
	// �����û�uid��ѯ��ҽ��׼�¼
	public List<CoinTrageRecordBean> findCoinRecordByUid(int uid) {
		List<CoinTrageRecordBean> coinTrageRecordList = new ArrayList();
		Connection conn = DataBase.getConnection();
		String sql = "Select * from coin_trage_record where u_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CoinTrageRecordBean coinTrageRecordBean = new CoinTrageRecordBean();
				coinTrageRecordBean.setuId(rs.getInt(1));
				coinTrageRecordBean.setCtrFinishTime(rs.getDate(2));
				coinTrageRecordBean.setCtrCount(rs.getInt(3));
				coinTrageRecordBean.setAddOrReduce(rs.getByte(4));
				coinTrageRecordList.add(coinTrageRecordBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return coinTrageRecordList;
	}

	// ���ݿ��ѯ
	public List<CoinTrageRecordBean> selectCoinTrageRecord() {
		List<CoinTrageRecordBean> coinTrageRecordList = new ArrayList();
		Connection conn = DataBase.getConnection();
		String sql = "Select u_id,ctr_finish_time,ctr_count,add_or_reduce from coin_trage_record";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CoinTrageRecordBean coinTrageRecordBean = new CoinTrageRecordBean();
				coinTrageRecordBean.setuId(rs.getInt(1));
				coinTrageRecordBean.setCtrFinishTime(rs.getDate(2));
				coinTrageRecordBean.setCtrCount(rs.getInt(3));
				coinTrageRecordBean.setAddOrReduce(rs.getByte(4));
				coinTrageRecordList.add(coinTrageRecordBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return coinTrageRecordList;
	}

	// ���ݿ����
	public void addCoinTrageRecord(int uId, Date ctrFinishTime, int ctrCount, byte addOrReduce) {

		Connection conn = DataBase.getConnection();
		String sql = "insert into coin_trage_record(u_id,ctr_finish_time,ctr_count,add_or_reduce) values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uId);
			pstmt.setDate(2, ctrFinishTime);
			pstmt.setInt(3, ctrCount);
			pstmt.setByte(4, addOrReduce);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ���ݿ��޸�
	public void updateCoinTrageRecord(int uId, String column, String changedColumn) {

		Connection conn = DataBase.getConnection();
		String sql = "update coin_trage_record set " + column + "=? where u_id=?";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			int n = Integer.parseInt(changedColumn);
			pstmt.setInt(1, n);
			pstmt.setInt(2, uId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ���ݿ�ɾ��
	public void deleteCoinTrageRecord(int id) {
		Connection conn = DataBase.getConnection();
		String sql = "delete from coin_trage_record where u_id=?";
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
