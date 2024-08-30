package com.example.demo.controller;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.example.demo.entity.Blog;
import com.example.demo.global.Result;
import com.example.demo.pojo.BlogPage;
import com.example.demo.pojo.Pagination;
import com.example.demo.service.BlogService;
import com.example.demo.vo.BlogVo;
import com.example.demo.web.page.PageDomain;
import com.example.demo.web.page.TableDataInfo;
import com.example.demo.web.page.TableSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "博客接口", tags = "博客接口")
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @ApiOperation(value = "按pagination查询博客的详细信息", notes = "按pagination查询博客的详细信息")
    @PostMapping("selectAll")
    public Result selectAllInstra(@RequestBody Pagination pagination){
        BlogPage blogPage = blogService.selectAll(pagination);
        return Result.succ(blogPage);
    }

    @ApiOperation(value = "分页查询博客详细信息")
    @GetMapping("getAll")
    public TableDataInfo selectAllInstraNew(Blog blog, HttpServletRequest request){
        return blogService.selectAllNew(blog, TableSupport.getPageDomain(request));
    }

    @ApiOperation(value = "按照pagination的一页大小搜索热门博客", notes = "按照pagination的一页大小搜索热门博客")
    @PostMapping("getHotBlog")
    public Result getHotBlog(@RequestBody Pagination pagination){
        BlogPage blogPage = blogService.getHotBlog(pagination);
        return Result.succ(blogPage);
    }

    @ApiOperation(value = "返回按照sort分类的blogList的map", notes = "返回按照sort分类的blogList的map")
    @GetMapping("getSortArticles")
    public Result getSortArticles(){
        Map<String , List> map = blogService.getSortArticles();
        return Result.succ(map);
    }

    @ApiOperation(value = "根据Pagination分页信息查询该分类下全部Blog", notes = "根据Pagination分页信息查询该分类下全部Blog")
    @PostMapping("getBlogByBSUid")
    public Result getBlogByBSUid(@RequestBody Pagination pagination){
        String BSUid = pagination.getSortId();
        BlogPage blogPage = blogService.getBlogByBSUid(BSUid, pagination);
        return Result.succ(blogPage);
    }

    @ApiOperation(value = "根据博客分类查询全部博客的简介信息", notes = "根据博客分类查询全部博客的简介信息")
    @GetMapping("selectAllByBSUid")
    public Result selectAllInstraByBSUid(@RequestBody String blogSortUid){
        List<Blog> list = blogService.selectByBlogSort(blogSortUid);
        return Result.succ(list);
    }

    @ApiOperation(value = "根据uid查询详细的博客记录", notes = "根据uid查询详细的博客记录")
    @GetMapping("selectOneByUid")
    public Result selectOneByUid(@RequestParam String uid, String userUid){
        BlogVo one = blogService.selectOne(uid, userUid);
        return Result.succ(one);
    }

    @ApiOperation(value = "根据uid删除博客记录", notes = "根据uid删除博客记录")
    @GetMapping("deleteByUid")
    public Result deleteByUid(String uid){
        int flag = blogService.deleteOne(uid);
        if (flag == 0){
            return Result.fail("删除失败");
        }else {
            return Result.succ("删除成功");
        }
    }

    @ApiOperation(value = "更新点击数", notes = "更新点击数")
    @GetMapping("updateClickCount")
    public Result updateClickCount(String uid){
        return Result.succ(blogService.updateClickCount(uid));
    }

    @ApiOperation(value = "根据uids批量删除博客记录", notes = "根据uids批量删除博客记录")
    @GetMapping("deleteByUidList")
    public Result deleteByUidList(String uids){
        int flag = blogService.deleteMany(uids);
        if (flag == 0){
            return Result.fail("删除失败");
        }else {
            return Result.succ("删除成功");
        }
    }

    @ApiOperation(value = "插入创建一条新的博客记录", notes = "插入创建一条新的博客记录")
    @PostMapping("insertOne")
    public Result insertOne(@RequestBody Blog blog){
        int flag = blogService.insertOne(blog);
        if (flag == 0){
            return Result.fail("插入失败");
        }else {
            return Result.succ("插入成功");
        }
    }

    @ApiOperation(value = "更新一条博客记录")
    @PostMapping("updateOne")
    public Result updateOne(@RequestBody Blog blog){
        return blogService.updateOne(blog);
    }

    @ApiOperation(value = "点赞功能接口", notes = "点赞功能接口")
    @GetMapping("updateLikeCount")
    public Result updateLikeCount(@RequestParam String uid, @RequestParam String userUid, @RequestParam boolean flag){
        return blogService.updateLikeCount(uid, userUid, flag);
    }

    @ApiOperation(value = "收藏功能接口", notes = "收藏功能接口")
    @GetMapping("updateRecordCount")
    public Result updateRecordCount(@RequestParam String uid, @RequestParam String userUid, @RequestParam boolean flag){
        return blogService.updateRecordCount(uid, userUid, flag);
    }

    @ApiOperation(value = "修改可见状态")
    @GetMapping("changeStatus")
    public Result changeStatus(String uid, int status){
        Blog blog = new Blog();
        blog.setUid(uid);
        blog.setStatus(status);
        return blogService.changeStatus(blog);
    }
    @ApiOperation(value = "修改评论开启状态")
    @GetMapping("changeOpenComment")
    public Result changeOpenComment(String uid, int openComment){
        Blog blog = new Blog();
        blog.setUid(uid);
        blog.setOpenComment(openComment);
        int flag = blogService.changeOpenComment(blog);
        if (flag > 0 ){
            return Result.succ("操作成功");
        }else {
            return Result.fail("操作失败");
        }
    }

    @GetMapping("blogCount")
    public Result blogCount(){
        int count = blogService.count();
        return Result.succ(count);
    }


}
