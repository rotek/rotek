<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ROTEK</title>
<%@include file="/assets/jsp/common_front.jsp"%>

<script type="text/javascript">
	$(function() {

		$(document).click(function() {

		});
	});
</script>

<style type="text/css">
* html {
	background-color: #f5f5f5;
}

body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	width: 500px;
	_width: 500px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
	width: 100%
}
</style>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<script type="text/javascript">
	
	$(function(){
		
		var msg = '${msg}';
		if('fail_blank' == msg){
			alert("用户名或密码不能为空");
		}else if("fail" == msg){
			alert("用户名或密码错误");
		}
	});
</script>
</head>

<body>

	<div class="container">

		<form class="form-signin" action="${pageContext.request.contextPath }/front/login/login">
			<h2 class="form-signin-heading">登录</h2>
			<input name="name" type="text" class="input-block-level" placeholder="请输入用户名">
			<input name="password" type="password" class="input-block-level" placeholder="请输入密码">

			<button class="btn btn-large btn-primary" type="submit">登录</button>
			<a href="${pageContext.request.contextPath }/front/regist/toRegist"><button class="btn btn-large btn-success" type="button">注册</button></a>
			
			<a href="${pageContext.request.contextPath }/front/mycenter/toMycenter"><button style="float: right" class="btn btn-large btn-warning" type="button">访客登入</button></a>
		</form>

	</div>

</body>
</html>