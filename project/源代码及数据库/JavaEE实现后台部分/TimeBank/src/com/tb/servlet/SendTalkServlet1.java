package com.tb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.tb.bean.DiscussBean;
import com.tb.dao.DiscussDao;
import com.tb.dao.UserDao;

/**
 * Servlet implementation class SendTalkServlet1
 */
@WebServlet("/SendTalkServlet1")
public class SendTalkServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendTalkServlet1() {
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
		String content=request.getParameter("content");
		System.out.println(content);
		int id=Integer.parseInt((String)request.getParameter("userId"));
		DiscussDao discuss=new 	DiscussDao();
		List<DiscussBean> discussBean=discuss.selectDiscuss();
		int size=discussBean.size()+1;
		
		int tId=7;
		if(content!=null) {
			if(content.contains("快递")) {
				tId=1;
			}else if(content.contains("饭")) {
				tId=2;
			}else if(content.contains("接水")) {
				tId=3;
			}else if(content.contains("逛街")) {
				tId=4;
			}else if(content.contains("运动")) {
				tId=5;
			}else if(content.contains("学习")) {
				tId=6;
			}else {
				tId=7;
			}
		}
		
		discuss.addDiscuss(size, tId, id, content);
		JSONObject object=new JSONObject();
		object.put("Last", size);
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
