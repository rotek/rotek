<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<div class="l_header">大管家</div>
		
					<div class="active l_menu">
						<a href="${pageContext.request.contextPath }/front/steward/toResource">资源下载</a>
					</div>
		
					<div class="l_menu">
						<a href="${pageContext.request.contextPath }/front/steward/toConsultants">咨询平台</a>
					</div>
		
					<div class="l_menu">
						<a href="${pageContext.request.contextPath }/front/steward/toAddConsultant">提交咨询</a>
					</div>
				</div>
		
			</div>
		</div>
		
		<div style="padding: 10px 0px 5px 0px; border: solid 1px #dddddd;padding-left: 10px">


					<table id="example" class="cell-border" cellspacing="0"
						width="100%">
						<thead>
							<tr>
								<th style="border: solid 1px #dddddd">序号</th>
								<th style="border: solid 1px #dddddd">资源名称</th>
								<th style="border: solid 1px #dddddd">发布者</th>
								<th style="border: solid 1px #dddddd">发布日期</th>
								<th style="border: solid 1px #dddddd">操作</th>
							</tr>
						</thead>

						<tbody>
							
							<c:forEach items="${dataList}" var="data">
								<tr>
									<td>${data.id }</td>
									<td>${data.title }</td>
									<td>${data.realname }</td>
									<td>
										<fmt:formatDate value="${data.send_time}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td><a href="${data.url}">下载</a></td>
								</tr>
							</c:forEach>
							
							
						</tbody>
					</table>

				</div>
		
		
		
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>