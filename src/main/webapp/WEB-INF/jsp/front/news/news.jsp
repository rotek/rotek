<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/js_lib/jquery/jquery.1.72.min.js"></script>
<title>新闻公告</title>
<script type="text/javascript">
function viewInfo(flag){
    $("#div_content").empty();
    $("#div_content").show();
    $.ajax({
        type: "POST",//
        url: "${pageContext.request.contextPath }/front/rmycenter/baseInfo?flag="+flag,
        data: {},
        error: function(){},
        success: function(datas){
          $("#div_content").html(datas);
          //window.parent.resetIfreamheight();
        }
    });
}
</script>
</head>
<body>
	<div style="font-size: 12px; width: 100%; padding-bottom: 20px; float: right; font-weight: bold;">
		文章分类： <select name="select" id="select"><option>新闻</option><option>公告</option></select> 
		标题<input type="text" name="textfield" id="textfield" style="width: 30%;" />
		<button value="查询">查询</button>
	</div>
	<table width="90%" border="0" cellspacing="1" cellpadding="0" style="background: #EF7E18;">
		<thead>
			<tr>
				<td width="10%" align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">文章分类</td>
				<td width="50%" align="center" bgcolor="#F7B77D"
					style="font-weight: bold; font-size: 14px;">标题</td>
				<td width="30%" align="center" bgcolor="#F7B77D"
					style="font-weight: bold; font-size: 14px;">发布时间</td>
				<td width="10%" align="center" bgcolor="#F7B77D"
					style="font-weight: bold; font-size: 14px;">操作</td>
			</tr>
		</thead>
		<tr>
			<td align="center" bgcolor="#FFFFFF">新闻</td>
			<td align="left" bgcolor="#FFFFFF">云南发生地震</td>
			<td align="center" bgcolor="#FFFFFF">2014-08-04</td>
			<td align="center" bgcolor="#FFFFFF">
				<a href="04_1liangjian.html">
					<img src="${pageContext.request.contextPath }/assets/images/icon_see.jpg" width="26" height="25" /> 查看</a>
			</td>
		</tr>
		<tr>
			<td align="center" bgcolor="#FFFFFF">新闻</td>
			<td align="left" bgcolor="#FFFFFF">云南发生地震</td>
			<td align="center" bgcolor="#FFFFFF">2014-08-04</td>
			<td align="center" bgcolor="#FFFFFF">
				<a href="04_1liangjian.html">
					<img src="${pageContext.request.contextPath }/assets/images/icon_see.jpg" width="26" height="25" /> 查看</a>
			</td>
		</tr>
		<tr>
			<td align="center" bgcolor="#FFFFFF">新闻</td>
			<td align="left" bgcolor="#FFFFFF">云南发生地震</td>
			<td align="center" bgcolor="#FFFFFF">2014-08-04</td>
			<td align="center" bgcolor="#FFFFFF">
				<a href="04_1liangjian.html">
					<img src="${pageContext.request.contextPath }/assets/images/icon_see.jpg" width="26" height="25" /> 查看</a>
			</td>
		</tr>
		<tr>
			<td align="center" bgcolor="#FFFFFF">新闻</td>
			<td align="left" bgcolor="#FFFFFF">云南发生地震</td>
			<td align="center" bgcolor="#FFFFFF">2014-08-04</td>
			<td align="center" bgcolor="#FFFFFF">
				<a href="04_1liangjian.html">
					<img src="${pageContext.request.contextPath }/assets/images/icon_see.jpg" width="26" height="25" /> 查看</a>
			</td>
		</tr>
	</table>
	<jsp:include page="../frame/paging.jsp" />
</body>
</html>