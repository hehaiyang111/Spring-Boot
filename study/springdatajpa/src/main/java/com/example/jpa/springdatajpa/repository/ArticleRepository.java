package com.example.jpa.springdatajpa.repository;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 11:00
 **/

import com.example.jpa.springdatajpa.entity.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/12 11:00
 **/
public interface ArticleRepository extends PagingAndSortingRepository<Article,Integer> {
}
