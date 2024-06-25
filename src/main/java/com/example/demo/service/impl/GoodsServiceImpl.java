package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Cate;
import com.example.demo.entity.Goods;
import com.example.demo.global.RedisConf;
import com.example.demo.global.SQLConf;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.service.GoodsService;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.RedisUtils;
import com.example.demo.utils.StringUtils;
import com.example.demo.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Goods服务实现类
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    GoodsMapper goodsMapper;

    @Autowired
    RedisUtils redisUtils;

    /*
     * @Author Huang
     * @Description 返回全表GoodsList
     * @Param []
     * @return java.util.List<com.example.demo.entity.Goods>
     **/
    @Override
    public List<Goods> getGoodsList(){
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        List<Goods> goodsList = goodsMapper.selectList(wrapper);
        String jsonResult = redisUtils.get(RedisConf.GOODSLIST);
        if (! StringUtils.isEmpty(jsonResult)){
            ArrayList arrayList = JsonUtils.jsonArrayToArrayList(jsonResult);
            log.info("从缓存中获取goodsList");
            return arrayList;
        }
        redisUtils.set(RedisConf.GOODSLIST, JsonUtils.objectToJson(goodsList).toString());
        log.info("从数据库中获取goodsList");
        return goodsList;
    }

    /*
     * @Author Huang
     * @Description 根据id查询商品，返回单条内容
     * @Param [id]
     * @return com.example.demo.entity.Goods
     **/
    @Override
    public Goods getGoodsById(Integer id) {
        return goodsMapper.selectById(id);
    }

    /*
     * @Author Huang
     * @Description 根据商品名查询，返回单条内容（假设商品名不重复）
     * @Param [name]
     * @return com.example.demo.entity.Goods
     **/
    @Override
    public Goods getGoodsByName(String name) {

        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.NAME, name);
        Goods goods = goodsMapper.selectOne(wrapper);
        return goods;
    }

    /*
     * @Author Huang
     * @Description 根据价格查询，返回一页3条内容
     * @Param [price]
     * @return java.util.List<com.example.demo.entity.Goods>
     **/
    @Override
    public List<Goods> getGoodsByPrice(int price) {
        // 目前只写了查询根据价格查询显示第一页（3条）
        int currentPage = 1;
        Page<Goods> page = new Page<>(currentPage, 3);

        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.PRICE, price);
        page = goodsMapper.selectPage(page, wrapper);
        List<Goods> goodsPageList = page.getRecords();
        return goodsPageList;
    }

    /*
     * @Author Huang
     * @Description 从goods中取出所有变量用以更新
     * @Param [goods]
     * @return int 非0为操作成功
     **/
    @Override
    public int updateGoods(Goods goods) {
        Goods updateGoods = new Goods();
        updateGoods.setName(goods.getName());
        updateGoods.setCategory(goods.getCategory());
        updateGoods.setQuantity(goods.getQuantity());
        updateGoods.setPrice(goods.getPrice());

        UpdateWrapper<Goods> wrapper = new UpdateWrapper<>();
        wrapper.eq(SQLConf.ID, goods.getId());
        int result = goodsMapper.update(updateGoods, wrapper);
        if (result > 0){
            redisUtils.delete(RedisConf.GOODSLIST);
        }
        return result;
    }

    /*
     * @Author Huang
     * @Description 根据id删除Goods
     * @Param [id]
     * @return int  非0操作成功
     **/
    @Override
    public int deleteGoods(Integer id) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq(SQLConf.ID, id);
        int result = goodsMapper.delete(wrapper);
        if (result > 0){
            redisUtils.delete(RedisConf.GOODSLIST);
        }
        return result;
    }

}
