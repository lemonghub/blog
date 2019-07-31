package com.xmgl.blog.controller.back.admin;

import com.xmgl.blog.entity.Admin;
import com.xmgl.blog.service.AdminService;
import com.xmgl.blog.util.ObjectMapperUtil;
import com.xmgl.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 郭秦平
 * @data: 2019/03/18
 **/
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    @ResponseBody
    public ServerResponse login(@RequestBody Map<String,Object> map, HttpServletRequest request){
        String adminStr = ObjectMapperUtil.objectToString(map.get("admin"));
        Admin admin = null;
        try{
            admin = ObjectMapperUtil.convertObj(adminStr, Admin.class);
        }catch (Exception e){
            return ServerResponse.createByFailure("登陆失败，请重试");
        }
        admin = adminService.selectAdminByLogin(admin.getAdminName(),admin.getPassword());
        if(admin == null){
            return ServerResponse.createByFailure("用户名或密码错误");
        }
        request.getSession().setAttribute("adminName", admin.getAdminName());
        return ServerResponse.createBySuccess();
    }

    @PostMapping("loginOut")
    @ResponseBody
    public ServerResponse loginOut(@RequestBody Map<String,Object> map, HttpServletRequest request){
        System.out.println(111);
        request.getSession().removeAttribute("adminName");
        return ServerResponse.createBySuccess();
    }
}


