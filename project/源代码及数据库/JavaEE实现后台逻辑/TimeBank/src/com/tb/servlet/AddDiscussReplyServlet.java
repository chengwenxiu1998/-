package com.tb.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tb.bean.DiscussReplyBean;
import com.tb.dao.DiscussReplyDao;

/**
 * Servlet implementation class AddDiscussReplyServlet
 */
@WebServlet("/AddDiscussReplyServlet")
public class AddDiscussReplyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDiscussReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int did=Integer.parseInt(request.getParameter("did"));
		int uid=Integer.parseInt(request.getParameter("uid"));
		String reply=request.getParameter("reply");
		String replyTime=request.getParameter("replyTime");
		System.out.println("鏃堕棿"+replyTime);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp date=null;
		date=Timestamp.valueOf(replyTime);
		DiscussReplyDao discussReplyDao=new DiscussReplyDao();
		List<DiscussReplyBean> list1=discussReplyDao.selectDiscussReply();
		discussReplyDao.addDiscussReply(did, uid,reply, date);
		List<DiscussReplyBean> list2=discussReplyDao.selectDiscussReply();
		JSONObject object=new JSONObject();
		if(list2.size()-list1.size()>0) {
			object.put("replys","true");
			System.out.println("娣诲姞鎴愬姛锛�");
		}else {
			object.put("replys", "false");
		}
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
