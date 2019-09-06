package cn.duyunzhi.security.common;

import cn.duyunzhi.security.bean.JsonAsynResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Ezreal-d
 * @Description:
 * @Date: 2019/9/6 16:26
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 异常处理(@ControllerAdvice注解注释的controller层和此注解注释的方法,会对所有controller层抛出的异常进行统一处理)
	 *
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public JsonAsynResult exceptionHandler(HttpServletRequest request, Exception e) {
		String defaultMessage = e.getMessage();
		if (e instanceof BadCredentialsException ||
				e instanceof UsernameNotFoundException) {
			//打印日志
			logger.error(defaultMessage);
			return JsonAsynResult.createErrorResult("账户名或者密码输入错误!");
		} else if (e instanceof LockedException) {
			logger.error(defaultMessage);
			return JsonAsynResult.createErrorResult("账户被锁定，请联系管理员!");
		} else if (e instanceof CredentialsExpiredException) {
			logger.error(defaultMessage);
			return JsonAsynResult.createErrorResult("密码过期，请联系管理员!");
		} else if (e instanceof AccountExpiredException) {
			logger.error(defaultMessage);
			return JsonAsynResult.createErrorResult("账户过期，请联系管理员!");
		} else if (e instanceof DisabledException) {
			logger.error(defaultMessage);
			return JsonAsynResult.createErrorResult("账户被禁用，请联系管理员!");
		} else if (e instanceof AccessDeniedException) {
			logger.error(defaultMessage);
			return JsonAsynResult.createErrorResult("没有权限，请联系管理员!");
		} else {
			logger.error(defaultMessage);
			return JsonAsynResult.createErrorResult(defaultMessage);
		}
	}
}
