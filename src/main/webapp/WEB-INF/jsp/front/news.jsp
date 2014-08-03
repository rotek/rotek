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
		
		<div class="left">
			<div style="background-color: #2ac1f2">
				<div class="l_box">
					<div class="l_header">新闻公告</div>
		
					<div class="active l_menu">
						<a href="#">新闻公告</a>
					</div>
				</div>
		
			</div>
		</div>
		
		<div style="padding: 10px 0px 5px 0px; border: solid 1px #dddddd;padding-left: 10px">


					<table id="example" class="cell-border" cellspacing="0"
						width="100%">
						<thead>
							<tr>
								<th style="border: solid 1px #dddddd">标题名称</th>
								<th style="border: solid 1px #dddddd">发布人</th>
								<th style="border: solid 1px #dddddd">发布时间</th>
								<th style="border: solid 1px #dddddd">类型</th>
							</tr>
						</thead>

						<tbody>
							
							<c:forEach items="${dataList}" var="data">
								<tr>
									<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail?id=${data.id}">${data.title}</a></td>
									<td>${data.realname }</td>
									<td>
									<fmt:formatDate value="${data.send_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>
										<c:if test="${1 == data.type}">
											新闻
										</c:if>
										<c:if test="${2 == data.type}">
											新闻
										</c:if>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>

				</div>
		
		
		
	</div>

</body>
</html>