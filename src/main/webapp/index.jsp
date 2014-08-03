<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Geolocation 对象，Geolocation 与 Google Map 交互 ------ JS Mix</title>
</head>
<body>
	<input type="button" id="getPos" value="获取我的位置">
	<div id="info" class="">
		您所在的位置： 经度 <span class="tip">unknown</span>，纬度 <span class="tip">unknown</span>
	</div>

	<script type="text/javascript">
		var t = 0;
		var dom = {
			btn : document.getElementById('getPos'),
			info : document.getElementById('info')
		};

		dom.btn.onclick = function() {
			if (navigator.geolocation) {
				dom.info.innerHTML = "请等待查询结果返回";
				dom.info.className = "warn";
				navigator.geolocation.getCurrentPosition(getPositionSuccess,
						getPositionError, {
							timeout : 5000
						});
			} else {
				dom.info.innerHTML = "抱歉，您所使用的浏览器不支持 Geolocation 接口";
				dom.info.className = "warn";
			}
		}

		function getPositionSuccess(position) {
			var lat = position.coords.latitude;
			var lng = position.coords.longitude;
			dom.info.innerHTML = "您所在的位置： 经度" + lat + "，纬度" + lng;

			if (typeof position.address === "undefined") {
				dom.info.innerHTML += "<br /><span class='tip'>您的浏览器目前仅提供坐标查询，使用 Firefox 3.5+ 可获得更多信息</span>";
			} else {
				dom.info.innerHTML += "<br /><span class='tip'>"
						+ position.address.country + " , "
						+ position.address.region + " , "
						+ position.address.city + "</span>";
			}
		}

		function getPositionError(error) {
			switch (error.code) {
			case error.TIMEOUT:
				dom.info.innerHTML = "连接超时，请重试";
				break;
			case error.PERMISSION_DENIED:
				dom.info.innerHTML = "您拒绝了使用位置共享服务，查询已取消";
				break;
			case error.POSITION_UNAVAILABLE:
				dom.info.innerHTML = "亲爱的火星网友，非常抱歉<br />我们暂时无法为您所在的星球提供位置服务";
				break;
			}
		}
	</script>
</body>
</html>

