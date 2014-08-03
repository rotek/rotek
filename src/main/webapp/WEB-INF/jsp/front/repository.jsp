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
		
		
		$(".r_container .left a").click(function(){
			
			var name = $(this).text();
			var url = "http://officeweb365.com/o/?i=166&furl=http://demo.cookwaimai.com/" + name;
			$("iframe").attr("src",url);
		});
	});
</script>
<body>

	

	<div class="r_container">

		<div class="left">
			<div style="background-color: #2ac1f2">
				<div class="l_box">
					<div class="l_header">知识库</div>

					<div class="active l_menu">
						<a href="#">test.ppt</a>

					</div>

					<div class="active l_menu">
						<a href="#">test2.doc</a>

					</div>
				</div>

			</div>
		</div>


<div style="padding: 10px 0px 5px 0px; border: solid 1px #dddddd;padding-left: 10px">



		<iframe
			src="http://officeweb365.com/o/?i=166&furl=http://demo.cookwaimai.com/test.ppt"
			style="width: 90%; height: 100%;">
			
			
			
		</iframe>					

</div>

	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>