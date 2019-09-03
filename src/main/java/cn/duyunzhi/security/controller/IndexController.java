package cn.duyunzhi.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Author: Ezreal-d
* @Description: index Controller
* @Date: 2019/9/3 10:23
*/

@Controller
public class IndexController {


	@RequestMapping("/index")
	public String index(){
		return "index";
	}

}
