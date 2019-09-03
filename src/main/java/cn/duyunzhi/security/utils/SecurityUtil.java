package cn.duyunzhi.security.utils;

import cn.duyunzhi.security.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.util.Collection;
/**
* @Author: Ezreal-d
* @Description: Security工具类
* @Date: 2019/9/3 13:11
*/

public class SecurityUtil {

	/**
	 * 获取认证信息
	 * @return
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取所有权限
	 * @return
	 */
	public static Collection<? extends GrantedAuthority> getAllPermission(){
		return getAuthentication().getAuthorities();
	}

	/**
	 * 是否有权限
	 * @param permission
	 * @return
	 */
	public static boolean hasPermission(String permission){
		if(StringUtils.isEmpty(permission)){
			return false;
		}
		Collection<? extends GrantedAuthority> authorities = getAllPermission();
		boolean hasPermission = false;
		for(GrantedAuthority grantedAuthority : authorities){
			String authority = grantedAuthority.getAuthority();
			if(authority.equals(permission)){
				hasPermission =true;
			}
		}
		return hasPermission;
	}


	/**
	 * 获取登录用户
	 * @return
	 */
	public static User getUser() {
		return (User) getAuthentication().getPrincipal();
	}

	/**
	 * 注销
	 */
	public static void logout(){
		SecurityContextHolder.clearContext();
	}
}
