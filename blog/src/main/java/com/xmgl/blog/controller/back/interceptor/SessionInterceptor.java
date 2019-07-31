package com.xmgl.blog.controller.back.interceptor;

import org.apache.ibatis.plugin.Intercepts;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 郭秦平
 * @data: 2019/04/18
 **/
@Component
public class SessionInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("uri="+request.getRequestURI());
        if(request.getRequestURI().length()>32) {
            System.out.println(request.getRequestURI().substring(0, 32));
            if(request.getRequestURI().substring(0,32).equals("/blog/view/background/layuiadmin")){
                return true;
            }
        }
        //登录不做拦截
        if(request.getRequestURI().equals("/blog/view/background/login.html") || request.getRequestURI().equals("/blog/admin/login"))
        {
            System.out.println("true");
            return true;
        }
        //前台不做拦截
        if(request.getRequestURI().length()>21  && request.getRequestURI().substring(0,21).equals("/blog/view/foreground")){
            return true;
        }
        //验证session是否存在
        Object obj = request.getSession().getAttribute("adminName");
        System.out.println(obj);
        if(obj == null)
        {
            response.sendRedirect("/blog/view/background/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
