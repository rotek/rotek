<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ROTEK</title>
<%@include file="/assets/jsp/common_front.jsp"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/front/main.css" />

<script type="text/javascript">
	$(function() {

		$("#xc").select2({
			placeholder : "请选择您要查看的现场"
		});

		$("#lj").select2({
			placeholder : "请选择您要查看的零件"
		});

		$("#ljxq").select2({
			placeholder : "请选择您要查看的零件详情"
		});
		
		$("#jcx").select2({
			placeholder : "请选择您要查看的零件监测项"
		});

		$('#container')
				.highcharts(
						{
							title : {
								text : '',
								x : -20
							//center
							},
							subtitle : {
								text : '',
								x : -20
							},
							chart : {
								type : 'spline'
							},
							credits : {
								enabled : false
							},
							exporting : {
								enabled : false
							},
							xAxis : {
								categories : [ '09:00', '09:03', '09:06',
										'09:09', '09:12', '09:15', '09:18',
										'09:21', '09:24', '09:27', '09:30',
										'09:30', '09:33', '09:36', '09:39'

								],
								title : {
									text : '时间'
								}
							},
							yAxis : {
								min : 0,
								title : {
									text : ''
								},
								plotLines : [ {
									value : 0,
									width : 1,
									color : '#808080'
								} ]
							},
							
							legend : {
								layout : 'vertical',
								align : 'right',
								verticalAlign : 'middle',
								borderWidth : 0
							},
							series : [ {
								name : '泵前',
								data : [ 6241254,

								6077990,

								5109029,

								5089420,

								5179244,

								5040082,

								5480296,

								5761536,

								5658397,

								5000961,

								5058386,

								5003862,

								5109044,

								5740651,

								3020836

								]
							}, {
								name : '泵后',
								data : [ 1080665,

								1024570,

								984955,

								978634,

								1002082,

								976896,

								1061218,

								1033341,

								1014192,

								971003,

								963881,

								961400,

								974155,

								1089427,

								601123

								]
							} ]
						});
	});
</script>
<body>

	<%@include file="/assets/jsp/common_front_top.jsp"%>

	<div class="r_container">

		<%@include file="/assets/jsp/common_front_left.jsp"%>
		<div class="right">
			<div class="r_top">

				<select id="xc" style="width: 250px">
					<option value=""></option>
					<option value="0">现场1</option>
					<option value="1">现场2</option>
					<option value="2">现场3</option>
				</select> 
				
				<select id="lj" style="width: 250px; margin-left: 20px">
					<option value=""></option>
					<option value="0">零件1</option>
					<option value="1">零件2</option>
				</select> 
				
				<select id="ljxq" style="width: 250px; margin-left: 20px">
					<option value=""></option>
					<option value="0">泵前</option>
					<option value="1">泵后</option>
				</select>
				
				<select id="jcx" style="width: 250px; margin-top: 20px">
					<option value=""></option>
					<option value="0">ph值</option>
					<option value="1">温度</option>
				</select>
			</div>

			<div class="r_center">
				
				<div  id="container"></div>
			</div>
		</div>
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>