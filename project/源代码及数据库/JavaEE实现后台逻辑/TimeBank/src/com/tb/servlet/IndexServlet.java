package com.tb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tb.bean.TagBean;
import com.tb.bean.TaskBean;
import com.tb.bean.UserBean;
import com.tb.dao.DataBase;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//鏌ヨ涔版椂闂村垪琛�
				response.setCharacterEncoding("utf-8");
				Connection conn=DataBase.getConnection();
				//鏌ヨ鎵�鏈塱tem鐨勪俊鎭�
				String sql="select u_nickname,u_image,u_time,t_desc,t_coin_count,tag_text,t_state,t_id,u_id_accept,t_endtime,t_imgurl,tc_id from users,task,tag where users.u_id=task.u_id_send and task.tag_id=tag.tag_id";
				JSONArray array=new JSONArray();
				try {
					PreparedStatement pstmt=conn.prepareStatement(sql);
					ResultSet rs=pstmt.executeQuery();
					while(rs.next()) {
						TaskBean taskBean=new TaskBean();
						UserBean userBean=new UserBean();
						TagBean tagBean=new TagBean();
						userBean.setuNickName(rs.getString(1));
						userBean.setuImage(rs.getString(2));
						taskBean.setuTime(rs.getTimestamp(3));
						taskBean.settDesc(rs.getString(4));
						taskBean.settCoinCount(rs.getInt(5));
						tagBean.setTagText(rs.getString(6));	
						taskBean.settState(rs.getString(7));
						taskBean.settId(rs.getInt(8));
						taskBean.setuIdAccept(rs.getInt(9));
						taskBean.settEndTime(rs.getTimestamp(10));
						taskBean.settImgUrl(rs.getString(11));
						taskBean.setTcId(rs.getInt(12));
						JSONObject object=new JSONObject();
						object.put("uNickName",userBean.getuNickName());
						object.put("uImage",userBean.getuImage());
						object.put("uTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(3)));
						object.put("tDesc",taskBean.gettDesc());
						object.put("tCoinCount",taskBean.gettCoinCount());
						object.put("tagText",tagBean.getTagText()); 
						object.put("tState", taskBean.gettState());
						object.put("tId", taskBean.gettId());
						object.put("uIdAccept", taskBean.getuIdAccept());
						object.put("tEndtime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(10)));
						object.put("tImageUrl", taskBean.gettImgUrl());
						object.put("tcId", tagBean.getTagId());
						array.put(object);
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				response.getWriter().append(array.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
