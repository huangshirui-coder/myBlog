package com.example.demo.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Goods;
import com.example.demo.vo.GoodsVo;

import java.util.List;

/**
 * Goods服务类
 */
public interface GoodsService extends IService<Goods>{
    /**
     * 通过id查找Goods
     * @param id
     * @return
     */
    public Goods getGoodsById(Integer id);

    /**
     * 通过name查找Goods
     * @param name
     * @return
     */
    public Goods getGoodsByName(String name);

    /**
     * 通过price查找GoodsList
     * @param price
     * @return
     */
    public List<Goods> getGoodsByPrice(int price);

    /*
     * @Author Huang
     * @Description 接收goods，根据Goods内id查找并跟新Goods
     * @Param [goods]
     * @return int
     **/
    public int updateGoods(Goods goods);

    /*
     * @Author Huang
     * @Description 根据id删除Goods
     * @Param [id]
     * @return int
     **/
    public int deleteGoods(Integer id);

    /*
     * @Author Huang
     * @Description 查询全表
     * @Param []
     * @return java.util.List<com.example.demo.entity.Goods>
     **/
    public List<Goods> getGoodsList();

}
