package com.example.demo.controller;

import com.example.demo.global.Result;
import com.example.demo.service.VisitService;
import com.example.demo.service.WebInfoService;
import com.example.demo.service.impl.WebInfoServiceImpl;
import com.example.demo.vo.VisitVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "网站信息接口", tags = "网站信息接口")
@RequestMapping("/webInfo")
public class WebInfoController {
    @Autowired
    WebInfoService webInfoService;
    @Autowired
    VisitService visitService;

    @ApiOperation(value = "获取网页信息", notes = "获取网页信息")
    @GetMapping("/getInfo")
    public Result getInfo(){
        return Result.succ(webInfoService.getInfo());
    }

    @ApiOperation(value = "具体访问信息列表")
    @GetMapping("getVisitList")
    public Result getVisitList(String time){
        return Result.succ(visitService.selectVisitList(time));
    }

    @ApiOperation(value = "获取总访问量省份排名前10和总访问次数")
    @GetMapping("getProvAndCount")
    public Result getProvAndCount(){
        return Result.succ(visitService.selectProvinceTen());
    }
}
