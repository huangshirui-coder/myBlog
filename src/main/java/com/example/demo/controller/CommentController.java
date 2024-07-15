package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.global.Result;
import com.example.demo.service.CommentService;
import com.example.demo.utils.IPUtils;
import com.example.demo.utils.StringUtils;
import com.example.demo.web.page.TableDataInfo;
import com.example.demo.web.page.TableSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.ContentHandlerFactory;
import java.util.Date;

@RestController
@Api(value = "评论相关接口", tags = "评论相关接口")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("insert")
    @ApiOperation(notes = "插入一条新的评论", value = "插入一条新的评论")
    public Result addComment(@RequestBody Comment comment) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String ip = IPUtils.getClientIP(attributes.getRequest());
        comment.setAuthorIp(ip);
        comment.setCreateTime(new Date());
        comment.setState("1");
        comment.setUid(StringUtils.getUUID());
        return commentService.insert(comment);
    }

    @GetMapping("selectByBlogUid")
    @ApiOperation(notes = "查询该博客下评论", value = "查询该博客下评论")
    public Result selectCommentByBlogUid(@RequestParam String source,@RequestParam Integer current,@RequestParam Integer size) {
        return commentService.selectByBlogUid(source, current, size);
    }

    @ApiOperation(notes = "查询该博客总评论数", value = "查询该博客总评论数")
    @GetMapping("getCommentCount")
    public Result getCount(@RequestParam String source){
        return commentService.getCount(source);
    }

    @PostMapping("updateState")
    @ApiOperation(notes = "更新单条评论记录", value = "更新单条评论记录")
    public Result updateCommentState(@RequestBody CommentDto commentDto) {
        return commentService.updateState(commentDto);
    }

    @GetMapping("delete")
    @ApiOperation(notes = "删除单条评论记录", value = "删除单条评论记录")
    public Result deleteCommentByUid(@RequestParam String uid) {
        return commentService.deleteByUid(uid);
    }

    @GetMapping("getListWithSearchParam")
    public TableDataInfo getListWithSearchParam(Comment comment, HttpServletRequest request){
        return commentService.getListWithSearchParam(comment, TableSupport.getPageDomain(request));
    }
}
