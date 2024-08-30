package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Like;
import com.example.demo.global.Result;

import java.util.List;


public interface LikeService extends IService<Like> {
    Result insertIfNotIn(String blogUid, String userUid);

    int insertLike(String blogUid, String userUid);

    int deleteLike(Like like);

    Like getLikeByUserUidAndBlogUid(String userUid, String blogUid);

    List<Like> getLikeByUserUid(String userUid);
}
