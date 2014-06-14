<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ext 支持文件 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/js/js_lib/ext3.3/resources/css/ext-all.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_lib/ext3.3/ext-base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_lib//ext3.3/ext-all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_lib/ext3.3/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_lib/ext3.3/plugin/TabCloseMenu.js"></script>

<!-- 系统基础的组件文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_common/Base.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_common/Window.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_common/Toolbar.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_common/Validate.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_common/FormPanel.js">
</script>

<script type="text/javascript" >
	//系统的根路径
	var basePath = "${pageContext.request.contextPath }";
	//某个用户在某个模块的权限
	var authority = eval("("+'<%=request.getAttribute("authority")%>'+")");
	//用户名
	var username = "${sessionScope.user.name}";
</script>