package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.WebInfo;

public interface WebInfoService extends IService<WebInfo> {
    public WebInfo getInfo();
}
