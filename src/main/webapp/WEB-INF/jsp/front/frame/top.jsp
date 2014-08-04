<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="top">
	<div class="topnav">
		<!-- 我的空间 -->
		<a href="" onclick="changeIframe('${pageContext.request.contextPath}/front/rmycenter/toMycenter');return false;" target="content_iframe">
			<img src="${pageContext.request.contextPath}/assets/images/nav_btn_wdkj.gif">
		</a>
		<!-- 大管家 -->
		<a href="" onclick="changeIframe('${pageContext.request.contextPath}/front/');return false;" target="content_iframe">
			<img src="${pageContext.request.contextPath}/assets/images/nav_btn_dgj.gif">
		</a>
		<!-- 水质监测 -->	
		<a href="" onclick="changeIframe('${pageContext.request.contextPath}/front/');return false;" target="content_iframe">
			<img src="${pageContext.request.contextPath}/assets/images/nav_btn_szjc.gif">
		</a>
		<!-- 流量监测 -->
		<a href="" onclick="changeIframe('${pageContext.request.contextPath}/front/');return false;" target="content_iframe">
			<img src="${pageContext.request.contextPath}/assets/images/nav_btn_lljc.gif">
		</a>
		<!-- 压力监测 -->
		<a href="" onclick="changeIframe('${pageContext.request.contextPath}/front/');return false;" target="content_iframe">
			<img src="${pageContext.request.contextPath}/assets/images/nav_btn_yljc.gif">
		</a>
		<!-- 维护保养 -->
		<a href="" onclick="changeIframe('${pageContext.request.contextPath}/front/');return false;" target="content_iframe">
			<img src="${pageContext.request.contextPath}/assets/images/nav_btn_whby.gif">
		</a>
		<!-- 节水EMC -->
		<a href="" onclick="changeIframe('${pageContext.request.contextPath}/front/');return false;" target="content_iframe">
			<img src="${pageContext.request.contextPath}/assets/images/nav_btn_jnjs.gif">
		</a>
		<!-- 水咨询 -->
		<a href="" onclick="changeIframe('${pageContext.request.contextPath}/front/');return false;" target="content_iframe">
			<img src="${pageContext.request.contextPath}/assets/images/nav_btn_szx.gif">
		</a>
		<!-- 新闻公告 -->
		<a href="" onclick="changeIframe('${pageContext.request.contextPath}/front/rnews/toNews');return false;" target="content_iframe">
			<img src="${pageContext.request.contextPath}/assets/images/nav_btn_xwgg.gif">
		</a>
	</div>
	<div class="logo">
		<img src="${pageContext.request.contextPath}/assets/images/logo.gif">
	</div>
</div>