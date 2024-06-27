package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.global.Result;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import com.example.demo.utils.StringUtils;
import com.example.demo.vo.CommentVo;
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
    @Autowired
    private BlogService blogService;

    @Override
    public Result insert(Comment comment) {
        int flag = commentMapper.insert(comment);
        if (flag > 0) {
            blogService.updateCommentCount(comment.getBlogUid());
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
    public Result selectByBlogUid(String blogUid, Integer currentPage, Integer pageSize) {
        List<Comment> list = commentMapper.selectByBlogUid(blogUid);
        Map<String, List<Comment>> map = new HashMap<>();
        List<Comment> parentList = new ArrayList<>();
        List<Comment> childList;
        List<Comment> resultList = new ArrayList<>();
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
            comment.setChildrenNode(new ArrayList<>());
            if (map.get(comment.getUid())!=null && map.get(comment.getUid()).size()>0){
                for (Comment child : map.get(comment.getUid())){
                    comment.getChildrenNode().add(child);
                }
            }
            int childTotal = comment.getChildrenNode().size();
            comment.setChildTotal(childTotal);
        }
        CommentVo commentVo = new CommentVo();
        int total = parentList.size();
        commentVo.setTotal(total);
        if ((currentPage - 1) * pageSize < total) {
            for (int i = (currentPage - 1) * pageSize; i < total && i < currentPage * pageSize; i++) {
                resultList.add(parentList.get(i));
            }
        }
        commentVo.setCommentList(resultList);
        return Result.succ(commentVo);
    }

    @Override
    public Result deleteByUid(String uid) {
        int flag = commentMapper.deleteById(uid);
        if (flag > 0) {
            return Result.succ("删除成功");
        }else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result getCount(String blogUid) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getBlogUid, blogUid);
        return Result.succ(commentMapper.selectCount(wrapper));
    }
}
