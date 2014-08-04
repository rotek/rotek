<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html >
<head>
<%@include file="../frame/meta.jsp"%>
<title>九如大管家</title>
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
	<div>
		<form action="${pageContext.request.contextPath }/front/rlogin/login">
			<h2>登录</h2>
			<input name="name" type="text" >
			<input name="password" type="password">
			<button type="submit">登录</button>
			<a href="${pageContext.request.contextPath }/front/rregist/toRegist"><button  type="button">注册</button></a>
			<a href="${pageContext.request.contextPath }/front/rmycenter/toMycenter"><button type="button">访客登入</button></a>
		</form>

	</div>

</body>
</html>