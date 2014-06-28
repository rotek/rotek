<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ROTEK</title>
<%@include file="/assets/jsp/common_front.jsp"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/front/main.css" />

<style type="text/css">

.r_top{
	height:5%;
	text-align: left;
}

.r_center{
	height:95%
}

</style>

<script type="text/javascript">
	$(function() {

		$("#test").select2({
			placeholder: "请选择您要查看的现场"
		});
		
		$("#test1").select2({
			placeholder: "请选择您要查看的现场"
		});
		
	});
</script>
<body>

	<%@include file="/assets/jsp/common_front_top.jsp"%>

	<div class="r_container">

		<%@include file="/assets/jsp/common_front_left.jsp"%>
		<div class="right">
			<div class="r_top well">
			
				<select id="xc" style="width:200px">
					<option value=""></option>
					<option value="12">10</option>
					<option value="13">20</option>
				</select>
			
				
				<select id="test1" style="width:200px;margin-left:20px">
					<option value=""></option>
					<option value="12">10</option>
					<option value="13">20</option>
				</select>
			
				
			</div>

			<div class="r_center well"></div>
		</div>
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>