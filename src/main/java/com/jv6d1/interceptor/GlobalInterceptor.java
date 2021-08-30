package com.jv6d1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jv6d1.service.CategoryService;
@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	CategoryService cateService;
	
	// sử dụng postHandle để sau khi controller được chạy thì mới chuyển sang postHandle
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		request.setAttribute("cates",cateService.findAll());
	}
}
