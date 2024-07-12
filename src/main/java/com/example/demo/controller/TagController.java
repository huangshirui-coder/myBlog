package com.example.demo.controller;

import com.example.demo.entity.Tag;
import com.example.demo.global.Result;
import com.example.demo.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "标签管理")
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @ApiOperation(value = "插入一条标签")
    @PostMapping("insert")
    public Result insert(@RequestBody Tag tag){
        return tagService.insert(tag);
    }

    @ApiOperation(value = "更新一条标签")
    @PostMapping("update")
    public Result update(@RequestBody Tag tag){
        return tagService.update(tag);
    }
}
