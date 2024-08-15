package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@TableName("t_record")
public class Record implements Serializable {
    @TableId(type = IdType.AUTO)
    private String uid;

    private String userUid;

    private String blogUid;
}
