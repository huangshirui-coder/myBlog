package com.example.demo.base.vo;

import com.example.demo.base.validator.Messages;
import com.example.demo.base.validator.group.GetList;
import com.example.demo.validator.annotion.LongNotNull;

/**
 * 用于分页
 * @param <T>
 */
public class PageInfo<T> {
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 当前页
     */
    @LongNotNull(groups = GetList.class, message = Messages.PAGE_NOT_NULL)
    private Long currentPage;

    /**
     * 页大小
     */
    @LongNotNull(groups = GetList.class, message = Messages.PAGE_NOT_NULL)
    private Long pageSize;
}
