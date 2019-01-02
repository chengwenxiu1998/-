package com.tb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tb.dao.UserDao;

/**
 * Servlet implementation class RealNameAuthenticationServlet
 */
@WebServlet("/RealNameAuthenticationServlet")
public class RealNameAuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealNameAuthenticationServlet() {
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

		int uid = Integer.parseInt(request.getParameter("uid"));
		String name = request.getParameter("name");
		String idCard = request.getParameter("idCard");
		
		System.out.println("uid"+uid);
		System.out.println("name"+name);
		System.out.println("idCard"+idCard);

		int  updateRowCount = new UserDao().realNameAuthenticationByUid(uid,name,idCard);

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
