package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Tag;
import com.example.demo.global.Result;

import java.util.List;

public interface TagService extends IService<Tag> {
    Tag getByuid(String uid);

    List<Tag> getByBlogSortUid(String blogSortUid);

    Result insert(Tag tag);

    Result update(Tag tag);
}
