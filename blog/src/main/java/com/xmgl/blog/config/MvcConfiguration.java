package com.xmgl.blog.config;


import com.xmgl.blog.controller.back.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

//    @Value("${win.photo.img.path}")
//    private String photoPath;
    @Resource
    private SessionInterceptor baseInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/photo/**").addResourceLocations(photoPath);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(baseInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(new SessionInterceptor()).excludePathPatterns("/blog/view/background/layuiadmin/").addPathPatterns("/**");
//        registry.addInterceptor(baseInterceptor).excludePathPatterns("/static/view/background/layuiadmin/**").addPathPatterns("/**");

    }
}