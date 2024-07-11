package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.Visit;
import com.example.demo.global.Result;
import com.example.demo.service.LoginService;
import com.example.demo.service.VisitService;
import com.example.demo.service.impl.LoginServiceImpl;
import com.example.demo.utils.IPUtils;
import com.example.demo.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登录控制类
 */
@RestController
@Api(value = "登录相关接口", tags = "登录相关接口")
@RequestMapping("/userLogin")
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    VisitService visitService;
    /*
     * @Author Huang
     * @Description 对登陆信息进行验证
     * @Param [user]
     * @return com.example.demo.global.Result
     **/
    @PostMapping("/login")
    @ApiOperation(value = "输入用户名和密码登录", notes = "输入用户名和密码登录")
    public Result login(@RequestBody User user, HttpServletRequest request){
        try {
            Result result = loginService.login(user);
            if (result.getCode() == 200){
                String ip = IPUtils.getClientIP(request);
                ip = "203.195.195.64";
                String province = IPUtils.getProvince(IPUtils.getInstance(), ip) == null ? IPUtils.getCountry(IPUtils.getInstance(), ip) : IPUtils.getProvince(IPUtils.getInstance(), ip);
                Visit visit = new Visit();
                visit.setIp(ip);
                visit.setUsername(user.getUserName());
                visit.setIpAddr(province);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = sdf.format(date);
                visit.setVisitTime(dateStr);
                visit.setUid(StringUtils.getUUID());
                visitService.insertVisit(visit);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("登录失败");
    }


    @GetMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public Result logout(){
        return loginService.logout();
    }
}
