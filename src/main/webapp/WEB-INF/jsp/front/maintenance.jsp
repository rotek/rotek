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

	<%@include file="/assets/jsp/common_front_top.jsp"%>

	<div class="r_container">
		
		<div class="left">
			<div style="background-color: #2ac1f2">
				<div class="l_box">
					<div class="l_header">维护保养</div>
		
					<div class="active l_menu">
						<a href="#">维护保养</a>
					</div>
				</div>
		
			</div>
		</div>
		
		<div style="padding: 10px 0px 5px 0px; border: solid 1px #dddddd;padding-left: 10px">


					<table id="example" class="cell-border" cellspacing="0"
						width="100%">
						<thead>
							<tr>
								<th style="border: solid 1px #dddddd">名称</th>
								<th style="border: solid 1px #dddddd">所属工程</th>
								<th style="border: solid 1px #dddddd">更换时间</th>
								<th style="border: solid 1px #dddddd">额定使用时间</th>
								<th style="border: solid 1px #dddddd">已使用时间</th>
								<th style="border: solid 1px #dddddd">是否超期</th>
							</tr>
						</thead>

						<tbody>
							
							<tr>
								<td>零件1</td>
							<td>测试工程</td>
							<td>2014-07-01 12:12:12</td>
							<td>365天</td>
							<td>32天</td>
							<td>否</td>
							</tr>
							
							<tr>
								<td>零件1</td>
							<td>测试工程</td>
							<td>2014-07-01 12:12:12</td>
							<td>365天</td>
							<td>32天</td>
							<td>否</td>
							</tr>
							
							<tr>
								<td>零件1</td>
							<td>测试工程</td>
							<td>2014-07-01 12:12:12</td>
							<td>365天</td>
							<td>32天</td>
							<td>否</td>
							</tr>
							
							<tr>
								<td>零件1</td>
							<td>测试工程</td>
							<td>2014-07-01 12:12:12</td>
							<td>365天</td>
							<td>32天</td>
							<td>否</td>
							</tr>
							
							<tr>
								<td>零件1</td>
							<td>测试工程</td>
							<td>2014-07-01 12:12:12</td>
							<td>365天</td>
							<td>32天</td>
							<td>否</td>
							</tr>
							
							<tr>
								<td>零件1</td>
							<td>测试工程</td>
							<td>2014-07-01 12:12:12</td>
							<td>365天</td>
							<td>32天</td>
							<td>否</td>
							</tr>
							
						</tbody>
					</table>

				</div>
		
		
		
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>