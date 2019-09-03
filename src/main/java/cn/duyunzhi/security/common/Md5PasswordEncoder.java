package cn.duyunzhi.security.common;

import cn.duyunzhi.security.utils.CipherUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
* @Author: Ezreal-d
* @Description: 采用MD5加密密码
* @Date: 2019/9/2 15:38
*/

public class Md5PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return CipherUtil.generatePassword(rawPassword.toString());
	}


	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return CipherUtil.generatePassword(rawPassword.toString()).equals(encodedPassword);
	}
}