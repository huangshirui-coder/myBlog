package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @TableName sys_visit
 */
@TableName(value ="sys_visit")
@Data
public class Visit implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "uid")
    private String uid;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * ip地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * ip地址所属省份
     */
    @TableField(value = "ip_addr")
    private String ipAddr;

    /**
     * 登陆时间
     */
    @TableField(value = "visit_time")
    private String visitTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}