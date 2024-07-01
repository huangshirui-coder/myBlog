package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@TableName("t_record")
public class Record implements Serializable {
    private String uid;

    private String userUid;

    private String blogUid;
}
