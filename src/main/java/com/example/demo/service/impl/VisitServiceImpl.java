package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Visit;
import com.example.demo.global.Result;
import com.example.demo.service.UserService;
import com.example.demo.service.VisitService;
import com.example.demo.mapper.VisitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author 25290
* @description 针对表【sys_visit】的数据库操作Service实现
* @createDate 2024-07-05 09:24:25
*/
@Service
public class VisitServiceImpl extends ServiceImpl<VisitMapper, Visit> implements VisitService{
    @Resource
    VisitMapper visitMapper;
    @Resource
    UserService userService;

    @Override
    public Result insertVisit(Visit visit) {
        int flag = visitMapper.insert(visit);
        if(flag > 0){
            return Result.succ("插入成功");
        }else {
            return Result.fail("插入失败");
        }
    }

    @Override
    public Result deleteVisitList(List<String> ids) {
        int flag = visitMapper.deleteBatchIds(ids);
        if(flag > 0){
            return Result.succ("删除成功");
        }else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public List<Visit> selectVisitList(String time) {
        QueryWrapper<Visit> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT ip", "ip_addr", "username").lambda().eq(Visit::getVisitTime,time);
        List<Visit> list = visitMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Result selectProvince() {
        return null;
    }

    @Override
    public Map<String, Object> selectProvinceTen() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Visit> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT visit_time", "username");
        List<Visit> list = visitMapper.selectList(queryWrapper);
        List<Map<String, Object>> listMap = visitMapper.selectProvTen();
        map.put("provinceTen", listMap);
        map.put("visitCount", list.size());
        return map;
    }

}




