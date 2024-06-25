package com.example.demo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private User user;

    //存储权限信息
    private List<String> permissions;

    public LoginUser(User user,List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    //存储SpringSecurity所需要的权限信息的集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    /*
     * @Author Huang
     * @Description 授予用户的权限
     * @Param []
     * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null){
            return authorities;
        }
                //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
                authorities = permissions.stream().
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /*
     * @Author Huang
     * @Description 用户的账号是否过期
                    true 未过期 false 已过期
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * @Author Huang
     * @Description 用户的账号是否被锁定
                    true 未被锁定 false 被锁定
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * @Author Huang
     * @Description 用户的凭据（密码）是否已过期
                    true 未过期 false 已过期
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * @Author Huang
     * @Description 用户是否启用
                    true 启用 false 禁用
     * @Param []
     * @return boolean
     **/
    @Override
    public boolean isEnabled() {
        return true;
    }
}
