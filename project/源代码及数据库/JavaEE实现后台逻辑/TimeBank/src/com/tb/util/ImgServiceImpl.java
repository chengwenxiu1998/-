package com.tb.util;


import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.tb.dao.TaskImageDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

/**
 * 鍥剧墖鏈嶅姟
 */
public class ImgServiceImpl {
	
	static DB db = MongoDBconnectUtil.connect();
	static GridFS gridFS = new GridFS(db, "img");
    /**
     * 璇诲彇mongodb
     * @param request
     * @param response
     * @param id
     */
    public static GridFSDBFile showImage(HttpServletRequest request, HttpServletResponse response, String id){
    	
        GridFSDBFile file = gridFS.findOne(id);
        try {
            file.writeTo(response.getOutputStream());
            return file;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    
    /**
     * upload image save mongodb
     * @param image_data
     * @return
     * @throws Exception 
     */
    public static String upLoadImg(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	String fileId = saveImg(request,response);
        String serverImgFront = "GetImageServlet?id=" + fileId;
        TaskImageDao dao = new TaskImageDao();
        dao.addTaskImage(serverImgFront);
        return serverImgFront;
    }
    
    /**
     * @param image_data
     * @return mongdb涓殑id
     * @throws Exception
     */
    private static String saveImg(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		Collection<Part> parts = request.getParts();  
		String path ="";
		for(Part part:parts){ 
			if(part.getName().equals("img")){  
				String cd = part.getHeader("Content-Disposition");  
		        String[] cds = cd.split(";");  
		        String fileName = cds[2].substring(cds[2].indexOf("=")+1).substring(cds[2].lastIndexOf("//")+1).replace("\"", "");  
		        String prefix = fileName.substring(0, fileName.lastIndexOf("."));
		        prefix += "-" + ZonedDateTime.now().toInstant().toEpochMilli();
		        String suffix = fileName.substring(fileName.lastIndexOf("."));
		        String newFileName = prefix + suffix;
		        try {
		            GridFSInputFile gfs = gridFS.createFile(part.getInputStream());
		            gfs.put("filename", newFileName);
		            gfs.put("contentType", part.getContentType());
		            gfs.save();
		            return newFileName;
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("存储文件时发生错误！！！");
		        }
			}  				
		}
		return path;
    	
    }
    
    
//    /**
//	 * 解析FormData型的数据
//	 * @param response 
//	 * @param request 
//	 * */
//	public static String upLoadImg(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			String realPath = request.getServletContext().getRealPath("upload");		
//			Collection<Part> parts = request.getParts();  
//			String path ="";
//			for(Part part:parts){ 
//				if(part.getName().equals("img")){  
//					String cd = part.getHeader("Content-Disposition");  
//			        String[] cds = cd.split(";");  
//			        String filename = cds[2].substring(cds[2].indexOf("=")+1).substring(cds[2].lastIndexOf("//")+1).replace("\"", "");  
//			        String ext = filename.substring(filename.lastIndexOf(".")+1);  
//			        path = "/"+new Date().getTime()+"."+ext;
//			        String name = realPath+"/"+path;
//			        part.write(name); 	
//				}  				
//			}
//			return "upload"+path;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";  
//		} 
//	}
}
