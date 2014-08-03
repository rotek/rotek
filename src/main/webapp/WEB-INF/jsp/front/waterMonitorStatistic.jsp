
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

<script type="text/javascript"> 

/**
 * 时间格式化 返回格式化的时间
 * @param date {object}  可选参数，要格式化的data对象，没有则为当前时间
 * @param fomat {string} 格式化字符串，例如：'YYYY年MM月DD日 hh时mm分ss秒 星期' 'YYYY/MM/DD week' (中文为星期，英文为week)
 * @return {string} 返回格式化的字符串
 * 
 * 例子:
 * formatDate(new Date("january 01,2012"));
 * formatDate(new Date());
 * formatDate('YYYY年MM月DD日 hh时mm分ss秒 星期 YYYY-MM-DD week');
 * formatDate(new Date("january 01,2012"),'YYYY年MM月DD日 hh时mm分ss秒 星期 YYYY/MM/DD week');
 * 
 * 格式：   
 *    YYYY：4位年,如1993
*　　YY：2位年,如93
*　　MM：月份
*　　DD：日期
*　　hh：小时
*　　mm：分钟
*　　ss：秒钟
*　　星期：星期，返回如 星期二
*　　周：返回如 周二
*　　week：英文星期全称，返回如 Saturday
*　　www：三位英文星期，返回如 Sat
 */
