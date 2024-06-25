package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Tag;

public interface TagService extends IService<Tag> {
    Tag getByuid(String uid);
}
