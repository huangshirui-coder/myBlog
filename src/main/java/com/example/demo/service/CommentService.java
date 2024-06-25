package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.global.Result;
import org.springframework.stereotype.Service;

public interface CommentService extends IService<Comment> {
    Result insert(Comment comment);

    Result updateState(CommentDto commentDto);

    Result selectByBlogUid(String blogUid);
}
