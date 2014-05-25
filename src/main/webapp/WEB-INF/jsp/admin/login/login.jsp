<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>酷客外卖网站后台管理系统登陆</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_lib/jquery/jquery.1.72.min.js"></script>
<style>
div,body,p,form,input,h1{
	margin:0; padding:0; font-size:12px; font-family:"宋体"}
body{
	background:url('${pageContext.request.contextPath }/assets/images/login/login.jpg') no-repeat center top}
#head{
	height:300px;
	margin:0 auto;
	}

#head2{
	height:110px;
	margin:0 auto;
	}
#main{
	width:185px;
	height:80px;
	margin:0 auto;
}
#main p{
	margin-bottom:20px;
	color:#969696}
.CMS_txt{
	width:133px;
	height:19px;
	background:url('${pageContext.request.contextPath }/assets/images/login/box.gif') no-repeat;
	padding:1px;
	line-height:19px;
	border:none}
.CMS_button{
	width:75px;
	height:30px;
	background:url('${pageContext.request.contextPath }/assets/images/login/button.gif') no-repeat;
	border:none;
	cursor:pointer;
	margin:0 auto}
#center{
	text-align:center;}
#footer{
	height:70px;
	margin:0 auto;
	}
#footer p{
	text-align:center;
	color:#666666;
	margin-bottom:20px;}
#footer p a{
	padding:0 3px;
	color:#666;
	text-decoration:none
	}
#footer p a:hover{
	text-decoration:underline}
</style>
</head>

<body>
<div id="head">

</div>
<div id="main">

<p><span>用户名：</span><input type="text" class="CMS_txt" id="username"/></p>
<p><span>密&nbsp;&nbsp;码：</span><input type="password" class="CMS_txt" id='password'/></p>
</div>
<div id="center"><input type="button" class="CMS_button" id = "subBtn" value="" /></div>
<div id="head2"></div>
<div id="head2"></div>
<div id="footer">
	<p>版权所有 Copyright 2008-2011 www.cncqa All Rights Reserved 京ICP备11029827号</p>

	<p>技术支持：Administrator</p>
	<p><a href="#">关于我们</a>|<a href="#">广告服务</a>|<a href="#">联系我们</a>|<a href="#">诚聘英才</a>|<a href="#">网站地图</a>|<a href="#">版权隐私</a>|<a href="#">使用协议</a>|<a href="#">帮助中心</a></p>

</div>
</body>
<script type="text/javascript">

	var LoginObj = {
		initListener : function(){
			$("#subBtn").click(function(){
				var username = $("#username").val();//根据id获取值
				var password = $("#password").val();
				if(LoginObj.testEmpty(username) || LoginObj.testEmpty(password)){//非空验证
					alert("用户名或密码不能为空！");
					return;
				}

				$.ajax({
					type: "POST",
		  		   	beforeSend:function(){
		  			   document.getElementById("subBtn").disabled = true;
		  		   	},
					url : "${pageContext.request.contextPath }/admin/login/login",
					data : {
						username : username,
						password : password
					},
					success : function(json,state){

						document.getElementById("subBtn").disabled = false;
						if("success" == state && "success" == json.result){

							window.location.href="${pageContext.request.contextPath }/admin/index/toIndex";
						}else {
							alert("用户名或密码错误！");
						}
					},
					error : function(){
						document.getElementById("subBtn").disabled = true;
						alert("对不起，系统繁忙，请退出浏览器后重试！");
					}
				});
			});
		},
		testEmpty : function(val){//非空验证
			if($.trim(val))return false;
			return true;
		}
	};
$(function(){
	LoginObj.initListener();
});
</script>
</html>
