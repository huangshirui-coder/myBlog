package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@TableName("t_blog_sort")
public class BlogSort extends Model {

    private static final long serialVersionUID = 1L;


    /**
     * uid
     */
    private String uid;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 状态，是否禁用
     */
    private Integer status;

    /**
     * 分类名
     */
    private String sortName;

    /**
     * 分类介绍
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String content;

    /**
     * 点击数
     */
    private Integer clickCount;

    /**
     * 排序字段，数值越大，越靠前
     */
    private Integer sort;

    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<Tag> tagList;
}
