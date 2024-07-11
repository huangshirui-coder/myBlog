package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.BlogSort;
import com.example.demo.global.RedisConf;
import com.example.demo.mapper.BlogSortMapper;
import com.example.demo.service.BlogSortService;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.RedisUtils;
import com.example.demo.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BlogSortServiceImpl extends ServiceImpl<BlogSortMapper, BlogSort> implements BlogSortService {

    @Autowired
    BlogSortMapper blogSortMapper;
    @Autowired
    RedisUtils redisUtils;

    @Override
    public List<BlogSort> getList() {
        LambdaQueryWrapper<BlogSort> wrapper = new LambdaQueryWrapper<BlogSort>();
        List<BlogSort> list = blogSortMapper.selectList(wrapper);
        String jsonResult = redisUtils.get("blogsort-List");
        if (! StringUtils.isEmpty(jsonResult)){
            ArrayList arrayList = JsonUtils.jsonArrayToArrayList(jsonResult);
            log.info("从缓存中获取blogsort-List");
            return arrayList;
        }
        redisUtils.set("blogsort-List", JsonUtils.objectToJson(list).toString());
        log.info("从数据库中获取blogsort-List");
        return list;
    }



    @Override
    public int insert(BlogSort blogSort) {
        blogSort.setUid(StringUtils.getUUID());
        blogSort.setCreateTime(new Date());
        blogSort.setUpdateTime(new Date());
        int flag = blogSortMapper.insert(blogSort);
        redisClear();
        return flag;
    }


    @Override
    public int updateByUid(BlogSort blogSort) {
        BlogSort blogSort1 = blogSortMapper.selectByUid(blogSort.getUid());
        blogSort1.setContent(blogSort.getContent());
        blogSort1.setClickCount(blogSort.getClickCount());
        blogSort1.setSort(blogSort.getSort());
        blogSort1.setSortName(blogSort.getSortName());
        blogSort1.setStatus(blogSort.getStatus());
        blogSort1.setUpdateTime(new Date());
        LambdaQueryWrapper<BlogSort> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogSort::getUid, blogSort.getUid());
        int result = blogSortMapper.update(blogSort1, wrapper);

        return result;
    }

    @Override
    public BlogSort getBlogSortByUid(String uid) {
        LambdaQueryWrapper<BlogSort> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BlogSort::getUid, uid);
        BlogSort blogSort = blogSortMapper.selectOne(wrapper);
        return blogSort;
    }


    private void redisClear(){
        redisUtils.delete("blogsort-List");
    }


}
