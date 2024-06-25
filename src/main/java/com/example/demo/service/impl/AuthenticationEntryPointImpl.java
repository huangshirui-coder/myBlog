package com.example.demo.service.impl;

import com.example.demo.global.Result;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 认证授权失败处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse
            response, AuthenticationException authException) throws IOException,
            ServletException {
        Result result = new Result().fail(HttpStatus.UNAUTHORIZED.value(), authException.getMessage() , null);
        String json = JsonUtils.objectToJson(result);
        WebUtils.renderString(response,json);
    }
}
