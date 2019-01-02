package com.tb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tb.dao.DataBase;
import com.tb.dao.TaskDao;

/**
 * Servlet implementation class HandleDatabaseServlet
 */
@WebServlet("/HandleDatabaseServlet")
public class HandleDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleDatabaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Connection conn=DataBase.getConnection();
		//获取客户端发送的tId,uId
		String tid=request.getParameter("tid");
		String uid=request.getParameter("uId");
		//当前系统时间
		Timestamp date=new Timestamp(System.currentTimeMillis());
		System.out.println(tid);
		System.out.println(uid);
		//更改接收人id,任务状态
		String sql="update task set t_state='进行中',u_id_accept="+uid+" where t_state='待接收' and t_id="+tid;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("更新成各个");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//更改接收时间
		TaskDao taskDao=new TaskDao();
		taskDao.updateTaskTime1(tid,"t_accept_time",date);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
