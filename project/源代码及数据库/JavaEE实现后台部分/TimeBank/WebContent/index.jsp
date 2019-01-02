<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding
	="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<body>
<%
	if(request.getAttribute("chatRecordList")!=null){
%>
<c:forEach var="row" items="${chatRecordList}">
${row.uId1 }:${row.uId2 }:${row. crRecordTextUrl }<br>
</c:forEach>
<%	}
%>

<a href="TestServlet">查询</a>
<a href="TestServlet?uId1=7&uId2=8&crRecordTextUrl=aaaa">添加</a>
<a href="TestServlet?uId1=7&column=cr_record_text_url&changedColumn=bbbbb">修改</a>
<a href="TestServlet?uId1=7">删除</a>




<form class="form-horizontal" id="addForm" enctype=”multipart/form-data”>	
	<input type="file" class="form-control" id="img" name="img" placeholder="图片地址"/>				
	<button type="button" class="btn btn-primary" onclick="doUploadImage()">确定</button>
</form>
<div id="img">

</div>

<script>

function doUploadImage(){
	var formData =  new FormData($("#addForm")[0]);
	$.ajax({
		url:'UploadImage',
		type:'post',
		data: formData,
		dataType:'json',
		cache: false,
		contentType: false,
		processData: false,
		success:function(result){
			var json_obj = eval(result);
			window.location.href = window.location.href+json_obj.serverImgFront;
		}
	})
}

</script>
</body>
</html>