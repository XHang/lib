<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" id="qzhai-css" href="resources/css/index_style.css" type="text/css" media="all">
    <script type="text/javascript" src="resources/js/jquery.min.js"></script>
    
    <style type="text/css">
    	body{
       			border-radius:13px;
       			background-color:white;  	
       		}
       		#index{  			
    		margin-left: 0px;      			
       		}
       		
       		
    </style>
    <title>类别</title>
    

  </head>
  
  <body>
  	<div id="index" >
		<h1 class="h4">分类</h1>
      	<div id="list" class="category">
        	<p class="date"><strong>${sessionScope.user.username}</strong>目前共有分类：${fn:length(categorys)}</p>
        	<c:forEach items="${categorys}"  var="category">
				<a href="article/articleList.do?categoryId=${category.id }" id="${category.id }" >${category.context}</a> 
				<a href="javascript:void(0)" id="break" onclick="changeCategory(${category.id },this)">编辑</a>
				<a href="javascript:void(0)" id="break" onclick="remove(${category.id })">删除</a>
				<br>   
			</c:forEach> 	
			<br/>
			<a href="javascript:void(0)"  onclick="add(this)">新增</a>		
		</div>	
	</div>
		
	<script type="text/javascript">
		function changeCategory(id,obj){
			var category =document.getElementById(id);		//获取编辑上方的类别a标签
			var href=category.href;									//取出a标签的链接
			var text=category.innerHTML;							//取出a标签的文本
			obj.setAttribute("onclick", "javascript:cancel('"+ href+"','"+text+"','"+id+"');")//改变编译标签的事件
			
		
			var context=$(category).text();
			$(category).replaceWith("<input type='text' value="   +context+  " />");//将a替换为输入标签
			$(obj).before("<a href='javascript:void(0)' onclick='update("+id+")' id='temp'>保存</a>");
			//添加两个a标签用于保存或者取消
		}
		//当再次点击编辑事件时恢复原状
		function cancel(href,text,id){
			var In=document.getElementsByTagName("input")[0];	//获取输入标签
			$(In).replaceWith("<a href="+href+" id="+id+">"+text+"</a> ")//替换掉输入标签为原来的a标签
			$("#temp").remove();								//移除保存和取消的标签
			$("#temp").remove();
			document.getElementById("break").setAttribute("onclick", "javascript:changeCategory("+id+",this);")//给编辑标签添加回原来的事件
		}
		function update(id){
			var context=$("input").val();
			alert(context);
			$.post("category/update.do",{id:id+"",context:context+""});
			alert("成功");
		}
		function add(obj){
			$(obj).before("<input type='text' name='context' id='add'/><a href='javascript:void(0)' onclick='javascript:submit()'>提交</a>")
		}
		function submit(){
			var add=document.getElementById("add").value;
			$.post("category/add.do",{context:add+""});
			alert("添加成功");
		}
		function remove(id){
			$.post("category/delete.do",{id:id+""});
			alert("删除成功");
		}
	</script>
  </body>
</html>
