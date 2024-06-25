package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.global.Result;
import com.example.demo.service.impl.LoginServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "用户接口", tags = "用户接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    /*
     * @Author Huang
     * @Description 用户名和密码不为空则注册成功，插入用户表
     * @Param [user]
     * @return com.example.demo.global.Result
     **/
    @ApiOperation(value = "注册用户", notes = "注册用户")
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if (userService.register(user) == 0){
            return Result.fail("注册失败");
        }else {
            return Result.succ("注册成功");
        }
    }

}
