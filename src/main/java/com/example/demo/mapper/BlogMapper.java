package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Blog;
import com.example.demo.global.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> selectAllInstra();

    List<Blog> selectAllOrderBysid();

    List<Blog> selectAllInstraByBSuid(String blogSortUid);

    int deleteAllByUidList(List<String> uidList);
    /*
     点击数+1
    */
    int updateClickCount(String uid);

    /*
    点赞数+1
     */
    int updateLikeCount(String uid);

    /*
    评论数+1
     */
    int updateCommentCount(String uid);

}
