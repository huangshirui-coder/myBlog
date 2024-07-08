package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.global.Result;
import com.example.demo.service.impl.LoginServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.web.page.TableDataInfo;
import com.example.demo.web.page.TableSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
        return userService.register(user);
    }

    @GetMapping("pageList")
    @ApiOperation(value = "查询用户列表")
    public TableDataInfo pageList(User user, String searchKey, HttpServletRequest request){
        return userService.pageList(user,searchKey , TableSupport.getPageDomain(request));
    }

    //TODO
    @GetMapping("changeUserType")
    @ApiOperation(value = "根据id修改用户类型")
    public Result changeUserType(User user){
        return null;
    }
}
