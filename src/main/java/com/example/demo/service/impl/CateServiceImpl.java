package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Cate;
import com.example.demo.mapper.CateMapper;
import com.example.demo.service.CateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品类别服务实现类
 */
@Slf4j
@Service
public class CateServiceImpl extends ServiceImpl<CateMapper, Cate> implements CateService {

    @Resource
    private CateMapper cateMapper;

    @Override
    public Cate getCateById(Integer id) {
        return cateMapper.selectById(id);
    }


}
