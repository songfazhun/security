package cn.duyunzhi.security.config;

import cn.duyunzhi.security.service.CustomUserDetailsService;
import cn.duyunzhi.security.common.Md5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService())
				.passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 所有用户均可访问的资源 只支持GET请求,其它请求需做处理
				.antMatchers("/favicon.ico", "/css/**", "/js/**", "/img/**", "/login/toLogin", "/login/getVerify", "/login/userLogin").permitAll()
				// 任何尚未匹配的URL只需要验证用户即可访问
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login/toLogin").successForwardUrl("/index")
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
