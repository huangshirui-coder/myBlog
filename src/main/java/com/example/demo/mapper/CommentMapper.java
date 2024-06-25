package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    int updateState(@Param("uids") List<String> uids, @Param("state") String state);

    List<Comment> selectByBlogUid(@Param("blogUid") String blogUid);
}
