package com.example.demo.controller;

import com.example.demo.entity.ToEmail;
import com.example.demo.global.Result;
import com.example.demo.service.MailService;
import com.example.demo.utils.RedisCache;
import com.example.demo.utils.VerCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/email")
@Api(value = "邮件相关接口", tags = "邮件相关接口")
public class EmailController {
    @Autowired
    MailService mailService;
    @Autowired
    private RedisCache redisCache;

    @ApiOperation(value = "邮件发送接口", notes = "邮件发送接口")
    @PostMapping("sendVerMail")
    public Result sendVerMail(@RequestBody ToEmail toEmail){
        if(toEmail == null || toEmail.getTos() == null ) {
            return Result.fail("参数错误!");
        }
        toEmail.setSubject("你本次的验证码是");
        // 获取验证码
        String verCode = VerCodeUtil.getVerCode();
        String content = "尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + verCode + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）";
        toEmail.setContent(content);
        redisCache.setCacheObject(toEmail.getTos(), verCode, 1, TimeUnit.MINUTES);
        redisCache.getCacheObject(toEmail.getTos());
        return mailService.sendTextMail(toEmail.getTos(), toEmail.getSubject(), toEmail.getContent());
    }
}
