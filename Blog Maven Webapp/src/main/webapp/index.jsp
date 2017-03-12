<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html class="uk-notouch">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的博客</title>
<base href="<%=basePath%>">
<link rel="stylesheet" id="qzhai-css" href="resources/css/index_style.css" type="text/css" media="all">
<link rel="stylesheet" id="highlight-css" href="resources/css/sunburst.css" type="text/css" media="all">
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script src="resources/writeBlog/layer.js"></script>
<script type="text/javascript">
</script>
</head>
<body class="uk-height-1-1">
	<div id="main" class="wp uk-grid uk-grid-collapse" style="max-width:1120px">
		<div class="uk-width-small-1-1 uk-width-medium-1-4 uk-width-large-1-6 posr">
			<div class="uk-sticky-placeholder" style="height: 513px; margin: 0px;">
				<div id="head" data-uk-sticky="{boundary: true,top:80}"
					style="position: fixed; top: 80px; width: 187px;"
					class="uk-sticky-init uk-active">
					<div id="op_head">
						<div class="uk-panel" id="op_hed">
							<div class="tx">
								<a href="xx" data-uk-modal=""></a>
								<div class="avatar_">
									<form action="user/uploadImage.do" enctype="multipart/form-data" method="post">
										<a href="#" id="break" data-uk-modal="" onclick="getElementById('Image').click()"><img src="resources/UserImages/${user.id}"></a>
										<input type="file" hidden="hidden" id="Image" name="Image"> 
										<input type="submit" hidden="hidden" id="submit">
									</form>
								</div>
							</div>
							<h1 class="uk-panel-title">
								<a href="user/intoSpace.do" id="break">${user.username}</a>
							</h1>
							<span><input type="text" id="mood" value="${user.mood==null?'你现在在想什么':user.mood}"/></span>
							<div class="my uk-grid-collapse uk-grid uk-grid-width-1-3">
								<div>
									<span>${ArticleTotal}</span>
									<span><i class="uk-icon-file-text"></i></span>
									<a href="article/getAllArticle.do" title="文章" data-uk-tooltip="{pos:&#39;bottom&#39;}"></a>
								</div>
								<div>
									<span>${categoryTotal}</span>
									<span><i class="uk-icon-folder"></i></span>
									<a	href="category/getCategoryList.do" title="分类" data-uk-tooltip="{pos:&#39;bottom&#39;}"></a>
								</div>
								<div>
									<span>${fn:length(friends)}</span><span><i class="uk-icon-tags"></i></span>
									<a	href="xx" title="好友" data-uk-tooltip="{pos:&#39;bottom&#39;}"></a>
								</div>
							</div>
							<a href="xx" class="s_s uk-navbar-toggle uk-hidden-large"
								data-uk-offcanvas=""></a>
							<ul id="nav-top" class="nav uk-nav uk-hidden-small">
								<li id="menu-item-112" class=""><a href="picture/myPicture.do">我的图片</a></li>
								<li id="menu-item-54" class="uk-parent"><a href="user/random.do">随缘</a></li>	
								<li id="menu-item-49" class="uk-parent"><a href="xx">关于作者</a></li>
								<li id="s" class="searchform"><a href="xx">待定</a></li>
								
							</ul>
							<form class="s uk-form uk-hidden-small" id="searchform" method="get" action="article/search.do" target="go">
								<input class="uk-width-1-1 " type="text" value="" name="keyWord" id="s" placeholder="搜索">
							</form>
							<a href="javascript:;" id="op_m" 
								class="uk-icon-music"></a>
						</div>
						<div class="op" style="width:80%">
							<iframe src="resources/inside/_player.jsp" width="280"
								height="380" frameborder="no" marginwidth="0" marginheight="0"></iframe>
						</div>
					</div>
					<div class="ft uk-hidden-small">
						<p>
							轻博客 -version 1<br/>
							<a href="user/exit.do" id="break">退出登录</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div id="content"
			class="uk-width-small-1-1 uk-width-medium-3-4 uk-width-large-5-6 uk-grid uk-grid-collapse">
			
			
			
			
			
			
			<!-- 框架标签 -->
			<iframe  name="go" scrolling="no"  class="frame" id="iframe"></iframe>
			<!-- 加载特效层 -->
			<div id="LoadDiv"  >
        		<img src="resources/img/load.gif" />
  			 </div>
  			 
  			 
  			 
  			 
  			 
  			 
			<div class="uk-width-small-1-1 uk-width-medium-3-4 uk-width-large-7-10">
				<div id="index" class="bs uk-text-break">
					<h4>最新文章</h4>
					<iframe id="myFrameId" name="myFrameName" scrolling="no" frameborder="0" style="width:200px; height:150px; "></iframe>
					
					<div id="list">
						<c:if test="${fn:length(newarticle) == 0}">
							<article class="article class_3">
								<h1>
									你什么都没写。。。
								</h1>
								<p>
									<a href="article/writeArticle.do" id="break">来指点江山，激扬文字</a>
								</p>
							</article>
						</c:if>
						<c:forEach items="${newarticle}"  var="article">
							<article class="article class_3">
								<h1>
									<a href="article/read.do?id=${article.id}">${article.title}</a>
								</h1>
								<p>
									<a href="xx"><img src="resources/img/111-e1452232566209.jpeg"></a>
										${article.context}[…]
									<time>
										<br><fmt:formatDate value="${article.date}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
									</time>
								</p>
							</article>	
						</c:forEach>
					</div>
				</div>
				<ul class="uk-pagination">
				</ul>


			</div>
			
			
			<div id="sidebar"
				class="uk-width-small-1-1 uk-width-medium-1-4 uk-width-large-3-10">
				<ul class="ul">
					<li>
						<h4>收信箱</h4>
						<ul><li><a href="javascript:void(0)" id="break" onclick="OpenMsg()">空空如也<i id="redDot"></i></a></li></ul>
					</li>
					<li>
						<h4>激扬文字</h4>
						<ul><li><a href="article/writeArticle.do" id="break">写博客</a></li></ul>
					</li>
					<li>					
						<h4>最受欢迎的文章</h4>			
						<ul>
							<c:if test="${fn:length(hotarticle)==0 }">
								<li><a>然而什么都没有</a></li>
							</c:if>
							<c:forEach items="${hotarticle}" var="article">
								<li><a href="article/read.do?id=${article.id}" target="go">${article.title}</a></li>
							</c:forEach>
						</ul>
					</li>
					
				</ul>
				
				<div class="uk-sticky-placeholder"
					style="height: 559px; margin: 0px 0px 0px 20px;">
					<ul id="ulsid" class="ul" style="margin: 0px;">
						<li>
							<h4>我的好友</h4>
							<ul>
								<c:if test="${fn:length(friends)==0 }">
									<li><a>你太孤独了</a></li>
								</c:if>
								<c:forEach items="${friends}" var="friend">
									<li><a href="xx">${friend.username}</a></li>
								</c:forEach>
							</ul>
						</li>
						
						<li class="adimg"><a href="xx" target="_blank"><img src="resources/img/016699565eaff132f875ae342bd9aa-683x1024.jpg"></a></li>
						
					</ul>
				</div>
			</div>

			
		</div>
		
		<script type="text/javascript" src="resources/js/app.js"></script>

		
		
	</div>
	<script type="text/javascript">
		$("#LoadDiv").hide();		//页面加载进来立即隐藏load图片
		$(".frame").hide();			//页面加载进来立即隐藏框架
		//把所有a标签点击后的内容显示在框架中，break属性的a标签不加
		$("a").not("#break").click(function ff(){
			$("#index").hide();
			$( this ).attr("target","go");		
			$("#LoadDiv").show();
			$(".frame").show();
		});
		$(".frame").load(function(){		//页面加载完毕把加载层隐藏
			$("#LoadDiv").hide();
			document.getElementById("iframe").height=0;  
   			document.getElementById("iframe").style.height=document.getElementById("iframe").contentWindow.document.body.scrollHeight+"px";  
		})
		$("#mood").blur(function(){
			var mood=$(this).val();
			alert(mood);
			$.post("user/changeMood.do",{mood:mood+""});
		});
		function uploadImag(){
		}
		function uploadImag(){
			alert("dd");
		}
		$("#Image").change(function(){
			document.getElementById("submit").click();
		})
		function OpenMsg(){
			layer.open({
				  type: 2,
				  area: ['500px', '500px'],
				  title: false,
				  skin: 'yourclass', 
				  closeBtn: 0,
				  shadeClose: true,
				  content: 'resources/inside/_Msg.jsp'
				  
				});  
			
		}
	</script>
	
	
</body>
</html>