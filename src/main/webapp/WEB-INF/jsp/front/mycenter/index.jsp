<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html >
<head>
<%@include file="../frame/meta.jsp"%>

<script type="text/javascript">
	
</script>
</head>
<body>
	<jsp:include page="../frame/top.jsp" ></jsp:include>
	<div style="min-width: 1200px;">
		<div style="margin-right: 260px; background-color: #FCE2CB;">
			<iframe style="width: 100%; height: 750px;" scrolling="auto" frameborder="0" 
			        id="content_iframe" name="content_iframe" src="${pageContext.request.contextPath }/front/rmycenter/toMycenter">
			</iframe>
		</div>
		<jsp:include page="../frame/right.jsp" ></jsp:include>
	</div>
	<jsp:include page="../frame/bottom.jsp" ></jsp:include>
</body>
</html>