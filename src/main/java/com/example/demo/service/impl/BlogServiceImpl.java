package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.LoginUser;
import com.example.demo.entity.Blog;
import com.example.demo.entity.BlogSort;
import com.example.demo.entity.Tag;
import com.example.demo.entity.User;
import com.example.demo.global.Result;
import com.example.demo.mapper.BlogMapper;
import com.example.demo.pojo.BlogPage;
import com.example.demo.pojo.Pagination;
import com.example.demo.service.BlogService;
import com.example.demo.service.BlogSortService;
import com.example.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagServiceImpl tagService;
    @Autowired
    BlogSortService blogSortService;

    @Override
    public BlogPage selectAll(Pagination pagination) {
        int currentPage = pagination.getCurrent();
        int size = pagination.getSize();
        BlogPage blogPage = new BlogPage();
        Page page = new Page(currentPage, size);
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        page = blogMapper.selectPage(page, wrapper);
        List<Blog> list = page.getRecords();
        blogPage.setBlogList(list);
        if (pagination.getTotal() == 0){
            blogPage.setTotal(blogMapper.selectCount(wrapper));
        }else {
            blogPage.setTotal(pagination.getTotal());
        }
        return blogPage;
    }

    @Override
    public Map<String, List> getSortArticles() {
        List<Blog> list = blogMapper.selectAllOrderBysid();
        Map<String,List> map = new HashMap<>();
        Iterator it =  list.iterator();
        String sid = null;
        List<Blog> tmp = new ArrayList<>();
        while (it.hasNext()){
            Blog blog = (Blog) it.next();
            String tagsUid = blog.getTagUid();
            String[] tagsUidArray = tagsUid.split(",");
            List<Tag> tagList = new ArrayList();
            for (int i = 0; i < tagsUidArray.length; i++){
                Tag tag = tagService.getByuid(tagsUidArray[i]);
                tagList.add(tag);
            }
            blog.setTagList(tagList);
            blog.setBlogSort(blogSortService.getBlogSortByUid(blog.getBlogSortUid()));
            if (!blog.getBlogSortUid().equals(sid) ){
                if (sid != null){
                    map.put(sid,new ArrayList(tmp));
                }
                sid = blog.getBlogSortUid();
                tmp.clear();
                tmp.add(blog);
            }else {
                tmp.add(blog);
            }
        }
        map.put(sid,tmp);
        return map;
    }

    @Override
    public Blog selectOne(String uid) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getUid, uid);
        Blog blog = blogMapper.selectOne(wrapper);
        BlogSort blogSort = blogSortService.getBlogSortByUid(blog.getBlogSortUid());
        blog.setBlogSort(blogSort);
        return blog;
    }

    @Override
    public int deleteOne(String uid) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getUid, uid);
        return blogMapper.delete(wrapper);
    }

    @Override
    public int deleteMany(String uids) {
        List<String> uidList = new ArrayList(Arrays.asList(uids.split(",")));
        int flag = blogMapper.deleteAllByUidList(uidList);
        return flag;
    }

    @Override
    public List<Blog> selectByBlogSort(String blogSortUid) {
        List<Blog> blogInstralist = blogMapper.selectAllInstraByBSuid(blogSortUid);
        return blogInstralist;
    }

    @Override
    public int insertOne(Blog blog) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((LoginUser)authentication.getPrincipal()).getUser();
        blog.setAuthor(user.getNickName());
        blog.setUid(StringUtils.getUUID());
        blog.setStatus(1);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        int flag = blogMapper.insert(blog);
        return flag;
    }

    @Override
    public Result updateClickCount(String uid) {
        int flag = blogMapper.updateClickCount(uid);
        if (flag > 0){
            return Result.succ("更新成功");
        }else {
            return Result.fail("更新失败");
        }
    }

    @Override
    public Result updateLikeCount(String uid, boolean flag) {
        int result = blogMapper.updateLikeCount(uid, flag);
        if (result > 0){
            return Result.succ("更新成功");
        }else {
            return Result.fail("更新失败");
        }
    }

    @Override
    public Result updateCommentCount(String uid) {
        int flag = blogMapper.updateCommentCount(uid);
        if (flag > 0){
            return Result.succ("更新成功");
        }else {
            return Result.fail("更新失败");
        }
    }

    @Override
    public BlogPage getHotBlog(Pagination pagination) {
        int currentPage = pagination.getCurrent();
        int size = pagination.getSize();
        BlogPage blogPage = new BlogPage();
        Page page = new Page(currentPage, size);
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Blog::getClickCount);
        page = blogMapper.selectPage(page, wrapper);
        List<Blog> list = page.getRecords();
        blogPage.setBlogList(list);
        return blogPage;
    }

    @Override
    public BlogPage getBlogByBSUid(String bsUid, Pagination pagination) {
        int size = pagination.getSize();
        int currentPage = pagination.getCurrent();
        BlogPage blogPage = new BlogPage();
        Page page = new Page(currentPage, size);
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getBlogSortUid, bsUid);
        page = blogMapper.selectPage(page, wrapper);
        List<Blog> list = page.getRecords();
        for(Blog blog : list){
            String tagsUid = blog.getTagUid();
            String[] tagsUidArray = tagsUid.split(",");
            List<Tag> tagList = new ArrayList();
            for (int i = 0; i < tagsUidArray.length; i++){
                Tag tag = tagService.getByuid(tagsUidArray[i]);
                tagList.add(tag);
            }
            blog.setTagList(tagList);

            String blogSortUid = blog.getBlogSortUid();
            BlogSort blogSort = blogSortService.getBlogSortByUid(blogSortUid);
            blog.setBlogSort(blogSort);
        }
        blogPage.setBlogList(list);
        if (pagination.getTotal() == 0){
            blogPage.setTotal(blogMapper.selectCount(wrapper));
        }else {
            blogPage.setTotal(pagination.getTotal());
        }
        return blogPage;
    }
}
