package cn.duyunzhi.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

/**
* @Author: Ezreal-d
* @Description: 用户实体
* @Date: 2019/9/2 10:45
*/
@Data
@TableName("tb_user")
public class User implements UserDetails,Serializable {

	private static final long serialVersionUID = -3185270343546143L;

	@TableId(type = IdType.AUTO)
	private Integer id;

	private String username;

	private String password;

	private Integer role;

	private String lastLoginTime;

	private String lastLoginIp;

	@TableField(exist = false)
	private List<? extends GrantedAuthority> authorities;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
