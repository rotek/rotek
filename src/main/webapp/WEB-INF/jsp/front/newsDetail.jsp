<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
	});
</script>
<body>

	

	<div class="r_container">

		<div class="right" style="width: 100%;overflow: scroll;">
			<a style="float: left" href="javascript:history.back()">
				<button type="button" class="btn btn-default btn-lg">返回</button>
			</a>
			<div class="page-header" style="text-align: center">
				<h2 style="">${data.title}</h2>
				<h5 style="color:gray"><fmt:formatDate value="${data.send_time}" pattern="yyyy-MM-dd HH:mm:ss" /></h5>
			</div>
			
			<div style="margin-left: 70px">
				${data.content}
			</div>

		</div>
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>