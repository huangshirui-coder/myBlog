package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import com.example.demo.global.Result;

import java.util.List;

/**
 * 用户类服务接口
 */
public interface UserService extends IService<User> {

    public Result register(User user);

    public User getUserById(Integer id);


}
