<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" id="qzhai-css" href="resources/css/index_style.css" type="text/css" media="all">
    
    <title>文章列表</title>

  </head>
  
  <body>
  	<div id="index" >
		<h1 class="h4" style="display: block;">文章列表</h1>
		<div id="list" style="display: block;">
		<c:forEach items="${articles}" var="article">
        	<article class="article class_3">
        		<h1><a href="article/read.do?id=${article.id}">${article.title}</a></h1>
        		<p>
        			<a href="article/read.do?id=${article.id}"><img src="resources/img/111-e1452232566209.jpeg"></a>
        				${article.context}[…]
        			<time><br><fmt:formatDate value="${article.date}" pattern="yyyy年MM月dd日 HH:mm:ss"/></time>
        		</p>
        	</article>
        </c:forEach>
        	</div>
        	
	</div>
  </body>
</html>