function formatDate(date, format) {
    if (arguments.length < 2 && !date.getTime) {
        format = date;
        date = new Date();
    }
    typeof format != 'string' && (format = 'YYYY-MM-DD 星期');
    var week = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', '日', '一', '二', '三', '四', '五', '六'];
    return format.replace(/YYYY|YY|MM|DD|hh|mm|ss|星期|周|www|week/g, function(a) {
        switch (a) {
        case "YYYY": return date.getFullYear();
        case "YY": return (date.getFullYear()+"").slice(2);
        case "MM": return date.getMonth() + 1;
        case "DD": return date.getDate();
        case "hh": return date.getHours();
        case "mm": return date.getMinutes();
        case "ss": return date.getSeconds();
        case "星期": return "星期" + week[date.getDay() + 7];
        case "周": return "周" +  week[date.getDay() + 7];
        case "week": return week[date.getDay()];
        case "www": return week[date.getDay()].slice(0,3);
        }
    });
}
 
	$(document).ready(function(){
  
		//初始化图表
		createCharts([{name:'请选择参数',data:[]}]);
		
		$("#xc").select2({
			placeholder : "请选择您要查看的现场"
		}).on("change", function(e) {
			
			 $.ajax({
			 	url : "${pageContext.request.contextPath }/front/water/listComponents",
			 	data : {projectId : e.val},
			 	success : function(response){
			 		if(response && response.dataList && response.dataList.length > 0){
			 			//更新input现场信息
			 			$("[name='xc']").val(e.val);
			 			
			 			var componentList = [];
			 			$.each(response.dataList,function(index,item){
			 				var data = {
			 					id : item.id,
			 					text :item.group_mc
			 				};
			 				componentList.push(data);
			 			});
			 			
			 			$("#lj").select2({
							placeholder: "请选择零件",
		  					data: componentList
							}).on("change", function(e) {
						
								$.ajax({
								 	url : "${pageContext.request.contextPath }/front/water/listComponentParts",
								 	data : {componentId : e.val},
								 	success : function(response){
								 		if(response && response.dataList){
									 		//更新input零件信息
									 		$("[name='lj']").val(e.val);
									 		
									 		var componentPartList = [];
								 			$.each(response.dataList,function(index,item){
								 				var data = {
								 					id : item.id,
								 					text :item.specific_part
								 				};
								 				componentPartList.push(data);
								 			});
								 			$("#ljxq").select2({
												placeholder : "请选择您要查看的零件详情",
												data: componentPartList
												
											}).on("change", function(e) {
												
												$.ajax({
												 	url : "${pageContext.request.contextPath }/front/water/listMonitorItems",
												 	success : function(response){
												 		if(response && response.dataList){
												 			//更新零件详情的input值
												 			$("[name='lj']").val(e.val);
												 			
												 			var monitorItemList = [];
												 			$.each(response.dataList,function(index,item){
												 				console.log(index);
												 				console.log(item);
												 				var data = {
												 					id : item.id,
												 					text :item.name
												 				};
												 				monitorItemList.push(data);
												 			});
												 			
												 			console.log(monitorItemList);
												 			
												 			$("#jcx").select2({
																placeholder : "请选择您要查看的零件监测项",
																multiple: true,
																allowClear: true,
																maximumInputLength: 10,
																data: monitorItemList
															}).on("change", function(e) {
																
																$("[name='jcx']").val(e.val);
															});
												 		}
												 	}
												});
												
											});
								 		}
								 	}
								});
								
							});
			 		}
			 	},
			 	error : function(){
			 		
			 	}
			 }); 
		});

		$('#datepicker').daterangepicker({
			format : 'YYYY-MM-DD',
			startDate : '2014-05-06',
			endDate : '2014-05-12'
		});

		$("#query").click(function(){
			
			$.ajax({
			 	url : "${pageContext.request.contextPath }/front/water/listStatistic",
			 	success : function(response){
			 		if(response && response.dataList){
			 			
			 			var serises = new Array();
			 			$.each(response.dataList,function(index,item){
			 				
			 				serises.push({
								name : item.name,
								data : item.dataList
							}); 	
			 			});
			 			
			 			createCharts(serises);
			 		}
			 	}
			});
		});
	});
	
	
	function createCharts(serises){
		
		$('#container').highcharts({
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
				type: 'datetime',
				title : {
					text : '时间'
				},
				dateTimeLabelFormats: {
					day: '%Y<br/>%m-%d',
					week: '%Y<br/>%m-%d',
					month: '%Y-%m',
					year: '%Y'
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
			tooltip: {
				formatter: function() {
	                var s = '日期：<b>'+ formatDate(new Date(this.x)) +'</b> <br />';
	                s += this.point.series.name + "：<b>" + this.y +'</b>';
	                return s;
				}
			},                
			series : serises
		});
	}
</script>
<body>

	

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
		
					<div class="active l_menu">
						<a href="${pageContext.request.contextPath }/front/water/toWaterMonitorStatistic">历史监测查询</a>
					</div>
					
						<div class="l_menu">
						<a href="${pageContext.request.contextPath }/front/water/toWaterWarning">水质报警</a>
					</div>
				</div>
		
			</div>
		</div>
		
		<div class="right">
			
			
			<div class="r_center">

				
				<div class="r_top">
<form action="/test">
					<select id="xc" style="width: 250px">
						<option value=""></option>
					 <c:forEach items="${dataList}" var="data">
						<option value="${data.id}">${data.gcmc}</option>
					</c:forEach>
					</select> 
					
					<input name="xc" value="" type="hidden"/>
					
					<input id="lj" style="width: 250px; margin-left: 20px" placeholder="零件"/>
						
					<input name="lj" value="" type="hidden"/>
					
					 <input id="ljxq" style="width: 250px; margin-left: 20px" placeholder="零件详情"/>
					 
					<input name="ljxq" value="" type="hidden"/>
					
					<input id="jcx" style="width: 250px; margin-top: 20px;float: left" placeholder="监测参数" />
						
					<input name="jcx" value="" type="hidden"/>

					<div class="input-group"
						style="width: 250px; margin-top: 20px; float: left; cursor: pointer; height: 40px">
						<input
							style="width: 230px; margin-left: 20px; float: left; cursor: pointer; height: 30px"
							type="text" name="timeInterval" id="datepicker"
							class="form-control" readonly="true" value="">
					</div>

					<div style="margin: 20px 0px 0px 30px">
						<button id="query" type="button" class="btn btn-primary">查询</button>
					</div>
</form>
			</div>
				
				
				
				
				
				
				
				<div id="container"></div>
				
         

				
				
			</div>
		</div>
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>