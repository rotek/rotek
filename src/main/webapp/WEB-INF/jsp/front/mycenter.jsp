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
	height: 100%
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
					<div class="control-group info">
						<label class="control-label" for="inputInfo">用户名：</label>
						<div class="controls">
							<input type="text" id="username" name="name"
								style="height: 30px; width: 500px"
								value="${sessionScope.user.name}"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">密 码：</label>
						<div class="controls">
							<input type="text" id="password" name="password"
								style="height: 30px; width: 500px"
								value="${sessionScope.user.password}"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">EMAIL：</label>
						<div class="controls">
							<input type="text" id="email" name="email" style="height: 30px; width: 500px"
								value="${sessionScope.user.email}"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">电话：</label>
						<div class="controls">
							<input type="text" id="telephone" name="telephone"
								style="height: 30px; width: 500px"
								value="${sessionScope.user.telephone}"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">真实姓名：</label>
						<div class="controls">
							<input type="text" id="realname" name="realname"
								style="height: 30px; width: 500px"
								value="${sessionScope.user.realname}"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">所属公司：</label>
						<div class="controls">
							<input type="text" id="companyname" name="companyname"
								style="height: 30px; width: 500px"
								value="${sessionScope.user.companyname}"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<div class="controls">
							<a style="width: 480px;" class="btn btn-primary btn-lg"
								id="updateUser">修改</a>
						</div>
					</div>

				</form>

			</div>
			
		</div>
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>