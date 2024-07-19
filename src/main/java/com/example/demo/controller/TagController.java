package com.example.demo.controller;

import com.example.demo.entity.Tag;
import com.example.demo.global.Result;
import com.example.demo.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "标签管理")
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("getListByBlogSortUid")
    @ApiOperation(value = "查询全表")
    public Result getList(String blogSortUid){
        List<Tag> tagList = tagService.getByBlogSortUid(blogSortUid);
        return Result.succ(tagList);
    }

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

    @ApiOperation(value = "更新启用状态")
    @PostMapping("updateStatus")
    public Result updateStatus(@RequestBody Tag tag){
        return tagService.updateStatus(tag);
    }
}
