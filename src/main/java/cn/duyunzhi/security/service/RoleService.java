package cn.duyunzhi.security.service;

import cn.duyunzhi.security.entity.Role;
import cn.duyunzhi.security.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Author: Ezreal-d
* @Description: 角色Service
* @Date: 2019/9/3 11:29
*/

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	public Role getRoleByRoleId(Integer role) {
		return roleMapper.selectById(role);
	}
}
