package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

@Data
@TableName(value = "t_comment")
public class Comment extends Model {
    /*
     主键ID
     */
    private String uid;

    /*
    评论博客ID
     */
    private String blogUid;

    /*
    评论者
     */
    private String author;

    /*
    评论者邮箱
     */
    private String authorEmail;

    /*
    评论者IP
     */
    private String authorIp;

    /*
    评论时间
     */
    private Date createTime;

    /*
    评论内容
     */
    private String content;

    /*
    状态
     */
    private String state;

    /*
    评论父ID
     */
    private String parentId;

    /*
    孩子节点，子评论
     */
    @Transient
    private List<Comment> childrenNode;
}
