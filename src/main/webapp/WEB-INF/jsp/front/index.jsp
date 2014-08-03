<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ROTEK</title>
<%@include file="/assets/jsp/common_front.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/assets/front/main.css" />
<style type="text/css">
body {
	margin: 0px;
	background-color: #fff;
}

.top {
	height: 88px;
	width: 100%;
	background:
		url(${pageContext.request.contextPath }/assets/images/bg.jpg) left top
		no-repeat;
	background-color: #EF7E18;
	min-width: 1200px;
}

.topnav {
	width: 898px;
	height: 88px;
	padding: 10px 0 0 30px;
	float: left;
}

.topnav a {
	padding-right: 40px;
	float: left;
}

.logo {
	float: right;
	padding: 13px 30px 0 0;
}

.rightnav {
	width: 260px;
	background-color: #F7B77D;
	position: absolute;
	right: 0;
	top: 88px;
	height: 750px;
}

.rightnav a {
	float: left;
	padding: 0 15px 20px 5px;
}
</style>



<script type="text/javascript">
	$(function() {

	});
</script>

</head>
<body>
	<div class="top">
		<div class="topnav">
			<!-- 要求的顺序 ； 我的空间-大管家-水质监测-流量监测-压力监测-维护保养-节能节水EMC-水咨询-新闻与公告 -->
			<a href="${pageContext.request.contextPath }/front/mycenter/toMycenterContent" target="content"><img
				src="${pageContext.request.contextPath }/assets/images/nav_btn_wdkj.gif"></a>
			
			<a href="${pageContext.request.contextPath }/front/news/toNews" target="content"><img
				src="${pageContext.request.contextPath }/assets/images/nav_btn_xwgg.gif"></a>
				
			<a href="###" target="content"><img
				src="${pageContext.request.contextPath }/assets/images/nav_btn_szjc.gif"></a>
			<a href="###" target="content"><img
				src="${pageContext.request.contextPath }/assets/images/nav_btn_lljc.gif"></a>
			<a href="###" target="content"><img
				src="${pageContext.request.contextPath }/assets/images/nav_btn_yljc.gif"></a>
			<a href="###" target="content"><img
				src="${pageContext.request.contextPath }/assets/images/nav_btn_jnjs.gif"></a>
			<a href="###" target="content"><img
				src="${pageContext.request.contextPath }/assets/images/nav_btn_whby.gif"></a>
			<a href="###" target="content"><img
				src="${pageContext.request.contextPath }/assets/images/nav_btn_szx.gif"></a>
			<a href="###" target="content"><img
				src="${pageContext.request.contextPath }/assets/images/nav_btn_dgj.gif"></a>
		</div>
		<div class="logo">
			<img src="${pageContext.request.contextPath }/assets/images/logo.gif">
		</div>
	</div>

	<div style="min-width: 1200px;">
		<div style="margin-right: 260px; background-color: #FCE2CB;">
			<iframe style="width: 100%; height: 750px;" scrolling="auto"
				frameborder="0" id="content" name="content"
				src="${pageContext.request.contextPath }/front/mycenter/toMycenterContent">


			</iframe>
		</div>
		<div class="rightnav">
			<div
				style="padding: 20px 0 0 20px; font-weight: bold; font-size: 16px; font-family: '微软雅黑', Tahoma;">
				XXXX,你好！</div>
			<div style="padding: 10px 0 20px 120px;">
				<a href="#"><img
					src="${pageContext.request.contextPath }/assets/images/button_logout.jpg"
					width="96" height="30" /></a>
			</div>
			<div style="padding: 0 0 0 10px; float: left;">
				<a href="###" target="content"><img
					src="${pageContext.request.contextPath }/assets/images/rnav_btn_jrjj.gif"></a>
				<a href="###" target="content"><img
					src="${pageContext.request.contextPath }/assets/images/rnav_btn_lsjc.gif"></a>
				<a href="###" target="content"><img
					src="${pageContext.request.contextPath }/assets/images/rnav_btn_ssjc.gif"></a>
				<a href="###" target="content"><img
					src="${pageContext.request.contextPath }/assets/images/rnav_btn_bjcx.gif"></a>
				<a href="###" target="content"><img
					src="${pageContext.request.contextPath }/assets/images/rnav_btn_lllj.gif"></a>
				<a href="###" target="content"><img
					src="${pageContext.request.contextPath }/assets/images/rnav_btn_emc.gif"></a>
				<a href="###" target="content"><img
					src="${pageContext.request.contextPath }/assets/images/rnav_btn_zlxz.gif"></a>
				<a href="###" target="content"><img
					src="${pageContext.request.contextPath }/assets/images/rnav_btn_zxpt.gif"></a>
			</div>
		</div>
	</div>

	<div
		style="width: 100%; height: 50px; line-height: 50px; text-align: center; color: #333; font-family: '微软雅黑', Tahoma; font-size: 12px; background-color: #FFF;">
		联系电话：（86-10）58673289、90、91、92、93、94 传真：（86-10）58672795、96
		电子邮箱：rotek@rotek.com.cn</div>
</body>
</html>