package com.tb.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tb.bean.ShaiShaiCommentBean;
import com.tb.dao.ShaiShaiCommentDao;
import com.tb.dao.UserDao;

/**
 * Servlet implementation class ShaiReplyServlet
 */
@WebServlet("/ShaiReplyServlet")
public class ShaiReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShaiReplyServlet() {
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
		int sid=Integer.parseInt(request.getParameter("sid"));
		//根据id查找评论
		ShaiShaiCommentDao shaiShaiCommentDao=new ShaiShaiCommentDao();
		List<ShaiShaiCommentBean> list=shaiShaiCommentDao.commentCount(sid);
		JSONArray objects=new JSONArray();
		for(ShaiShaiCommentBean li:list) {
			JSONObject object=new JSONObject();
			UserDao userDao=new UserDao();
			String pickName=userDao.selectName(li.getuIdComment());
			object.put("pickName",pickName);
			object.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(li.getcCommentTime()));
			object.put("content", li.getcCommentContent());
			object.put("rid", li.getuIdComment());
			object.put("sid", li.getsId());
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
