package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.BlackIp;
import com.example.demo.web.page.PageDomain;
import com.example.demo.web.page.TableDataInfo;

import java.util.List;

public interface BlackIpService extends IService<BlackIp > {
    TableDataInfo selectBlackIpWithPage(BlackIp blackIp, PageDomain pageDomain);

    List<BlackIp> selectBlackIpList();

    int insertBlackIp(BlackIp blackIp);

    int deleteBlackIp(Long blackId);

    BlackIp selectBlackIpByBlackId(Long blackId);

    int updateStopTimeByBlackIp(BlackIp blackIp);
}
