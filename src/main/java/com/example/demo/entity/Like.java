package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@TableName("t_like")
public class Like implements Serializable {
    private String uid;

    private String userUid;

    private String blogUid;
}