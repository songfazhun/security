package cn.duyunzhi.security.config;

import cn.duyunzhi.security.bean.JsonAsynResult;
import cn.duyunzhi.security.service.CustomUserDetailsService;
import cn.duyunzhi.security.common.Md5PasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @Author: Ezreal-d
 * @Description:
 * @Date: 2019/9/2 11:28
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService())
				.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//防止iframe错误:in a frame because it set 'X-Frame-Options' to 'deny'.
		http.headers()
				.frameOptions().sameOrigin()
				.httpStrictTransportSecurity().disable();

		http.authorizeRequests()
				// 所有用户均可访问的资源 只支持GET请求,其它请求需做处理
				.antMatchers("/favicon.ico", "/css/**", "/js/**", "/img/**", "/login/toLogin", "/login/getVerify", "/login/userLogin").permitAll()
				// 任何尚未匹配的URL只需要验证用户即可访问
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login/toLogin")
				.successForwardUrl("/index")
				.and()
				//权限拒绝的页面
				.exceptionHandling().accessDeniedPage("/error-403");

		http.logout().logoutSuccessUrl("/login/toLogin");
	}


	/**
	 * 设置用户密码的加密方式
	 *
	 * @return
	 */
	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();

	}

	/**
	 * 自定义UserDetailsService，授权
	 *
	 * @return
	 */
	private CustomUserDetailsService customUserDetailsService() {
		return customUserDetailsService;
	}

	/**
	 * AuthenticationManager
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


}
