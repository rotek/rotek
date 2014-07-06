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
	width: 100%;
}

.r_container .center{
	height: 100%;
	padding: 30px
}
.input-group{
	margin: 30px 150px 0px 150px;
	min-width: 800px;
	
}
</style>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<script type="text/javascript">
	
	
	
</script>
</head>

<body>

	<div class="container">
		
		<form class="form-horizontal" style="margin-top: 30px;width: 100px;" action="${pageContext.request.contextPath }/front/mycenter/modify">
				
					<input type="hidden" name="id" value="${sessionScope.user.id}" />
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">用户名</span>
					  <input type="text" class="form-control" placeholder="用户名不能为空" value="">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码</span>
					  <input type="text" class="form-control" placeholder="密码名不能为空" value="">
					</div>
					
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">重复密码</span>
					  <input type="text" class="form-control" placeholder="重复密码名不能为空" value="">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">EMAIL</span>
					  <input type="text" class="form-control" placeholder="EMAIL不能为空" value="">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">电&nbsp;&nbsp;&nbsp;话</span>
					  <input type="text" class="form-control" placeholder="电话不能为空" value="">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">真是姓名</span>
					  <input type="text" class="form-control" placeholder="真是姓名不能为空" value="">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">所属公司</span>
					  <input type="text" class="form-control" placeholder="所属公司不能为空" value="">
					</div>
					
					<div class="input-group input-group-lg control-group info" style="width: 100%;min-width: 800px;text-align: center;">
						<div class="controls" style="float: left;text-align: right;width: 40%">
							<a style="width: 250px;" class="btn btn-primary btn-lg" id="updateUser">提交</a>
						</div>
						
						<div class="controls" style="float: right;text-align: left;width: 40%">
							<a href="${pageContext.request.contextPath }/front/login/toLogin" style="width: 250px" class="btn btn-primary btn-lg" id="updateUser">取消</a>
						</div>
					</div>

				
				</form>		
	</div>

</body>
</html>