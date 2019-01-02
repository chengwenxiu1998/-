package com.tb.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tb.bean.TaskBean;
import com.tb.dao.TagDao;
import com.tb.dao.TaskDao;
import com.tb.dao.UserDao;

/**
 * Servlet implementation class FindMySendServlet
 */
@WebServlet("/FindMySendServlet")
public class FindMySendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindMySendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("我发布的");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//锟斤拷取锟酵伙拷锟剿凤拷锟酵碉拷uid
		String uidStr = request.getParameter("uid");
		int uid = Integer.parseInt(uidStr);
		
		//锟斤拷询锟斤拷锟矫伙拷锟斤拷锟酵碉拷锟斤拷锟斤拷
		List<TaskBean> tasks = new TaskDao().findMySend(uid);
		
		//锟斤拷锟斤拷锟捷憋拷锟斤拷锟絁son锟斤拷式
		JSONObject  object = new JSONObject();
		JSONArray ready = new JSONArray();
		JSONArray doing = new JSONArray();
		JSONArray finish = new JSONArray();
		Iterator<TaskBean> it = tasks.iterator();
		while(it.hasNext()) {
			TaskBean task = it.next();
			
			JSONObject obj = new JSONObject();
			obj.put("tid", task.gettId());
			obj.put("uIdSend", task.getuIdSend());
			obj.put("uTime", task.getuTime());
			obj.put("tcId", task.getTcId());
			obj.put("tDesc", task.gettDesc());
			obj.put("tCoinCount", task.gettCoinCount());
			obj.put("tState", task.gettState());
			obj.put("uIdAccept", task.getuIdAccept());
			obj.put("tagId", task.getTagId());
			obj.put("tImgUrl", task.gettImgUrl());
			obj.put("tEndTime", task.gettEndTime());
			obj.put("tAcceptTime", task.gettAcceptTime());
			obj.put("tFinishTime", task.gettFinishTime());
			
			String nickname = new UserDao().findNicknameByUid(task.getuIdSend());
			obj.put("sendNickname", nickname);
			String sendHeadImg = new UserDao().findHeadImgByUid(task.getuIdSend());
			obj.put("sendHeadImg",sendHeadImg);
			String address = new UserDao().findAddressByUid(task.getuIdSend());
			obj.put("sendAddress", address);
			String tagName = new TagDao().findTagnameByTid(task.getTagId());
			obj.put("tag", tagName);
			
			if(task.gettState().equals("进行中")) {//锟斤拷锟诫到锟斤拷锟斤拷锟叫碉拷JSON锟斤拷锟斤拷锟斤拷
				doing.put(obj);
			}else if(task.gettState().equals("已完成")) {//锟斤拷锟诫到锟斤拷锟斤拷傻锟絁SON锟斤拷锟斤拷锟斤拷
				finish.put(obj);
			}else if(task.gettState().equals("待接收")) {
				ready.put(obj);
			}
		}
		System.out.println("ready"+ready.toString());
		object.put("ready", ready);
		object.put("doing", doing);
		object.put("finish", finish);
		
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
