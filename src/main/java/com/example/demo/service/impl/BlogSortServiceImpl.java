package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Blog;
import com.example.demo.entity.BlogSort;
import com.example.demo.entity.Tag;
import com.example.demo.global.RedisConf;
import com.example.demo.global.Result;
import com.example.demo.mapper.BlogSortMapper;
import com.example.demo.service.BlogSortService;
import com.example.demo.service.TagService;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.RedisUtils;
import com.example.demo.utils.StringUtils;

import com.example.demo.web.page.PageDomain;
import com.example.demo.web.page.TableDataInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BlogSortServiceImpl extends ServiceImpl<BlogSortMapper, BlogSort> implements BlogSortService {

    @Autowired
    BlogSortMapper blogSortMapper;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    TagService tagService;

    @Override
    public List<BlogSort> getList() {
        String jsonResult = redisUtils.get("blogsort-List");
        if (! StringUtils.isEmpty(jsonResult)){
            ArrayList arrayList = JsonUtils.jsonArrayToArrayList(jsonResult);
            log.info("从缓存中获取blogsort-List");
            return arrayList;
        }
        LambdaQueryWrapper<BlogSort> wrapper = new LambdaQueryWrapper<BlogSort>();
        List<BlogSort> list = blogSortMapper.selectList(wrapper);
        for (BlogSort blogSort: list){
            List<Tag> tagList = tagService.getByBlogSortUid(blogSort.getUid());
            blogSort.setTagList(tagList);
        }
        redisUtils.setEx("blogsort-List", JsonUtils.objectToJson(list).toString(), 1, TimeUnit.DAYS);
        log.info("从数据库中获取blogsort-List");
        return list;
    }



    @Override
    public int insert(BlogSort blogSort) {
        blogSort.setUid(StringUtils.getUUID());
        blogSort.setCreateTime(new Date());
        blogSort.setUpdateTime(new Date());
        blogSort.setStatus(1);
        blogSort.setClickCount(0);
        int flag = blogSortMapper.insert(blogSort);
        redisClear();
        return flag;
    }


    @Override
    public int updateByUid(BlogSort blogSort) {
        BlogSort blogSort1 = blogSortMapper.selectByUid(blogSort.getUid());
        blogSort1.setContent(blogSort.getContent());
        blogSort1.setSort(blogSort.getSort());
        blogSort1.setSortName(blogSort.getSortName());
        blogSort1.setUpdateTime(new Date());
        LambdaQueryWrapper<BlogSort> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogSort::getUid, blogSort.getUid());
        int result = blogSortMapper.update(blogSort1, wrapper);
        redisClear();

        return result;
    }

    @Override
    public BlogSort getBlogSortByUid(String uid) {
        LambdaQueryWrapper<BlogSort> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogSort::getUid, uid);
        BlogSort blogSort = blogSortMapper.selectOne(wrapper);
        return blogSort;
    }

    @Override
    public TableDataInfo pageList(BlogSort blogSort, PageDomain pageDomain) {
        LambdaQueryWrapper<BlogSort> wrapper = new LambdaQueryWrapper<>();
        Page page = new Page(pageDomain.getPageNum(), pageDomain.getPageSize());
        page = blogSortMapper.selectPage(page, wrapper);
        TableDataInfo tableDataInfo = TableDataInfo.suss(page.getRecords(),page.getTotal(),"操作成功");
        return tableDataInfo;
    }

    @Override
    public Result updateStatus(BlogSort blogSort) {
        int flag = blogSortMapper.updateStatus(blogSort);
        if (flag > 0){
            redisClear();
            return Result.succ("成功");
        }else {
            return Result.fail("失败");
        }
    }


    private void redisClear(){
        redisUtils.delete("blogsort-List");
    }


}
