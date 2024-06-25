package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 商品类别类
 */
@Data
@TableName(value = "cate")
public class Cate extends Model {
    @TableId(value = "cate_id")
    Integer id;
    String cateName;
    String cateDesc;
    String updateTime;
}
