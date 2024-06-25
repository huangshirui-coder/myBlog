package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.global.RedisConf;
import com.example.demo.global.Result;
import com.example.demo.global.VueConf;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.LoginService;
import com.example.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisCache redisCache;


    @Override
    public Result login(User user) {
        // 认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)){
            throw new RuntimeException("用户名或密码错误！");
        }
        // 生成JwtToken、发往前端 将loginUser保存到Redis、
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtils.generateToken(userId);
        redisCache.setCacheObject(RedisConf.LOGIN + RedisConf.COLON + userId, loginUser);
        Map<String, String> map = new HashMap<>();
        map.put(VueConf.TOKEN, jwt);
        map.put("userName", loginUser.getUser().getUserName());
        return new Result().succ(map);
    }


    @Override
    public Result logout() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject(RedisConf.LOGIN + RedisConf.COLON + userid);
        return new Result().succ("退出成功");
    }
}
