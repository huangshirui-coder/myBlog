package com.example.demo.service.impl;
import com.example.demo.global.Result;
import com.example.demo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.example.demo.service.MailService;
import com.example.demo.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;

@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    @Override
    public void checkMail(String receiveEmail, String subject, String emailMsg) {
        if (StringUtils.isEmpty(receiveEmail)){
            throw new RuntimeException("目标邮箱地址不能为空");
        }
        if (StringUtils.isEmpty(subject)){
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(emailMsg)){
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    @Override
    public Result sendTextMail(String receiveEmail, String subject, String emailMsg) {
        checkMail(receiveEmail, subject, emailMsg);
        try {
            // true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            // 邮件发件人
            mimeMessageHelper.setFrom(sendMailer);
            // 邮件收件人
            mimeMessageHelper.setTo(receiveEmail.split(","));
            // 邮件主题
            mimeMessageHelper.setSubject(subject);
            // 邮件内容
            mimeMessageHelper.setText(emailMsg);
            // 邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            // 发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            return Result.succ("发送邮件成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            return Result.fail("发送邮件失败");
        }
    }
}
