package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.BlogSort;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogSortMapper extends BaseMapper<BlogSort> {
    BlogSort selectByUid(String uid);

    int updateStatus(BlogSort blogSort);
}
