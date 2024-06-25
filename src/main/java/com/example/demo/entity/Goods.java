package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.base.validator.group.Delete;
import com.example.demo.base.validator.group.Update;
import com.example.demo.base.vo.PageInfo;
import com.example.demo.validator.annotion.IdValid;
import lombok.Data;

import java.io.Serializable;

/**
 * 仓库商品类
 */
@Data
@TableName("goods")
public class Goods extends Model {
    private  static final long serialVersionUID = 1L;

    @IdValid(groups = {Update.class, Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    /**
     * 商品名
     */
    String name;

    /**
     * 类别
     */
    String category;

    /**
     * 质量、数量
     */
    int quantity;

    /**
     * 单价
     */
    int price;

}
