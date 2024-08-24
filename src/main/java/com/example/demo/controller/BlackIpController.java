package com.example.demo.controller;

import com.example.demo.entity.BlackIp;
import com.example.demo.global.Result;
import com.example.demo.service.BlackIpService;
import com.example.demo.utils.SecurityUtils;
import com.example.demo.web.page.TableDataInfo;
import com.example.demo.web.page.TableSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Api(value = "黑名单相关接口")
@RequestMapping("black")
public class BlackIpController {
    @Autowired
    BlackIpService blackIpService;

    @ApiOperation(value = "分页查询黑名单")
    @GetMapping("getAllWithPage")
    public TableDataInfo getBlackIpList(BlackIp blackIp, HttpServletRequest request) {
        return blackIpService.selectBlackIpWithPage(blackIp, TableSupport.getPageDomain(request));
    }

    @PostMapping("insert")
    @ApiOperation(value = "插入一条新数据")
    public Result insertBlackIp(@RequestBody BlackIp blackIp) {
        blackIp.setCreateTime(new Date());
        blackIp.setCreateBy(SecurityUtils.getLoginUser());
        int flag = blackIpService.insertBlackIp(blackIp);
        if (flag > 0){
            return Result.succ("操作成功");
        }else {
            return Result.fail("操作失败");
        }
    }

    @ApiOperation(value = "删除一条数据")
    @GetMapping("delete")
    public Result deleteBlackIp(Long blackId) {
        int flag = blackIpService.deleteBlackIp(blackId);
        if (flag > 0){
            return Result.succ("操作成功");
        }else {
            return Result.fail("操作失败");
        }
    }

    @ApiOperation(value = "根据Ip查找一条数据")
    @GetMapping("selectByBlackId")
    public Result getBlackIpById(Long blackId) {
        return Result.succ(blackIpService.selectBlackIpByBlackId(blackId));
    }

    @ApiOperation(value = "更新封禁截止时间")
    @PostMapping("updateStopTime")
    public Result updateStopTimeByBlackId(@RequestBody BlackIp blackIp) {
        int flag = blackIpService.updateStopTimeByBlackIp(blackIp);
        if (flag > 0){
            return Result.succ("操作成功");
        }else {
            return Result.fail("操作失败");
        }
    }
}
