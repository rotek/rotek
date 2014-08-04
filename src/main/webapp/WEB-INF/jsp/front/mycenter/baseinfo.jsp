<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 客户基本信息 -->
<c:if test="${flagStr eq 'info' }">
	<div class="h2">客户基本信息</div>
	<p style="margin-top: 0px;">客户名称：</p>
	<p>客户经纬度地址：</p>
	<p>客户通信地址：</p>
	<p>联系人：</p>
	<p>联系电话：</p>
	<p>所属代理商：</p>
</c:if>

<!-- 客户资源信息 -->
<c:if test="${flagStr eq 'resource' }">
	<div class="h2">客户资源信息</div>
	<table width="90%" border="0" cellspacing="1" cellpadding="0" style="background: #EF7E18;">
		<thead>
			<tr>
				<td width="50%" align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">资料名称</td>
				<td width="30%" align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">资料类型</td>
				<td align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">操作</td>
			</tr>
		</thead>
		<tr>
			<td bgcolor="#FFFFFF">32131321</td>
			<td align="center" bgcolor="#FFFFFF">文档</td>
			<td align="center" bgcolor="#FFFFFF">
				<a href="#" target="_blank"><img src="${pageContext.request.contextPath }/assets/images/icon_see.jpg" width="26" height="25" /> 在线查看</a>
			</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF">&nbsp;</td>
			<td align="center" bgcolor="#FFFFFF">视频</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF">&nbsp;</td>
			<td align="center" bgcolor="#FFFFFF">演示稿</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF">&nbsp;</td>
			<td align="center" bgcolor="#FFFFFF">图片</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
	</table>
</c:if>

<!-- 客户工程信息 -->
<c:if test="${flagStr eq 'project' }">
	<div class="h2">客户工程信息</div>
	<table width="90%" border="0" cellspacing="1" cellpadding="0" style="background: #EF7E18;">
		<thead>
			<tr>
				<td width="50%" align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">工程名称</td>
				<td width="30%" align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">工程分类</td>
				<td align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">操作</td>
			</tr>
		</thead>
		<tr>
			<td bgcolor="#FFFFFF">客户工程信息1</td>
			<td align="center" bgcolor="#FFFFFF">普通工程</td>
			<td align="center" bgcolor="#FFFFFF">
				<a href="03_1gongcheng.html"><img src="${pageContext.request.contextPath }/assets/images/icon_see.jpg" width="26" height="25" /> 在线查看</a>
			</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF">客户工程信息2</td>
			<td align="center" bgcolor="#FFFFFF">普通工程</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF">客户工程信息3</td>
			<td align="center" bgcolor="#FFFFFF">EMC工程</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF">客户工程信息4</td>
			<td align="center" bgcolor="#FFFFFF">EMC工程</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
	</table>
</c:if>

<!-- 客户零件信息 -->
<c:if test="${flagStr eq 'component' }">
	<div class="h2">客户零件信息</div>
	<div style="font-size: 12px; width: 100%; padding-bottom: 20px; float: right; font-weight: bold;">
		零件查询： <select name="select" id="select"><option>零件类别</option></select> 
		<input type="text" name="textfield" id="textfield" style="width: 30%;" />
		<button value="查询">查询</button>
	</div>
	<table width="90%" border="0" cellspacing="1" cellpadding="0" style="background: #EF7E18;">
		<thead>
			<tr>
				<td width="10%" align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">零件编号</td>
				<td width="50%" align="center" bgcolor="#F7B77D"
					style="font-weight: bold; font-size: 14px;">零件名称</td>
				<td width="30%" align="center" bgcolor="#F7B77D"
					style="font-weight: bold; font-size: 14px;">零件类别</td>
				<td width="10%" align="center" bgcolor="#F7B77D"
					style="font-weight: bold; font-size: 14px;">操作</td>
			</tr>
		</thead>
		<tr>
			<td align="center" bgcolor="#FFFFFF">客户零件信息1</td>
			<td align="left" bgcolor="#FFFFFF">普通工程</td>
			<td align="center" bgcolor="#FFFFFF">1泵，砂滤器，碳滤器，软化器，过滤器，膜，紫外杀菌器，水箱，加药装置</td>
			<td align="center" bgcolor="#FFFFFF">
				<a href="04_1liangjian.html">
					<img src="${pageContext.request.contextPath }/assets/images/icon_see.jpg" width="26" height="25" /> 在线查看</a>
			</td>
		</tr>
		<tr>
			<td align="center" bgcolor="#FFFFFF">客户零件信息2</td>
			<td align="left" bgcolor="#FFFFFF">普通工程</td>
			<td align="center" bgcolor="#FFFFFF">1泵，砂滤器，碳滤器，软化器，过滤器，膜，紫外杀菌器，水箱，加药装置</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
		<tr>
			<td align="center" bgcolor="#FFFFFF">客户零件信息3</td>
			<td align="left" bgcolor="#FFFFFF">EMC工程</td>
			<td align="center" bgcolor="#FFFFFF">1泵，砂滤器，碳滤器，软化器，过滤器，膜，紫外杀菌器，水箱，加药装置</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
		<tr>
			<td align="center" bgcolor="#FFFFFF">客户零件信息4</td>
			<td align="left" bgcolor="#FFFFFF">EMC工程</td>
			<td align="center" bgcolor="#FFFFFF">1泵，砂滤器，碳滤器，软化器，过滤器，膜，紫外杀菌器，水箱，加药装置</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
	</table>
</c:if>

<!-- 客户申报  -->
<c:if test="${flagStr eq 'shenbao' }">
	<div class="h2">客户申报</div>
	<table width="90%" border="0" cellspacing="1" cellpadding="0" style="background: #EF7E18;" id="listtable">
		<thead>
			<tr>
				<td width="60%" align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">标题</td>
				<td width="20%" align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">留言时间</td>
				<td width="20%" align="center" bgcolor="#F7B77D" style="font-weight: bold; font-size: 14px;">操作</td>
			</tr>
		</thead>
		<tr>
			<td align="left" bgcolor="#FFFFFF">客户零件信息1</td>
			<td align="center" bgcolor="#FFFFFF">普通工程</td>
			<td align="center" bgcolor="#FFFFFF">
				<a href="05_1shenbao.html">
					<img src="${pageContext.request.contextPath }/assets/images/icon_see.jpg" width="26" height="25" /> 在线查看</a>
			</td>
		</tr>
		<tr>
			<td align="left" bgcolor="#FFFFFF">客户零件信息2</td>
			<td align="center" bgcolor="#FFFFFF">普通工程</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
		<tr>
			<td align="left" bgcolor="#FFFFFF">客户零件信息3</td>
			<td align="center" bgcolor="#FFFFFF">EMC工程</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
		<tr>
			<td align="left" bgcolor="#FFFFFF">客户零件信息4</td>
			<td align="center" bgcolor="#FFFFFF">EMC工程</td>
			<td align="center" bgcolor="#FFFFFF">&nbsp;</td>
		</tr>
	</table>
	<table border="0" cellspacing="0" cellpadding="0" id="notable" style="margin-top: 20px;">
		<tr>
			<td width="60"><span style="font-size: 12px;"><strong>提交问题：</strong></span></td>
			<td><input type="text" name="textfield" id="textfield" style="width: 500px;" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td style="padding: 10px 0;"><textarea name="textfield2" id="textfield2" style="width: 500px;"></textarea></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td align="center"><button value="查询">提交</button></td>
		</tr>
	</table>
	<div style="font-size: 12px;"></div>
</c:if>