package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.global.Result;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisCache;
import com.example.demo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 用户类服务实现类
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisCache redisCache;

    @Override
    public Result register(User user) {
        int flag = 0;
        List<String> list = userMapper.getAllUsername();
        if (list.contains(user.getUserName())){
            return Result.fail("用户名重复");
        }
        String createById = "";
        // 当未进行认证时，Principal中存的是一个"anonymousUser"字符串，
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object object = authentication.getPrincipal();
        if (authentication.getPrincipal() != "anonymousUser"){
            User user1 = ((LoginUser)authentication.getPrincipal()).getUser();
            createById = user1.getCreateBy();
        }


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user.getPassword() != null){
            String newPassword = encoder.encode(user.getPassword());
            user.setPassword(newPassword);
            if (user.getUserName() != null){
                user.setId(StringUtils.getUUID());
                user.setCreateTime(new Date());
                user.setUpdateTime(new Date());
                user.setCreateBy(createById);
                String verCode = redisCache.getCacheObject(user.getEmail());
                if (StringUtils.isNotBlank(verCode) && verCode.equals(user.getVerCode())){
                    flag = userMapper.insert(user);
                }

            }
        }
        if (flag > 0){
            return Result.succ("注册成功");
        }else {
            return Result.fail("注册失败");
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }
}
