package com.xmgl.blog.service.impl;

import com.xmgl.blog.dao.AdminMapper;
import com.xmgl.blog.entity.Admin;
import com.xmgl.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郭秦平
 * @data: 2019/03/18
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin selectAdminByLogin(String adminName, String password) {
        return adminMapper.selectByLogin(adminName,password);
    }
}
