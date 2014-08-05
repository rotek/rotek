<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/js_lib/jquery/jquery.1.72.min.js"></script>
<title>我的空间</title>
<style type="text/css">
.navtop{
	padding-bottom:20px; 
	border-bottom:dotted 1px #333;
}
.navtop a{
	border:1px solid #EF7E18; 
	background:#F7B77D; 
	border-radius:5px; 
	text-decoration:none; 
	font-size:14px; 
	font-family:"微软雅黑", Tahoma; 
	color:#000; 
	padding:5px 10px; 
	margin-right:20px;
}
.h2{
	font-size:16px; 
	padding:20px 0; 
	font-weight:bold; 
	font-family:"微软雅黑", Tahoma;
}
#listtable tr td{
	font-family:"微软雅黑", Tahoma; 
	font-size:12px; padding:10px;
}
#listtable tr td a{ 
	text-decoration:none;
}
img{ vertical-align:middle;}
</style>
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
	<div class="navtop">
		<a href="" onclick="viewInfo('info');return false;">客户基本信息</a>
		<a href="" onclick="viewInfo('resource');return false;">客户资源信息</a>
		<a href="" onclick="viewInfo('project');return false;">客户工程信息</a>
		<a href="" onclick="viewInfo('component');return false;">客户零件信息</a>
		<a href="" onclick="viewInfo('shenbao');return false;">客户申报</a>
	</div>
	
	<div style="" id="div_content">
	   	<div class="h2">客户基本信息</div>
		<p style="margin-top: 0px;">客户名称：</p>
		<p>客户经纬度地址：</p>
		<p>客户通信地址：</p>
		<p>联系人：</p>
		<p>联系电话：</p>
		<p>所属代理商：</p>
	</div>

</body>
</html>