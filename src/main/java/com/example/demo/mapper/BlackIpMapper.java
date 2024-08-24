package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.BlackIp;

public interface BlackIpMapper extends BaseMapper<BlackIp> {
    int deleteByBlackId(Long BlackIp);

    BlackIp selectBlackIpByBlackId(Long blackId);

    int updateStopTimeByBlackId(BlackIp blackIp);
}
