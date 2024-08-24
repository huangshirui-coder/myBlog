package com.example.demo.utils;

import com.example.demo.dto.LoginUser;
import com.example.demo.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() != "anonymousUser"){
            User user = ((LoginUser)authentication.getPrincipal()).getUser();
            return user.getUserName();
        }else {
            return "";
        }
    }
}
