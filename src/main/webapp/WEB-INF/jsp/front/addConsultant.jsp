<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>ROTEK</title>
<%@include file="/assets/jsp/common_front.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/front/main.css" />
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#example').dataTable();

		$("#consultCategory").select2({
			placeholder : "请选择咨询分类"
		}).on("change", function(e) {
			
			$("[name=type]").val(e.val);
		});
		
		$("#addConsultant").click(function(){
			
			var type = $("[name=type]").val();
			var content = $("[name=content]").val();
			
			if(!type){
				alert("请选择咨询类型");
				return;
			}
			
			if(!content){
				alert("请填写咨询内容");
				return;
			}
			
			$("form").submit();
		});
	});
</script>

<style type="text/css">
.input-group {
	margin: 30px 150px 0px 100px
}
</style>
<body>

	

	<div class="r_container">

		<div class="left">
			<div style="background-color: #2ac1f2">
				<div class="l_box">
					<div class="l_header">大管家</div>

					<div class="l_menu">
						<a
							href="${pageContext.request.contextPath }/front/steward/toResource">资源下载</a>
					</div>

					<div class="l_menu">
						<a
							href="${pageContext.request.contextPath }/front/steward/toConsultants">咨询平台</a>
					</div>

					<div class="active l_menu">
						<a
							href="${pageContext.request.contextPath }/front/steward/toAddConsultant">提交咨询</a>
					</div>
				</div>

			</div>
		</div>



		<div class="right">
			<div class="top well">当前时间：2014-06-22 客户名称：管理员 工程名称：测试工程</div>

			<div class="center well">

				<form class="form-horizontal" style="margin-top: 20px;"
					action="${pageContext.request.contextPath }/front/steward/addConsultant"
					method="post">

					<select id="consultCategory" style="width: 80%">
						<option value=""></option>
						<option value="1">技术交流</option>
						<option value="2">业务分享</option>
					</select>
					
					<input name="type" type="hidden" value=""/>

					<textarea placeholder="请填写咨询内容" name="content"
						style="width: 80%; height: 350px; margin: 20px 0 20px 0"></textarea>


						<div class="controls">
							<a  style="width: 200px" class="btn btn-primary btn-lg" id="addConsultant">提交</a>
						</div>
				</form>
	
			</div>

		</div>


	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>