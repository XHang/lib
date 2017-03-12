package com.Blog.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/*
 * 在进入个人空间时，检查session里面有没有user，以及user是否有效
 */
public class IntoSpaceInterceptor implements HandlerInterceptor{

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
	//在进入个人空间时，检查session里面有没有user，以及user是否有效
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		String url=arg0.getRequestURL().toString();
		if(url.endsWith("login.do") || url.endsWith("register.do") || url.endsWith("generateCode.do")  || url.endsWith("resendEmail.do")){	//如果拦截的地址是登录，注册，生成验证码就放行，其余要判断session有没有user
			return true;	
			
		}
		Object u=arg0.getSession().getAttribute("user");	
		if(u==null){		//说明在进入个人空间时，session里面并没有user，需要重定向到登录页面、
			arg1.sendRedirect("../login.jsp");
			return false;
		}
		return true;
	}

}
