package cn.duyunzhi.security.controller;

import cn.duyunzhi.security.bean.JsonAsynResult;
import cn.duyunzhi.security.entity.User;
import cn.duyunzhi.security.utils.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
* @Author: Ezreal-d
* @Description: 用户Controller
* @Date: 2019/9/3 13:37
*/

@RestController
@RequestMapping("/user")
public class UserController {


	@RequestMapping(value = "/userInfo",method = RequestMethod.GET)
	public User getUserInfo(){
		return SecurityUtil.getUser();
	}

	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('系统管理员') or hasAuthority('管理员')")
	public JsonAsynResult add(){
		return JsonAsynResult.createSucResult("add success");
	}

	@RequestMapping(value = "/del",method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('系统管理员')")
	public JsonAsynResult del(){
		return JsonAsynResult.createSucResult("del success");
	}

	@RequestMapping(value = "/update",method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('系统管理员') or hasAuthority('管理员')")
	public JsonAsynResult update(){
		return JsonAsynResult.createSucResult("update success");
	}

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('系统管理员','管理员','普通用户')")
	public JsonAsynResult list(){
		return JsonAsynResult.createSucResult("list success");
	}
}
