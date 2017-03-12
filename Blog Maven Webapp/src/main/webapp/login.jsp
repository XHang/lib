<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zn" class="no-js">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>注册或登录</title>
		<base href="<%=basePath%>">
        <!-- CSS -->
        <link rel="stylesheet" href="resources/css/reset.css">
        <link rel="stylesheet" href="resources/css/supersized.css">
        <link rel="stylesheet" href="resources/css/style.css">
    </head>

    <body>

        <div class="page-container">
        	<h1></h1>
            <h2>请登录</h2>
            <form action="user/login.do" method="post" class="login">
                <input type="text" name="username" class="username" placeholder="用户名">
                <input type="password" name="password" class="password" placeholder="密码">
                <input type="text" name="check" class="check"  placeholder="请输入左侧的验证码">
                <a href="javascript:void(0);"><img alt="验证码" src="user/generateCode.do" class="checkimg" onclick="this.src='user/generateCode.do?'+Math.random()"></a>
                <button type="submit">提交</button>
                <div class="error"><span>+</span></div>
            </form>
            <form action="user/register.do" method="post" class="register">
                <input type="text" name="username" class="username" placeholder="用户名">
                <input type="password" name="password" class="password" placeholder="密码">
                <input type="password" name="repassword" class="password" placeholder="确认密码">
                <input type="email" name="email" placeholder="请输入邮箱" >
                <input type="text"  name="check" class="check"  placeholder="请输入左侧的验证码">
                <a href="javascript:void(0);"><img alt="验证码" src="user/generateCode.do" class="checkimg" onclick="this.src='user/generateCode.do?'+Math.random()"></a>
                <button type="submit">提交</button>
                <div class="error"><span>+</span></div>
            </form>
            <div class="connect">
                <p>没有账号？</p>
                <p>
                    <a href="javascript:void(0);">点此切换注册</a>
                </p>
            </div>
        </div>
		
        <!-- Javascript -->
        <script src="resources/js/jquery.min.js"></script>
        <script src="resources/js/supersized.3.2.7.min.js"></script>
        <script src="resources/js/supersized-init.js"></script>
        <script src="resources/js/scripts.js"></script>
    </body>

</html>


