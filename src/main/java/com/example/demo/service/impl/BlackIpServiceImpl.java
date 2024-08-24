package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.BlackIp;
import com.example.demo.mapper.BlackIpMapper;
import com.example.demo.service.BlackIpService;
import com.example.demo.utils.StringUtils;
import com.example.demo.web.page.PageDomain;
import com.example.demo.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BlackIpServiceImpl extends ServiceImpl<BlackIpMapper, BlackIp> implements BlackIpService {
    @Autowired
    BlackIpMapper blackIpMapper;

    @Override
    public TableDataInfo selectBlackIpWithPage(BlackIp blackIp, PageDomain pageDomain) {
        Page<BlackIp> page = new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize());
        LambdaQueryWrapper<BlackIp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(blackIp.getBlackIp()), BlackIp::getBlackIp, blackIp.getBlackIp())
                .le(blackIp.getStopTime() != null, BlackIp::getStopTime, blackIp.getStopTime());
        page = blackIpMapper.selectPage(page, wrapper);
        List<BlackIp> list = page.getRecords();
        return TableDataInfo.suss(list, page.getTotal(), "操作成功");
    }

    @Override
    public List<BlackIp> selectBlackIpList() {
        LambdaQueryWrapper<BlackIp> wrapper = new LambdaQueryWrapper<>();
        return blackIpMapper.selectList(wrapper);
    }

    @Override
    public int insertBlackIp(BlackIp blackIp) {
        return blackIpMapper.insert(blackIp);
    }

    @Override
    public int deleteBlackIp(Long blackId) {
        return blackIpMapper.deleteByBlackId(blackId);
    }

    @Override
    public BlackIp selectBlackIpByBlackId(Long blackId) {
        return blackIpMapper.selectBlackIpByBlackId(blackId);
    }

    @Override
    public int updateStopTimeByBlackIp(BlackIp blackIp) {
        return blackIpMapper.updateStopTimeByBlackId(blackIp);
    }
}
