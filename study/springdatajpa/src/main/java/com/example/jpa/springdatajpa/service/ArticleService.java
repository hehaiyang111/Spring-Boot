package com.example.jpa.springdatajpa.service;/**
 * @Description TO
 * @Author hehaiyang
 * @Date 2020/5/12 11:02
 **/

import com.example.jpa.springdatajpa.entity.Article;
import com.example.jpa.springdatajpa.repository.ArticleRepository;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *@Description TO
 *@Author hehaiyang
 *@Date 2020/5/12 11:02
 **/
@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    /*
    排序查
     */
    public Iterable<Article> findAll(Sort sort){
        return articleRepository.findAll(sort);
    }
    /*
    分页查
     */
    public Page<Article> findAllByPage(Pageable pageable){
        return articleRepository.findAll(pageable);
    }
}
