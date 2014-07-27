<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息管理</title>
<%@include file="/assets/jsp/common.jsp"%>
</head>
<body>

</body>

<c:choose>
	<c:when test="${docType eq 3 }">
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/customer/customerdoc.js"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/customer/agentdoc.js"></script>
	</c:otherwise>
</c:choose>

</html>