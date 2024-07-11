package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import com.example.demo.web.page.PageDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户类Mapper接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    public List<String> getAllUsername();

    User getUserWithRoleByUsername(String username);

    List<User> pageList(User user, String searchKey, PageDomain pageDomain);
}
