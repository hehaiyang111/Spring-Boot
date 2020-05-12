package com.example.thymeleaf.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/11 23:05
 **/
@Data
@AllArgsConstructor
public class Book {
    private Integer id;
    private String title;   //书名
    private String author;  //作者
    private String price;   //价格
}
