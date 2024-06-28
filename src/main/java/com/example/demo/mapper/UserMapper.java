package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户类Mapper接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    public List<String> getAllUsername();
}
