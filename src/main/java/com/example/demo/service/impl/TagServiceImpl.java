package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Tag;
import com.example.demo.mapper.TagMapper;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public Tag getByuid(String uid) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Tag::getUid, uid);
        return tagMapper.selectOne(wrapper);
    }
}
