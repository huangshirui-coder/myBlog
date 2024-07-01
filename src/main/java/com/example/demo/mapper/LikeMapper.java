package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Like;

public interface LikeMapper extends BaseMapper<Like> {
    Like getLikeByUserUidAndBlogUid(String userUid, String blogUid);
}
