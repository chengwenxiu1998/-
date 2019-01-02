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

import com.tb.bean.ShaiShaiBean;
import com.tb.bean.ShaiShaiCommentBean;
import com.tb.dao.ShaiShaiCommentDao;
import com.tb.dao.ShaiShaiDao;
import com.tb.dao.UserDao;

/**
 * Servlet implementation class ShaishaiServlet
 */
@WebServlet("/ShaishaiServlet")
public class ShaishaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShaishaiServlet() {
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
		ShaiShaiDao shaiShaiDao=new ShaiShaiDao();
		List<ShaiShaiBean> list=shaiShaiDao.selectShaiShai();
		JSONArray objects=new JSONArray();
		for(ShaiShaiBean li:list) {
			JSONObject object=new JSONObject();
			//根据id查找用户的昵称
			UserDao userDao=new UserDao();
			String petName=userDao.selectPetName(li.getUid());
			//根据sid查找回复的条数
			ShaiShaiCommentDao comment=new ShaiShaiCommentDao();
			List<ShaiShaiCommentBean> list1=comment.commentCount(li.getSid());
			object.put("reply", list1.size());
			object.put("petName",petName);
			object.put("sid",li.getSid());
			object.put("uid", li.getUid());
			object.put("stext", li.getStext());
			object.put("stime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(li.getTime()));
			object.put("scount",li.getCount());
			objects.put(object);
		}
		System.out.println("晒晒数组的个数为:"+objects.length());
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
