package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import com.example.demo.global.Result;
import com.example.demo.web.page.PageDomain;
import com.example.demo.web.page.TableDataInfo;

import java.util.List;

/**
 * 用户类服务接口
 */
public interface UserService extends IService<User> {

    public Result register(User user);

    public User getUserById(Integer id);

    TableDataInfo pageList(User user, String searchKey, PageDomain pageDomain);

    Result changeUserType(User user);

    Result changeUserStatus(User user);

}
