package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.global.Result;
import com.example.demo.service.impl.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制类
 */
@RestController
@Api(value = "登录相关接口", tags = "登录相关接口")
@RequestMapping("/userLogin")
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;
    /*
     * @Author Huang
     * @Description 对登陆信息进行验证
     * @Param [user]
     * @return com.example.demo.global.Result
     **/
    @PostMapping("/login")
    @ApiOperation(value = "输入用户名和密码登录", notes = "输入用户名和密码登录")
    public Result login(@RequestBody User user){
        return loginService.login(user);
    }


    @GetMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public Result logout(){
        return loginService.logout();
    }
}
