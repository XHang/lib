
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
    });

    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });
    $(".register").hide();
    var index=0;
    $("div p a").click(function(){
    	index++;
    	var hide=$("form:hidden");		//先把所有不可见的表单jq对象存起来
    	$("form:visible").hide("slow");//所有可见的表单元素隐藏
    	hide.show("slow");
    	if(index%2!=0){
    		$("h2").text("请注册");
    		$(".connect p:first").text("已是会员");
    		$(".connect p a").text("点此切换登录");
    	}else{
    		$("h2").text("请登录");
    		$(".connect p:first").text("没有账号");
    		$(".connect p a").text("点此切换注册");
    	}
    });
    var date=new Date();
    var hours= date.getHours();
    if(hours>0 && hours<12){
    	$("h1").eq(0).text("早上好，new Day!");
    }else if(hours>12 && hours<18){
    	$("h1").eq(0).text("下午好");
    }else{
    	$("h1").eq(0).text("晚上好，祝晚安");
    }
    
});
