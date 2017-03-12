<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<html>

<!-- 现在的问题是，文章默认类别固定选一，然而数据库的默认类别是2，想把文章从category设为null出现坏请求。除飞根本不发送
	  大概解决办法就是用ajax请求出默认类别的id，本来也应该这么做。还有。。。恩，没了。。。 -->
<head>
	<script src="../js/jquery.min.js"></script>
	<base href="<%=basePath%>">
</head>
<body>
<form action="article/execModify.do" method="post" target="_parent">
	
	<select name="category.id">
		<option>请选择文章类型</option>
	</select>
	是否隐藏?<br>
	是
	<input type="radio" name="hide" value="true" >
	否
	<input type="radio" name="hide" value="false" >
	<input type="hidden" value="${article.id}" name="id" />
	<input type="hidden" value="" name="title" />
	<input type="hidden" value="" name="context" />
	<input type="submit" value="修改！">
</form>
	<script type="text/javascript">
		$(document).ready(function(){
			$("[name='title']").val($(window.parent.document).find(".title").val());
			$("[name='context']").val($(window.parent.document).find("textarea").text());
			$("[name='id']").val($(window.parent.document).find(".id").text());
			$.post("category/getCategory.do",function(data,status){
				for(var i=0;i<data.length;i++){
					$("option").after("<option value="+data[i].id+">"+data[i].context+"</option>");
				}
			});
		});

	</script>
</body>