package com.tb.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tb.dao.TaskDao;

/**
 * Servlet implementation class UpdateTaskStateServlet
 */
@WebServlet("/UpdateTaskStateServlet")
public class UpdateTaskStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaskStateServlet() {
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

		int tid = Integer.parseInt(request.getParameter("tid"));


		
		int updateRowCount1 = new TaskDao().updateTask(tid, "t_state", "已完成");
		int updateRowCount2 = new TaskDao().updateTask(tid, "t_endtime",new Date());
		int updateRowCount = (updateRowCount1==1&&updateRowCount2==1) ? 1 : 0;
		// �����ݱ����Json��ʽ
		JSONObject object = new JSONObject();
		object.put("updateRowCount", updateRowCount);

		// Json����ת���ַ�����ʽobject.toString()
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
