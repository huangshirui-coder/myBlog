package com.example.demo.vo;

import com.example.demo.entity.Visit;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class VisitVo implements Serializable {
    private List<Visit> visitList;
    private List<String> provinceTen;
    private int visitCount;
}
