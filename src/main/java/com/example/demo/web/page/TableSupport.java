package com.example.demo.web.page;

import javax.servlet.http.HttpServletRequest;

/**
 * 表格数据处理
 *
 * @author activity
 */
public class TableSupport
{
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 封装分页对象
     * @param request
     * @return
     */
    public static  PageDomain getPageDomain(HttpServletRequest request){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(Integer.valueOf(request.getParameter(PAGE_NUM)));
        pageDomain.setPageSize(Integer.valueOf(request.getParameter(PAGE_SIZE)));
        pageDomain.setOrderByColumn(request.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(request.getParameter(IS_ASC));
        return pageDomain;
    }
}
