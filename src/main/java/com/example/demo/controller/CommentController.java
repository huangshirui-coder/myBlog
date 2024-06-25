package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "评论相关接口", tags = "评论相关接口")
@RequestMapping("/comment")
public class CommentController {

}
