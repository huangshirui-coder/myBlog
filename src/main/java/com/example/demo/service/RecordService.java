package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Like;
import com.example.demo.entity.Record;
import com.example.demo.global.Result;
import com.example.demo.mapper.RecordMapper;

public interface RecordService extends IService<Record> {
    Result insertIfNotIn(String blogUid, String userUid);

    Record selectByBlogUidAndUserUid(String blogUid, String userUid);
}
