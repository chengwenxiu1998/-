package com.tb.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tb.dao.TaskDao;


/**
 * Servlet implementation class InsertTaskServlet
 */
@WebServlet("/InsertTaskServlet")
public class InsertTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Timestamp tEndtime;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest httpRequest=(HttpServletRequest)request;  
        
		  
		String strBackUrl = "http://" + request.getServerName() 
		                      
		+ ":"   
		                      
		+ request.getServerPort()
		                      
		+ httpRequest.getContextPath()
		                     
		 + httpRequest.getServletPath()
		                 
		 + "?" + (httpRequest.getQueryString());
		System.out.println(strBackUrl);
		int uIdSend=Integer.parseInt(request.getParameter("uIdSend"));
		int tcId=Integer.parseInt(request.getParameter("tcId"));
		String tDesc=request.getParameter("tDesc");
		int tCoinCount=Integer.parseInt(request.getParameter("tCoinCount"));
		String tState=request.getParameter("tState");
		int uIdAccept=Integer.parseInt(request.getParameter("uIdAccept"));
		int tagId=Integer.parseInt(request.getParameter("tagId"));
		System.out.println("------------------"+tagId);
		String tEndtimeMonth=request.getParameter("tEndtimeMonth");
		String tEndtimeDay=request.getParameter("tEndtimeDay");
		String tEndtimeHour=request.getParameter("tEndtimeHour");
		String tEndtimeMin=request.getParameter("tEndtimeMin");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String s=year+"-"+ tEndtimeMonth+"-"+tEndtimeDay+" "+tEndtimeHour+":"+tEndtimeMin+":00";
		System.out.println(s);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*System.out.println("aaa");*/
		java.util.Date d1=new java.util.Date();
		
		
		
		
		try {
			
			d1 = sdf.parse(s);
			
			System.out.println("d1="+d1);
			tEndtime= new Timestamp(d1.getTime());
			System.out.println(tEndtime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //�Ȱ��ַ���תΪutil.Date����
		 //��ת��Ϊsql.Date����
		
		
		String t_imgurl=request.getParameter("t_imgurl");
		
		JSONObject object=new JSONObject();
		object.put("uIdSend", uIdSend);
		object.put("tcId",tcId);	
		object.put("tDesc", tDesc);
		object.put("tCoinCount",tCoinCount);
		object.put("tState",tState);
		object.put("uIdAccept",uIdAccept);
		object.put("tagId",tagId);
		object.put("tEndtime",tEndtime);
		object.put("t_imgurl",t_imgurl);
		System.out.println(object.toString());
		
		TaskDao taskDao=new TaskDao();
		taskDao.addTask(uIdSend,new Timestamp(System.currentTimeMillis()),tcId, tDesc, tCoinCount, tState, uIdAccept, tagId, tEndtime, t_imgurl,null,null);
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
