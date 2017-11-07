package com.switch007.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object handle) throws Exception {
		boolean result = false;
		Object ob = request.getSession().getAttribute("admin");
		System.out.println(request.getRequestURI());
		if (null == ob) {
			//result = false;// 只有返回true才会继续向下执行，返回false取消当前请求
			respone.sendRedirect("/admin/user/loginPage");
		} else {
			result = true;
		}
		return result;
	}

}
