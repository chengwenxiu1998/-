package com.tb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tb.bean.SignInBean;
import com.tb.bean.UserBean;
import com.tb.dao.SignInDao;
import com.tb.dao.UserDao;

/**
 * Servlet implementation class LoginSerlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoginServlet����������������");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ��ȡ�ͻ��˷��͵��û���������
		String phoneNum = request.getParameter("phoneNum");
		String password = request.getParameter("password");

		boolean isLoginSuccessful = false;

		UserBean user = new UserDao().findUser(phoneNum, password);
		JSONObject object = new JSONObject();
		if (user != null) {
			isLoginSuccessful = true;
			// �����ݱ����Json��ʽ
			JSONArray userJson = new JSONArray();
			userJson.put(user.getuId());
			userJson.put(user.getuName());
			userJson.put(user.getuPhone());
			userJson.put(user.getuSex() == 0 ? "男" : "女");
			userJson.put(user.getuArea());
			userJson.put(user.getuNickName());
			userJson.put(user.getuImage());
			userJson.put(user.getuPassword());
			userJson.put(user.getuIdCard());
			userJson.put(user.getuCoin());

			object.put("user", userJson);
			
			//����id��ѯ���û���ǩ����Ϣ
			SignInBean signInInfo = new SignInDao().findSignInByUid(user.getuId());
			
			JSONObject signInInfoObj = new JSONObject();
			signInInfoObj.put("signDayCount", signInInfo.getSignDayCount());
			signInInfoObj.put("ifSignIn", signInInfo.getIfSignIn());
			signInInfoObj.put("finishCount", signInInfo.getFinishCount());
			
			object.put("signInInfo",signInInfoObj);
			System.out.println("signInInfoObj"+signInInfoObj);
		}else {
			isLoginSuccessful = false;
		}
		
		object.put("isLoginSuccessful", isLoginSuccessful);

		// Json����ת���ַ�����ʽobject.toString()
		response.getWriter().append(object.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
