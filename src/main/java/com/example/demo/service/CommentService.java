package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.global.Result;
import com.example.demo.web.page.PageDomain;
import com.example.demo.web.page.TableDataInfo;
import org.springframework.stereotype.Service;

public interface CommentService extends IService<Comment> {
    Result insert(Comment comment);

    Result updateState(CommentDto commentDto);

    Result selectByBlogUid(String blogUid, Integer current, Integer size);

    Result deleteByUid(String uid);

    Result getCount(String blogUid);

    TableDataInfo getListWithSearchParam(Comment comment, PageDomain pageDomain);
}
