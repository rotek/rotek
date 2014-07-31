
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
 
	$(document).ready(function(){
		//所有点
		window.pointList = [];
		//定时任务
		window.intervalTask = 0;
		//
		var projects = {};
		<c:forEach items="${dataList}" var="data">
			projects["${data.ID}"] = "${data.GCJCTLX}";
		</c:forEach>
		
		$("#xc").select2({
			placeholder : "请选择您要查看的现场"
		}).on("change", function(e) {
			$("[name='xc']").val(e.val);
			
			$(".p_img").attr("src","${pageContext.request.contextPath }/assets/front/img/" + projects[e.val]);
			
			$.ajax({
				url : '${pageContext.request.contextPath }/front/water/getProjectInfo?projectId=' + e.val,
				success : function(response){
					
					//初始化pointlist
					pointList = [];
					$(".mark-text").remove();
					
					var allParams = response.params;
					var projectParamList = response.projectParamList;
					
					$.each(projectParamList,function(index,data){
						//待添加点
						var span = document.createElement("span");
						var id = "id_"+Math.random();
						$(span).attr("id",id);
						$(span).text(allParams[data.SPECIFICPART_PARAM] + " 加载中...");
						$(span).attr("class","mark-text");
						$(span).css({top:data.X+"px",left: data.Y+"px"});
						
						data.ID = id;
						$(".img_container").append(span);
						
						pointList.push(data);
						
					});
					
					loadData(e.val);
					//初始化任务
					window.clearInterval(intervalTask);
					intervalTask = setInterval(loadData,3000);
				}
			});
		});
		
		//加载数据
		function loadData(){
			
			$.ajax({
				
				url : "${pageContext.request.contextPath }/front/water/getWaterMonitorValues?projectId="+ $("[name='xc']").val(),
				success : function(response){
					
					var dataList = response.dataList;
					console.log(response)
					console.log(pointList)
					
					
					$.each(pointList,function(index,data){
						
						$(".mark-text[id='"+data.ID+"']").text(dataList[index]);
					});			
				}
			});
		}
		
	});
	
</script>


<style type="text/css">



.img_container {
	width: 880px;
	height: 600px;
	position: absolute;
	margin: 0 0 0 50px;
}


.p_img {
	position: absolute;
	border: solid 1px blue;
	width: 100%;
	height: 100%;
	z-index: 1;
	top: 0;
	left:0
}

.mark {
	position: absolute;
	z-index: 2;
	width: 42px;
	height: 42px;
	top: 0;
	left:0
}

.mark-text {
	position: absolute;
	z-index: 2;
	background-color: #000000;
	color: white;
	padding: 3px
}

.r_container .right .r_top {
	height : 40px;
	text-align: left;
	background-color: #ffffff;
	padding: 5px 10px 0px 50px;
	min-height : 40px;
	text-align: left;
}
</style>
<body>

	<%@include file="/assets/jsp/common_front_top.jsp"%>

	<div class="r_container">

		<div class="left">
			<div style="background-color: #2ac1f2">
				
				<div class="l_box">
					<div class="l_header">压力监测</div>
					
					<div class="active l_menu">
						<a href="${pageContext.request.contextPath }/front/pressure/toWaterMonitor">压力实时监测</a>
					</div>
		
					
					<div class="l_menu">
						<a href="${pageContext.request.contextPath }/front/pressure/toWaterMonitorSetter">压力监测设置</a>
					</div>
		
					<div class="l_menu">
						<a href="${pageContext.request.contextPath }/front/pressure/toWaterMonitorStatistic">历史监测查询</a>
					</div>
					
				</div>
		
			</div>
		</div>
		

		
		<div class="right">
				
				<div class="r_top">
				
				<select id="xc" style="width: 250px;height: 30px">
					<option value=""></option>
				 <c:forEach items="${dataList}" var="data">
					<option value="${data.ID}" val="${data.GCJCTLX}">${data.GCMC}</option>
				</c:forEach>
				</select> 
				
				<input name="xc" value="" type="hidden"/>
				
				</div>
				
					<div class="img_container">
						<img src="${pageContext.request.contextPath }/assets/front/img/img_700_600.png" class="p_img" />
						
					</div>
					
					
		</div>
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>