package com.example.demo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class GoodsVo {
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
     * 类别描述
     */
    @TableField(value = "cate_desc")
    String cateDesc;

    /**
     * 类别更新时间
     */
    @TableField(value = "update_time")
    String updateTime;

    /**
     * 质量、数量
     */
    int quantity;

    /**
     * 单价
     */
    int price;


}
