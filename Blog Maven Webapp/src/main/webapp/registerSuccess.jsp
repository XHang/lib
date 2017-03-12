<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'registerSuccess.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="resources/css/registerSuccess.css">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div class="total">
		<table cellpadding="0" align="center">
			<tbody>
				<tr>
					<th valign="middle">轻博客</th>
				</tr>
				<tr>
					<td>
						<div class="detailed">
							<h2 >亲爱的 ${username}：</h2>
							<p>欢迎注册轻博客，开启你的奇异人生吧。
							   但是首先，请登录您的邮箱进行验证。
							   验证通过，你就是我们的一员了！
							   PS:如果收不到邮件，请查看您的邮箱垃圾站，可能我们的邮件正躺在那里不见天日呢</p>
							<p align="right">轻博客官方团队</p>
							<p align="right"></p>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
	Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
	}
		window.onload=function(){
			var date=new Date().Format("yyyy-MM-dd hh:mm:ss");
			document.getElementsByTagName("p")[2].innerHTML=date;
		};
	</script>
</body>
</html>
