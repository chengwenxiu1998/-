package com.tb.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tb.bean.ShaiShaiCommentBean;
import com.tb.dao.ShaiShaiCommentDao;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("添加评论");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int sid=Integer.parseInt(request.getParameter("Sid"));
		int uid=Integer.parseInt(request.getParameter("uid"));
		String content=request.getParameter("content");
		String sdate=request.getParameter("reply");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp date=null;
		date=Timestamp.valueOf(sdate);
		ShaiShaiCommentDao comment=new ShaiShaiCommentDao();
		List<ShaiShaiCommentBean> list1=comment.commentCountt();
		comment.addComent(sid, uid, date, content);
		List<ShaiShaiCommentBean> list2=comment.commentCountt();
		JSONObject object=new JSONObject();
		if((list2.size())-(list1.size())>0) {
			object.put("lasty", "true");
		}else {
			object.put("lasty", "false");
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
