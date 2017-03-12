package com.Blog.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/*
 * 登录前拦截，判断验证码是否有问题
 */
public class LoginAndRegiseterInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
			
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	//登录时，检查验证码是否正确以及表单数据是否为空
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1,
			Object arg2) throws Exception {
		String url=request.getRequestURL().toString();
		if(!url.endsWith("login.do") && !url.endsWith("register.do")){	//如果拦截的地址不是注册或者登录则放行
			return true;
		}
		if(url.endsWith("register.do")){								//如果拦截的地址是注册，还需要搞个大新闻：重复密码确认
			String password=request.getParameter("password");
			String repassword=request.getParameter("repassword");
			if((password==null || repassword==null || !password.equals(repassword))){
				arg1.sendRedirect("../login.jsp");
				return false;
			}
		}
		String code=(String) request.getSession().getAttribute("code");	
		String prepareCode=request.getParameter("check");
		System.out.println(code+"...."+prepareCode+"两个验证码！");
		if(prepareCode !=null && prepareCode.equalsIgnoreCase(code)){		//判断验证码是否一致
			return true;
		}
		
		arg1.sendRedirect("../login.jsp");
		return false;
	}

}
