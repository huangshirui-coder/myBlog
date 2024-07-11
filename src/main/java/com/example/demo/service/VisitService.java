package com.example.demo.service;

import com.example.demo.entity.Visit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.global.Result;

import java.util.List;
import java.util.Map;

/**
* @author 25290
* @description 针对表【sys_visit】的数据库操作Service
* @createDate 2024-07-05 09:24:25
*/
public interface VisitService extends IService<Visit> {
    Result insertVisit(Visit visit);

    Result deleteVisitList(List<String> ids);

    /*
    查询该日的访问列表
     */
    List<Visit> selectVisitList(String time);

    /*
    统计每个省份访问排名
     */
    Result selectProvince();

    /*
    查询访问榜省份前10和总访问次数
     */
    Map<String, Object> selectProvinceTen();

}
