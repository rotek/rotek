<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CTA后台管理系统</title>
<%@include file="/assets/jsp/common.jsp"%>
</head>
<body>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_app/admin/about/abouts.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/js_lib/ext3.3/plugin/FCKeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/js_lib/fckeditor/fckeditor.js"></script>

<script type="text/javascript" >
	var about_pic_upload_url = basePath+"${about_pic_upload_url}";

	var oFCKeditorOptions = {
	        BasePath: '${pageContext.request.contextPath}/assets/js/js_lib/fckeditor/',
	        abasepath:basePath,
	        Config: {
	            SkinPath: '${pageContext.request.contextPath}/assets/js/js_lib/fckeditor/editor/skins/silver/',
	            CustomConfigurationsPath: '${pageContext.request.contextPath}/assets/js/js_lib/fckeditor/fckconfig.js',
	            ImageUploadURL : about_pic_upload_url,
	            ProcessHTMLEntities: true,
	            ProcessNumericEntities: false
	        },
	        AllowQueryStringDebu: false,
	        ToolbarSet: 'myCTA'
	    };
</script>
</html>