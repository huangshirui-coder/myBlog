package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.validator.annotion.IntNotNull;
import com.example.demo.validator.annotion.StringNotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@TableName("t_blog")
@Data
public class Blog extends Model {

    private static final long serialVersionUID = 1L;

    @StringNotNull
    private String uid;


    /**
     * 博客标题
     */
    private String title;

    /**
     * 封面图片
     */
    private String coverpic;

    /**
     * 状态
     */
    @IntNotNull
    private Integer status;

    /**
     * 点赞数
     */
    private int likeCount;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 博客简介
     * updateStrategy = FieldStrategy.IGNORED ：表示更新时候忽略非空判断
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String summary;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 标签uid
     */
    private String tagUid;

    /**
     * 博客分类UID
     */
    private String blogSortUid;

    /**
     * 博客点击数
     */
    private Integer clickCount;

    /**
     * 博客收藏数
     */
    private Integer collectCount;

    /**
     * 标题图片UID
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String fileUid;

    /**
     * 管理员UID
     */
    private String adminUid;

    /**
     * 是否发布
     */
    private String isPublish;

    /**
     * 是否原创
     */
    private String isOriginal;

    /**
     * 如果原创，作者为管理员名
     */
    private String author;

    /**
     * 推荐级别，用于首页推荐
     * 0：正常
     * 1：一级推荐(轮播图)
     * 2：二级推荐(top)
     * 3：三级推荐 ()
     * 4：四级 推荐 (特别推荐)
     */
    private Integer level;

    /**
     * 排序字段，数值越大，越靠前
     */
    @IntNotNull
    private Integer sort;

    /**
     * 是否开启评论(0:否， 1:是)
     */
    @IntNotNull
    private Integer openComment;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 文章类型【0 博客， 1：推广】
     */
    @IntNotNull
    private Integer type;

    /**
     * 外链【如果是推广，那么将跳转到外链】
     */
    private String outsideLink;

    /**
     * 投稿用户ID
     */
    private String userUid;

    /**
     * 投稿来源
     */
    @IntNotNull
    private Integer articleSource;


    // 以下字段不存入数据库，封装为了方便使用

    /**
     * 标签,一篇博客对应多个标签
     */
    @TableField(exist = false)
    private List<Tag> tagList;

    /**
     * 标题图
     */
    @TableField(exist = false)
    private List<String> photoList;

    /**
     * 博客分类
     */
    @TableField(exist = false)
    private BlogSort blogSort;


    /**
     * 博客标题图
     */
    @TableField(exist = false)
    private String photoUrl;


    /**
     * 版权申明
     */
    @TableField(exist = false)
    private String copyright;
}
