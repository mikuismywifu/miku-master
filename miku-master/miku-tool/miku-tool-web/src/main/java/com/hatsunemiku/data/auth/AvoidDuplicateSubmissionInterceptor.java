package com.hatsunemiku.data.auth;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hatsunemiku.data.annotation.AvoidDuplicateSubmission;
import org.apache.struts.util.TokenProcessor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <p>
 * 防止重复提交过滤器
 * </p>
 *
 * @author:
 * @date:
 */
public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.needSaveToken();
				if (needSaveSession) {
					String token = TokenProcessor.getInstance().generateToken(request);
					System.out.println("[token]:" + token);
					request.getSession(false).setAttribute("token", token);
				}

				boolean needRemoveSession = annotation.needRemoveToken();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						// LOG.warn("please don't repeat submit,[user:" +
						// user.getUsername() + ",url:"
						// + request.getServletPath() + "]");
						return false;
					}
					request.getSession(false).removeAttribute("token");
				}
			}
		}
		return true;
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (clinetToken == null) {
			
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

}