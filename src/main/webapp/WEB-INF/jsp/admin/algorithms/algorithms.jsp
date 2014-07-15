<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>算法设置管理</title>
<%@include file="/assets/jsp/common.jsp"%>
</head>
<body>

</body>

<c:choose>
	<c:when test="${algorithmsType eq 1 }">
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/algorithms/algorithms1.js"></script>
	</c:when>
	<c:when test="${algorithmsType eq 2 }">
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/algorithms/algorithms2.js"></script>
	</c:when>
	<c:when test="${algorithmsType eq 3 }">
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/algorithms/algorithms3.js"></script>
	</c:when>
	<c:when test="${algorithmsType eq 4 }">
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/algorithms/algorithms4.js"></script>
	</c:when>
	<c:when test="${algorithmsType eq 5 }">
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/algorithms/algorithms5.js"></script>
	</c:when>
	<c:when test="${algorithmsType eq 6 }">
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/algorithms/algorithms6.js"></script>
	</c:when>
	<c:when test="${algorithmsType eq 7 }">
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/algorithms/algorithms7.js"></script>
	</c:when>
	<c:when test="${algorithmsType eq 8 }">
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/algorithms/algorithms8.js"></script>
	</c:when>
	<c:otherwise><jsp:include page="../../error/404.jsp"></jsp:include></c:otherwise>
</c:choose>


</html>