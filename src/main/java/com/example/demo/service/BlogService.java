package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Blog;
import com.example.demo.global.Result;
import com.example.demo.pojo.BlogPage;
import com.example.demo.pojo.Pagination;
import com.example.demo.vo.BlogVo;
import com.example.demo.web.page.PageDomain;
import com.example.demo.web.page.TableDataInfo;

import java.util.List;
import java.util.Map;

public interface BlogService extends IService<Blog> {
    /*
     * @Author Huang
     * @Description 查询所有博客的展示信息（主要是简介、题目等一些信息）
     * @Param []
     * @return java.util.List<com.example.demo.entity.Blog>
     **/
    public BlogPage selectAll(Pagination pagination);

    /**
     * 分页查询（新）
     * @param blog
     * @param pageDomain
     * @return
     */
    TableDataInfo selectAllNew(Blog blog, PageDomain pageDomain);

    /*
     * @Author Huang
     * @Description 返回按照sort分类的BlogList的map
     * @Param []
     * @return java.util.Map<java.lang.Integer,java.util.List>
     **/
    Map<String, List> getSortArticles();

    /*
     * @Author Huang
     * @Description 查询一条博客的详细全部内容
     * @Param []
     * @return com.example.demo.entity.Blog
     **/
    public BlogVo selectOne(String uid, String userUid);

    /*
     * @Author Huang
     * @Description 根据uid删除一条博客记录
     * @Param [uid]
     * @return int
     **/
    public int deleteOne(String uid);

    /*
     * @Author Huang
     * @Description 将uid分割开后并进行批量删除
     * @Param [uids] uid1,uid2,uid3...的形式拼接的字符串
     * @return int
     **/
    public int deleteMany(String uids);

    /*
     * @Author Huang
     * @Description 根据博客分类的uid查询该分类下的博客的简介信息
     * @Param [BlogSortUid]
     * @return java.util.List<com.example.demo.entity.Blog>
     **/
    public List<Blog> selectByBlogSort(String blogSortUid);

    /*
     * @Author Huang
     * @Description 插入一条新的博客记录
     * @Param [blog]
     * @return int
     **/
    public int insertOne(Blog blog);

    Result updateOne(Blog blog);

    /*
    点击数+1
     */
    public Result updateClickCount(String uid);

    /*
    点赞数+1
     */
    public Result updateLikeCount(String uid, String userUid, boolean flag);

    /*
    收藏数+1
     */
    public Result updateRecordCount(String uid, String userUid, boolean flag);

    /*
    评论数+1
     */
    public Result updateCommentCount(String uid, int count);

    /*
        获取热门博客
     */
    public BlogPage getHotBlog(Pagination pagination);

    BlogPage getBlogByBSUid(String bsUid, Pagination pagination);

    Result changeStatus(Blog blog);

    int changeOpenComment(Blog blog);

    TableDataInfo getLikeBlogByUser(String uid, PageDomain pageDomain);

    TableDataInfo getRecordBlogByUser(String userUid, PageDomain pageDomain);
}
