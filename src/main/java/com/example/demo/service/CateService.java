package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Cate;

/**
 * 商品类别服务类
 */
public interface CateService extends IService<Cate> {
    /*
     * @Author Huang
     * @Description 通过Id查找Cate
     * @Param [id]
     * @return com.example.demo.entity.Cate
     **/
    public Cate getCateById(Integer id);


}
