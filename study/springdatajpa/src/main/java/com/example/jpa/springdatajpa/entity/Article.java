package com.example.jpa.springdatajpa.entity;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 10:58
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/12 10:58
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_article")
public class Article {
    /**
     * 使用@id指定主键。使用代码@GeneratedValue(strategy = GenerationType.IDENTITY)
     * 指定主键的生存策略，mysql默认为自动增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键
    private String title;   //名称
    private String supplier;    //作者
    private Double price;   //单价
    private String locality;    //出版社
    private int storage;        //库存
    private String image;   //商品图片
    private String description; //商品描述
}
