package com.example.demo.web.page;

import com.example.demo.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页数据
 *
 * @author activity
 */
@Data
public class PageDomain implements Serializable {
    /**
     * 当前记录起始索引
     */
    private Integer pageNum;
    /**
     * 每页显示记录数
     */
    private Integer pageSize;
    /**
     * 排序列
     */
    private String orderByColumn;
    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    private String isAsc;

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return orderByColumn + " " + isAsc;
    }
}