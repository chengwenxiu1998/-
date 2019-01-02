package com.tb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tb.dao.ShaiShaiDao;

/**
 * Servlet implementation class ClickServlet
 */
@WebServlet("/ClickServlet")
public class ClickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClickServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("改变赞的个数");
		int sid=Integer.parseInt(request.getParameter("sid"));
		int count=Integer.parseInt(request.getParameter("zancount"));
		//更新晒晒的赞的个数
		ShaiShaiDao shaishaiDao=new ShaiShaiDao();
		int changed=shaishaiDao.updateZan(sid, count);
		JSONObject object=new JSONObject();
		object.put("over",changed);
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
