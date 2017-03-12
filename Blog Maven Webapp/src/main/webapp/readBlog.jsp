<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html >
    <head>
    	<base href="<%=basePath%>">
        <meta charset="utf-8" />
        <title>我的博客</title>
        <link rel="stylesheet" href="resources/css/readerBlogStyle.css" />
        <link rel="stylesheet" href="resources/css/editormd.preview.css" />
       	<style type="text/css">
       		.editormd-html-preview {
                width: 1059px;
                margin: 0 auto;
                position: absolute;
                right: -380px;
            }
       		body{
       			border-radius:13px;
       		}
       		
			#icon{
				display:block;
				width:16px;
				height:15px;
				float: left;
				margin:3px auto auto auto;
			} 
			.icon1{
				background-image: url("resources/img/icon3.png");
			}
			.icon2{
				background-image: url("resources/img/icon2.png");
			}
			.icon3{
				background-image: url("resources/img/icon1.png");
			}
			div{
				float: left;
			}
			
			
			
			
       	</style>
    </head>
    <body>
        <div id="test-editormd-view2">  
            <h1 >${article.title}</h1> 
			<div >
				<div class="attribute"><span class="icon1" id="icon"></span><fmt:formatDate value="${article.date}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;/</div>
				<div class="attribute"><span class="icon2" id="icon"></span>&nbsp;${article.god}&nbsp;/</div>
				<div class="attribute"><span class="icon3" id="icon"></span>&nbsp;0</div>
				&nbsp;&nbsp;&nbsp;<a href="article/motdify.do?articleId=${article.id}" target="_parent">(* ￣︿￣)，我要修改</a>
				&nbsp;&nbsp;<a href="article/delete.do?articleid=${article.id}" target="_parent">代表月亮消灭你</a>
			</div>        
			<br><br>    
            <div id="test-editormd-view2">
                <textarea id="append-test">
${article.context}
				</textarea>          
            </div>
            <a href="article/downloadFile.do?filename=No.7_qzhai_2_8_9.zip">附件下载</a>
        </div>
        <script src="resources\js\jquery.min.js"></script>
        <script src="resources\writeBlog\lib\marked.min.js"></script>
        <script src="resources\writeBlog\lib\prettify.min.js"></script>
        
        <script src="resources\writeBlog\lib\raphael.min.js"></script>
        <script src="resources\writeBlog\lib\underscore.min.js"></script>
        <script src="resources\writeBlog\lib\sequence-diagram.min.js"></script>
        <script src="resources\writeBlog\lib\flowchart.min.js"></script>
        <script src="resources\writeBlog\lib\jquery.flowchart.min.js"></script>

        <script src="resources\writeBlog\editormd.js"></script>
        <script type="text/javascript">
            $(function() {
                var testEditormdView, testEditormdView2;
                
                
                    
                testEditormdView2 = editormd.markdownToHTML("test-editormd-view2", {
                    htmlDecode      : "style,script,iframe",  // you can filter tags decode
                    emoji           : true,
                    taskList        : true,
                    tex             : true,  // 默认不解析
                    flowChart       : true,  // 默认不解析
                    sequenceDiagram : true,  // 默认不解析
                    
                });
            });
            $(document).ready(function(){
        	
        	
        });
        </script>
        
    </body>
</html>