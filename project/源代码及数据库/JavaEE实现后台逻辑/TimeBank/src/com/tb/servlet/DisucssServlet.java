package com.tb.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tb.bean.DiscussBean;
import com.tb.dao.DiscussDao;
import com.tb.dao.TagDao;
import com.tb.dao.UserDao;

/**
 * Servlet implementation class DisucssServlet
 */
@WebServlet("/DisucssServlet")
public class DisucssServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisucssServlet() {
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
		DiscussDao discussDao=new DiscussDao();
		List<DiscussBean> discuss=discussDao.selectDiscuss();
		JSONArray objects=new JSONArray();
		
		for(DiscussBean dis:discuss) {
			JSONObject object=new JSONObject();
			TagDao tag=new TagDao();
			String text=tag.selectContent(dis.getTagId());
			UserDao user=new UserDao();
			object.put("petName",user.selectName(dis.getuIdLouZhu()));
			object.put("dId",dis.getdId());
			object.put("tId", dis.getTagId());
			object.put("uId",dis.getuIdLouZhu());
			object.put("content",dis.getdTopicCoutent());
			object.put("tContent", text);
			objects.put(object);
		}
		response.getWriter().append(objects.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
