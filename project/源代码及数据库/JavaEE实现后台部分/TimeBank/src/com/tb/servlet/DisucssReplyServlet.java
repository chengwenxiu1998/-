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

import com.tb.bean.DiscussReplyBean;
import com.tb.dao.DiscussReplyDao;
import com.tb.dao.UserDao;

/**
 * Servlet implementation class DisucssReplyServlet
 */
@WebServlet("/DisucssReplyServlet")
public class DisucssReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisucssReplyServlet() {
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
		int did=Integer.parseInt(request.getParameter("did"));
		//根据id查找评论
		DiscussReplyDao discussReplyDao=new DiscussReplyDao();
		List<DiscussReplyBean> replyList=discussReplyDao.selectDiscussReplys(did);
		JSONArray objects=new JSONArray();
		for(DiscussReplyBean reply:replyList) {
			JSONObject object=new JSONObject();
			//根据回复人id找到昵称
			UserDao userDao=new UserDao();
			String pickName=userDao.selectName(reply.getuIdReply());
			object.put("pickName",pickName);
			object.put("did", reply.getdId());
			object.put("rid", reply.getuIdReply());
			object.put("cotent", reply.getDrReplyContent());
			object.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reply.getDrReplyTime()));
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
