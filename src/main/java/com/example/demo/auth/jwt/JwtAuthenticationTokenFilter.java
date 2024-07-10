package com.example.demo.auth.jwt;

import com.example.demo.dto.LoginUser;
import com.example.demo.global.RedisConf;
import com.example.demo.global.VueConf;
import com.example.demo.utils.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userId;
        try {
            Claims claims = JwtUtils.getClaimsByToken(token);
            userId = claims.getSubject();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Token非法");
        }
        //从Redis中获取用户信息
        String redisKey = RedisConf.LOGIN + RedisConf.COLON + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)){
//            throw new RuntimeException("用户未登录");
            response.getWriter().print(400);
            return;
        }
        //存入SecurityContextHolder
        //获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
