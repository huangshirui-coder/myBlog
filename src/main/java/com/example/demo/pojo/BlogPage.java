package com.example.demo.pojo;

import com.example.demo.entity.Blog;
import lombok.Data;

import java.util.List;

/**
 * packageName com.example.demo.pojo
 *
 * @author Huang
 * @version JDK 8
 * @className BlogPage
 * @date 2024/5/29
 * @description TODO
 */
@Data
public class BlogPage {
    private List<Blog> blogList;
    private Integer total;
}
