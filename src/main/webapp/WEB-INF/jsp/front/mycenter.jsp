<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ROTEK</title>
<%@include file="/assets/jsp/common_front.jsp"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/front/main.css" />

<style type="text/css">

.r_container .center{
	height: 100%;
	padding: 30px
}
.input-group{
	
	margin: 30px 150px 0px 100px  
}
</style>

</head>
<body>

	<%@include file="/assets/jsp/common_front_top.jsp"%>

	<div class="r_container">

		<%@include file="/assets/jsp/common_front_left.jsp"%>
		<div class="right">
			<div class="top well">当前时间：2014-06-22 客户名称：管理员 工程名称：测试工程</div>

			<div class="center well">

				<form class="form-horizontal" style="margin-top: 30px" action="${pageContext.request.contextPath }/front/mycenter/modify">
				
					<input type="hidden" name="id" value="${sessionScope.user.id}" />
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">用户名</span>
					  <input type="text" class="form-control" placeholder="用户名不能为空" value="${sessionScope.user.name}">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码</span>
					  <input type="text" class="form-control" placeholder="密码名不能为空" value="${sessionScope.user.password}">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">EMAIL</span>
					  <input type="text" class="form-control" placeholder="EMAIL不能为空" value="${sessionScope.user.email}">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">电&nbsp;&nbsp;&nbsp;话</span>
					  <input type="text" class="form-control" placeholder="电话不能为空" value="${sessionScope.user.telephone}">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">真是姓名</span>
					  <input type="text" class="form-control" placeholder="真是姓名不能为空" value="${sessionScope.user.realname}">
					</div>
					
					<div class="input-group input-group-lg">
					  <span class="input-group-addon">所属公司</span>
					  <input type="text" class="form-control" placeholder="所属公司不能为空" value="${sessionScope.user.companyname}">
					</div>
					
					
					<div class="input-group input-group-lg control-group info">
						<div class="controls">
							<a style="width: 668px" class="btn btn-primary btn-lg" id="updateUser">修改</a>
						</div>
					</div>

				
				</form>		
								
			</div>
			
		</div>
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>