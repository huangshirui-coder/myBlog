package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CommentDto implements Serializable {
    private String state;
    private List<String> uids;
}
