<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ROTEK</title>
<%@include file="/assets/jsp/common_front.jsp"%>

<script type="text/javascript">
	$(function() {

		$(document).click(function() {

		});
	});
</script>

<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
	color: #000;
	font-family: "Microsoft Yahei", "微软雅黑", Tahoma, Arial, Helvetica,
		STHeiti, sans-serif;
	font-size: 13px;
	background-color: #FFF;
}
/**header**/
.r_header {
	width: 1200px;
	height: 100px;
	margin: 0 auto;
	overflow: hidden;
	background-color: red;
}

.r_header .left {
	width: 200px;
	height: 100px;
	float: left
}

.r_header .left img {
	width: 200px;
	height: 100px
}

.r_header .right {
	margin-left: 210px;
	width: 1000px;
	height: 100px;
	padding: 5px
}

.r_header .right img {
	width: 90px;
	height: 90px;
	margin-right: 10px
}

/**=====================================================main=============================================================**/
.r_container {
	width: 1200px;
	height: 750px;
	margin: 0px auto;
	margin-top: 10px;
	overflow: hidden
}

.r_container .left .nav-big-font {
	font-size: 15px;
}

.r_container .left {
	width: 200px;
	height: 100%;
	padding: 8px 0 0 0;
	font-size: 14px;
	float: left
}

.r_container .right {
	width: 980px;
	height: 735px;
	margin-left: 10px;
	padding: 8px 0 0 0;
	font-size: 14px;
	float: right;
}

.r_container .right .top {
	width: 960px;
	height: 30px;
	margin-left: 10px;
	margin-right: 10px;
	padding: 6px 0 0 0;
	font-size: 14px;
	border-style: solid;
	text-align: center;
}

.r_container .right .center {
	width: 960px;
	height: 665px;
	margin-left: 10px;
	padding: 8px 0 0 0;
	border-style: solid;
	border-color: #dddddd;

}

/**=====================================================main=============================================================**/
/**foot**/
.r_footer {
	width: 1200px;
	height: 30px;
	margin: 0px auto;
	margin-top: 10px;
	overflow: hidden;
	background-color: #f5f5f5;
	text-align: center;
	padding: 10px
}
</style>
<body>


	<%@include file="/assets/jsp/common_front_top.jsp"%>

	<div class="r_container">

		<%@include file="/assets/jsp/common_front_left.jsp"%>
		<div class="right">
			<div class="top well">当前时间：2014-06-22 客户名称：管理员 工程名称：测试工程</div>

			<div class="center well">

				<form class="form-horizontal" style="margin-top:30px">

					<div class="control-group info">
						<label class="control-label" for="inputInfo">用户名：</label>
						<div class="controls">
							<input type="text" id="inputInfo" style="height:30px;width:500px"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">密		码：</label>
						<div class="controls">
							<input type="text" id="inputInfo" style="height:30px;width:500px"> <span
								class="help-inline"></span>
						</div> 
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">EMAIL：</label>
						<div class="controls">
							<input type="text" id="inputInfo" style="height:30px;width:500px"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">电话：</label>
						<div class="controls">
							<input type="text" id="inputInfo" style="height:30px;width:500px"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">真实姓名：</label>
						<div class="controls">
							<input type="text" id="inputInfo" style="height:30px;width:500px"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<label class="control-label" for="inputInfo">所属公司：</label>
						<div class="controls">
							<input type="text" id="inputInfo" style="height:30px;width:500px"> <span
								class="help-inline"></span>
						</div>
					</div>

					<div class="control-group info">
						<div class="controls">
							<a  style="width:480px;" class="btn btn-primary btn-lg" role="button">修改</a>
						</div>
					</div>

					
				</form>

				


			</div>
		</div>
	</div>


	<%@include file="/assets/jsp/common_front_bottom.jsp"%>
</body>
</html>