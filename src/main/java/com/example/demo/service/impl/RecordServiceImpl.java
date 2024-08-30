package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Like;
import com.example.demo.entity.Record;
import com.example.demo.global.Result;
import com.example.demo.mapper.RecordMapper;
import com.example.demo.service.RecordService;
import com.example.demo.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Resource
    RecordMapper recordMapper;

    @Override
    public Result insertIfNotIn(String blogUid, String userUid) {
        Record searRecord = recordMapper.selectByBlogUidAndUserUid(blogUid, userUid);
        int result = 0;
        if (searRecord == null) {
            String uid = StringUtils.getUUID();
            Record record = new Record(uid, userUid, blogUid);
            result = recordMapper.insert(record);
        }else {
            LambdaQueryWrapper<Record> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Record::getUid, searRecord.getUid());
            result = recordMapper.delete(wrapper);
        }
        if (result > 0) {
            return Result.succ("操作成功");
        }else {
            return Result.fail("操作失败");
        }
    }

    @Override
    public int insertRecord(String blogUid, String userUid) {
        String uid = StringUtils.getUUID();
        Record record = new Record(uid, userUid, blogUid);
        return recordMapper.insert(record);
    }

    @Override
    public int deleteRecord(Record record) {
        LambdaQueryWrapper<Record> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Record::getUid, record.getUid());
        return recordMapper.delete(wrapper);
    }

    @Override
    public Record selectByBlogUidAndUserUid(String blogUid, String userUid) {
        return recordMapper.selectByBlogUidAndUserUid(blogUid, userUid);
    }

    @Override
    public List<Record> getRecordByUserUid(String userUid) {
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Record::getUserUid, userUid);
        return recordMapper.selectList(wrapper);
    }
}
