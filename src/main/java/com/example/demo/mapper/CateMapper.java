package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Cate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品类别Mapper
 */
@Mapper
public interface CateMapper extends BaseMapper<Cate> {
}
