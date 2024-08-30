package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Like;
import com.example.demo.global.Result;
import com.example.demo.mapper.LikeMapper;
import com.example.demo.service.LikeService;
import com.example.demo.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {
    @Resource
    LikeMapper likeMapper;

    @Override
    public Result insertIfNotIn(String blogUid, String userUid) {

        Like searLike = likeMapper.getLikeByUserUidAndBlogUid(userUid, blogUid);
        int result = 0;
        if (searLike == null) {
            String uid = StringUtils.getUUID();
            Like like = new Like(uid, userUid, blogUid);
            result = likeMapper.insert(like);
        }else {
            LambdaQueryWrapper<Like> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Like::getUid, searLike.getUid());
            result = likeMapper.delete(wrapper);
        }
        if (result > 0){
            return Result.succ("操作成功");
        }else {
            return Result.fail("操作失败");
        }
    }

    @Override
    public int insertLike(String blogUid, String userUid) {
        String uid = StringUtils.getUUID();
        Like like = new Like(uid, userUid, blogUid);
        return likeMapper.insert(like);
    }

    @Override
    public int deleteLike(Like like) {
        LambdaQueryWrapper<Like> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Like::getUid, like.getUid());
        return likeMapper.delete(wrapper);
    }


    @Override
    public Like getLikeByUserUidAndBlogUid(String userUid, String blogUid) {
        return likeMapper.getLikeByUserUidAndBlogUid(userUid, blogUid);
    }

    @Override
    public List<Like> getLikeByUserUid(String userUid) {
        QueryWrapper<Like> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Like::getUserUid, userUid);
        List<Like> list = likeMapper.selectList(wrapper);
        return list;
    }
}
