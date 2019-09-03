package cn.duyunzhi.security.service;

import cn.duyunzhi.security.entity.User;
import cn.duyunzhi.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
* @Author: Ezreal-d
* @Description: 用户Service
* @Date: 2019/9/2 11:33
*/

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User loadUserByUsername(String username) {
		return this.userMapper.selectUserByName(username);
	}

	public boolean updateUser(User user) {
		return userMapper.updateById(user) == 1;
	}
}
