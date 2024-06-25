package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.global.Result;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.service.CommentService;
import com.example.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result insert(Comment comment) {
        int flag = commentMapper.insert(comment);
        if (flag > 0) {
            return Result.succ("插入成功");
        }else {
            return Result.fail("插入失败");
        }
    }

    @Override
    public Result updateState(CommentDto commentDto) {
        int flag = commentMapper.updateState(commentDto.getUids(), commentDto.getState());
        if (flag > 0) {
            return Result.succ("批量更新成功");
        }else {
            return Result.fail("批量更新失败");
        }
    }

    @Override
    public Result selectByBlogUid(String blogUid) {
        List<Comment> list = commentMapper.selectByBlogUid(blogUid);
        Map<String, List<Comment>> map = new HashMap<>();
        List<Comment> parentList = new ArrayList<>();
        List<Comment> childList;
        for (Comment comment : list) {
            if(map.get(comment.getParentId()) != null){
                childList = map.get(comment.getParentId());
            }else {
                childList = new ArrayList<>();
            }
            if (StringUtils.isNotBlank(comment.getParentId())){     //有父节点 是孩子
                childList.add(comment);
                map.put(comment.getParentId(), childList);
            }else {     //无父节点 是父亲
                parentList.add(comment);
            }
        }
        for (Comment comment: parentList) {
            for (Comment child : map.get(comment.getParentId())){
                comment.getChildrenNode().add(child);
            }
        }
        return Result.succ(parentList);
    }
}
