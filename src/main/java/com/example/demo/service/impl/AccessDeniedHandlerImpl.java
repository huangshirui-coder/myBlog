package com.example.demo.service.impl;

import com.example.demo.global.Result;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * @Author Huang
 * @Description 授权失败处理类
 * @Param
 * @return
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        Result result = new Result().fail(HttpStatus.FORBIDDEN.value(), "权限不足", null);
        String json = JsonUtils.objectToJson(result);
        WebUtils.renderString(response,json);
    }
}
