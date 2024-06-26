package com.example.demo.vo;

import com.example.demo.entity.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentVo {
    List<Comment> commentList;
    Integer total;
}
