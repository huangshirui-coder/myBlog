package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.WebInfo;
import com.example.demo.mapper.WebInfoMapper;
import com.example.demo.service.WebInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebInfoServiceImpl extends ServiceImpl<WebInfoMapper, WebInfo> implements WebInfoService {

    @Autowired
    WebInfoMapper webInfoMapper;

    @Override
    public WebInfo getInfo() {
        LambdaQueryWrapper<WebInfo> wrapper = new LambdaQueryWrapper();
        return webInfoMapper.selectOne(wrapper);
    }

    @Override
    public int insert(WebInfo webInfo) {
        return webInfoMapper.insert(webInfo);
    }

    @Override
    public int update(WebInfo webInfo) {
        LambdaQueryWrapper<WebInfo> wrapper = new LambdaQueryWrapper();
        wrapper.eq(WebInfo::getId, webInfo.getId());
        return webInfoMapper.update(webInfo, wrapper);
    }
}
