package com.example.demo.mapper;

import com.example.demo.entity.Visit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
* @author 25290
* @description 针对表【sys_visit】的数据库操作Mapper
* @createDate 2024-07-05 09:24:25
* @Entity com.example.demo.entity.Visit
*/
public interface VisitMapper extends BaseMapper<Visit> {
    List<Map<String, Object>> selectProvTen();

}




