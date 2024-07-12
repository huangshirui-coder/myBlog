package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Blog;
import com.example.demo.entity.BlogSort;
import com.example.demo.global.Result;
import com.example.demo.web.page.PageDomain;
import com.example.demo.web.page.TableDataInfo;

import java.util.List;

public interface BlogSortService extends IService<BlogSort> {
    /*
     * @Author Huang
     * @Description 查询BlogSort全表并返回
     * @Param []
     * @return java.util.List<com.example.demo.entity.BlogSort>
     **/
    public List<BlogSort> getList();

    /**
     * 插入一条BlogSort记录
     * @param blogSort
     * @return
     */
    public int insert(BlogSort blogSort);

    /**
     * 更具uid更新一条记录
     * @param uid
     * @return
     */
    public int updateByUid(BlogSort blogSort);

    /*
    根据uid查询BlogSort信息
     */
    BlogSort getBlogSortByUid(String uid);

    TableDataInfo pageList(BlogSort blogSort, PageDomain pageDomain);

    Result updateStatus(BlogSort blogSort);
}

