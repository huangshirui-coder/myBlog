package com.example.demo.pojo;

import lombok.Data;

/**
 * packageName com.example.demo.pojo
 *
 * @author Huang
 * @version JDK 8
 * @className Pagination
 * @date 2024/5/29
 * @description TODO
 */
@Data
public class Pagination {
    private Integer current;
    private Integer size;
    private Integer total;
    private String searchKey;
    private String sortId;
    private String articleSearch;
}
