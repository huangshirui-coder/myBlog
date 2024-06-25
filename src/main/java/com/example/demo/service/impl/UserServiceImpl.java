package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * 用户类服务实现类
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public int register(User user) {
        int flag = 0;
        Long createById = 0l;
        // 当为进行认证时，Principal中存的是一个"anonymousUser"字符串，
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object object = authentication.getPrincipal();
        if (authentication.getPrincipal() != "anonymousUser"){
            User user1 = ((LoginUser)authentication.getPrincipal()).getUser();
            createById = user1.getId();
        }


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user.getPassword() != null){
            String newPassword = encoder.encode(user.getPassword());
            user.setPassword(newPassword);
            if (user.getUserName() != null){
                user.setCreateTime(new Date());
                user.setUpdateTime(new Date());
                user.setCreateBy(createById);
                flag = userMapper.insert(user);
            }
        }
        return flag;
    }
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }
}
