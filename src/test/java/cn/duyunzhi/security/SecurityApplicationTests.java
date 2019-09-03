package cn.duyunzhi.security;

import cn.duyunzhi.security.common.Md5PasswordEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test1(){
		String password = md5PasswordEncoder.encode("password");
		System.err.println(password);
		boolean admin = md5PasswordEncoder.matches("password", "3B73CCA8B7D9D93A834631FB22769334");
		System.err.println(admin);
	}
}
