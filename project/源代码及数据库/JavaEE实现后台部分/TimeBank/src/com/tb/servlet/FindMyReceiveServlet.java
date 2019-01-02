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
 * Servlet implementation class FindMyReceiveServlet
 */
@WebServlet("/FindMyReceiveServlet")
public class FindMyReceiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindMyReceiveServlet() {
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
		//��ȡ�ͻ��˷��͵�uid
		String uidStr = request.getParameter("uid");
		int uid = Integer.parseInt(uidStr);
		
		//��ѯ���û����յ�����
		List<TaskBean> tasks = new TaskDao().findMyReceive(uid);
		
		//�����ݱ����Json��ʽ
		JSONObject  object = new JSONObject();
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
//			System.out.println("nickname"+nickname);
//			System.out.println("sendHeadImg"+sendHeadImg);
//			System.out.println("sendAddress"+address);
//			System.out.println("tag"+tagName);
			
			
			if(task.gettState().equals("进行中")) {//���뵽�����е�JSON������
				doing.put(obj);
			}else if(task.gettState().equals("已完成")) {//���뵽����ɵ�JSON������
				finish.put(obj);
			}
			
		}
		
		
		object.put("doing", doing);
		object.put("finish", finish);
		
//		System.out.println(doing.toString());
//		System.out.println(finish.toString());
		
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
