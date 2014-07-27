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
					<div class="l_header">水质监测</div>
					
					<div class="l_menu">
						<a href="${pageContext.request.contextPath }/front/water/toWaterMonitor">实时监测</a>
					</div>
		
					
					<div class="l_menu">
						<a href="${pageContext.request.contextPath }/front/water/toWaterMonitorSetter">实时监测设置</a>
					</div>
		
					<div class="l_menu">
						<a href="${pageContext.request.contextPath }/front/water/toWaterMonitorStatistic">历史监测查询</a>
					</div>
					
						<div class="active l_menu">
						<a href="${pageContext.request.contextPath }/front/water/toWaterWarning">水质报警</a>
					</div>
				</div>
		
			</div>
		</div>
		<div style="padding: 10px 0px 5px 0px; border: solid 1px #dddddd;padding-left: 10px">


					<table id="example" class="cell-border" cellspacing="0"
						width="100%">
						<thead>
							<tr>
								<th style="border: solid 1px #dddddd">报警类型</th>
								<th style="border: solid 1px #dddddd">报警内容</th>
								<th style="border: solid 1px #dddddd">报警时间</th>
								<th style="border: solid 1px #dddddd">是否处理</th>
							</tr>
						</thead>

						<tbody>
							
								<tr>
									<td>水质ph值超限报警1</td>
									<td>1号泵前，水质ph值高于正常值0.6</td>
									<td>2014-07-21</td>
									<td>已处理</td>
								</tr>
							
							
								<tr>
									<td>水质ph值超限报警2</td>
									<td>1号泵前，水质ph值高于正常值0.6</td>
									<td>2014-07-22</td>
									<td>已处理</td>
								</tr>
							
							
								<tr>
									<td>水质ph值超限报警3</td>
									<td>1号泵前，水质ph值高于正常值0.6</td>
									<td>2014-07-23</td>
									<td>已处理</td>
								</tr>
							
							
								<tr>
									<td>水质ph值超限报警4</td>
									<td>1号泵前，水质ph值高于正常值0.6</td>
									<td>2014-07-24</td>
									<td>已处理</td>
								</tr>
							
						</tbody>
					</table>

				</div>
		
		
		
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>