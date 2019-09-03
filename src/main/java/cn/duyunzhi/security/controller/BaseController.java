package cn.duyunzhi.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* @Author: Ezreal-d
* @Description: 基类Controller
* @Date: 2019/9/3 14:10
*/
@Controller
public class BaseController {

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpServletResponse getResponse(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected String getUserIp() {
        return  getRequest().getRemoteHost() + "/" + getRequest().getRemoteAddr();
    }
}
