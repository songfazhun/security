package cn.duyunzhi.security.service;

import cn.duyunzhi.security.entity.Role;
import cn.duyunzhi.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Ezreal-d
 * @Description: 认证和授权
 * @Date: 2019/9/2 11:32
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//认证账号
		User user = userService.loadUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("The account does not exist");
		}


		//开始授权
		Role role = roleService.getRoleByRoleId(user.getRole());
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
		//此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
		grantedAuthorities.add(grantedAuthority);
		user.setAuthorities(grantedAuthorities);
		return user;
	}


}
