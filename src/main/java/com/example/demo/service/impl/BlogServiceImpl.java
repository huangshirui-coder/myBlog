package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.LoginUser;
import com.example.demo.entity.*;
import com.example.demo.global.Result;
import com.example.demo.mapper.BlogMapper;
import com.example.demo.pojo.BlogPage;
import com.example.demo.pojo.Pagination;
import com.example.demo.service.BlogService;
import com.example.demo.service.BlogSortService;
import com.example.demo.service.LikeService;
import com.example.demo.service.TagService;
import com.example.demo.utils.StringUtils;
import com.example.demo.vo.BlogVo;
import com.example.demo.web.page.PageDomain;
import com.example.demo.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    TagService tagService;
    @Autowired
    BlogSortService blogSortService;
    @Autowired
    LikeService likeService;

    @Override
    public BlogPage selectAll(Pagination pagination) {
        int currentPage = pagination.getCurrent();
        int size = pagination.getSize();
        BlogPage blogPage = new BlogPage();
        Page page = new Page(currentPage, size);
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        page = blogMapper.selectPage(page, wrapper);
        List<Blog> list = page.getRecords();
        for (Blog item : list){
            handleBlog(item);
        }
        blogPage.setBlogList(list);
        if (pagination.getTotal() == 0){
            blogPage.setTotal(blogMapper.selectCount(wrapper));
        }else {
            blogPage.setTotal(pagination.getTotal());
        }
        return blogPage;
    }

    @Override
    public TableDataInfo selectAllNew(Blog blog, PageDomain pageDomain) {
        Page<Blog> page = new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize());
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(blog.getBlogSortUid()), Blog::getBlogSortUid, blog.getBlogSortUid())
                .eq(StringUtils.isNotBlank(blog.getTagUid()), Blog::getTagUid, blog.getTagUid())
                .like(StringUtils.isNotBlank(blog.getTitle()), Blog::getTitle, blog.getTitle());
        page = blogMapper.selectPage(page, wrapper);
        List<Blog> blogList = page.getRecords();
        for (Blog blog1 : blogList){
            handleBlog(blog1);
        }
        return TableDataInfo.suss(blogList, page.getTotal(), "查询成功");
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
            handleBlog(blog);
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
    public BlogVo selectOne(String uid, String userUid) {
        BlogVo blog = blogMapper.selectOneByUid(uid, userUid);
        handleBlogVo(blog);
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
    public Result updateOne(Blog blog) {
        int flag = blogMapper.update(blog,new UpdateWrapper<>());
        if (flag > 0){
            return Result.succ("更新成功");
        }else {
            return Result.fail("更新失败");
        }
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
    public Result updateLikeCount(String uid, String userUid, boolean flag) {
        Like searLike = likeService.getLikeByUserUidAndBlogUid(userUid, uid);
        int result = 0;
        if (searLike == null) {
            result = blogMapper.updateLikeCount(uid, false);
        }else {
            result = blogMapper.updateLikeCount(uid, true);
        }
        likeService.insertIfNotIn(uid, userUid);
        if (result > 0){
            return Result.succ("更新成功");
        }else {
            return Result.fail("更新失败");
        }
    }

    @Override
    public Result updateRecordCount(String uid, String userUid, boolean flag) {
        return null;
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

    @Override
    public Result changeStatus(Blog blog) {
        UpdateWrapper<Blog> wrapper = new UpdateWrapper<>();
        wrapper.eq("uid", blog.getUid());
        blogMapper.update(blog, wrapper);
        return Result.succ("操作成功");
    }

    @Override
    public int changeOpenComment(Blog blog) {
        UpdateWrapper<Blog> wrapper = new UpdateWrapper<>();
        wrapper.eq("uid", blog.getUid());
        return blogMapper.update(blog, wrapper);
    }

    void handleBlogVo(BlogVo blog){
        BlogSort blogSort = blogSortService.getBlogSortByUid(blog.getBlogSortUid());
        blog.setBlogSort(blogSort);
        String[] tags = blog.getTagUid().split(",");
        ArrayList<String> tagUidList = new ArrayList<>(tags.length);
        Collections.addAll(tagUidList, tags);
        List<Tag> tagList = tagService.getByTagList(tagUidList);
        blog.setTagList(tagList);
    }

    void handleBlog(Blog blog){
        BlogSort blogSort = blogSortService.getBlogSortByUid(blog.getBlogSortUid());
        blog.setBlogSort(blogSort);
        String[] tags = blog.getTagUid().split(",");
        ArrayList<String> tagUidList = new ArrayList<>(tags.length);
        Collections.addAll(tagUidList, tags);
        List<Tag> tagList = tagService.getByTagList(tagUidList);
        blog.setTagList(tagList);
    }
}
