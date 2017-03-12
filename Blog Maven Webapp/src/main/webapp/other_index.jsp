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
<style type="text/css">
iframe {
	height:700px;
	width:700px;
	border:medium double rgb(250,0,255);
	position:absolute;
	right: 330px;
	border-radius:10px;	
}
#sidebar{
position:absolute;
right:30px;
width:300px;
}
#LoadDiv{
	position:absolute;
	right: 700px;
	top: 240px;
}
#mood{
	 border:none;
	 outline:medium;
	 color: gray;
	 text-align: center;
	
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的博客</title>
<base href="<%=basePath%>">
<meta name="robots" content="noindex,follow">
<link rel="stylesheet" id="qzhai-css" href="resources/css/index_style.css" type="text/css" media="all">
<link rel="stylesheet" id="highlight-css" href="resources/css/sunburst.css" type="text/css" media="all">
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
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
										<input type="file" hidden="hidden" id="Image" name="Image">  <!-- 然而上传图片需要这个做载体 -->
										<input type="submit" hidden="hidden" id="submit">
									</form>
								</div>
							</div>
							<h1 class="uk-panel-title">
								<a href="user/intoSpace.do" id="break">${user.username}</a>
							</h1>
							<span><div>${user.mood==null?'什么都木有哦':user.mood}</div></span> 
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
								<li id="menu-item-112" class=""><a href="xx">他的书柜</a></li>
								<li id="menu-item-54" class="uk-parent"
									data-uk-dropdown="{pos:&#39;right-top&#39;}"><a href="xx">页面</a>
									<div class="uk-dropdown uk-dropdown-navbar">
										<ul class="uk-nav uk-nav-navbar">
											<li id="menu-item-55" class=""><a href="xx">留言板</a></li>
											<li id="menu-item-58" class=""><a href="xx">归档</a></li>
											<li id="menu-item-56" class=""><a href="xx">标签</a></li>
											<li id="menu-item-57" class=""><a href="xx">分类</a></li>
											<li id="menu-item-6" class=""><a href="xx">示例页面</a></li>
										</ul>
									</div></li>
								<li id="menu-item-48" class=""><a href="xx">随缘</a></li>
								<li id="menu-item-49" class="uk-parent"
									data-uk-dropdown="{pos:&#39;right-top&#39;}"><a href="xx">关于作者</a>
									<div class="uk-dropdown uk-dropdown-navbar">
										<ul class="uk-nav uk-nav-navbar">
											<li id="menu-item-20" class=""><a href="x">衫小寨</a></li>
											<li id="menu-item-50" class=""><a href="xx">官方社区</a></li>
										</ul>
									</div></li>
							</ul>
							<form class="s uk-form uk-hidden-small" id="searchform" method="get" action="article/search.do" target="go">
								<input class="uk-width-1-1 " type="text" value="" name="keyWord" id="s" placeholder="搜索">
							</form>
							<a href="javascript:;" id="op_m" lock="open"
								class="uk-icon-music"></a>
						</div>
						<div class="op" style="width:80%">
							<iframe src="resources/inside/_player.jsp" width="280"
								height="380" frameborder="no" marginwidth="0" marginheight="0"></iframe>
						</div>
					</div>
					<div class="ft uk-hidden-small">
						<p>
							轻博客 -version 1
						</p>
					</div>
					<div id="my-head" class="uk-modal">
						<div class="uk-modal-dialog-blank uk-height-viewport">
							<a class="uk-modal-close uk-close"></a>
							<div class="uk-grid uk-flex-middle" data-uk-grid-margin="">
								<div
									class="uk-width-medium-1-2 uk-height-viewport uk-cover-background"
									style="background-image: url(&#39; http://qzhai.net/000/wp-content/uploads/2016/04/geren.jpeg &#39;);"></div>
								<div class="uk-width-medium-1-2 p">
									我就是我<br> <br> 啦啦啦啦
								</div>
							</div>
						</div>
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
									他什么都没写。。。
								</h1>
								
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
					<li><h4>回到自己的主页</h4>
						<div class="textwidget"><a href="user/intoSpace.do" id="break">走你</a></div></li>
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
							<h4>他的好友</h4>
							<ul>						
								<c:forEach items="${friends}" var="friend">
									<li><a href="xx">${friend.username}</a></li>
								</c:forEach>
							</ul>
						</li>
						<li>
							<h4>标签</h4>
							<div class="post-tags">
								<a href="xx" class="tag-link-4 tag-link-position-1" title="1个话题"
									style="font-size: 12px;">文艺青年</a> 
								<a href="user/testajax.do"
									class="tag-link-5 tag-link-position-2" title="1个话题"
									style="font-size: 12px;">泪</a> 
								<a href="xx"
									class="tag-link-6 tag-link-position-3" title="1个话题"
									style="font-size: 12px;">姑娘</a>
							</div>
						</li>
						<li class="adimg"><a href="xx" target="_blank"><img
								src="resources/img/016699565eaff132f875ae342bd9aa-683x1024.jpg"></a></li>
					</ul>
				</div>
			</div>

			<div class="ft uk-visible-small">
				<p>
					<a href="xx" data-uk-modal="">友情链接</a> - <a href="xx"
						target="_black" title="主题制作:衫小寨"
						data-uk-tooltip="{pos:&#39;bottom&#39;}"> Theme by Qzhai
					</a>
				</p>
			</div>
		</div>
		
		<script type="text/javascript" src="resources/js/app.js"></script>

		<a href="xx" class="top" id="break" data-uk-smooth-scroll="" style="display:none"><i
			class="uk-icon-angle-up"></i></a>
		<div id="my-link" class="uk-modal">
			<div class="uk-modal-dialog">
				<a class="uk-modal-close uk-close"></a>
				<h2>友情链接</h2>
				<ul id="link_qzhai" class=" uk-subnav uk-subnav-line">
					<li id="menu-item-40" class="">
						<a href="xx" target="_blank">
						<h6> <img src="resources/img/qzhai.net" alt="Sina" width="20" height="20">衫小寨
						</h6>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#LoadDiv").hide();		//页面加载进来立即隐藏load图片
		$(".frame").hide();			//页面加载进来立即隐藏框架
		//把所有a标签点击后的内容显示在框架中，break属性的a标签不加
		$("a").not("#break").click(function(){
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
		$("#Image").change(function(){
			document.getElementById("submit").click();
		})
	</script>
	
	
</body>
</html>