
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
		
		
		$("#lj").select2({
			placeholder: "请选择零件"
			}).on("change", function(e) {
		
				$.ajax({
				 	url : "${pageContext.request.contextPath }/front/water/listComponentParts",
				 	data : {componentId : e.val},
				 	success : function(response){
				 		if(response && response.dataList){
					 		//更新input零件信息
					 		$("[name='lj']").val(e.val);
					 		
					 		componentPartList = [];
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
								 			$("[name='ljxq']").val(e.val);
								 			
								 			monitorItemList = [];
								 			$.each(response.dataList,function(index,item){
								 				var data = {
								 					id : item.alias,
								 					text :item.name
								 				};
								 				monitorItemList.push(data);
								 			});
								 			
								 			$("#jcx").select2({
												placeholder : "请选择您要查看的零件监测项",
												multiple: false,
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
		
		
		$("#saveChange").click(function(){
			var ljId = $("[name='lj']").val();
			var ljxqId = $("[name='ljxq']").val();
			var jcxId = $("[name='jcx']").val();
			
			var xOffset = '${xOffset}';
			var yOffset = '${yOffset}';
			
			if(ljId  && ljxqId && jcxId){
				
				var jcxName = "";
				$.each(monitorItemList,function(index,item){
	 				if(item.id == jcxId){
	 					
	 					jcxName = item.text;
	 				}
	 			});
				var id = Math.random();
				var params = {
						id : id,
						ljId : ljId,
						ljxqId : ljxqId,
						jcxId : jcxId,
						jcxName : jcxName,
						xOffset : xOffset,
						yOffset : yOffset
				};
				
				parent.pointList.push(params);
				
				$.each(parent.pointList,function(data,index){
					
					//待添加点
					parent.span = document.createElement("span");
					/* parent.mark = document.createElement("img");
					
					parent.$(parent.mark).attr("src","${pageContext.request.contextPath }/assets/front/img/marker-32-32.png");
					parent.$(parent.mark).attr("class","mark");
					parent.$(parent.mark).css({top:xOffset+"px",left: yOffset+"px"});
					parent.$(".img_container").append(parent.mark); 
					
					*/
					
					parent.$(parent.span).attr("class","mark-text");
					parent.$(parent.span).attr("id",id);
					parent.$(parent.span).text(jcxName);
					parent.$(parent.span).css({top:xOffset+"px",left: yOffset+"px"});
					parent.$(".img_container").append(parent.span);
					
				});
				parent.layer.close(parent.selectorLayer);
			}
			
		});
	});
</script>

<style type="text/css">

form{
	margin: 0 auto;
	width: 300px
}
.select2-container{

width: 250px;
margin: 10px;
}

button {
	width: 250px
}
</style>
<body>


<form action="/test" >
					<select id="lj" style="width: 250px">
						<option value=""></option>
					 <c:forEach items="${dataList}" var="data">
						<option value="${data.id}">${data.group_mc}</option>
					</c:forEach>
					</select> 
					<br/>	
					<input name="lj" value="" type="hidden"/>
					<br/>
					<input id="ljxq"  placeholder="零件详情"/>
					 <br/>
					<input name="ljxq" value="" type="hidden"/>
					<br/>
					<input id="jcx" placeholder="监测参数" />
					<br/>
					<input name="jcx" value="" type="hidden"/>
					<br/>
					
					<button id="saveChange" type="button" class="btn btn-primary">确定</button>

</form>


</body>
</html>