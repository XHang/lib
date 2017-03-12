<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'verifySuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<style type="text/css">
	.kg_reg_body_area{ 
		width:960px; 
		margin:0 auto; 
		padding:40px 0 50px;

	}
	.kg_lgn_module01{ 
		border-radius:5px; 
		border:1px solid #ccc;
		 background:#fff;  
		 -webkit-box-shadow:0 0 10px #ccc; 
		 -moz-box-shadow:0 0 10px #ccc; 
		 box-shadow:0 0 10px #ccc; 
		 min-height:550px;
		  _height:550px;
		}

	.kg_lgn_module01_hd{ 
		height:22px; 
		padding:30px 0; 
		border-bottom:1px solid #ccc; 
		border-radius:5px 5px 0 0;
	}
	.kg_lgn_module01_hd .h_tl{
		font:21px/22px Microsoft Yahei,"\5b8b\4f53",sans-serif; 
		color:#006dc1; 
		text-align:center;
	}
</style>
  </head>
  	<div class="kg_reg_body_area">
        <div class="kg_lgn_module01">
            <div class="kg_lgn_module01_hd">
                <h1 class="h_tl">${infohead}</h1>
            </div>
            <div class="kg_lgn_module01_ct">
                <p class="f14 gray" align="center">${infobody}</p>

            </div>
        </div>
    </div>
  <body>
    This is my JSP page. <br>
  </body>
</html>
