package com.tb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tb.bean.UserBean;
import com.tb.dao.UserDao;

/**
 * Servlet implementation class RegisterActivity
 */
@WebServlet("/RegisterActivity")
public class RegisterActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("运行");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取客户端发送的用户名和密码
		String petName= " ";
		String phone=" ";
		String password =" ";
		String petName1=request.getParameter("petName");
		if(petName1!=null) {
			petName=petName1;
		}
		String phone1=request.getParameter("phone");
		System.out.println(phone1);
		if(phone1!=null) {
			phone=phone1;
		}
		String password1=request.getParameter("password");
		if(password1!=null) {
			password=password1;
		}
		if(petName!=null&&phone!=null&&password!=null) {
		UserDao userDao=new UserDao();
		List<UserBean> userList=userDao.selectUser();
		int id=userList.size()+1;
		userDao.addUser1(id,petName,phone,password);
		}
		JSONObject object=new JSONObject();
		object.put("petName", petName);
		object.put("phone", phone);
		object.put("password", password);
		System.out.println("昵称："+petName+"   手机号:"+phone+"    密码："+password);
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
