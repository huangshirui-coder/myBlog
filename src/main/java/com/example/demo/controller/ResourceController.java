package com.example.demo.controller;

import com.example.demo.global.Result;
import com.example.demo.utils.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.util.Random;

@Api(value = "资源上传")
@RestController
@RequestMapping("resource")
public class ResourceController {

    @PostMapping("/upload")
    public Result handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            if (file.isEmpty()){
                return Result.fail("文件为空");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")) + StringUtils.getUUID().substring(0,4)
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String abselutePath = "/huang/demoFile/fileResource/";
            String filePath = "/fileResource/";
            // 保存文件到指定位置
            file.transferTo(new File(abselutePath + fileName));
            return Result.succ(filePath + fileName);
        } catch (IOException e) {
            return Result.fail("Failed to upload file: " + e.getMessage());
        }
    }
}
