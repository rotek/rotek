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
		
		<div class="left">
			<div style="background-color: #2ac1f2">
				<div class="l_box">
					<div class="l_header">大管家</div>
		
					<div class="l_menu">
						<a href="${pageContext.request.contextPath }/front/steward/toResource">资源下载</a>
					</div>
		
					<div class="active l_menu">
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
								<th style="border: solid 1px #dddddd">咨询类型</th>
								<th style="border: solid 1px #dddddd">咨询人</th>
								<th style="border: solid 1px #dddddd">咨询内容</th>
								<th style="border: solid 1px #dddddd">回复人</th>
								<th style="border: solid 1px #dddddd">回复内容</th>
								<th style="border: solid 1px #dddddd">咨询时间</th>
							</tr>
						</thead>

						<tbody>
							
							<tr>
								<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail">咨询类型</a></td>
								<td>客户1</td>
								<td>咨询内容</td>
								<td>管理员1</td>
								<td>回复内容</td>
								<td>2014-06-28</td>
							</tr>
							
							<tr>
								<td><a href="${pageContext.request.contextPath }/front/news/toNewsDetail">咨询类型</a></td>
								<td>客户1</td>
								<td>咨询内容</td>
								<td>管理员1</td>
								<td>回复内容</td>
								<td>2014-06-28</td>
							</tr>
							
							
							
							
						</tbody>
					</table>

				</div>
		
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>