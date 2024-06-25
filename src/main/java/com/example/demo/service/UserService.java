package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import com.example.demo.global.Result;

/**
 * 用户类服务接口
 */
public interface UserService extends IService<User> {

    public int register(User user);

    public User getUserById(Integer id);



}
