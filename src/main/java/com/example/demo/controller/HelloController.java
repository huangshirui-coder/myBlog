package com.example.demo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试", tags = "测试")
public class HelloController {
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "测试", notes = "测试")
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
