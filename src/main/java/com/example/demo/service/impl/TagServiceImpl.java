package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Tag;
import com.example.demo.global.Result;
import com.example.demo.mapper.TagMapper;
import com.example.demo.service.TagService;
import com.example.demo.utils.RedisUtils;
import com.example.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public Tag getByuid(String uid) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Tag::getUid, uid);
        return tagMapper.selectOne(wrapper);
    }

    @Override
    public List<Tag> getByBlogSortUid(String blogSortUid) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getBlogSortUid, blogSortUid);
        return tagMapper.selectList(wrapper);
    }

    @Override
    public Result insert(Tag tag) {
        tag.setUid(StringUtils.getUUID());
        tag.setCreateTime(new Date());
        tag.setUpdateTime(new Date());
        tag.setClickCount(0);
        tag.setStatus(1);
        int flag = tagMapper.insert(tag);
        if (flag > 0){
            redisClear();
            return Result.succ("插入成功");
        }else {
            return Result.fail("插入失败");
        }
    }

    @Override
    public Result update(Tag tag) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getUid, tag.getUid());
        Tag tagResult = tagMapper.selectOne(wrapper);
        tagResult.setContent(tag.getContent());
        tagResult.setSort(tag.getSort());
        tagResult.setBlogSortUid(tag.getBlogSortUid());
        tagResult.setUpdateTime(new Date());
        int flag = tagMapper.update(tagResult, wrapper);
        if (flag > 0){
            redisClear();
            return Result.succ("更新成功");
        }else {
            return Result.fail("更新失败");
        }
    }

    private void redisClear(){
        redisUtils.delete("blogsort-List");
    }
}
