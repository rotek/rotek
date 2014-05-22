<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="java.text.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script language="JavaScript" type="text/javascript"> 
    function showTime() { 
	    var today;
	    var hour;
	    var second;
	    var minute;
	    var year;
	    var month;
	    var date;
	    var strDate; 
	    today = new Date(); 
	    var n_day = today.getDay(); 
	    switch (n_day) { 
		    case 0:{ 
		    	strDate = "星期日" 
		    }break; 
		    case 1:{ 
		    	strDate = "星期一" 
		    }break; 
		    case 2:{ 
		    	strDate ="星期二" 
		    }break; 
		    case 3:{ 
		    	strDate = "星期三" 
		    }break; 
		    case 4:{ 
		    	strDate = "星期四" 
		    }break; 
		    case 5:{ 
		    	strDate = "星期五" 
		    }break; 
		    case 6:{ 
		    	strDate = "星期六" 
		    }break; 
		    case 7:{ 
		    	strDate = "星期日" 
		    }break; 
	    } 
	    year = today.getFullYear(); 
	    month = today.getMonth()+1; 
	    date = today.getDate(); 
	    hour = today.getHours(); 
	    minute =today.getMinutes(); 
	    second = today.getSeconds(); 
	    if(month<10) month="0"+month; 
	    if(date<10) date="0"+date; 
	    if(hour<10) hour="0"+hour; 
	    if(minute<10) minute="0"+minute; 
	    if(second<10) second="0"+second; 
	    document.getElementById('clock').innerHTML = year + "年" + month + "月" + date + "日 " + strDate +" " + hour + ":" + minute + ":" + second; 
	    setTimeout("showTime();", 1000); 
    }
    
</script> 

</head>

<body onload="showTime()">

<font size="5">现在时间是：</font><div style="color: red;" id="clock"></div>

<h1 align="center" >您已经进入登录测试页面！！</h1>
<h2 align="center"> 恭喜，项目代码工程部署好了。。。。</h2>

</body>
</html>