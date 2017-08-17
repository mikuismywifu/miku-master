package com.hatsunemiku.data.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @ClassName: LogAspect
 * @Description: 日志记录AOP实现
 * @author
 * @date
 *
 */
public class ControllerLogAspect {
	static final Logger logger = LogManager.getLogger("Weixin");

	private String requestPath = null; // 请求地址
	private String userName = null; // 用户名
	private Map<?, ?> inputParamMap = null; // 传入参数
	private Map<String, Object> outputParamMap = null; // 存放输出结果
	private long startTimeMillis = 0; // 开始时间
	private long endTimeMillis = 0; // 结束时间

	/**
	 * 
	 * @Title：doBeforeInServiceLayer
	 * @Description: 方法调用前触发 记录开始时间
	 * @author shaojian.yu
	 * @date 2014年11月2日 下午4:45:53
	 * @param joinPoint
	 * 
	 * @Before("execution(* com.xinhua.data.controller..*.*(..))")
	 */
	public void doBeforeInServiceLayer(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		// User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
		// 请求的IP
		String ip = request.getRemoteAddr();
		try {
			// *========控制台输出=========*//
//			System.out.println("=====前置通知开始=====");
//			System.out.println("请求方法:"
//					+ (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//			System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
//			// System.out.println("请求人:" + user.getName());
//			System.out.println("请求IP:" + ip);
//			// *========数据库日志=========*//
//			System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
		startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
//		System.out.println("请求Before:"
//				+ (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
	}

	/**
	 * 
	 * @Title：doAfterInServiceLayer
	 * @Description: 方法调用后触发 记录结束时间
	 * @author shaojian.yu
	 * @date 2014年11月2日 下午4:46:21
	 * @param joinPoint
	 */
	public void doAfterInServiceLayer(JoinPoint joinPoint) {
		endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
		System.out.println("请求After:"
				+ (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
		this.printOptLog();
	}

	/**
	 * 
	 * @Title：doAround
	 * @Description: 环绕触发
	 * @author shaojian.yu
	 * @date 2014年11月3日 下午1:58:45
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		/**
		 * 1.获取request信息 2.根据request获取session 3.从session中取出登录用户信息
		 */
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		// 从session中获取用户信息
		// String loginInfo = (String) session.getAttribute("username");
		// if(loginInfo != null && !"".equals(loginInfo)){
		// userName = operLoginModel.getLogin_Name();
		// }else{
		// userName = "用户未登录" ;
		// }
		// 获取输入参数
		inputParamMap = request.getParameterMap();
		// 获取请求地址
		requestPath = request.getRequestURI();

		// 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
		outputParamMap = new HashMap<String, Object>();
		Object result = pjp.proceed();// result的值就是被拦截方法的返回值
		outputParamMap.put("result", result);
		return result;
	}

	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @Before("execution(* com.xinhua.data.controller..*.*(..))")
	 * @param joinPoint
	 * @param e
	 */
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		// User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
		// 获取请求ip
		String ip = request.getRemoteAddr();
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				//params += JSONObject.toJSONString(joinPoint.getArgs()[i]) + ";";
			}
		}
		try {
			/* ========控制台输出========= */
			System.out.println("=====异常通知开始=====");
			System.out.println("异常代码:" + e.getClass().getName());
			System.out.println("异常信息:" + e.getMessage());
			System.out.println("异常方法:"
					+ (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
			// System.out.println("请求人:" + user.getName());
			System.out.println("请求IP:" + ip);
			System.out.println("请求参数:" + params);
			/* ==========数据库日志========= */
			System.out.println("=====异常通知结束=====");
		} catch (Exception ex) {
			// 记录本地异常日志
			logger.error("==异常通知异常==");
			logger.error("异常信息:{}", ex.getMessage());
		}
		/* ==========记录本地异常日志========== */
		logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName()
				+ joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);

	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {

					break;
				}
			}
		}
		return description;
	}
	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					break;
				}
			}
		}
		return description;
	}
	/**
	 * 
	 * @Title：printOptLog
	 * @Description: 输出日志
	 * @author shaojian.yu
	 * @date 2014年11月2日 下午4:47:09
	 */
	private void printOptLog() {

		String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
		logger.info("\n user：" + userName + "  url：" + requestPath + "; op_time：" + optTime + " pro_time："
				+ (endTimeMillis - startTimeMillis) + "ms ;" + " param：" + JSONObject.toJSONString(inputParamMap) + ";"
				+ "\n result：" + JSONObject.toJSONString(outputParamMap));
	}

}