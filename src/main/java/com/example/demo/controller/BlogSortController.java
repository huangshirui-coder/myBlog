package com.example.demo.controller;

import com.example.demo.entity.BlogSort;
import com.example.demo.global.Result;
import com.example.demo.service.impl.BlogSortServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "博客类别接口", tags = "博客类别接口")
@RequestMapping("/blogsort")
public class BlogSortController {
    @Autowired
    BlogSortServiceImpl blogSortServiceImpl;

    @GetMapping("getList")
    @ApiOperation(value = "查询blogsort全表", notes = "查询blogsort全表")
    public Result getList(){
        List list = blogSortServiceImpl.getList();
        return Result.succ(list);
    }

    @PostMapping("insert")
    @ApiOperation(value = "插入一条BlogSort记录", notes = "插入一条BlogSort记录")
    public Result insert(BlogSort blogSort){
        if (blogSortServiceImpl.insert(blogSort)==1){
            return Result.succ("插入成功");
        }else {
            return Result.fail("插入失败");
        }
    }

    @ApiOperation(value = "更新一条BlogSort记录", notes = "更新一条BlogSort记录")
    @PostMapping("update")
    public  Result updateByUid(@RequestBody BlogSort blogSort){
        if (blogSortServiceImpl.updateByUid(blogSort) == 1){
            return Result.succ("更新成功");
        }else {
            return Result.fail("更新失败");
        }
    }
}
