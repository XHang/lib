<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
    <!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
	<title>博客编辑器</title>
	<link rel="stylesheet" href="resources/writeBlog/editormd.css" />
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/writeBlog/editormd.js"></script>
	<script src="resources/writeBlog/layer.js"></script>
</head>
<body >
<div id="layout">

	<div class="btns">
         <button class="disabled-btn" >发表博客</button>
         <input type="text" class="title" name="title" value="文章标题">
         <br/>
    </div>
   
	<div class="editormd" id="test-editormd">
	    	<textarea name="content"  style="display:none;"></textarea>
	</div>
</div>
</body>
	<script type="text/javascript">
    $(function() {
        var testEditor = editormd("test-editormd", {
        		width: "90%",
                height: 560,
                path : 'resources/writeBlog/lib/',
                imageUpload : true,
                imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL :"article/imageupload.do",
                
            });
        });
   
  
   	
  $(".disabled-btn").click(function(){
   		layer.open({
				  type:2 
				  ,area: ['500px', '500px']
				  ,title: '文章设置'
				  ,shade: 0.6 
				  ,maxmin: true 
				  ,anim: 1 
				  ,content: 'resources/inside/_writeArticleSettings.jsp'
				});  
   });
  
</script>
</html>
