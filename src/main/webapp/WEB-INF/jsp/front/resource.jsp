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
	});
</script>
<body>

	<%@include file="/assets/jsp/common_front_top.jsp"%>

	<div class="r_container">
		
		
		<div style="padding: 10px 0px 5px 0px; border: solid 1px #dddddd">


					<table id="example" class="cell-border" cellspacing="0"
						width="100%">
						<thead>
							<tr>
								<th style="border: solid 1px #dddddd">新闻名称</th>
								<th style="border: solid 1px #dddddd">发布人</th>
								<th style="border: solid 1px #dddddd">发布时间</th>
								<th style="border: solid 1px #dddddd">是否重要</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail">Tiger Nixon</a></td>
								<td>管理员1</td>
								<td>2014-06-28</td>
								<td>重要</td>
							</tr>
							
							<tr>
								<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail">Tiger Nixon</a></td>
								<td>管理员1</td>
								<td>2014-06-28</td>
								<td>重要</td>
							</tr>
							
							<tr>
								<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail">Tiger Nixon</a></td>
								<td>管理员1</td>
								<td>2014-06-28</td>
								<td>重要</td>
							</tr>
							
							<tr>
								<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail">Tiger Nixon</a></td>
								<td>管理员1</td>
								<td>2014-06-28</td>
								<td>重要</td>
							</tr>
							
							<tr>
								<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail">Tiger Nixon</a></td>
								<td>管理员1</td>
								<td>2014-06-28</td>
								<td>重要</td>
							</tr>
							
							<tr>
								<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail">Tiger Nixon</a></td>
								<td>管理员1</td>
								<td>2014-06-28</td>
								<td>重要</td>
							</tr>
							
							<tr>
								<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail">Tiger Nixon</a></td>
								<td>管理员1</td>
								<td>2014-06-28</td>
								<td>重要</td>
							</tr>
							
							
						</tbody>
					</table>

				</div>
		
		
		
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>