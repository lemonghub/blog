package com.xmgl.blog.service;

import com.xmgl.blog.entity.Admin;

/**
 * @author 郭秦平
 * @data: 2019/03/18
 **/
public interface AdminService {

    /**
     * 后台登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Admin selectAdminByLogin(String username,String password);
}
