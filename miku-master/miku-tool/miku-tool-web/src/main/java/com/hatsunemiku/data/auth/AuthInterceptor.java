package com.hatsunemiku.data.auth;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hatsunemiku.data.annotation.AuthPassport;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hatsunemiku.data.util.Utils;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 在Controller被调用前，先执行，可以在这里执行一些安全检查
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);

			// 没有声明需要权限,或者声明不验证权限
			if (authPassport == null || authPassport.validate() == false)
				return true;
			else {
				// 在这里实现自己的权限验证逻辑
				HttpSession session = request.getSession();
				Object userId = session.getAttribute("userId");
				boolean isPermit = false;
				String url = request.getRequestURI();
				if (StringUtils.isNotBlank(url) && Utils.isPermitUrl("login", url)) {
					return true;
				}
				if (userId == null)
					isPermit = false;
				else
					isPermit = true;
				if (isPermit) // 如果验证成功返回true（这里直接写false来模拟验证失败的处理）
					return true;
				else// 如果验证失败
				{
					// 返回到登录界面
					String path = request.getContextPath();
					// 记录登录前的页面,登录完成后跳转 by update by liulihai 20160801-1702
					if (!Utils.isPermitUrl("login", url)) {
						// 取得参数内容
						String queryString = request.getQueryString();
						String reurl=path + "/login?redirect=" + URLEncoder.encode(request.getRequestURL() + "?" + request.getQueryString(), "utf-8");
						if (StringUtils.isNotBlank(queryString))
							response.sendRedirect(reurl);
						else {
							reurl=path + "/login?redirect=" + URLEncoder.encode(request.getRequestURL().toString(), "utf-8");
							response.sendRedirect(reurl);
						}
						return false;
					}
					response.sendRedirect(path + "/login");
					return false;
				}
			}
		} else
			return true;
	}

	/**
	 * 在Controller调用全部完成后执行，如果ex变量不为空，表示有异常了，这里可以记录异常日志
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * Controller调用后执行，这时，可以修改ModelAndView，比如转到其它view之类
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}