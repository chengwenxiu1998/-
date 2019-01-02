package com.tb.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tb.bean.CoinTrageRecordBean;
import com.tb.dao.CoinTrageRecordDao;

/**
 * Servlet implementation class FindCoinRecordServlet
 */
@WebServlet("/FindCoinRecordServlet")
public class FindCoinRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindCoinRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�ͻ��˷��͵�uid
		String uidStr = request.getParameter("uid");
		int uid = Integer.parseInt(uidStr);
		
		//��ѯ���û��Ľ�ҽ��׼�¼
		List<CoinTrageRecordBean> records = new CoinTrageRecordDao().findCoinRecordByUid(uid);
		
		//�����ݱ����Json��ʽ
		JSONObject  object = new JSONObject();
		JSONArray all = new JSONArray();
		for(int i=0;i<records.size();i++) {
			JSONObject obj = new JSONObject();
			CoinTrageRecordBean record = records.get(i);
			Date ctrFinishTime = record.getCtrFinishTime();
			int ctrCount = record.getCtrCount();
			byte addOrReduce = record.getAddOrReduce();
			obj.put("ctrFinishTime",ctrFinishTime);
			obj.put("ctrCount",ctrCount);
			obj.put("addOrReduce",addOrReduce==1?"+":"-");
			
			all.put(obj);
		}
		
		object.put("records", all);
		
		//Json����ת���ַ�����ʽobject.toString()
		response.getWriter().append(object.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
