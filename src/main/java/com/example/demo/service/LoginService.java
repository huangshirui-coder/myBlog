package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import com.example.demo.global.Result;

public interface LoginService{
    public Result login(User user);

    public Result logout();
}
