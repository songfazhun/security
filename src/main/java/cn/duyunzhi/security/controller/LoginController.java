package cn.duyunzhi.security.controller;

import cn.duyunzhi.security.bean.JsonAsynResult;
import cn.duyunzhi.security.entity.User;
import cn.duyunzhi.security.service.UserService;
import cn.duyunzhi.security.utils.DateUtil;
import cn.duyunzhi.security.utils.RandomValidateCodeUtil;
import cn.duyunzhi.security.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @Author: Ezreal-d
 * @Description: 登录Controller
 * @Date: 2019/9/2 15:39
 */

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AuthenticationManager myAuthenticationManager;

	@Autowired
	private UserService userService;

	@RequestMapping("/toLogin")
	public String login() {
		return "login";
	}

	@PostMapping("/userLogin")
	@ResponseBody
	public JsonAsynResult userLogin(User user, String inputStr, HttpSession session) {
		if (StringUtils.isEmpty(inputStr) || !((String) session.getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY)).toLowerCase().equals(inputStr.toLowerCase())) {
			return JsonAsynResult.createErrorResult("验证码错误");
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		//使用SpringSecurity拦截登陆请求 进行认证和授权
		Authentication authenticate = myAuthenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		// 这个非常重要，否则验证后将无法登陆
		session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
		//更新用户登录信息
		User updateUser = SecurityUtil.getUser();
		updateUser.setLastLoginTime(DateUtil.getNowDateTime());
		updateUser.setLastLoginIp(getUserIp());
		userService.updateUser(updateUser);
		return JsonAsynResult.createSucResult();
	}

	/**
	 * 生成验证码
	 */
	@GetMapping(value = "/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			//设置相应类型,告诉浏览器输出的内容为图片
			response.setContentType("image/jpeg");
			//设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			//输出验证码图片方法
			randomValidateCode.getRandcode(request, response);
		} catch (Exception e) {
			logger.error("Failed to get verification code >>>>   ", e);
		}
	}

	@RequestMapping("/logout")
	public String logout() {
		SecurityUtil.logout();
		return "redirect:login/toLogin";
	}
}
