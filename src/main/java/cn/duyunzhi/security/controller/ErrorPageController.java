package cn.duyunzhi.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Author: Ezreal-d
* @Description: 错误页面Controller
* @Date: 2019/9/3 14:43
*/

@Controller
public class ErrorPageController extends BaseController {

    @RequestMapping("/error-404")
    public String toPage404(){
        return "error/error-404";
    }
    @RequestMapping("/error-400")
    public String toPage400(){
        return "error/error-400";
    }
    @RequestMapping("/error-403")
    public String toPage403(){
        return "error/error-403";
    }
    @RequestMapping("/error-500")
    public String toPage500(){
        return "error/error-500";
    }

}
