package cn.duyunzhi.security.common;

import cn.duyunzhi.security.entity.User;
import cn.duyunzhi.security.mapper.UserMapper;
import cn.duyunzhi.security.utils.CipherUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Ezreal-d
 * @Description: 超级用户初始化
 * @Date: 2019/9/3 14:28
 */
@Component
public class SuperUserInit implements CommandLineRunner {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;


	@Override
	public void run(String... args) {
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.lambda()
				.eq(User::getUsername, "admin");
		User superUser = userMapper.selectOne(userQueryWrapper);
		if (null == superUser) {
			logger.debug("No super user,init data");

			superUser = new User("admin", CipherUtil.generatePassword("admin"));
			superUser.setRole(1);
			userMapper.insert(superUser);
		}

	}

}
